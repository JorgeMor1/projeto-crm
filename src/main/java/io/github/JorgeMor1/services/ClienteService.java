package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.exception.BadRequestException;
import io.github.JorgeMor1.exception.CustomerDataException;
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

    private void validarCpfExistente(String cpf) {
        if (clienteRepository.buscaCpf(cpf)) {
            throw new BadRequestException("CPF já existe ", cpf); // Mudar par o conflict ⚠️⚠️
        }
    }

    private void validaremailExistente(String email) {
        if (clienteRepository.buscaEmail(email)) {
            throw new BadRequestException("E-mail já existe ", email); // Mudar par o conflict ⚠️⚠️
        }
    }

    private String validaCpfValido(String cpf){
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

    private String validaEmailValido(String email){
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches() && email != null){
            return email.toLowerCase().trim();
        } else {
            throw  new BadRequestException("E-mail", email);
        }

    }

    private String  validaTelefone(String telefoneContato){
        String TELEFONE_REGEX = "^\\(?(\\d{2})\\)?\\s?9?\\d{4}-?\\d{4}$";
        Pattern pattern = Pattern.compile(TELEFONE_REGEX);
        Matcher matcher = pattern.matcher(telefoneContato);
        if (matcher.matches() && telefoneContato != null){
            return telefoneContato.toLowerCase().trim();
        }else {
            throw  new BadRequestException("Telefone de contato", telefoneContato);
        }
    }

    //Criando cliente
    public Cliente createClient (ClienteDTO clienteDTO){
        String cpfFormatted = validaCpfValido(clienteDTO.getCpf());
        String emailValidado = validaEmailValido(clienteDTO.getEmail());
        String telefoneValidado = validaTelefone(clienteDTO.getTelefoneContato());

        validarCpfExistente(cpfFormatted);
        validaremailExistente(clienteDTO.getEmail());
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
        Cliente cliente = buscarClienteOuFalhar(id);
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(clienteDTO.getCpf());
        cliente.setTelefoneContato(clienteDTO.getTelefoneContato());
        cliente.setEmail(clienteDTO.getEmail());
    }

    public void  validarClienteSemEventos(Long clienteId) {
        if (eventosRepository.existsByClienteId(clienteId)) {
            throw new WebApplicationException(
                    "Cliente possui eventos associados",   // Mudar par o conflict ⚠️⚠️
                    Response.Status.CONFLICT
            );
        }
    }

    public void deletaCliente(Long id){
        Cliente cliente = buscarClienteOuFalhar(id);
        validarClienteSemEventos(id);
        clienteRepository.delete(cliente);
    }
}
