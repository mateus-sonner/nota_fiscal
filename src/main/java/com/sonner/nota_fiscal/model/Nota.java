package com.sonner.nota_fiscal.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.List;

@Table(name = "notas")
@Entity(name = "Nota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Nota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String numero;

    @NotNull
    private LocalDate data;

    // relacionamento de varias notas para um cliente declarando o id do cliente como o join entre as 2 tabelas
    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    // relacionamento de uma nota para uma lista de varios itens
    @OneToMany(mappedBy = "nota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemNota> itemNotaList;

    public Nota(@Valid Nota nota) {
        this.numero = nota.numero;
        this.data = nota.data;
        this.cliente = nota.cliente;
        //insere o id da nota em cada item da lista
        nota.getItemNotaList()
                .forEach(item -> item.setNota(nota));
    }
}
