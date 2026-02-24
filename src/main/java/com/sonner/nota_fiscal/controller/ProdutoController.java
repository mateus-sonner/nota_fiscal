package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Produto;
import com.sonner.nota_fiscal.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Produto produto){
        new Produto(produto);
        produtoRepository.save(produto);
        return ResponseEntity.ok("Produto cadastrado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Produto>> ler(){
        List<Produto> produtos = produtoRepository.findAll();
        return ResponseEntity.ok(produtos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id){
        produtoRepository.deleteById(id);
        return ResponseEntity.ok("Produto deletado com sucesso");
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> atualizar(@RequestBody @Valid Produto produtoAtualiza){
        var produto = produtoRepository.getReferenceById(produtoAtualiza.getId());
        produto.atualizarProduto(produtoAtualiza);
        return ResponseEntity.ok("Produto atualizado com sucesso");
    }
}
