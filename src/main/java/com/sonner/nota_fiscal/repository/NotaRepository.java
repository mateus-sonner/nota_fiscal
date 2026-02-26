package com.sonner.nota_fiscal.repository;

import com.sonner.nota_fiscal.model.Nota;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaRepository extends JpaRepository<Nota, Long> {
}
