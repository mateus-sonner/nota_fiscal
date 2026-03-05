package com.sonner.nota_fiscal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;


@Table(name = "itemnota")
@Entity(name = "ItemNota")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemNota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @NotNull
    private BigDecimal quantidade;

    @NotNull
    private BigDecimal valorTotalItem;

    // relacionamento entre varios itens da nota para uma unica nota
    @ManyToOne
    // JoinColumn indica a classe dono do relacionamento e é quem vai gerar a chave estrangeira no banco de dados atraves da anotacao name = "nota_id"
    @JoinColumn(name = "nota_id")
    @JsonIgnore
    private Nota nota;

    // relacionamento onde varias listas so podem conter o produto uma unica vez
    // nesse relacionamento nao foi criado o JoinColumn para que a coluna seja criada automaticamente para aprendizado
    @ManyToOne
    private Produto produto;

}
