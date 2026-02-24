package com.sonner.nota_fiscal.domain;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "clientes") // anotacao para definir o nome da tabela no banco de dados
@Entity(name = "Cliente") // anotacao para definir que essa classe ira representar um objeto a ser criado no banco de dados
@Getter // anotacao para criacao automatica dos getters
@NoArgsConstructor // anotacao para criacao automatica do construtor sem argumentos
@AllArgsConstructor // anotacao para criacao automatica do construtor com todos os argumentos
@EqualsAndHashCode(of = "id") // anotacao para definir que dois objetos serao iguais se o id for igual um ao outro
public class Cliente {

    @Id // anotacao para definir que este campo sera o id da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // anotacao para definir que o id será gerado de forma automatica e sequencial
    private Long id;

    private String nome;

    // metodo responsavel por receber dadosCliente e atribuir a Cliente
    public Cliente(DadosCliente dadosCliente){
        this.nome = dadosCliente.nome();
    }

    // metodo responsavel por receber as informacoes do cliente a serem atualizadas
    public void atualizarCliente(@Valid DadosAtualizaCliente dadosAtualizaCliente){
        if (dadosAtualizaCliente.nome() != null){
            this.nome = dadosAtualizaCliente.nome();
        }
    }
}
