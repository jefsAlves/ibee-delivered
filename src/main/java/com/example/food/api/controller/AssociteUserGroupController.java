package com.example.food.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.services.AssociateGroupUserService;

@RestController
@RequestMapping(value = "/user/{userId}")
public class AssociteUserGroupController {

	@Autowired
	private AssociateGroupUserService associateUserGroupService;

//	@GetMapping(value = "/groups")
//	public ResponseEntity<List<GroupsDTO>> listGroups(@PathVariable Long userId) {
//		return new ResponseEntity<>(associateUserGroupService.listGroups(userId), HttpStatus.OK);
//	}

	@PutMapping(value = "/groups/{groupId}")
	public ResponseEntity<Void> associateUsertoGroup(@PathVariable Long userId, @PathVariable Long groupId) {
		associateUserGroupService.associateGroupToUser(userId, groupId);
		return ResponseEntity.noContent().build();
	}
	
	@DeleteMapping(value = "/group/{groupId}")
	public ResponseEntity<Void> desassociateUserToGroup(@PathVariable Long userId, @PathVariable Long groupId) {
		associateUserGroupService.desassociateGroupToUser(userId, groupId);	
		return ResponseEntity.noContent().build();
	}

}
