package com.sonner.nota_fiscal.domain;

import jakarta.validation.constraints.NotBlank;

// Classe para validação de informações
public record DadosCliente(


        @NotBlank // anotacao que indica que o nome nao pode ser nulo, vazio ou espaço em branco
        String nome
) {
}
