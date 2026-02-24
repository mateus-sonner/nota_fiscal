package com.sonner.nota_fiscal.repository;

import com.sonner.nota_fiscal.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
