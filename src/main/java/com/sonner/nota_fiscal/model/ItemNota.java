package com.sonner.nota_fiscal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


// define a ordem dos campos no momento do retorno da requisicao http
@JsonPropertyOrder({
        "produto",
        "quantidade",
        "valorUnitario",
        "valorTotal"})
@Table(name = "itensnota")
@Entity(name = "ItemNota")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ItemNota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore // ignora este campo no momento do retorno da requisicao http
    private Long id;

    @NotNull
    private Integer quantidade;

    // relacionamento entre varios itens da nota para uma unica nota
    @ManyToOne
    // JoinColumn indica a classe dono do relacionamento e é quem vai gerar a chave estrangeira no banco de dados atraves da anotacao name = "nota_id"
    @JoinColumn(name = "nota_id")
    @JsonIgnore /// ignora este campo no momento do retorno da requisicao http
    private Nota nota;

    // relacionamento onde varias listas so podem conter o produto uma unica vez
    // nesse relacionamento nao foi criado o JoinColumn para que a coluna seja criada automaticamente para aprendizado
    @ManyToOne
    private Produto produto;

    // metodo utilizado para setar o id da nota em cada um dos itens da lista
    public void setNota(Nota nota) {
        this.nota = nota;
    }

    @JsonProperty("produto") // quando converter para JSON produto sera o nome do metodo
    public String getProdutoDescricao() {
        return produto.getDescricao(); // retorna apenas o nome do produto
    }

    @JsonProperty("valorUnitario") // quando converter para JSON produto sera o nome do metodo
    public double getValorUnitario() {
        return produto.getValor_unitario(); // retorna apenas o valor unitario
    }

    // metodo utilizado para calcular o valor total do item de acordo com a quantidade
    @JsonProperty("valorTotal")
    public double getValorTotalItem() {
        return this.quantidade * this.produto.getValor_unitario();
    }
}
