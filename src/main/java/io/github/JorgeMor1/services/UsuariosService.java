package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.domain.Usuarios;
import io.github.JorgeMor1.dto.CargoDTO;
import io.github.JorgeMor1.dto.UsuariosDTO;
import io.github.JorgeMor1.repository.CargoRepository;
import io.github.JorgeMor1.repository.UsuarioRepository;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class UsuariosService {

    @Inject
    UsuarioRepository usuarioRepository = new UsuarioRepository();

    @Inject
    CargoRepository cargoRepository = new CargoRepository();


    public Cargos validaCargo(UsuariosDTO usuariosDTO){
        String cargoFormatado = usuariosDTO.getNomeCargo().toUpperCase();
        return cargoRepository
                .find("upper(nomeCargo) = ?1", cargoFormatado)
                .firstResultOptional()
                .orElseThrow(() ->
                        new RuntimeException("Cargo não encontrado: " + cargoFormatado)
                );
    }


    public Usuarios createUser(UsuariosDTO usuariosDTO){
        Usuarios usuario = new Usuarios();
        usuario.setLogin(usuariosDTO.getLogin());
        usuario.setNome(usuariosDTO.getNome());
        usuario.setSobrenome(usuariosDTO.getSobrenome());
        usuario.setEmail(usuariosDTO.getEmail());

        Cargos cargo = validaCargo(usuariosDTO);
        usuario.setCargo(cargo);
        usuarioRepository.persist(usuario);
        return usuario;
    }


    public void updateUser(UsuariosDTO usuariosDTO, Long userId) {
        Usuarios usuario = usuarioRepository.findById(userId);
        usuario.setLogin(usuariosDTO.getLogin());
        usuario.setNome(usuariosDTO.getNome());
        usuario.setSobrenome(usuariosDTO.getSobrenome());
        usuario.setEmail(usuariosDTO.getEmail());
    }

    public void deleteUser(Long idUser){
        Usuarios user = usuarioRepository.findById(idUser);
        usuarioRepository.delete(user);
    }
}
