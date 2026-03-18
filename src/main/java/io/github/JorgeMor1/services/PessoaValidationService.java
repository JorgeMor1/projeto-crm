package io.github.JorgeMor1.services;

import io.github.JorgeMor1.exception.BadRequestException;
import io.github.JorgeMor1.exception.ConflictException;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.UsuarioRepository;
import jakarta.inject.Inject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PessoaValidationService {
    @Inject
    ClienteRepository clienteRepository;

    @Inject
    UsuarioRepository usuarioRepository;

    protected void validarCpfExistente(String cpf) {
        if (clienteRepository.buscaCpf(cpf) || usuarioRepository.buscaCpf(cpf)) {
            throw new ConflictException("CPF", cpf);
        }
    }

    protected void validaremailExistente(String email) {
        if (clienteRepository.buscaEmail(email) || usuarioRepository.buscaEmail(email)) {
            throw new ConflictException("E-mail", email);
        }
    }

    protected String validaCpfValido(String cpf){
        String cpfFormatted = cpf.replaceAll("\\D","");
        String CPF_REGEX = "^\\d{11}$";
        Pattern pattern = Pattern.compile(CPF_REGEX);
        Matcher matcher = pattern.matcher(cpfFormatted);

        if (cpf != null && matcher.matches() && !cpfFormatted.matches("(\\d)\\1{10}")) {
            return cpfFormatted;
        }else {
            throw new BadRequestException("Cpf: ", cpf);
        }
    }

    protected String validaEmailValido(String email){
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches() && email != null){
            return email.toLowerCase().trim();
        } else {
            throw  new BadRequestException("E-mail", email);
        }

    }

    protected String  validaTelefone(String telefoneContato){
        String TELEFONE_REGEX = "^\\(?(\\d{2})\\)?\\s?9?\\d{4}-?\\d{4}$";
        Pattern pattern = Pattern.compile(TELEFONE_REGEX);
        Matcher matcher = pattern.matcher(telefoneContato);
        if (matcher.matches() && telefoneContato != null){
            return telefoneContato.toLowerCase().trim();
        }else {
            throw  new BadRequestException("Telefone de contato", telefoneContato);
        }
    }
}
