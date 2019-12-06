package com.jatis.tripatra.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.jatis.tripatra.demo.entity.MaterialEntity;

/**
 * Material repository to access table material_entity
 * @author hwicaksono
 *
 */
public interface MaterialRepository extends PagingAndSortingRepository<MaterialEntity, String> {

}
