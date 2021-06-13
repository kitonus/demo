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

	/*
	 * Material repository
	 */
	@Autowired
	private MaterialRepository repo;

	/**
	 * Find all maaterials
	 * @return {@link Iterable} of {@link MaterialEntity}
	 */
	@GetMapping
	public Iterable<MaterialEntity> findAll(){
		return repo.findAll();
	}

	@PostMapping
	public MaterialEntity save(@RequestBody MaterialEntity material) {
		return repo.save(material);
	}
	
	static boolean isFound = false; //this is really wrong!
	
	@GetMapping("/{code}")
	public MaterialEntity find(@PathVariable String code) {
		//Find by material ID if not found return null - Fix BTN-111
		MaterialEntity mat = repo.findById(code).orElse(null);
		if (mat != null) {
			isFound = true;
		} else {
			isFound = false;
		}
		return mat;
	}
	
}
