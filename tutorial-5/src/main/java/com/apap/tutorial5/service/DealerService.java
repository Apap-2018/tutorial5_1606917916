package com.apap.tutorial5.service;

import java.util.List;
import java.util.Optional;

import com.apap.tutorial5.model.DealerModel;

/*
 * DealerService
 */
public interface DealerService {
	Optional<DealerModel> getDealerDetailById(Long id);
	
	List<DealerModel> getAllDealer();
	
	void addDealer(DealerModel dealer);
	void deleteDealer(DealerModel dealer);
	void updateDealer(long id, Optional<DealerModel> dealer);
}