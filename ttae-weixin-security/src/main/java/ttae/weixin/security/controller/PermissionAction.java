package ttae.weixin.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ttae.weixin.security.criteria.PermissionCriteria;
import ttae.weixin.security.http.ResponseEntity;
import ttae.weixin.security.model.Permission;
import ttae.weixin.security.service.PermissionService;


@RestController
@RequestMapping("/permission")
public class PermissionAction {

	@Autowired PermissionService permissionService;
	
	@GetMapping
	public List<Permission> findPermission(PermissionCriteria criteria) {
		return permissionService.findAll(criteria);
	}
	
	@PutMapping
	public ResponseEntity<?> updataPermission(Permission permission){
		permissionService.save(permission);
		return ResponseEntity.ok();
	}
	
	@PostMapping
	public ResponseEntity<?> addPermission(Permission permission){
		permissionService.save(permission);
		return ResponseEntity.ok();
	}
	
	@DeleteMapping
	public ResponseEntity<?> addPermission(Long id){
		permissionService.delete(id);
		return ResponseEntity.ok();
	}
}
