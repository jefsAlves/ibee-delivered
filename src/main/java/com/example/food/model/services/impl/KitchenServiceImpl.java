package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.food.model.dto.KitchenDTO;
import com.example.food.model.entities.Kitchen;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.mapper.KitchenMapper;
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

	@Autowired
	private KitchenMapper mapper;

	@Override
	public KitchenDTO searchKitchen(Long id) {
		Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		kitchen.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return (KitchenDTO) kitchen.stream().map(src -> mapper.toDTO(src)).collect(Collectors.toList());
	}

	@Override
	public List<KitchenDTO> listKitchens(KitchenDTO kitchenDTO) {
		List<Kitchen> kitchen = kitchenRepository.findAll();
		return mapper.toDTOList(kitchen);
	}

	@Transactional
	@Override
	public KitchenDTO createKitchen(KitchenDTO kitchenDTO) {
		var kitchen = mapper.toEntity(kitchenDTO);
		validationKitchen.verifyKitchenExist(kitchen.getName());
		kitchenRepository.save(kitchen);
		return mapper.toDTO(kitchen);
	}

	@Transactional
	@Override
	public KitchenDTO updateKitchen(Long id, KitchenDTO kitchenDTO) {
		Kitchen kitchen = validationKitchen.verifyKitchenExist(id);
		BeanUtils.copyProperties(kitchenDTO, kitchen, "id", "restaurant");
		kitchenRepository.save(kitchen);
		return mapper.toDTO(kitchen);
	}

	@Transactional
	@Override
	public void deleteKitchen(Long id) {
		try {
			kitchenRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
