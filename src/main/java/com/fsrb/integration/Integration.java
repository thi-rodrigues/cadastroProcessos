package com.fsrb.integration;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "localidade", url = "${localidades.url}")
public interface Integration {

	@GetMapping("/{UF}/regioes-imediatas")
	List<Localidade> findMunicipio(@PathVariable String UF);

}
