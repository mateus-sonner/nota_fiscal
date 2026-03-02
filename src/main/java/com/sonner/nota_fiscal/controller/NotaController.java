package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Nota;
import com.sonner.nota_fiscal.service.NotaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Nota nota){
        this.notaService.novaNota(nota);
        return ResponseEntity.ok("Nota Fiscal cadastrada com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<Nota>> ler(){
        List<Nota> notas = this.notaService.lerNota();
        return ResponseEntity.ok(notas);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<String> deletar(@PathVariable Long id){
        this.notaService.deletarNota(id);
        return ResponseEntity.ok("Nota deletada com sucesso");
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<String> atualizar(@PathVariable Long id, @RequestBody @Valid Nota notaAtualiza){
        this.notaService.atualizarNota(id, notaAtualiza);
        return ResponseEntity.ok("Nota atualizada com sucesso");
    }
}
