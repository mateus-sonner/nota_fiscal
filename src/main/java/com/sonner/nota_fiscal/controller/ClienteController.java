package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Cliente;
import com.sonner.nota_fiscal.service.ClienteService;
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
    private ClienteService clienteService; // instancia da classe que sera responsavel pelas chamadas ao banco de dados

    @PostMapping // anotacao para indicar a qual protocolo http este metodo ira responder
    @Transactional // anotacao para indicar o commit caso sucesso ou rollback caso nao
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // RequestBody e a anotacao responsavel por converter o JSON em Java
    // Valid e a anotacao para fazer a validacao de um objeto
    // Cliente e a classe que ira receber os dados em Json e converter para Java
    public ResponseEntity cadastrar(@RequestBody @Valid Cliente cliente){
        this.clienteService.novoCliente(cliente); // chamada ao metodo clienteservice que ira gravar as informacoes no banco de dados
        return ResponseEntity.ok("Cliente cadastrado com sucesso"); // retorna a resposta a requisicao http
    }

    @GetMapping // anotacao para indicar a qual protocolo http este metodo ira responder
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <List<Cliente>> representacao de uma lista de objetos do tipo Cliente
    public ResponseEntity<List<Cliente>> ler(){
        List<Cliente> clientes = this.clienteService.lerCliente(); // chamada do metodo para leitura dos clientes no banco de dados e retorno para a lista de clientes
        return ResponseEntity.ok(clientes); // retorna a resposta http com os clientes no formato Json
    }

    @GetMapping("/{id}") // anotacao para indicar a qual protocolo http este metodo ira responder
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <List<Cliente>> representacao de uma lista de objetos do tipo Cliente
    public ResponseEntity<Cliente> lerId(@PathVariable Long id){
        Cliente cliente = this.clienteService.lerId(id); // chamada do metodo para leitura dos clientes no banco de dados e retorno para a lista de clientes
        return ResponseEntity.ok(cliente); // retorna a resposta http com os clientes no formato Json
    }

    @DeleteMapping("/{id}") // significa que a exclusao ira ocorrer de acordo com o id do registro encaminhado na requisicao http
    @Transactional // anotacao para indicar o commit caso sucesso ou rollback caso nao
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <String> significa que ira retornar uma string no corpo na resposta
    // @PathVariable Long id - indica que o id recebido sera o id a ser deletado atraves da anotacao @PathVariable
    public ResponseEntity<String> deletar(@PathVariable Long id){
        clienteService.deletarCliente(id);// chamada do metodo para deletar o cliente no banco de dados de acordo com o id
        return ResponseEntity.ok("Cliente deletado com sucesso"); // retorna a resposta a requisicao http
    }

    @PutMapping("/{id}") // significa que a atualizacao ira ocorrer de acordo com o id do registro encaminhado na requisicao http
    @Transactional // anotacao para indicar o commit caso sucesso ou rollback caso nao
    // ResponseEntity e a classe responsavel por controlar a resposta da requisicao http
    // <String> significa que ira retornar uma string no corpo na resposta
    // @PathVariable Long id - indica que o id recebido sera o id a ser atualizado atraves da anotacao @PathVariable
    // RequestBody e a anotacao responsavel por converter o JSON em Java
    // Valid e a anotacao para fazer a validacao de um objeto
    // DadosAtualizaCliente e a classe que ira receber os dados em Json e converter para Java
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente clienteAtualiza){
         this.clienteService.atualizarCliente(id, clienteAtualiza); // chamada do metodo para atualizar o cliente no banco de dados de acordo com o id
         return ResponseEntity.ok("Cliente atualizado com sucesso"); // retorna a resposta a requisicao http
    }
}