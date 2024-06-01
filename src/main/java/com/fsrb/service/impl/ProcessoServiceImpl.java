package com.fsrb.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.fsrb.domain.Processo;
import com.fsrb.domain.record.ProcessoRecord;
import com.fsrb.integration.Integration;
import com.fsrb.integration.Localidade;
import com.fsrb.repository.ProcessoRepository;
import com.fsrb.service.ProcessoService;

@Service
public class ProcessoServiceImpl implements ProcessoService {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	@Autowired
	private Integration integration;
	
	@Override
	public ProcessoRecord save(ProcessoRecord processoRecord) {
		Processo processo = new Processo();
		BeanUtils.copyProperties(processoRecord, processo);
		processoRepository.save(processo);
		return processoRecord;
	}
	
	@Override
	public Page<Processo> findAll(Pageable pageable) {
		return processoRepository.findAll(pageable);
	}
	
	@Override
	public Optional<Processo> findById(Long id) {
		return processoRepository.findById(id);
	}
	
	@Override
	public ProcessoRecord updateById(Long id, ProcessoRecord processoRecord) {
		findById(id).ifPresent(p -> {
			Processo processo = new Processo(id, processoRecord.nome(), 
				processoRecord.npu(), processoRecord.dataCadastro(), null, null, null);
			processoRepository.save(processo);
		});
		return processoRecord;
	}
	
	@Override
	public void deleteById(Long id) {
		processoRepository.deleteById(id);
	}
	
	@Override
	public List<Localidade> municipios(String uf ) {
		return integration.findMunicipio(uf);
	}

}
