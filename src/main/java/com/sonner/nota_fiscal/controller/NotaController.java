package com.sonner.nota_fiscal.controller;

import com.sonner.nota_fiscal.model.Nota;
import com.sonner.nota_fiscal.model.Produto;
import com.sonner.nota_fiscal.repository.NotaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notas")
public class NotaController {

    @Autowired
    private NotaRepository notaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid Nota nota){
        new Nota(nota);
        notaRepository.save(nota);
        return ResponseEntity.ok("Nota Fiscal cadastrada com sucesso");
    }
}
