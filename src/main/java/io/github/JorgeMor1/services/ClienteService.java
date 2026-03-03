package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClientResponseDTO;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.exception.CustomerDataException;
import io.github.JorgeMor1.exception.CustomerNotFoundException;
import io.github.JorgeMor1.repository.ClienteRepository;
import io.github.JorgeMor1.repository.EventosRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class ClienteService {
    @Inject
    ClienteRepository clienteRepository = new ClienteRepository();

    @Inject
    EventosRepository eventosRepository = new EventosRepository();

    //Passar essas validações do cliente para um método e deixar o service mais enxuto

    private void validarCpfExistente(String cpf) {
        if (clienteRepository.buscaCpf(cpf)) {
            throw new CustomerDataException("O seguinte CPF já está cadastrado: ", cpf);
        }
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findByIdOptional(id)
                .orElseThrow(() ->
                        new NotFoundException("Cliente não encontrado"));
    }

    private void validaremailExistente(String email) {
        if (clienteRepository.buscaEmail(email)) {
            throw new CustomerDataException("O seguinte E-MAIL já está cadastrado: ", email);
        }
    }

    //Criando cliente
    public Cliente createClient (ClienteDTO clienteDTO){
        String cpfFormatted = clienteDTO.getCpf().replaceAll("\\D", "");
        validarCpfExistente(cpfFormatted);
        validaremailExistente(clienteDTO.getEmail());

        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setCpf(cpfFormatted);
        cliente.setTelefoneContato(clienteDTO.getTelefoneContato());
        cliente.setEmail(clienteDTO.getEmail());

        clienteRepository.persist(cliente);
        return cliente;
    }

    public Cliente buscarClienteOuFalhar (Long idCliente){
        return clienteRepository.buscarClientePorId(idCliente)
                .orElseThrow(() -> new CustomerNotFoundException(idCliente));
    }


    public List<Cliente> listAllClients(int page, int size){
        return clienteRepository.findAll().page(Page.of(page, size)).list();
    }

    public void atualizaCliente(Long id, ClienteDTO clienteDTO){
        Cliente cliente = buscarClientePorId(id);
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
        Cliente cliente = buscarClientePorId(id);
        validarClienteSemEventos(id);
        clienteRepository.delete(cliente);
    }
}
