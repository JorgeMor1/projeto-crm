package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.exception.BadRequestException;
import io.github.JorgeMor1.exception.ConflictException;
import io.github.JorgeMor1.exception.ResourceNotFoundException;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.EventosRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class ClienteService {
    @Inject
    ClienteRepository clienteRepository = new ClienteRepository();

    @Inject
    EventosRepository eventosRepository = new EventosRepository();

    @Inject
    PessoaValidationService pessoaValidationService;


    public Cliente createClient (ClienteDTO clienteDTO){
        //Valida formatação correta dos dados (CPF, E-mail e Telefone)
        String cpfFormatted = pessoaValidationService.validaCpfValido(clienteDTO.getCpf());
        String emailValidado = pessoaValidationService.validaEmailValido(clienteDTO.getEmail());
        String telefoneValidado = pessoaValidationService.validaTelefoneValido(clienteDTO.getTelefoneContato());

        //Valida  CPF, telefone e e-mail já existentes no Banco
        pessoaValidationService.validarCpfExistente(cpfFormatted);
        pessoaValidationService.validarEmailExistente(clienteDTO.getEmail());
        pessoaValidationService.validarTelefoneExistente(clienteDTO.getTelefoneContato());

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpfFormatted);
        cliente.setTelefoneContato(telefoneValidado);
        cliente.setEmail(emailValidado);
        clienteRepository.persist(cliente);
        return cliente;
    }

    public Cliente buscarClienteOuFalhar (Long idCliente){
        return clienteRepository.buscarClientePorId(idCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente ", idCliente));
    }


    public List<Cliente> listAllClients(int page, int size){
        return clienteRepository.findAll().page(Page.of(page, size)).list();
    }

    public void atualizaCliente(Long id, ClienteDTO clienteDTO){

        //Valida formatação correta dos dados (CPF, E-mail e Telefone)
        String cpfFormatted = pessoaValidationService.validaCpfValido(clienteDTO.getCpf());
        String emailValidado = pessoaValidationService.validaEmailValido(clienteDTO.getEmail());
        String telefoneValidado = pessoaValidationService.validaTelefoneValido(clienteDTO.getTelefoneContato());

        //Valida  CPF, telefone e e-mail já existentes no Banco
        pessoaValidationService.validarCpfExistente(clienteDTO.getCpf());
        pessoaValidationService.validarEmailExistente(clienteDTO.getEmail());
        pessoaValidationService.validarTelefoneExistente(clienteDTO.getTelefoneContato());

        Cliente cliente = buscarClienteOuFalhar(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpfFormatted);
        cliente.setTelefoneContato(telefoneValidado);
        cliente.setEmail(emailValidado);
    }

    public void  validarClienteSemEventos(Long clienteId) {
        if (eventosRepository.existsByClienteId(clienteId)) {
            throw new ConflictException(
                    "possui eventos associados", clienteId

            );
        }
    }

    public void deletaCliente(Long id){
        Cliente cliente = buscarClienteOuFalhar(id);
        validarClienteSemEventos(id);
        clienteRepository.delete(cliente);
    }
}
