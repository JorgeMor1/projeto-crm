package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cargos;
import io.github.JorgeMor1.domain.Usuarios;
import io.github.JorgeMor1.dto.UsuariosDTO;
import io.github.JorgeMor1.exception.BadRequestException;
import io.github.JorgeMor1.exception.ResourceNotFoundException;
import io.github.JorgeMor1.repository.CargoRepository;
import io.github.JorgeMor1.repository.UsuarioRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class UsuariosService {

    @Inject
    UsuarioRepository usuarioRepository;

    @Inject
    CargoRepository cargoRepository;

    @Inject
    PessoaValidationService pessoaValidationService;


    public Cargos validaCargo(UsuariosDTO usuariosDTO){
        String cargoFormatado = usuariosDTO.getNomeCargo().toUpperCase();
        return cargoRepository
                .find("upper(nomeCargo) = ?1", cargoFormatado)
                .firstResultOptional()
                .orElseThrow(() ->
                        new ResourceNotFoundException("Cargo", cargoFormatado)
                );
    }


    public Usuarios createUser(UsuariosDTO usuariosDTO){

        //Valida formato válido do e-mail
        String emailValidado = pessoaValidationService.validaEmailValido(usuariosDTO.getEmail());

        //Valida e-mail e login já existentes no Banco
        pessoaValidationService.validarEmailExistente(usuariosDTO.getEmail());
        pessoaValidationService.validaLoginExistente(usuariosDTO.getLogin());

        Usuarios usuario = new Usuarios();
        usuario.setLogin(usuariosDTO.getLogin());
        usuario.setNome(usuariosDTO.getNome());
        usuario.setSobrenome(usuariosDTO.getSobrenome());
        usuario.setEmail(emailValidado);

        Cargos cargo = validaCargo(usuariosDTO);
        usuario.setCargo(cargo);
        usuarioRepository.persist(usuario);
        return usuario;
    }


    public void updateUser(UsuariosDTO usuariosDTO, Long userId) {
        //Valida formato válido do e-mail
        String emailValidado = pessoaValidationService.validaEmailValido(usuariosDTO.getEmail());

        //Valida e-mail e login já existentes no Banco
        pessoaValidationService.validarEmailExistente(usuariosDTO.getEmail());
        pessoaValidationService.validaLoginExistente(usuariosDTO.getLogin());

        Usuarios usuario = usuarioRepository.findByIdOptional(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário",userId));
        usuario.setLogin(usuariosDTO.getLogin());
        usuario.setNome(usuariosDTO.getNome());
        usuario.setSobrenome(usuariosDTO.getSobrenome());
        usuario.setEmail(emailValidado);
    }

    public void deleteUser(Long idUser){
        Usuarios usuarios = usuarioRepository.findByIdOptional(idUser)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário", idUser));
        usuarioRepository.delete(usuarios);
    }

    public List<Usuarios> listAllUsers(int page, int size){
         return usuarioRepository.findAll().page(Page.of(page, size)).list();
    }
}
