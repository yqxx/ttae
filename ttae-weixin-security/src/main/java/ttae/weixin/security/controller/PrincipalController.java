package ttae.weixin.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import ttae.weixin.bean.Result;
import ttae.weixin.security.criteria.PrincipalCriteria;
import ttae.weixin.security.model.Principal;
import ttae.weixin.security.service.PrincipalService;
import ttae.weixin.utils.ResultUtil;

@RestController
@RequestMapping("/principal")
@PreAuthorize("hasRole('ADMIN')")
@Api(tags="用户管理")
public class PrincipalController {

	@Autowired PrincipalService principalService;

	@GetMapping
	@ApiOperation(value = "获取用户信息")
	public Result<?> findPrincipal(PrincipalCriteria criteria) {
		List<Principal> list = principalService.findAll(criteria);
		return ResultUtil.ok(list);
	}
	
	@PutMapping
	@ApiOperation(value = "更新用户信息")
	public Result<?> updataPrincipal(Principal principal){
		principalService.save(principal);
		return ResultUtil.ok();
	}
	
	@PostMapping
	@ApiOperation(value = "创建用户信息")
	public Result<?> addPrincipal(Principal principal){
		principalService.save(principal);
		return ResultUtil.ok();
	}
	
	@DeleteMapping
	@ApiOperation(value = "删除用户信息")
	public Result<?> delPrincipal(Long id){
		principalService.delete(id);
		return ResultUtil.ok();
	}
}
