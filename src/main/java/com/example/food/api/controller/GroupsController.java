package com.example.food.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.api.dto.GroupDTO;
import com.example.food.model.services.GroupService;

@RestController
@RequestMapping(value = "/groups")
public class GroupsController {

	@Autowired
	private GroupService groupsService;

	@GetMapping
	public ResponseEntity<List<GroupDTO>> listsGroups() {
		return new ResponseEntity<>(groupsService.listGroups(), HttpStatus.OK);
	}

	@GetMapping(value = "/{groupId}")
	public ResponseEntity<GroupDTO> searchGroup(@PathVariable Long groupId) {
		return new ResponseEntity<>(groupsService.searchGroup(groupId), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<GroupDTO> createGroups(@RequestBody GroupDTO groupsDTO) {
		return new ResponseEntity<>(groupsService.createGroup(groupsDTO), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{groupsId}")
	public ResponseEntity<GroupDTO> updateGroups(@PathVariable Long groupsId, @RequestBody GroupDTO groupsDTO) {
		return new ResponseEntity<>(groupsService.updateGroup(groupsId, groupsDTO), HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping(value = "/{groupsId}")
	public ResponseEntity<Void> deleteGroups(@PathVariable Long groupsId) {
		groupsService.deleteGroup(groupsId);
		return ResponseEntity.noContent().build();
	}
	

}
