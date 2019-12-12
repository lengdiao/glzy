package com.ecard.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.RoleQr;
import com.ecard.service.GlzyPcService;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/glzyPc")
@CrossOrigin
@Api(tags = "GlzyPcController|PC端接口" )
public class GlzyPcController {
	
	@Autowired
	private GlzyPcService glzyPcService;
	
	@ApiOperation(value="后台登录接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "passWord", value = "密码", dataType = "String")
    })
	@PostMapping("/login")
	public Response drugStoreLogin(
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "passWord") String passWord) {
		Response response = glzyPcService.login(phone,passWord);
		return response;
	}
	
	//添加平台人员
	@ApiOperation(value="添加平台人员接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "sex", value = "性别", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "departmentNo", value = "所属部门Id", dataType = "Long")
    })
	@PostMapping("/addUser")
	public Response addUser(
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "sex") String sex,
			@RequestParam(value = "departmentNo") Long departmentNo) {
		Response response = glzyPcService.addUser(phone,name,sex,departmentNo);
		return response;
	}
	
	//修改平台人员
	@ApiOperation(value="修改平台人员接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "cloudPassNo", value = "云通行证号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "sex", value = "性别", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "departmentNo", value = "所属部门ID", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "启用标志", dataType = "Integer")
    })
	@PostMapping("/updateUser")
	public Response updateUser(
			@RequestParam(value = "cloudPassNo") Long cloudPassNo,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "sex") String sex,
			@RequestParam(value = "departmentNo") Long departmentNo,
			@RequestParam(value = "disableFlag") Integer disableFlag) {
		Response response = glzyPcService.updateUser(cloudPassNo,phone,name,sex,departmentNo,disableFlag);
		return response;
	}
	
	//查询平台人员
	@ApiOperation(value="查询平台人员接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "departName", value = "所属部门", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
	@PostMapping("/findUser")
	public Response findUser(
			@RequestParam(value = "phone", required=false) String phone,
			@RequestParam(value = "name", required=false) String name,
			@RequestParam(value = "departName", required=false) String departName,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findUser(phone,name,departName,page,rows);
		return response;
	}
	
	//添加机构
	@ApiOperation(value="添加机构接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mechanismName", value = "机构名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "启用标志", dataType = "Integer")
    })
	@PostMapping("/addMechanism")
	public Response addMechanism(
			@RequestParam(value = "mechanismName") String mechanismName,
			@RequestParam(value = "disableFlag") Integer disableFlag) {
		Response response = glzyPcService.addMechanism(mechanismName,disableFlag);
		return response;
	}
	
	//更新机构
	@ApiOperation(value="更新机构接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "id", value = "机构id", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "mechanismName", value = "机构名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "启用标志", dataType = "Integer")
    })
	@PostMapping("/updateMechanism")
	public Response updateMechanism(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "mechanismName") String mechanismName,
			@RequestParam(value = "disableFlag") Integer disableFlag) {
		Response response = glzyPcService.updateMechanism(id,mechanismName,disableFlag);
		return response;
	}
	
	//查询机构
	@ApiOperation(value="查询机构")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mechanismName", value = "机构名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
	@PostMapping("/findMechanism")
	public Response findMechanism(
			@RequestParam(value = "mechanismName", required=false) String mechanismName,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findMechanism(mechanismName,page,rows);
		return response;
	}
	
	//新增部门
	@ApiOperation(value="新增部门")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mechanismId", value = "机构编号", dataType = " mechanismId"),
        @ApiImplicitParam(paramType="query", name = "name", value = "部门名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/addDepartment")
	public Response addDepartment(
			@RequestParam(value = "mechanismId") Long mechanismId,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "disableFlag") Integer disableFlag) {
		Response response = glzyPcService.addDepartment(mechanismId,name,disableFlag);
		return response;
	}
	
	//修改部门
	@ApiOperation(value="修改部门")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "id", value = "部门编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "name", value = "部门名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/updateDepartment")
	public Response updateDepartment(
			@RequestParam(value = "id") Long id,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "disableFlag") Integer disableFlag) {
		Response response = glzyPcService.updateDepartment(id,name,disableFlag);
		return response;
	}
	
	//查询部门
	@ApiOperation(value="查询部门")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mechanismName", value = "机构名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "departmentName", value = "部门名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示条数", dataType = "Integer")
    })
	@PostMapping("/findDepartment")
	public Response findDepartment(
			@RequestParam(value = "mechanismName", required=false) String mechanismName,
			@RequestParam(value = "departmentName", required=false) String departmentName,
			@RequestParam(value = "disableFlag", required=false) Integer disableFlag,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findDepartment(mechanismName,departmentName,disableFlag,page,rows);
		return response;
	}
	
	//新增药店
	@ApiOperation(value="新增药店")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "contact", value = "负责人", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "contactPhone", value = "负责人手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/addDrugStore")
	public Response addDrugStore(
			@RequestParam(value = "drugStoreName") String drugStoreName,
			@RequestParam(value = "contact") String contact,
			@RequestParam(value = "contactPhone") String contactPhone,
			@RequestParam(value="address") String address,
    		@RequestParam(value="disableFlag") Integer disableFlag) {
		Response response = glzyPcService.addDrugStore(drugStoreName,contact,contactPhone,address,disableFlag);
		return response;
	}
	
	//更新药店
	@ApiOperation(value="更新药店")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "药店编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "contact", value = "负责人", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "contactPhone", value = "负责人手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "oldCode", value = "原手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/updateDrugStore")
	public Response updateDrugStore(
			@RequestParam(value = "drugStoreNo") Long drugStoreNo,
			@RequestParam(value = "drugStoreName") String drugStoreName,
			@RequestParam(value = "contact") String contact,
			@RequestParam(value = "contactPhone") String contactPhone,
			@RequestParam(value = "oldCode") String oldCode,
			@RequestParam(value="address") String address,
    		@RequestParam(value="disableFlag") Integer disableFlag) {
		Response response = glzyPcService.updateDrugStore(drugStoreNo,drugStoreName,contact,contactPhone,address,disableFlag,oldCode);
		return response;
	}
	
	//药店列表
	@ApiOperation(value="药店列表")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "drugStoreName", value = "药店名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
    })
	@PostMapping("/findDrugStore")
	public Response findDrugStore(
			@RequestParam(value = "drugStoreName", required=false) String drugStoreName,
    		@RequestParam(value="disableFlag", required=false) Integer disableFlag,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findDrugStore(drugStoreName,disableFlag,page,rows);
		return response;
	}
	
	/**
	 * 查询角色列表
	 * @return
	 */
	@ApiOperation(value="查询角色列表")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "roleName", value = "角色名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
    })
	@GetMapping("/list")
	public Response roleList(
			@RequestParam(value="roleName" ,required=false) String roleName,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.list(roleName,page,rows);
		return response;
	}
	
	/**
	 * 查询角色详情
	 * @param roleNo
	 * @return
	 */
	@ApiOperation(value="查询角色详情")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "roleNo", value = "角色编号", dataType = "String")
    })
	@GetMapping("/selectByNo")
	public Response selectByNo(@RequestParam("roleNo") String roleNo) {
		Response response = glzyPcService.selectByNo(roleNo);
		return response;
	}
	
	/**
	 * 新增角色
	 * @param roleQr
	 * @return
	 */
	@ApiOperation(value=" 新增角色")
	@PostMapping("/save")
	public Response save(@RequestBody RoleQr roleQr) {
		Response response = glzyPcService.save(roleQr);
		return response;
	}
	
	/**
	 * 修改角色
	 * @param roleQr
	 * @return
	 */
	@ApiOperation(value="修改角色")
	@PostMapping("/update")
	public Response update(@RequestBody RoleQr roleQr) {
		Response response = glzyPcService.update(roleQr);
		return response;
	}
	
	/**
	 * 删除角色
	 * @param roleNo
	 * @return
	 */
	@ApiOperation(value="删除角色")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "roleNo", value = "角色编号", dataType = "Long")
    })
	@PostMapping("/delete")
	public Response delete(@RequestParam("roleNo") Long roleNo) {
		Response response = glzyPcService.delete(roleNo);
		return response;
	}
	
	/**
	 * 查询所有权限
	 * @param roleNo
	 * @return
	 */
	@ApiOperation(value="查询所有权限")
	@PostMapping("/findAllAuthority")
	public Response findAllAuthority() {
		Response response = glzyPcService.findAllAuthority();
		return response;
	}
	
	
	
	//添加药店人员
	@ApiOperation(value="添加药店人员")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "药店人员名字", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "药店人员手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "所属药店编号", dataType = "Long")
    })
	@PostMapping("/addDrugStorePerson")
	public Response addDrugStorePerson(
			@RequestParam(value ="name") String name,
			@RequestParam(value ="phone") String phone,
			@RequestParam(value ="drugStoreNo") Long drugStoreNo) {
		Response response = glzyPcService.addDrugStorePerson(name,phone,drugStoreNo);
		return response;
	}
	
	//修改药店人员
	@ApiOperation(value="修改药店人员")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "id", value = "药店人员编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "cloudPassNo", value = "云通行证号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "name", value = "人员姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "人员手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "所属药店编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "oldCode", value = "原手机号码", dataType = "String")
    })
	@PostMapping("/updateDrugStorePerson")
	public Response updateDrugStorePerson(
			@RequestParam(value ="id") Long id,
			@RequestParam(value ="cloudPassNo") Long cloudPassNo,
			@RequestParam(value ="name") String name,
			@RequestParam(value ="phone") String phone,
			@RequestParam(value ="drugStoreNo") Long drugStoreNo,
			@RequestParam(value ="disableFlag") Integer disableFlag,
			@RequestParam(value ="oldCode") String oldCode) {
		Response response = glzyPcService.updateDrugStorePerson(id,cloudPassNo,name,phone,drugStoreNo,oldCode,disableFlag);
		return response;
	}
	
	//查询药店人员
	@ApiOperation(value="查询药店人员")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "药店人员名字", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "人员电话号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "所属药店编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
    })
	@PostMapping("/findDrugStorePerson")
	public Response findDrugStorePerson(
			@RequestParam(value ="name", required=false) String name,
			@RequestParam(value ="phone", required=false) String phone,
			@RequestParam(value ="drugStoreNo", required=false) Long drugStoreNo,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findDrugStorePerson(name,phone,drugStoreNo,page,rows);
		return response;
	}
	
	//新增患者
	@ApiOperation(value="新增患者")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "患者名字", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "cloudPassNo", value = "平台人员编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "idCard", value = "身份真号码", dataType = "String")
    })
	@PostMapping("/addPtInfo")
	public Response addPtInfo(
			@RequestParam(value ="name") String name,
			@RequestParam(value ="cloudPassNo") Long cloudPassNo,
			@RequestParam(value ="idCard") String idCard) {
		Response response = glzyPcService.addPtInfo(name,cloudPassNo,idCard);
		return response;
	}
	
	//修改患者
	@ApiOperation(value="修改患者")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "ptNo", value = "患者编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "cloudPassNo", value = "患者云通行证号码", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "name", value = "患者姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "cloudPassNo1", value = "平台人员编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "idCard", value = "身份真号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/updatePtInfo")
	public Response updatePtInfo(
			@RequestParam(value ="ptNo") Long ptNo,
			@RequestParam(value ="cloudPassNo") Long cloudPassNo,
			@RequestParam(value ="name") String name,
			@RequestParam(value ="cloudPassNo1") Long cloudPassNo1,
			@RequestParam(value ="idCard") String idCard,
			@RequestParam(value ="disableFlag",required=false) Integer disableFlag) {
		Response response = glzyPcService.updatePtInfo(ptNo,cloudPassNo,name,cloudPassNo1,idCard,disableFlag);
		return response;
	}
	
	//查询患者列表
	@PostMapping("/findPtInfo")
	@ApiOperation(value="查询患者列表")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "患者姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "cloudPassNo", value = "平台人员编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "idCard", value = "患者身份证号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	public Response findPtInfo(
			@RequestParam(value ="name", required=false) String name,
			@RequestParam(value ="cloudPassNo", required=false) Long cloudPassNo,
			@RequestParam(value ="idCard", required=false) String idCard,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows,
			@RequestParam(value ="disableFlag",required=false) Integer disableFlag) {
		Response response = glzyPcService.findPtInfo(name,cloudPassNo,idCard,page,rows,disableFlag);
		return response;
	}
	
	/**
	* 修改密码
	* @param telePhone
	* @param oldPassword
	* @param newPassword
	* @return
	*/
	@ApiOperation(value="修改密码")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "oldPassword", value = "旧密码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "newPassword", value = "新密码", dataType = "String")
    })
	@PostMapping("/updateByPhoneAndPass")
	public Response updateByPhoneAndPass(
			@RequestParam(value ="phone") String phone,
			@RequestParam(value ="oldPassword") String oldPassword,
			@RequestParam(value ="newPassword") String newPassword) {
		Response response = glzyPcService.updateByPhoneAndPass(phone,oldPassword,newPassword);
		return response;
	} 
	
	/**
	* 发送手机验证码
	* 
	* @param phone
	* @return
	*/
	@ApiOperation(value="发送手机验证码")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String")
    })
	@PostMapping(value = "/sednSms")
	public Response sendCodeSms(@RequestParam("phone") String phone) {
		Response response = glzyPcService.sendCodeSms(phone);
		return response;
	}
	
	/**
	     * 用户未登录，忘记密码 ---填写新密码
	* @param code  手机验证码
	* @param newCode
	* @return
	*/
	@ApiOperation(value="用户未登录，忘记密码 ---填写新密码")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "code", value = "验证码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "newCode", value = "新密码", dataType = "String")
    })
	@PostMapping("/updateByCode")
	public Response updateByCode(@RequestParam(value="phone",required=false) String phone,
			@RequestParam(value="code",required=false) String code,    		
			@RequestParam(value="newCode",required=false) String newCode) {
		Response response = glzyPcService.updateByCode(phone,code,newCode);
		return response;
	}
	
	//订单查询
	@PostMapping("/findMallOrder")
	@ApiOperation(value="订单查询")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "startTime", value = "开始时间", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "stopTime", value = "结束时间", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "checkStatus", value = "审核标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "shippingStatus", value = "配送标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "orderStatus", value = "订单状态", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
    })
	public Response findMallOrder(
			@RequestParam(value="mallNo",required=false) Long mallNo,
			@RequestParam(value="startTime",required=false) String startTime,    		
			@RequestParam(value="stopTime",required=false) String stopTime,
			@RequestParam(value="checkStatus",required=false) Integer checkStatus,
			@RequestParam(value="shippingStatus",required=false) Integer shippingStatus,
			@RequestParam(value="orderStatus",required=false) Integer orderStatus,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findMallOrder(mallNo,startTime,stopTime,checkStatus,
				shippingStatus,orderStatus,page,rows);
		return response;
	}
	
	//新增产品
	@ApiOperation(value="新增产品")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "drugName", value = "产品名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "describe", value = "产品描述", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugPrice", value = "产品单价", dataType = "BigDecimal"),
        @ApiImplicitParam(paramType="query", name = "unit", value = "包装单位", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "discount", value = "优惠方案(B3|P2:买3付2)", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/addDrugSet")
	public Response addDrugSet(@RequestParam(value="drugName") String drugName,
			@RequestParam(value="describe") String describe,    		
			@RequestParam(value="drugPrice") BigDecimal drugPrice,
			@RequestParam(value="unit") String unit,
			@RequestParam(value="discount") String discount,
			@RequestParam(value="disableFlag") Integer disableFlag) {
		Response response = glzyPcService.addDrugSet(drugName,describe,drugPrice,unit,
				discount,disableFlag);
		return response;
	}
	
	//添加产品图片
	@ApiOperation(value="新增产品图片")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "img", value = "图片", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "name", value = "图片名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "id", value = "产品编号（编辑时必传）", dataType = "Long")
    })
	@PostMapping("/addImg")
	public Response addImg(
			@RequestParam(value = "img") String img,
			@RequestParam(value = "name") String name,
			@RequestParam(value = "id" ,required = false , defaultValue="0") int id) {
		Response response = glzyPcService.addImg(img,name,id);
		return response;
	}
	
	//修改产品
	@ApiOperation(value="修改产品")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "drugNo", value = "产品编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "drugName", value = "产品名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "describe", value = "产品描述", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugPrice", value = "产品单价", dataType = "BigDecimal"),
        @ApiImplicitParam(paramType="query", name = "unit", value = "包装单位", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "discount", value = "优惠方案", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer")
    })
	@PostMapping("/updateDrugSet")
	public Response updateDrugSet(
			@RequestParam(value="drugNo") Long drugNo,
			@RequestParam(value="drugName") String drugName,
			@RequestParam(value="describe") String describe,    		
			@RequestParam(value="drugPrice") BigDecimal drugPrice,
			@RequestParam(value="unit") String unit,
			@RequestParam(value="discount") String discount,
			@RequestParam(value="disableFlag") Integer disableFlag) {
		Response response = glzyPcService.updateDrugSet(drugNo,drugName,describe,drugPrice,unit,
				discount,disableFlag);
		return response;
	}
	
	//更新产品图片
	@PostMapping("/updateImg")
	public Response updateImg(
			@RequestParam(value="drugNo") Long drugNo,
			@RequestParam(value = "img") String img,
			@RequestParam(value = "name") String name) {
		Response response = glzyPcService.updateImg(drugNo,img,name);
		return response;
	}
	
	//查询产品列表
	@ApiOperation(value="查询产品列表")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "drugName", value = "产品名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "rows", value = "每页显示条数", dataType = "Integer")
    })
	@PostMapping("/findDrugSet")
	public Response findDrugSet(
			@RequestParam(value="drugName", required=false) String drugName,
			@RequestParam(value="disableFlag", required=false) Integer disableFlag,
			@RequestParam(value="page", required=false, defaultValue="1") Integer page,
    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
		Response response = glzyPcService.findDrugSet(drugName,disableFlag,page,rows);
		return response;
	}
	
	
	//订单编辑
	@ApiOperation(value="订单编辑")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mallNo", value = "产品编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "shippingCompany", value = "配送公司", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "shippingNo", value = "快递单号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugStoreNo", value = "药店编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "drugStorePerson", value = "药店负责人编号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "checkStatus", value = "审核标志", dataType = "Integer"),
        @ApiImplicitParam(paramType="query", name = "cause", value = "未通过原因", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugA", value = "赠品1数量", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "drugB", value = "赠品2数量", dataType = "String")
    })
	@PostMapping("/updateMallOrder")
	public Response updateMallOrderByCode(
			@RequestParam(value="mallNo") Long mallNo,
			@RequestParam(value="shippingCompany",required=false) String shippingCompany,
			@RequestParam(value="shippingNo",required=false) String shippingNo,
			@RequestParam(value="drugStoreNo",required=false) Long drugStoreNo,
			@RequestParam(value="drugStorePerson",required=false) String drugStorePerson,
			@RequestParam(value="checkStatus",required=false) Integer checkStatus,
			@RequestParam(value="cause",required=false) String cause,
			@RequestParam(value="drugA",required=false) String drugA,
			@RequestParam(value="drugB",required=false) String drugB) {
		Response response = glzyPcService.updateMallOrder(mallNo,shippingCompany,shippingNo,drugStoreNo,drugStorePerson,
				checkStatus,cause,drugA,drugB);
		return response;
	}
	
	
	//添加厂家人员
	@ApiOperation(value="添加厂家人员")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "厂家人员名称", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "厂家人员电话", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "sex", value = "厂家人员性别", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "mechanismNo", value = "所属机构编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "departmentNo", value = "所属部门编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标识（0：启用，1：停用）", dataType = "Integer")
    })
	@PostMapping("/addFactoryPerson")
	public Response addFactoryPerson(
			@RequestParam(value ="name") String name,
			@RequestParam(value ="phone") String phone,
			@RequestParam(value ="sex") String sex,
			@RequestParam(value ="mechanismNo") Long mechanismNo,
			@RequestParam(value ="departmentNo") Long departmentNo,
			@RequestParam(value ="disableFlag") Integer disableFlag) {
		Response response = glzyPcService.addFactoryPerson(name,phone,sex,mechanismNo,departmentNo,departmentNo,disableFlag);
		return response;
	}
	
	
	
		//更新厂家人员
		@ApiOperation(value="更新厂家人员")
		@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "id", value = "厂家人员Id", dataType = "Long"),
	        @ApiImplicitParam(paramType="query", name = "name", value = "厂家人员名称", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "phone", value = "厂家人员电话", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "sex", value = "厂家人员性别", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "mechanismNo", value = "所属机构编号", dataType = "Long"),
	        @ApiImplicitParam(paramType="query", name = "departmentNo", value = "所属部门编号", dataType = "Long"),
	        @ApiImplicitParam(paramType="query", name = "disableFlag", value = "停用标识（0：启用，1：停用）", dataType = "Integer")
	    })
		@PostMapping("/updateFactoryPerson")
		public Response updateFactoryPerson(
				@RequestParam(value ="id") Long id,
				@RequestParam(value ="name") String name,
				@RequestParam(value ="phone") String phone,
				@RequestParam(value ="sex") String sex,
				@RequestParam(value ="mechanismNo") Long mechanismNo,
				@RequestParam(value ="departmentNo") Long departmentNo,
				@RequestParam(value ="disableFlag") Integer disableFlag) {
			Response response = glzyPcService.updateFactoryPerson(id,name,phone,sex,mechanismNo,departmentNo,disableFlag);
			return response;
		}
		
		
		//查询厂家人员
		@ApiOperation(value="查询厂家人员")
		@ApiImplicitParams({
	        @ApiImplicitParam(paramType="query", name = "name", value = "厂家人员名字", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "phone", value = "人员电话号", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "mechanismNo", value = "所属机构编号", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "departmentNo", value = "所属部门编号", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "page", value = "当前页", dataType = "Integer"),
	        @ApiImplicitParam(paramType="query", name = "rows", value = "显示个数", dataType = "Integer")
	    })
		@PostMapping("/findFactoryperson")
		public Response findFactoryperson(
				@RequestParam(value ="name", required=false) String name,
				@RequestParam(value ="phone", required=false) String phone,
				@RequestParam(value ="mechanismNo", required=false) Long mechanismNo,
				@RequestParam(value ="departmentNo", required=false) Long departmentNo,
				@RequestParam(value="page", required=false, defaultValue="1") Integer page,
	    		@RequestParam(value="rows", required=false, defaultValue="10") Integer rows) {
			Response response = glzyPcService.findFactoryperson(name,phone,mechanismNo,departmentNo,page,rows);
			return response;
		}
	
}
