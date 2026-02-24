package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Cliente;
import com.sonner.nota_fiscal.repository.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // anotação que esta é uma classe de controle do Rest
@RequestMapping("/clientes") // anotacao que indica a url de mapeamento que o controller vai responder
public class ClienteController {

    @Autowired // anotacao que indica a injecao automatica da classe
    private ClienteRepository clienteRepository; // instancia da classe que sera responsavel pelas operações de banco de dados

    @PostMapping // anotacao para indicar a qual protocolo http este metodo ira responder
    @Transactional // anotacao para indicar o commit caso sucesso ou rollback caso nao
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // RequestBody e a anotacao responsavel por converter o JSON em Java
    // Valid e a anotacao para fazer a validacao de um objeto
    // Cliente e a classe que ira receber os dados em Json e converter para Java
    public ResponseEntity cadastrar(@RequestBody @Valid Cliente cliente){
        new Cliente(cliente); // cria um novo cliente passando as informacoes de cliente no construtor
        clienteRepository.save(cliente); // salva as informacoes de cliente no banco de dados
        return ResponseEntity.ok("Cliente cadastrado com sucesso"); // retorna a resposta a requisicao http
    }

    @GetMapping // anotacao para indicar a qual protocolo http este metodo ira responder
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <List<Cliente>> representacao de uma lista de objetos do tipo Cliente
    public ResponseEntity<List<Cliente>> ler(){
        List<Cliente> clientes = clienteRepository.findAll(); // busca todos os registros da tabela e retorna para a lista de objetos Cliente
        return ResponseEntity.ok(clientes); // retorna a resposta http com os clientes no formato Json
    }

    @DeleteMapping("/{id}") // significa que a exclusao ira ocorrer de acordo com o id do registro encaminhado na requisicao http
    @Transactional // anotacao para indicar o commit caso sucesso ou rollback caso nao
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <String> significa que ira retornar uma string no corpo na resposta
    // @PathVariable Long id - indica que o id recebido sera o id a ser deletado atraves da anotacao @PathVariable
    public ResponseEntity<String> deletar(@PathVariable Long id){
        clienteRepository.deleteById(id); // deleta o registro no banco de dados de acordo com o id recebido na requisicao http
        return ResponseEntity.ok("Cliente deletado com sucesso"); // retorna a resposta a requisicao http
    }

    @PutMapping // significa que a atualizacao ira ocorrer de acordo com o id do registro encaminhado na requisicao http
    @Transactional // anotacao para indicar o commit caso sucesso ou rollback caso nao
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <String> significa que ira retornar uma string no corpo na resposta
    // RequestBody e a anotacao responsavel por converter o JSON em Java
    // Valid e a anotacao para fazer a validacao de um objeto
    // DadosAtualizaCliente e a classe que ira receber os dados em Json e converter para Java
    public ResponseEntity<String> atualizar(@RequestBody @Valid Cliente clienteAtualiza){
         var cliente = clienteRepository.getReferenceById(clienteAtualiza.getId()); // busca a referencia passada no parametro dadosAtualizaCliente.id e atribui a classe Cliente
         cliente.atualizarCliente(clienteAtualiza); // recebe as informacoes a serem atualizadas atraves do metodo atualizarCliente da classe Cliente
         return ResponseEntity.ok("Cliente atualizado com sucesso"); // retorna a resposta a requisicao http
    }
}