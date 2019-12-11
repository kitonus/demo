package com.jatis.tripatra.demo.controller;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jatis.tripatra.demo.entity.WarehouseEntity;
import com.jatis.tripatra.demo.repository.WarehouseRepository;
import com.jatis.tripatra.demo.service.WareHouseService;

@RestController
@RequestMapping("/warehouse")
public class WarehouseController implements InitializingBean{
	
	private static final Logger log = LoggerFactory.getLogger(WarehouseController.class);

	@Autowired
	private WarehouseRepository repo;
	
	@Autowired
	private WareHouseService service;
	
	@GetMapping
	public Iterable<WarehouseEntity> findAll(){
		return repo.findAll();
	}

	@GetMapping("/findAllPaged/{pageNo}/{pageSize}")
	public Iterable<WarehouseEntity> findAll(@PathVariable("pageNo") int pageNo,
			@PathVariable("pageSize") int pageSize){
		PageRequest pageRequest = PageRequest.of(pageNo, pageSize, Sort.by("code"));
		return repo.findAll(pageRequest);
	}

	@PostMapping
	public WarehouseEntity save(@RequestBody WarehouseEntity warehouse) {
		return repo.save(warehouse);
	}
	
	@PostMapping("/warehouses")
	public WarehouseEntity[] saveWarehouses(@RequestBody WarehouseEntity[] warehouses) throws Exception {
		service.editWarehouse(warehouses);
		return warehouses;
	}

	/*
	 * This is wrong cache usage
	 */
	private HashMap<String, WarehouseEntity> cache = new HashMap<String, WarehouseEntity>();	
	
	@GetMapping("/{code}")
	public WarehouseEntity find(@PathVariable String code) {
		try {
			if (cache.get(code) != null) {
				log.debug("====>>>>>>> ambil cache !!!!! CODE="+code);
				return cache.get(code);
			}
			
			log.debug("====>>>>>>> ambil dari DB!!!!! CODE="+code);
			WarehouseEntity warehouse = repo.findById(code).get();
			cache.put(code, warehouse);
			return warehouse;
		} catch (Exception e) {
			log.error("Failed getting warehouses "+e);
			throw e;
		}
	}
	
	@PostMapping("/selectWarehouse/{code}")
	public WarehouseEntity selectWarehouse(@PathVariable("code") String code) {
		return service.selectWarehouse(code);
	}
	
	@GetMapping("/showSelected")
	public WarehouseEntity showSelected() {
		return service.showSelected();
	}
	
	@GetMapping("/demoLogger")
	public String demoLogger() {
		log.error("Ini error!");
		log.warn("Ini warning");
		log.info("Ini info");
		log.debug("Ini debug saja");
		
		try {
			throw new Exception("My exception test");
		} catch (Exception e) {
			log.error("Error ", e);
		}
		return "DEMO LOGGER";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.error("Ini error!");
		log.warn("Ini warning");
		log.info("Ini info");
		log.debug("Ini debug saja");
	}
}
