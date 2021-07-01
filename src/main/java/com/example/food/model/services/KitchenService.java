package com.example.food.model.services;

import java.util.List;

import com.example.food.model.dto.KitchenDTO;

public interface KitchenService {

	KitchenDTO searchKitchen(Long id);

	List<KitchenDTO> listKitchens(KitchenDTO kitchen);

	KitchenDTO createKitchen(KitchenDTO kitchen);

	KitchenDTO updateKitchen(Long id, KitchenDTO kitchen);

	void deleteKitchen(Long id);

}
