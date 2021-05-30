package com.example.food.model.services;

import java.util.List;

import com.example.food.model.entities.Kitchen;

public interface KitchenService {

	Kitchen searchKitchen(Long id);

	List<Kitchen> listKitchens(Kitchen kitchen);

	Kitchen createKitchen(Kitchen kitchen);

	Kitchen updateKitchen(Long id, Kitchen kitchen);

	void deleteKitchen(Long id);

}
