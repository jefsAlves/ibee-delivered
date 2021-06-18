package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.Kitchen;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.repository.KitchenRepository;
import com.example.food.model.services.KitchenService;
import com.example.food.model.util.MessageUtil;
import com.example.food.model.util.ValidationKitchen;

@Service
public class KitchenServiceImpl implements KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;

	@Autowired
	private ValidationKitchen validationKitchen;

	@Override
	public Kitchen searchKitchen(Long id) {
		Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		return kitchen.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public List<Kitchen> listKitchens(Kitchen kitchen) {
		return kitchenRepository.findAll();
	}

	@Transactional
	@Override
	public Kitchen createKitchen(Kitchen kitchen) {
		validationKitchen.verifyKitchenExist(kitchen.getName());
		return kitchenRepository.save(kitchen);
	}

	@Transactional
	@Override
	public Kitchen updateKitchen(Long id, Kitchen kitchen) {
		Kitchen validKitchen = validationKitchen.verifyKitchenExist(id);
		BeanUtils.copyProperties(kitchen, validKitchen, "id", "restaurant");
		return kitchenRepository.save(validKitchen);
	}

	@Transactional
	@Override
	public void deleteKitchen(Long id) {
		try {
			kitchenRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException  e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
