package com.sonner.nota_fiscal.service;

import com.sonner.nota_fiscal.model.Produto;
import com.sonner.nota_fiscal.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto novoProduto(Produto produto) {
        return produtoRepository.save(produto);
    }

    public List<Produto> lerProduto(){
        return produtoRepository.findAll();
    }

    public void deletarProduto(Long id){
        produtoRepository.deleteById(id);
    }

    public void atualizarProduto(Long id, Produto produtoAtualiza) {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado"));
        produto.setDescricao(produtoAtualiza.getDescricao());
        produto.setValorUnitario(produtoAtualiza.getValorUnitario());
    }
}
