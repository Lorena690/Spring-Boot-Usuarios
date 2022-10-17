package com.cursojava.curso.controllers.models;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuarios/{id}" , method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
            Usuario usuario = new Usuario();
            usuario.setId((id));
            usuario.setNombre("Lorena");
            usuario.setApellido("Sanchez");
            usuario.setEmail("lorraines690.ls@gmail.com");
            usuario.setTelefono("3226271671");
            return usuario;
    }

    @RequestMapping(value = "api/usuarios" , method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value = "Authorization") String token) {

        String usuarioId = jwtUtil.getKey(token);
        if (!validarToken(token)) {
          return null;
        }

        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token){
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }


    @RequestMapping(value = "api/usuarios" , method = RequestMethod.POST)
    public String registrarUsuario(@RequestBody Usuario usuario) {
        if(!usuario.getEmail().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")){
            return "Correo inválido";
        }
        usuarioDao.registrar(usuario);
        return "Usuario registrado correctamente";
    }

    @RequestMapping(value = "usuario12")
    public Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Lorena");
        usuario.setApellido("Sanchez");
        usuario.setEmail("lorraines690.ls@gmail.com");
        usuario.setTelefono("3226271671");
        return usuario;
    }

    @RequestMapping(value = "api/usuarios/{id}" , method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value = "Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)) {
            return;
        }
        usuarioDao.eliminar(id);
    }
}
