package com.fsrb.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fsrb.domain.Processo;

public interface ProcessoRepository extends JpaRepository<Processo, Long>{

	Page<Processo> findAll(Pageable pageable);

}
