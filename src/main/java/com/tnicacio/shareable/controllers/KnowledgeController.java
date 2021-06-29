package com.tnicacio.shareable.controllers;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tnicacio.shareable.dto.KnowledgeDTO;
import com.tnicacio.shareable.services.KnowledgeService;

@RestController
@RequestMapping(value = "/knowledges")
public class KnowledgeController {
	
	private KnowledgeService service;
	
	@Autowired
	public KnowledgeController(KnowledgeService service) {
		this.service = service;
	}
	
	@GetMapping
	public ResponseEntity<Page<KnowledgeDTO>> findAll(Pageable pageable) {
		Page<KnowledgeDTO> list = service.findAll(pageable);
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<KnowledgeDTO> findById(@PathVariable(value = "id") Long id) {
		KnowledgeDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	
	@PostMapping
	public ResponseEntity<KnowledgeDTO> insert(@Valid @RequestBody KnowledgeDTO dto) {
		KnowledgeDTO newDto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(newDto.getId()).toUri();
		return ResponseEntity.created(uri).body(newDto);
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<KnowledgeDTO> update(@PathVariable(value = "id") Long id, @Valid @RequestBody KnowledgeDTO dto) {
		KnowledgeDTO newDto = service.update(id, dto);
		return ResponseEntity.ok().body(newDto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
