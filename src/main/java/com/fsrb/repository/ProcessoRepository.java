package com.fsrb.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fsrb.domain.Processo;

import jakarta.transaction.Transactional;

public interface ProcessoRepository extends JpaRepository<Processo, Long>{

	Page<Processo> findAll(Pageable pageable);

	@Modifying
	@Transactional
	@Query("UPDATE Processo SET dataVisualizacao = :viewProcesso WHERE id = :id")
	void updateDataVisualizacao(LocalDateTime viewProcesso, Long id);

}
