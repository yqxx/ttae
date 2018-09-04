package ttae.weixin.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ttae.weixin.bean.Result;
import ttae.weixin.security.criteria.PermissionCriteria;
import ttae.weixin.security.model.Permission;
import ttae.weixin.security.service.PermissionService;
import ttae.weixin.utils.ResultUtil;


@RestController
@RequestMapping("/permission")
@PreAuthorize("hasRole('ADMIN')")
@Api(tags="权限管理")
public class PermissionController {

	@Autowired PermissionService permissionService;
	
	@GetMapping
	@ApiOperation(value = "获取权限描述")
	public Result<?> findPermission(PermissionCriteria criteria) {
		List<Permission> list = permissionService.findAll(criteria);
		return ResultUtil.ok(list);
	}
	
	@PutMapping
	@ApiOperation(value = "更新权限描述")
	public Result<?> savePermission(@RequestBody Permission permission){
		permissionService.save(permission);
		return ResultUtil.ok();
	}
	
	@PostMapping
	@ApiOperation(value = "创建权限描述")
	public Result<?> addPermission(@RequestBody Permission permission){
		permissionService.save(permission);
		return ResultUtil.ok();
	}
	
	@DeleteMapping
	@ApiOperation(value = "删除权限描述")
	public Result<?> delPermission(@RequestBody Permission permission){
		permissionService.delete(permission.getId());
		return ResultUtil.ok();
	}
}
