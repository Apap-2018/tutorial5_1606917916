package com.apap.tutorial5.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.CarService;
import com.apap.tutorial5.service.DealerService;

/*
 * CarController
 */
@Controller
public class CarController {
	@Autowired
	private CarService carService;
	
	@Autowired
	private DealerService dealerService;

	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
	private String add(@PathVariable(value = "dealerId") Long dealerId, Model model) {
		DealerModel dealer  = dealerService.getDealerDetailById(dealerId).get();
		ArrayList<CarModel> list = new ArrayList<CarModel>();
		list.add(new CarModel());
		dealer.setListCar(list);
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params={"save"})
	private String addCarSubmit(@ModelAttribute DealerModel dealer) {
		DealerModel dealerNow  = dealerService.getDealerDetailById(dealer.getId()).get();
		for(CarModel car: dealer.getListCar()) {
			car.setDealer(dealerNow);
			carService.addCar(car);
		}
		return "add";
	}
//	@RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.GET)
//	private String add(@PathVariable(value="dealerId") Long dealerId, Model model) {
//		CarModel car = new CarModel();
//		DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
//		car.setDealer(dealer);
//		
//		model.addAttribute("car", car);
//		return "addCar";
//	}
//	
//	@RequestMapping(value = "/car/add", method = RequestMethod.POST)
//	private String addCarSubmit(@ModelAttribute CarModel car) {
//		carService.addCar(car);
////		System.out.println(car.getId());
//		return "add";
//	}
	
//	@RequestMapping(value="/car/delete/{id}", method=RequestMethod.GET)
//	private String deleteCar(@PathVariable(value = "id") Long id, Model model) {
//			carService.deleteById(id);
//			return "delete-car";
//	}
	@RequestMapping(value="/car/delete", method = RequestMethod.POST)
	private String delete(@ModelAttribute DealerModel dealer, Model model) {
		for (CarModel car: dealer.getListCar()) {
			carService.deleteCar(car);
		}
		return "delete-car";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.GET)
	private String updateCar(@PathVariable(value = "id") long id, Model model) {
		CarModel car = carService.getCar(id);
		model.addAttribute("car",car);
		return "update-car";
	}
	
	@RequestMapping(value = "/car/update/{id}", method = RequestMethod.POST)
	private String updateCarSubmit(@PathVariable(value = "id") long id, @ModelAttribute CarModel car) {
		carService.updateCar(id, car);
		return "update";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params= {"addRow"})
	public String addRow(@ModelAttribute DealerModel dealer, BindingResult bindingResult, Model model) {
		if (dealer.getListCar() == null) {
            dealer.setListCar(new ArrayList<CarModel>());
        }
		dealer.getListCar().add(new CarModel());
		
		model.addAttribute("dealer", dealer);
		return "addCar";
	}
	
	@RequestMapping(value="/car/add/{dealerId}", method = RequestMethod.POST, params={"removeRow"})
	public String removeRow(@ModelAttribute DealerModel dealer, final BindingResult bindingResult, final HttpServletRequest req, Model model) {
	    final Integer rowId = Integer.valueOf(req.getParameter("removeRow"));
	    dealer.getListCar().remove(rowId.intValue());
	    
	    model.addAttribute("dealer", dealer);
	    return "addCar";
	}
}
