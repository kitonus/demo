package com.jatis.tripatra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.tripatra.demo.entity.MaterialEntity;
import com.jatis.tripatra.demo.repository.MaterialRepository;

@RestController
@RequestMapping("/material")
public class MaterialController {

	@Autowired
	private MaterialRepository repo;
	
	@GetMapping
	public Iterable<MaterialEntity> findAll(){
		return repo.findAll();
	}

	@PostMapping
	public MaterialEntity save(@RequestBody MaterialEntity material) {
		return repo.save(material);
	}
	
	@GetMapping("/{code}")
	public MaterialEntity find(@PathVariable String code) {
		return repo.findById(code).orElse(null);
	}
}
