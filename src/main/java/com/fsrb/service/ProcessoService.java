package com.fsrb.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.fsrb.domain.Processo;
import com.fsrb.domain.record.ProcessoRecord;
import com.fsrb.integration.Localidade;

public interface ProcessoService {

	ProcessoRecord save(ProcessoRecord processoRecord);

	Page<Processo> findAll(int page, int size);

	Optional<Processo> findById(Long id);

	ProcessoRecord updateById(Long id, ProcessoRecord processoRecord);

	void deleteById(Long id);

	List<Localidade> findMunicipios(String uf);

	List<Localidade> findEstados();

	void viewProcesso(Long id);
}
