package com.jatis.tripatra.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.tripatra.demo.entity.MaterialEntity;

public interface MaterialRepository extends PagingAndSortingRepository<MaterialEntity, String> {

}
