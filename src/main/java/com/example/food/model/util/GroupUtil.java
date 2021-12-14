package com.example.food.model.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.food.model.entities.Group;
import com.example.food.model.exceptions.IdNotFoudException;
import com.example.food.infra.repository.GroupRepository;

@Component
public class GroupUtil {

	@Autowired
	private GroupRepository groupsRepository;

	public Group verifyGroupsExist(Long groupsId) {
		Optional<Group> groups = groupsRepository.findById(groupsId);
		return groups.orElseThrow(() -> new IdNotFoudException(MessageUtil.ID_NOT_FOUND));
	}

}
