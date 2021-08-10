package com.example.food.model.services;

public interface AssociateGroupUserService {

//	List<GroupsDTO> listGroups(Long groupId);

	void associateGroupToUser(Long userId, Long groupId);

	void desassociateGroupToUser(Long userId, Long groupId);

}
