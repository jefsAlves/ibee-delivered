package com.example.food.model.services;

import java.util.List;

import com.example.food.api.dto.KitchenDTO;
import com.example.food.model.entities.Kitchen;

public interface KitchenService {

	KitchenDTO searchKitchen(Long id);

	List<KitchenDTO> listKitchens(KitchenDTO kitchen);

	KitchenDTO createKitchen(KitchenDTO kitchen);

//	Kitchen createKitchen(Kitchen kitchen);

	KitchenDTO updateKitchen(Long id, KitchenDTO kitchen);

	void deleteKitchen(Long id);

}
