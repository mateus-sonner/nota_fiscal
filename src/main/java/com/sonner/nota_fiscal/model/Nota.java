package com.sonner.nota_fiscal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@JsonPropertyOrder({
        "cliente",
        "numero",
        "data",
        "itemNotaList",
        "valorTotalNota"})
@Table(name = "nota")
@Entity(name = "Nota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotBlank
    private String numero;

    @NotNull
    private LocalDate data;

    private BigDecimal valorTotalNota;

    // relacionamento de varias notas para um cliente declarando o id do cliente como o join entre as 2 tabelas
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // quando converter para JSON cliente sera o nome do metodo
    @JsonProperty("cliente")
    public String getClienteNome() {
        return cliente.getNome(); // retorna apenas o nome do cliente
    }

    // relacionamento de uma nota para uma lista de varios itens
    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNota> itemNotaList;

    }
