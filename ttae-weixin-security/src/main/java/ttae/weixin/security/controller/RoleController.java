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
import ttae.weixin.security.criteria.RoleCriteria;
import ttae.weixin.security.model.Role;
import ttae.weixin.security.service.RoleService;
import ttae.weixin.utils.ResultUtil;


@RestController
@RequestMapping("/role")
@PreAuthorize("hasRole('ADMIN')")
@Api(tags="角色管理")
public class RoleController {

	@Autowired RoleService roleService;
	
	@GetMapping
	@ApiOperation(value = "获取角色")
	public Result<?> findRole(RoleCriteria criteria) {
		List<Role> list = roleService.findAll(criteria);
		return ResultUtil.ok(list);
	}
	
	@PutMapping
	@ApiOperation(value = "更新角色")
	public Result<?> saveRole(@RequestBody Role role){
		roleService.save(role);
		return ResultUtil.ok();
	}
	
	@PostMapping
	@ApiOperation(value = "创建角色")
	public Result<?> addRole(@RequestBody Role role){
		roleService.save(role);
		return ResultUtil.ok();
	}
	
	@DeleteMapping
	@ApiOperation(value = "删除角色")
	public Result<?> delRole(@RequestBody Role role){
		roleService.delete(role.getId());
		return ResultUtil.ok();
	}
}
