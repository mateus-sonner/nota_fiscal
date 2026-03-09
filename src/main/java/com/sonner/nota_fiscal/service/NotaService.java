package com.sonner.nota_fiscal.service;

import com.sonner.nota_fiscal.model.ItemNota;
import com.sonner.nota_fiscal.model.Nota;
import com.sonner.nota_fiscal.model.Produto;
import com.sonner.nota_fiscal.repository.NotaRepository;
import com.sonner.nota_fiscal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NotaService {

    @Autowired
    private NotaRepository notaRepository;
    @Autowired
    private ProdutoRepository produtoRepository;

    public Nota novaNota(Nota nota) {
        totalizaNota(nota); // cria um novo objeto a partir das informacoes vindas do JSON
        return notaRepository.save(nota);
    }

    private void totalizaNota(Nota nota) {
        BigDecimal totalNota = BigDecimal.ZERO;
        //calcula o valor total de cada item de acordo com o valor unitario e quantidade
        for (var itemNota : nota.getItemNotaList()) {
            Produto produto = produtoRepository.findById(itemNota.getProduto().getId())
                    .orElseThrow(() ->
                            new RuntimeException("Produto não encontrado")
                    );
            BigDecimal valorUnitario = produto.getValorUnitario();
            BigDecimal totalItem = valorUnitario.multiply(itemNota.getQuantidade());
            itemNota.setValorTotalItem(totalItem);
            itemNota.setProduto(produto);
            itemNota.setNota(nota);
            totalNota = totalNota.add(totalItem);
        }

        nota.setValorTotalNota(totalNota);
    }

    public List<Nota> lerNota(){
        return notaRepository.findAll();
    }

    public Nota lerId(Long id) {
        return notaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota não encontrada"));
    }

    public void deletarNota(Long id){
        notaRepository.deleteById(id);
    }

    public void atualizarNota(Long id, Nota notaAtualiza){
        Nota nota = notaRepository.findById(id).orElseThrow(() -> new RuntimeException("Nota não encontrada"));

        nota = notaAtualiza;
        totalizaNota(notaAtualiza);

        nota.setValorTotalNota(notaAtualiza.getValorTotalNota());

        notaRepository.save(nota);
    }
}
