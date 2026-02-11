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


    public Cargos validaCargo(String cargoUsuario){
        PanacheQuery<Cargos> selectNomeCargoFromCargo = cargoRepository.find("select nome_cargo from cargo");
        String cargoFromatado = cargoUsuario.toUpperCase();
        if(selectNomeCargoFromCargo.equals(cargoFromatado)){
            return cargoRepository.findById(Long.valueOf("selec id from cargo"));
        }
        return null;
    }


    public Usuarios createUser(UsuariosDTO usuariosDTO){
        Usuarios usuario = new Usuarios();
        usuario.setLogin(usuariosDTO.getLogin());
        usuario.setNome(usuariosDTO.getNome());
        usuario.setEmail(usuariosDTO.getEmail());
        usuario.setCargo(validaCargo(usuariosDTO.getCargo().getNomeCargo()));
        usuarioRepository.persist(usuario);
        return usuario;
    }



}
