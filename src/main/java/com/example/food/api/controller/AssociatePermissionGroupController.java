package com.example.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.api.dto.GroupDTO;
import com.example.food.model.services.AssociatePermissionGroupService;

@RestController
@RequestMapping(value = "/group/{groupId}")
public class AssociatePermissionGroupController {

	@Autowired
	private AssociatePermissionGroupService accessAssociateService;
	
	@GetMapping(value = "/permission")
	public ResponseEntity<List<GroupDTO>> searchGroupPermission(@PathVariable Long groupId) {
		return new ResponseEntity<>(accessAssociateService.listPermissionGroup(groupId), HttpStatus.OK);
	}

	@PutMapping(value = "permission/{permissionId}")
	public ResponseEntity<Void> associatePermissionGroup(@PathVariable Long groupId, @PathVariable Long permissionId) {
		accessAssociateService.associatePermissionToGroup(groupId, permissionId);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "permission/{permissionId}")
	public ResponseEntity<Void> desassociatePermissionToGroup(@PathVariable Long groupId, @PathVariable Long permissionId) {
		accessAssociateService.desassociatePermissiontoGroup(groupId, permissionId);
		return ResponseEntity.noContent().build();
	}

}
