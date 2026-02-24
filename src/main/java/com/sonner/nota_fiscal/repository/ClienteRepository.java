package com.sonner.nota_fiscal.repository;

import com.sonner.nota_fiscal.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

// classe utilizada para persistir as informacoes de Cliente no banco de dados
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
