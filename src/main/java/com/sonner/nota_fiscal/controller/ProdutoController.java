package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Produto;
import com.sonner.nota_fiscal.service.ProdutoService;
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
    private ProdutoService produtoService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Produto produto){
        this.produtoService.novoProduto(produto);
        return ResponseEntity.ok("Produto cadastrado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Produto>> ler(){
        List<Produto> produtos = this.produtoService.lerProduto();
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> lerId(@PathVariable Long id){
        Produto produto = this.produtoService.lerId(id);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id){
        this.produtoService.deletarProduto(id);
        return ResponseEntity.ok("Produto deletado com sucesso");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid Produto produtoAtualiza){
        this.produtoService.atualizarProduto(id, produtoAtualiza);
        return ResponseEntity.ok("Produto atualizado com sucesso");
    }
}
