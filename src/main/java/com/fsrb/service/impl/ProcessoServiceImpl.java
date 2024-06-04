package com.fsrb.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fsrb.domain.Processo;
import com.fsrb.domain.record.ProcessoRecord;
import com.fsrb.integration.Localidade;
import com.fsrb.integration.LocalidadeIntegration;
import com.fsrb.repository.ProcessoRepository;
import com.fsrb.service.ProcessoService;
import com.google.gson.Gson;

@Service
public class ProcessoServiceImpl implements ProcessoService {
	
	@Autowired
	private ProcessoRepository processoRepository;
	
	@Autowired
	private LocalidadeIntegration integration;
	
	@Value("${diretorioUpload}")
	private String diretorioUpload;
	
	@Override
	public ProcessoRecord save(String processoJSON, MultipartFile file) {
		Gson gson = new Gson();
		ProcessoRecord processoRecord = gson.fromJson(processoJSON, ProcessoRecord.class);
		
		Processo processo = new Processo();
		BeanUtils.copyProperties(processoRecord, processo);
		
		String pathUploadDocumento = uploadFile(file);
		processo.setPathUploadDocumento(pathUploadDocumento);
		
		processo.setDataCadastro(LocalDateTime.now());
		processoRepository.save(processo);
		return processoRecord;
	}
	
	private String uploadFile(MultipartFile fileUpload) {
		String pathUploadDocumento = null;
		File file = new File(diretorioUpload + fileUpload.getOriginalFilename(), "");
		
		File parentFile = file.getParentFile();
		boolean mkdir = parentFile.mkdir();
		if (!mkdir) {
			try {
				Path write = Files.write(Paths.get(file.getAbsolutePath()), fileUpload.getBytes(), StandardOpenOption.WRITE, 
						StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
				pathUploadDocumento = write.toString();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pathUploadDocumento;
	}
	
	@Override
	public Page<Processo> findAll(int page, int size) {
		PageRequest pageRequest = obterRequisicaoPaginada(page, size);
		return processoRepository.findAll(pageRequest);
	}
	
	@Override
	public Optional<Processo> findById(Long id) {
		return processoRepository.findById(id);
	}
	
	@Override
	public ProcessoRecord updateById(Long id, String processoJSON, MultipartFile file) {
		Gson gson = new Gson();
		ProcessoRecord processoRecord = gson.fromJson(processoJSON, ProcessoRecord.class);
		String pathUploadDocumento = uploadFile(file);
		
		findById(id).ifPresent(p -> {
			Processo processo = new Processo(id, processoRecord.npu(), p.getDataCadastro(),
					processoRecord.municipio(), processoRecord.uf(), pathUploadDocumento);
			processoRepository.save(processo);
		});
		return processoRecord;
	}
	
	@Override
	public void deleteById(Long id) {
		processoRepository.deleteById(id);
	}
	
	@Override
	public List<Localidade> findMunicipios(String uf ) {
		return integration.findMunicipio(uf);
	}

	@Override
	public List<Localidade> findEstados() {
		return integration.findEstados();
	}
	
	@Override
	public void viewProcesso(Long id) {
		LocalDateTime now = LocalDateTime.now();
		processoRepository.updateDataVisualizacao(now, id);
	}
	
	private PageRequest obterRequisicaoPaginada(int page, int size) {
		return PageRequest.of(page, size);
	}

}
