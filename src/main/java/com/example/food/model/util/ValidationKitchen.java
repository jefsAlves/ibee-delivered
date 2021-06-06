package com.example.food.model.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.Kitchen;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.exceptions.KitchenException;
import com.example.food.model.repository.KitchenRepository;

@Component
public class ValidationKitchen {

	@Autowired
	private KitchenRepository kitchenRepository;

	public Kitchen verifyKitchenExist(Long id) {
		Optional<Kitchen> kitchenBase = kitchenRepository.findById(id);
		return kitchenBase.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	public boolean verifyKitchenExist(String name) {
		Kitchen kitchenBase = kitchenRepository.findByName(name);
		if (kitchenBase != null) {
			throw new KitchenException(MessageUtil.KITCHEN_ALREADY_EXIST);
		}
		return true;
	}

}
