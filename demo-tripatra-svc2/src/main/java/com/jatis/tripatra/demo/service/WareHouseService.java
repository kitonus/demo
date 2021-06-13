package com.jatis.tripatra.demo.service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jatis.tripatra.demo.entity.WarehouseEntity;
import com.jatis.tripatra.demo.repository.WarehouseRepository;

@Service
public class WareHouseService {
	private static final Logger log = LoggerFactory.getLogger(WareHouseService.class);

	@Autowired
	private WarehouseRepository repo;
	
	@Autowired
	private ApplicationContext context;

	@Transactional(rollbackFor=Throwable.class)
	public void editWarehouse(WarehouseEntity[] warehouses) throws Exception {
		warehouses[0] = repo.save(warehouses[0]);
		try {
			warehouses[1] = childService().editA(warehouses[1]);
			//warehouses[1] = editA(warehouses[1]);//direct call > not transactional
		} catch (Exception e) {
			log.warn("editA error: "+e.toString());
		}
		try {
			warehouses[2] = childService().editB(warehouses[2]);
			//warehouses[2] = editB(warehouses[2]);//direct call > not transactional
		} catch (Exception e) {
			log.warn("editB error: "+e.toString());
		}
		log.info("Since edit B throws error whole transaction is rolled back, warehousesp[2], warehouses[0], warehouses[1] are not saved");
		//log.info("Since edit B trhwoable is not caught by transaction manager, "
		//		+ "transaction is not rolled back. warehouses[0], warehouses[1], and warehouses[2] are still saved.");
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public WarehouseEntity editA(WarehouseEntity warehouse) throws Exception {
		WarehouseEntity wA = repo.save(warehouse);
		return wA;
		//throw new Exception("Pokoknya error ajah");
	}
	
	@Transactional(rollbackFor=Throwable.class)
	public WarehouseEntity editB(WarehouseEntity warehouse) throws Exception {
		repo.save(warehouse);
		throw new Exception("Pokoknya error ajah");
	}

	public WareHouseService childService() throws Exception {
		return context.getBean(WareHouseService.class);
	}

	/*
	 * contoh kesalahan penggunaan object variable
	 */
	private WarehouseEntity selected;
	
	@Transactional(readOnly = true, rollbackFor = Throwable.class)
	public WarehouseEntity selectWarehouse(String code) {
		selected = repo.findById(code).orElse(null);
		return selected;
	}
	
	public WarehouseEntity showSelected() {
		return selected;
	}
	
	
	//Thread local cache for warehouse currently accessed by current thread
	private static final ThreadLocal<WarehouseEntity> currentWarehouse = new ThreadLocal<>();
	
	/**
	 * Manipulate warehouse
	 * @param code warehouse code
	 * @param name warehouse name
	 * @param capacity warehouse capacity
	 * @return warehouse entity
	 */
	public WarehouseEntity manipulateWarehouse(String code, String name, double capacity) {
		try {
			WarehouseEntity warehouse = repo.findById(code).orElse(null);
			currentWarehouse.set(warehouse);
			editName(name);
			editCapacity(capacity);
			return repo.save(warehouse);		 
		} finally {
			/*
			 * Jangan lupa kalau pakai thread local, object yg sudah tidak dipakai di remove()!!!! 
			 */
			currentWarehouse.remove();
		}
	}
	
	//uneeded public as method's access modifier
	public void editName(String name) {
		WarehouseEntity warehouse = currentWarehouse.get();
		warehouse.setName(name);
	}
	
	private void editCapacity(double capacity) {
		WarehouseEntity warehouse = currentWarehouse.get();
		warehouse.setCapacity(capacity);
	}
	
	/*
	 * Jangan pakai synchronized keyword jika masih ada cara lain
	 */
	private AtomicInteger sequenceNo = new AtomicInteger(1);
	public String generateWarehouseCode(String prefix) {
		String str = prefix+String.valueOf(sequenceNo.getAndIncrement());
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			log.error("Interrupted exception: " + e);//salah karena stack trace hilang
		}
		return str;
	}
}
