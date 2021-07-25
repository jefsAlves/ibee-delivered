package com.example.food.model.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.Groups;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.model.repository.GroupsRepository;

@Component
public class GroupsUtil {

	@Autowired
	private GroupsRepository groupsRepository;

	public Groups verifyGroupsExist(Long groupsId) {
		Optional<Groups> groups = groupsRepository.findById(groupsId);
		return groups.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

}
