package com.sonner.nota_fiscal.service;

import com.sonner.nota_fiscal.model.Cliente;
import com.sonner.nota_fiscal.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente novoCliente(Cliente cliente){ // recebe o objeto cliente oriundo do json
        return clienteRepository.save(cliente); // salva os dados do cliente no banco de dados
    }

    public List<Cliente> lerCliente(){ // recebe uma lista de objetos do tipo cliente
        return clienteRepository.findAll(); // retorna todos os registros de clientes no banco de dados para a lista
    }

    public Cliente lerId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
    }

    public void deletarCliente(Long id){ // recebe o id do cliente a ser deletado no banco de dados
        clienteRepository.deleteById(id); // deleta o cliente do banco de dados de acordo com o id
    }

    public void atualizarCliente(Long id, Cliente clienteAtualiza){ // // recebe o id e os dados do cliente a serem atualizados no banco de dados
        Cliente cliente = clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado")); // busca o cliente pelo id se nao encontrar lança uma excecao
        cliente.setNome(clienteAtualiza.getNome()); // atualiza o nome do cliente de acordo com o passado na requisicao http via json
        cliente.setCodigo(clienteAtualiza.getCodigo()); // atualiza o codigo do cliente de acordo com o passado na requisicao http via json

    }


}
