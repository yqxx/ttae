package ttae.mp.restful.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ttae.weixin.security.service.AuthService;

@RestController
@RequestMapping("/user")
@Api(tags="用户管理")
public class UserController {

	@Autowired
	AuthService principalService;

	@GetMapping
	@PreAuthorize("hasRole('RESTFUL')")
	@ApiOperation(value = "RESTFUL角色判断")
	public String user() {
		return "user success";
	}
	
	@GetMapping("/dep/1")
	@PreAuthorize("hasPermission('M001', 1)")
	@ApiOperation(value = "M001 列表权限")
	public String dep1() {
		return "dep1 success";
	}
	
	@GetMapping("/dep/2")
	@PreAuthorize("hasPermission('M001', 2)")
	@ApiOperation(value = "M001 导出权限")
	public String dep2() {
		return "dep2 success";
	}
	
	@GetMapping("/dep/3")
	@PreAuthorize("hasPermission('M001', 4)")
	@ApiOperation(value = "M001 编辑权限")
	public String dep3() {
		return "dep3 success";
	}
	
	@GetMapping("/dep/4")
	@PreAuthorize("hasPermission('M001', 8)")
	@ApiOperation(value = "M001 删除权限")
	public String dep4() {
		return "dep4 success";
	}
}
