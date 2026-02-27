package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Nota;
import com.sonner.nota_fiscal.repository.NotaRepository;
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
    private NotaRepository notaRepository;

    // cadastro de notas
    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Nota nota){
        new Nota(nota);
        notaRepository.save(nota);
        return ResponseEntity.ok("Nota Fiscal cadastrada com sucesso");
    }

    // listagem de notas
    @GetMapping
    public ResponseEntity<List<Nota>> ler(){
        List<Nota> notas = notaRepository.findAll();
        return ResponseEntity.ok(notas);

    }
}
