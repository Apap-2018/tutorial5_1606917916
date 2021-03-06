package com.apap.tutorial5.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apap.tutorial5.model.CarModel;

public interface CarDb extends JpaRepository<CarModel, Long> {
	CarModel findByType(String type);
//	List<CarModel> getListCarOrderByPriceAsc();

	List<CarModel> findByDealerId(Long dealerId);
	List<CarModel> findByDealerIdOrderByPriceAsc(long dealerId);
}