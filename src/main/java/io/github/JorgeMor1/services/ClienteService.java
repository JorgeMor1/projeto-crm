package io.github.JorgeMor1.services;

import io.github.JorgeMor1.domain.Cliente;
import io.github.JorgeMor1.dto.ClienteDTO;
import io.github.JorgeMor1.exception.CustomerDataException;
import io.github.JorgeMor1.exception.CustomerNotFoundException;
import io.github.JorgeMor1.repository.ClienteRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class ClienteService {
    @Inject
    ClienteRepository clienteRepository = new ClienteRepository();

    //Passar essas validações do cliente para um método e deixar o service mais enxuto

    private void validarCpfExistente(String cpf) {
        if (clienteRepository.buscaCpf(cpf)) {
            throw new CustomerDataException("O seguinte CPF já está cadastrado: ", cpf);
        }
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
}
