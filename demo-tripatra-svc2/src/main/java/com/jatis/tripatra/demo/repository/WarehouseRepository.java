package com.jatis.tripatra.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.tripatra.demo.entity.WarehouseEntity;

public interface WarehouseRepository extends PagingAndSortingRepository<WarehouseEntity, String> {
}
