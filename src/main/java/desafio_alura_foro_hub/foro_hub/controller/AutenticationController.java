package desafio_alura_foro_hub.foro_hub.controller;

import desafio_alura_foro_hub.foro_hub.dto.DatosDeAutenticacion;
import desafio_alura_foro_hub.foro_hub.dto.DatosTokenJWT;
import desafio_alura_foro_hub.foro_hub.service.TokenServices;
import desafio_alura_foro_hub.foro_hub.usuario.Usuario;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AutenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenServices tokenService;

    @PostMapping
    public ResponseEntity<DatosTokenJWT> realizarLogin(@RequestBody @Valid DatosDeAutenticacion datosDeAutenticacion) {
        Authentication token = new UsernamePasswordAuthenticationToken(
                datosDeAutenticacion.login(), datosDeAutenticacion.clave());
        Authentication usuarioAutenticado = authenticationManager.authenticate(token);
        String JWTtoken = tokenService.generarToken((Usuario) usuarioAutenticado.getPrincipal());
        return ResponseEntity.ok(new DatosTokenJWT(JWTtoken));
    }
}
