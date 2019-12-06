package com.jatis.tripatra.demo;

import static org.junit.Assert.*;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.jatis.tripatra.demo.service.WareHouseService;

public class GenerateCodeTest {
	
	private static final Executor executor = Executors.newCachedThreadPool();
	
	private static final WareHouseService service = new WareHouseService();

	@Test
	public void testGenerateWarehouseCode() throws InterruptedException {
		
		for (int i = 0; i<1000; ++i) {
			executor.execute(()-> {
				System.out.println(Thread.currentThread().getName()+" warehouse code="+service.generateWarehouseCode("JKT"));
			});
		}
		Thread.sleep(10000);
	}

}
