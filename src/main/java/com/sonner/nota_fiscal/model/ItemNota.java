package com.sonner.nota_fiscal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "itensnota")
@Entity(name = "ItemNota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemNota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Integer quantidade;

    // relacionamento entre varios itens da nota para uma unica nota
    @ManyToOne
    // JoinColumn indica a classe dono do relacionamento e é quem vai gerar a chave estrangeira no banco de dados atraves da anotacao name = "nota_id"
    @JoinColumn(name = "nota_id")
    private Nota nota;

    // relacionamento onde varias listas so podem conter o produto uma unica vez
    // nesse relacionamento nao foi criado o JoinColumn para que a coluna seja criada automaticamente para aprendizado
    @ManyToOne
    private Produto produto;

    // metodo utilizado para setar o id da nota em cada um dos itens da lista
    public void setNota(Nota nota) {
        this.nota = nota;
    }
}
