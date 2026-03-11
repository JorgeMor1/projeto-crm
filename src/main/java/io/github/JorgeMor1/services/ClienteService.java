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
            throw new BadRequestException("CPF ", cpf); // Mudar par o conflict ⚠️⚠️
        }
    }

    private void validaremailExistente(String email) {
        if (clienteRepository.buscaEmail(email)) {
            throw new BadRequestException("E-mail: ", email); // Mudar par o conflict ⚠️⚠️
        }
    }

    private String validaCpfValido(String cpf){
        String cpfFormatted = cpf.replaceAll("\\D","");
        if (cpfFormatted.length() == 11 && !cpfFormatted.matches(".*[a-zA-Z].*")) {
            return cpfFormatted;
        }else {
            throw new BadRequestException("Cpf: ", cpf);
        }
    }

    private boolean validaEmailValido(String email){
        String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        } else {
            throw  new BadRequestException("E-mail", email);
        }

    }

    //Criando cliente
    public Cliente createClient (ClienteDTO clienteDTO){
        String cpfFormatted = validaCpfValido(clienteDTO.getCpf());
        boolean emailValidado = validaEmailValido(clienteDTO.getEmail());

        validarCpfExistente(cpfFormatted);
        validaremailExistente(clienteDTO.getEmail());
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpfFormatted);
        cliente.setTelefoneContato(clienteDTO.getTelefoneContato());
        if(emailValidado){
            cliente.setEmail(clienteDTO.getEmail());

        }

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
                    "Cliente possui eventos associados",
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
