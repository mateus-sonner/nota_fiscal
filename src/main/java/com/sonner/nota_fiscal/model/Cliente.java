package com.sonner.nota_fiscal.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "cliente") // anotacao para definir o nome da tabela no banco de dados
@Entity(name = "Cliente") // anotacao para definir que essa classe ira representar um objeto a ser criado no banco de dados
@Getter // anotacao para criacao automatica dos getters
@Setter
@NoArgsConstructor // anotacao para criacao automatica do construtor sem argumentos
@AllArgsConstructor // anotacao para criacao automatica do construtor com todos os argumentos
@EqualsAndHashCode(of = "id") // anotacao para definir que dois objetos serao iguais se o id for igual um ao outro
public class Cliente {

    @Id // anotacao para definir que este campo sera o id da tabela
    @GeneratedValue(strategy = GenerationType.IDENTITY) // anotacao para definir que o id será gerado de forma automatica e sequencial
    private Long id;

    @NotBlank
    private String nome;

    @NotBlank
    private String codigo;
}
