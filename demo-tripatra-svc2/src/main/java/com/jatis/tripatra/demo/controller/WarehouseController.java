package com.jatis.tripatra.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.tripatra.demo.entity.WarehouseEntity;
import com.jatis.tripatra.demo.repository.WarehouseRepository;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

	@Autowired
	private WarehouseRepository repo;
	
	@GetMapping
	public Iterable<WarehouseEntity> findAll(){
		return repo.findAll();
	}

	@PostMapping
	public WarehouseEntity save(@RequestBody WarehouseEntity warehouse) {
		return repo.save(warehouse);
	}
	
	@GetMapping("/{code}")
	public WarehouseEntity find(@PathVariable String code) {
		return repo.findById(code).orElse(null);
	}
}
