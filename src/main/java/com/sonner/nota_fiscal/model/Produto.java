package com.sonner.nota_fiscal.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String descricao;

    @NotNull
    private double valor_unitario;

    public Produto(@Valid Produto produto) {
        this.descricao = produto.descricao;
        this.valor_unitario = produto.valor_unitario;
    }

    public void atualizarProduto(@Valid Produto produtoAtualiza) {
        if (produtoAtualiza.descricao != null){
            this.descricao = produtoAtualiza.descricao;
        }if (produtoAtualiza.valor_unitario > 0){
            this.valor_unitario = produtoAtualiza.valor_unitario;
        }
    }
}
