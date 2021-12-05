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

import com.example.food.api.dto.PermissionsDTO;
import com.example.food.model.services.PermissionsService;

@RestController
@RequestMapping(value = "/permissions")
public class PermissionsController {

	@Autowired
	private PermissionsService permissionsService;

	@GetMapping(value = "/{permissionId}")
	public ResponseEntity<PermissionsDTO> searchPermissions(@PathVariable Long permissionId) {
		return new ResponseEntity<>(permissionsService.searchPermission(permissionId), HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<PermissionsDTO>> listPermissions() {
		return new ResponseEntity<>(permissionsService.listPermissions(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<PermissionsDTO> createPermissions(@RequestBody PermissionsDTO permissionsDTO) {
		return new ResponseEntity<>(permissionsService.createPermission(permissionsDTO), HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/{permissionId}")
	public ResponseEntity<PermissionsDTO> updatePermissions(@PathVariable Long permissionId, @RequestBody PermissionsDTO permissionsDTO) {
		return new ResponseEntity<>(permissionsService.updatePermission(permissionId, permissionsDTO), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{permissionId}")
	public ResponseEntity<Void> deletePermission(@PathVariable Long permissionId) { 
		permissionsService.deletePermission(permissionId);
		return ResponseEntity.noContent().build();
	}

}
