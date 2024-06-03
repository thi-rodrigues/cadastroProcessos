package com.fsrb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fsrb.domain.Processo;
import com.fsrb.domain.record.ProcessoRecord;
import com.fsrb.integration.Localidade;
import com.fsrb.service.ProcessoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/processo")
public class ProcessoResource {
	
	@Autowired
	private ProcessoService processoService;
	
	@PostMapping("/save")
	public ResponseEntity<ProcessoRecord> save(
			@RequestParam("processo") String processo,
			@RequestParam("file") MultipartFile file) {
		ProcessoRecord processoRecord = processoService.save(processo, file);
		return ResponseEntity.status(HttpStatus.CREATED).body(processoRecord);
	}
	
	@GetMapping()
	public ResponseEntity<Page<Processo>> findAll(
			@RequestParam(value = "page", required = false, defaultValue = "0") int page,
			@RequestParam(value = "size", required = false, defaultValue = "5") int size) {
		Page<Processo> processos = processoService.findAll(page, size);
		return ResponseEntity.ok(processos);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Processo> findById(@PathVariable("id") Long id) {
		Processo processo = processoService.findById(id).get();
		return ResponseEntity.ok(processo);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ProcessoRecord> updateById(@PathVariable("id") Long id, @RequestBody @Valid ProcessoRecord processoRecord) {
		ProcessoRecord processoupdate = processoService.updateById(id, processoRecord);
		return ResponseEntity.ok(processoupdate);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		processoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/ufs")
	public ResponseEntity<List<Localidade>> estados() {
		return ResponseEntity.ok(processoService.findEstados());
	}

	@GetMapping("/municipios/{uf}")
	public ResponseEntity<List<Localidade>> municipios(@PathVariable("uf") String uf) {
		return ResponseEntity.ok(processoService.findMunicipios(uf));
	}
	
	@GetMapping("/viewProcesso/{id}")
	public ResponseEntity<List<Localidade>> viewProcesso(@PathVariable("id") Long id) {
		processoService.viewProcesso(id);
		return ResponseEntity.ok().build();
	}
}
