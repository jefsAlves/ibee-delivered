package com.example.food.model.services.impl;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import com.example.food.infra.kafka.integration.SendKitchen;
import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.entities.Kitchen;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.mapper.impl.KitchenMapperImpl;
import com.example.food.infra.repository.KitchenRepository;
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
	private KitchenMapperImpl mapper;

	@Autowired
	private SendKitchen integration;

	@Override
	public KitchenDTO searchKitchen(Long id) {
		Optional<Kitchen> kitchen = kitchenRepository.findById(id);
		kitchen.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
		return mapper.toDTO(kitchen);
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
//		integration.sendMessage(kitchenDTO);
		return mapper.toDTO(kitchen);
	}

	@Transactional
	@Override
	public KitchenDTO updateKitchen(Long id, KitchenDTO kitchenDTO) {
		var kitchenBase = validationKitchen.verifyKitchenExist(id);
		mapper.copyProperties(kitchenDTO, kitchenBase);
		kitchenRepository.save(kitchenBase);
		return mapper.toDTO(kitchenBase);
	}

	@Transactional
	@Override
	public void deleteKitchen(Long id) {
		try {
			kitchenRepository.deleteById(id);
		} catch (DataIntegrityViolationException | EmptyResultDataAccessException e) {
			throw new IdNotFoudException(MessageUtil.ID_NOT_FOUND);
		}
	}

}
