package com.sonner.nota_fiscal.domain;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// classe responsavel por representar os dados a serem atualizados
public record DadosAtualizaCliente(

        @NotNull
        Long id,

        @NotBlank
        String nome) {
}
