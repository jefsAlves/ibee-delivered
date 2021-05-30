package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.food.model.entities.Kitchen;
import com.example.food.model.exceptions.BusinessException;
import com.example.food.model.repository.KitchenRepository;
import com.example.food.model.services.KitchenService;
import com.example.food.model.util.MessageUtil;

@Service
public class KitchenServiceImpl implements KitchenService {

	@Autowired
	private KitchenRepository kitchenRepository;

	@Override
	public Kitchen searchKitchen(Long id) {
		Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		return kitchen.orElseThrow(() -> new BusinessException(MessageUtil.ID_NOT_FOUND));
	}

	@Override
	public List<Kitchen> listKitchens(Kitchen kitchen) {
		return kitchenRepository.findAll();
	}

	@Transactional
	@Override
	public Kitchen createKitchen(Kitchen kitchen) {
		return validKitchenExist(kitchen);
	}

	private Kitchen validKitchenExist(Kitchen kitchen) {
		Kitchen kitchenBase = kitchenRepository.findByName(kitchen.getName());
		if (kitchenBase != null) {
			throw new BusinessException(MessageUtil.KITCHEN_ALREADY_EXIST);
		}
		return kitchenRepository.save(kitchen);
	}

	@Transactional
	@Override
	public Kitchen updateKitchen(Long id, Kitchen kitchen) {
		Optional<Kitchen> kitchenBase = kitchenRepository.findById(id);
		if (kitchenBase.isPresent()) {
			BeanUtils.copyProperties(kitchen, kitchenBase.get(), "id");
			return kitchenRepository.save(kitchenBase.get());
		}
		throw new BusinessException(MessageUtil.ID_NOT_FOUND);
	}

	@Transactional
	@Override
	public void deleteKitchen(Long id) {
		kitchenRepository.deleteById(id);
	}

}
