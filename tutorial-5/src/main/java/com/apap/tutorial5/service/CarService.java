package com.apap.tutorial5.service;

import java.util.List;

import com.apap.tutorial5.model.CarModel;

/*
 * CarService
 */
public interface CarService {
	void addCar(CarModel car);
	CarModel getCar(Long id);
	public void deleteById(Long id);
	void updateCar(long id,CarModel car);
	List<CarModel> getListCarOrderByPriceAsc(Long dealerId);
	void deleteCar(CarModel car);
}