package com.ecard.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.NoRouteToHostException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ecard.entity.WeiXinUser;
import com.ecard.pojo.Response;
import com.ecard.pojo.queryResult.RoleQr;
import com.ecard.service.GlzyPcService;
import com.ecard.service.GlzyWxService;
import com.ecard.service.WxPayService;
import com.ecard.utils.GetOpenIdUtils;
import com.ecard.utils.WeiXinUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/glzyWx")
@CrossOrigin
@Api(tags = "GlzyWxController|微信端接口")
public class GlzyWxController {
	@Autowired
	private GlzyWxService glzyWxService;
	@Autowired
	private WxPayService wxPayService;
	
	//订单录入
	@PostMapping("/addMallOrder")
	@ApiOperation(value="订单录入")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "患者姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "患者电话号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "idCard", value = "患者身份证号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "addressName", value = "收货人姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "addressPhone", value = "收货人手机号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "province", value = "省份", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "city", value = "市", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "area", value = "区", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "volunteerName", value = "志愿者姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "volPhone", value = "志愿者电话号码", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "code", value = "wxCode", dataType = "String")
    })
	public Response addMallOrder(
			@RequestParam(value = "name") String name,
			@RequestParam(value = "phone") String phone,
			@RequestParam(value = "idCard") String idCard,
			@RequestParam(value = "addressName") String addressName,
			@RequestParam(value = "addressPhone") String addressPhone,
			@RequestParam(value = "province") String province,
			@RequestParam(value = "city") String city,
			@RequestParam(value = "area") String area,
			@RequestParam(value = "address") String address,
			@RequestParam(value = "volunteerName") String volunteerName,
			@RequestParam(value = "volPhone") String volPhone,
			@RequestParam(value = "code") String code) {
		Response response = glzyWxService.addMallOrder(name,phone,idCard,addressName,
				addressPhone,province,city,area,address,volunteerName,volPhone,code);
		return response;
	}
	
	
	//gengxing订单
		@PostMapping("/updateMallOrder")
		@ApiOperation(value="更新订单")
		@ApiImplicitParams({
			@ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
	        @ApiImplicitParam(paramType="query", name = "name", value = "患者姓名", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "phone", value = "患者电话号码", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "idCard", value = "患者身份证号码", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "addressName", value = "收货人姓名", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "addressPhone", value = "收货人手机号码", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "province", value = "省份", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "city", value = "市", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "area", value = "区", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "address", value = "地址", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "volunteerName", value = "志愿者姓名", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "volPhone", value = "志愿者电话号码", dataType = "String"),
	        @ApiImplicitParam(paramType="query", name = "code", value = "wxCode", dataType = "String")
	    })
		public Response updateMallOrder(
				@RequestParam(value = "mallNo") Long mallNo,
				@RequestParam(value = "name") String name,
				@RequestParam(value = "phone") String phone,
				@RequestParam(value = "idCard") String idCard,
				@RequestParam(value = "addressName") String addressName,
				@RequestParam(value = "addressPhone") String addressPhone,
				@RequestParam(value = "province") String province,
				@RequestParam(value = "city") String city,
				@RequestParam(value = "area") String area,
				@RequestParam(value = "address") String address,
				@RequestParam(value = "volunteerName") String volunteerName,
				@RequestParam(value = "volPhone") String volPhone,
				@RequestParam(value = "code") String code) {
			Response response = glzyWxService.updateMallOrder(mallNo,name,phone,idCard,addressName,
					addressPhone,province,city,area,address,volunteerName,volPhone,code);
			return response;
		}
		
	
	//上传图片凭证
	@PostMapping("/addImg")
	@ApiOperation(value="上传图片凭证")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "img", value = "文件", dataType = "File"),
        @ApiImplicitParam(paramType="query", name = "name", value = "名称", dataType = "String")
    })
	public Response addImg(
			@RequestParam(value = "img") MultipartFile file,
			@RequestParam(value = "name") String name) {
		Response response = glzyWxService.addImg(file,name);
		return response;
	}
	
	
	//录入订单商品
	@ApiOperation(value="录入订单商品")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "drugNo", value = "商品编号", dataType = "Long"),
        @ApiImplicitParam(paramType="query", name = "number", value = "购买数量", dataType = "int"),
        @ApiImplicitParam(paramType="query", name = "orderAmount", value = "订单金额", dataType = "BigDecimal")
    })
	@PostMapping("/addDrugSet")
	public Response addDrugSet(
			@RequestParam(value = "mallNo") Long mallNo,
			@RequestParam(value = "drugNo") Long drugNo,
			@RequestParam(value = "number") int number) {
		Response response = glzyWxService.addDrugSet(mallNo,drugNo,number);
		return response;
	}
	
	//订单查询
	@ApiOperation(value="订单查询")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "name", value = "姓名", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "phone", value = "手机号", dataType = "String"),
        @ApiImplicitParam(paramType="query", name = "code", value = "验证码", dataType = "String")
    })
	@PostMapping("/findMallOrderByCode")
	public Response findMallOrderByCode(
			@RequestParam(value="name",required=false) String name,    		
			@RequestParam(value="phone",required=false) String phone,
			@RequestParam(value="code",required=false) String code) {
		Response response = glzyWxService.findMallOrderByCode(name,phone,code);
		return response;
	}
	
	
	//查看是否有未填写完订单
	@ApiOperation(value="查看是否有未填写完订单")
	@PostMapping("/findMallOrderByOpenId")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "code", value = "wxCode", dataType = "String")
    })
	public Response findMallOrderByOpenId(@RequestParam(value="code") String code) {
		Response response = glzyWxService.findMallOrderByOpenId(code);
		return response;
	}
	
	
	//微信支付
	@ApiOperation(value="支付接口")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "String")
    })
	@PostMapping("/wxPay")
	public void wxPay(
			@RequestParam(value="mallNo") String mallNo) throws Exception {
		wxPayService.wxPay(mallNo);
	}
	
	//微信支付
		@ApiOperation(value="支付接口")
		@ApiImplicitParams({
	        @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "String")
	    })
		@PostMapping("/wxPay1")
		public Response wxPay1(
				@RequestParam(value="mallNo") String mallNo) throws Exception {
			return wxPayService.wxPay1(mallNo);
		}
	
	//支付成功通知
	@ApiOperation(value="支付成功通知")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "mallNo", value = "订单编号", dataType = "String")
    })
	@PostMapping("/notice")
	public Response notice(@RequestParam(value="mallNo") String mallNo) throws Exception {
		return glzyWxService.notice(mallNo);
	}
	
	
	
	
	
	
	
	
	/**
        * 进行网页授权，便于获取到用户的绑定的内容
	* @param request
	* @param session
	* @param map
	* @return
	*/
	@RequestMapping("/tologin/userinfo")
	public void check(@RequestParam(value="status") int status) {
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getResponse();
		HttpSession session = request.getSession(); 
	   //首先判断一下session中，是否有保存着的当前用户的信息，有的话，就不需要进行重复请求信息
	   WeiXinUser  weiXinUser = null ;
	   if(session.getAttribute("currentUser") != null){
	       weiXinUser = (WeiXinUser) session.getAttribute("currentUser");
	       System.out.println("session中存在openId");
	       glzyWxService.selectByOpenId(status);
	   }else {
	       /**
	        * 进行获取openId，必须的一个参数，这个是当进行了授权页面的时候，再重定向了我们自己的一个页面的时候，
	        * 会在request页面中，新增这个字段信息，要结合这个ProjectConst.Get_WEIXINPAGE_Code这个常量思考
	        */
	       String code = request.getParameter("code");
	       System.out.println("code"+code);
	       try {
	           //得到当前用户的信息(具体信息就看weixinUser这个javabean)
	           weiXinUser = getTheCode(session, code);
	           //将获取到的用户信息，放入到session中
	           session.setAttribute("currentUser", weiXinUser);
	           
	           glzyWxService.selectByOpenId(status);
	           
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }
	  // map.put("weiXinUser", weiXinUser);
	}
	
	/**
	* 获取用户的openId
	* @param session
	* @param code
	* @return 返回封装的微信用户的对象
	*/
	private WeiXinUser getTheCode(HttpSession session, String code) {
	   Map<String , String>  authInfo = new HashMap<>();
	   String openId = "";
	   if (code != null)
	   {
	       // 调用根据用户的code得到需要的授权信息
	       authInfo= glzyWxService.getAuthInfo(code);
	      //获取到openId
	       openId = authInfo.get("Openid");
	       System.out.println("wxcontroller:openId:"+openId);
	   }
	   // 获取基础刷新的接口访问凭证（目前还没明白为什么用authInfo.get("AccessToken");这里面的access_token就不行）
	   String accessToken = WeiXinUtils.getAccessToken().getToken();
	   //获取到微信用户的信息
	   WeiXinUser userinfo = glzyWxService.getUserInfo(accessToken ,openId);
	
	   return userinfo;
	}  
	
	@GetMapping("/getSign")
	public Response getSign(@RequestParam(value="url") String url) {
		Response response = glzyWxService.getSign(url);
		return response;
	}
	
	
	//获取openid	
	@ApiOperation(value="获取openid")
	@PostMapping("/getOpenId")
	@ApiImplicitParams({
        @ApiImplicitParam(paramType="query", name = "code", value = "", dataType = "String")
    })
	public Response getOpenid(@RequestParam(value="code") String code) {
		return GetOpenIdUtils.getOpenid(code);
	}
	
}
