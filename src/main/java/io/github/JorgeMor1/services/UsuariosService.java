package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Usuarios;
import io.github.JorgeMor1.dto.UsuariosDTO;
import io.github.JorgeMor1.repository.CargoRepository;
import io.github.JorgeMor1.repository.UsuarioRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped

public class UsuariosService {

    @Inject
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    @Inject
    CargoRepository cargoRepository = new CargoRepository();



    public Usuarios createUser(UsuariosDTO usuariosDTO){
        Usuarios usuario = new Usuarios();
        usuario.setLogin(usuariosDTO.getLogin());
        usuario.setNome(usuariosDTO.getNome());
        usuario.setEmail(usuariosDTO.getEmail());
        usuario.setCargo(usuariosDTO.getCargo());
        usuarioRepository.persist(usuario);
        return usuario;
    }



}
