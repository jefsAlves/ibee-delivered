package com.example.food.model.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.food.model.dto.GroupsDTO;
import com.example.food.model.services.GroupsService;

@RestController
@RequestMapping(value = "/groups")
public class GroupsController {

	@Autowired
	private GroupsService groupsService;

	@GetMapping
	public ResponseEntity<List<GroupsDTO>> listsGroups() {
		return new ResponseEntity<>(groupsService.listGroups(), HttpStatus.OK);
	}

	@GetMapping(value = "/{groupId}")
	public ResponseEntity<GroupsDTO> searchGroup(@PathVariable Long groupId) {
		return new ResponseEntity<>(groupsService.searchGroups(groupId), HttpStatus.OK);
	}

}
