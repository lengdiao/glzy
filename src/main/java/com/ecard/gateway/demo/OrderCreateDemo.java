/**
 * Copyright : http://www.sandpay.com.cn , 2011-2014
 * Project : sandpay-gateway-demo
 * $Id$
 * $Revision$
 * Last Changed by pxl at 2018-4-25 下午8:15:41
 * $URL$
 * 
 * Change Log
 * Author      Change Date    Comments
 *-------------------------------------------------------------
 * pxl         2018-4-25        Initailized
 */
package com.ecard.gateway.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;
import com.ecard.cashier.sdk.CertUtil;
import com.ecard.cashier.sdk.SDKConfig;
import com.ecard.pojo.ResponseHasData;
import com.google.gson.Gson;

/**
 * 产品：杉德线上支付<br>
 * 交易：统一下单接口-银行网关支付<br>
 * 日期： 2018-04<br>
 * 版本： 1.0.0 
 * 说明：以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己需要，按照技术文档编写。该代码仅供参考。<br>
 */
public class OrderCreateDemo {

	public static  Logger logger = LoggerFactory.getLogger(OrderCreateDemo.class);
	
	public JSONObject header = new JSONObject();
	public JSONObject body=new JSONObject();
	
	//支付模式定义
	public enum PayMode {  
	    bank_pc,sand_h5, sand_wx, sand_alipay, sand_wxsdk, sand_wxh5,sand_upsdk
	}  
	
	
	public void setHeader(PayMode mode) {
		
		header.put("version", DemoBase.version);			//版本号
		header.put("method", DemoBase.ORDERPAY);			//接口名称:统一下单
		header.put("mid", SDKConfig.getConfig().getMid());	//商户ID
		String plMid=SDKConfig.getConfig().getPlMid();		//平台商户ID
		if(plMid!=null && StringUtils.isNotEmpty(plMid)) {  //平台商户存在时接入
			header.put("accessType", "2");					//接入类型设置为平台商户接入
			header.put("plMid", plMid);
		}else {
			header.put("accessType", "1");					//接入类型设置为平台商户接入												//接入类型设置为普通商户接入
		}		
		header.put("channelType", "07");					//渠道类型：07-互联网   08-移动端
		header.put("reqTime", DemoBase.getCurrentTime());	//请求时间
		String productId="";
		
		switch(mode) {			
			case  bank_pc:    	//银行网关支付
				productId="00000007";
				break;
			case  sand_h5:    	//银行卡H5支付
				productId="00000008";
				break;
			case  sand_wx:		//微信公众号支付
				productId="00000005";
				break;
			case  sand_alipay:	//支付宝服务窗支付
				productId="00000006";
				break;
			case  sand_wxsdk:	//微信APP支付
				productId="00000024";
				break;
			case  sand_wxh5:	//微信H5支付
				productId="00000025";
				break;	
			case  sand_upsdk:	//银联SDK
				productId="00000030";
				break;			
		
		}
		
		header.put("productId", productId);//产品编码,详见《杉德线上支付接口规范》 附录
		
		
	};
	
	
	public void setBody(PayMode mode,String orderCode,String totalAmount,String goodsbody,String subject,String clienIp,String notifyUrl,String frontUrl,String openId,String appId) {
		//DemoBase.getOrderCode()
		body.put("orderCode",orderCode );                           //商户订单号
		body.put("totalAmount", totalAmount);                                  //订单金额
		body.put("subject", subject);                                             //订单标题
		body.put("body", goodsbody);                                         //订单描述 
		body.put("txnTimeOut", DemoBase.getNextDayTime());                        //订单超时时间
		body.put("clientIp", clienIp);                                    //客户端IP
		body.put("notifyUrl", notifyUrl);                         //异步通知地址  
		body.put("frontUrl", frontUrl);                     //前台通知地址
		body.put("storeId", "");                                                  //商户门店编号
		body.put("terminalId", "");                                               //商户终端编号
		body.put("operatorId", "");                                               //操作员编号
		body.put("clearCycle", "");                                               //清算模式
		body.put("royaltyInfo", "");                                              //分账信息
		body.put("riskRateInfo", "");                                             //风控信息域
		body.put("bizExtendParams", "");                                          //业务扩展参数
		body.put("merchExtendParams", "");                                        //商户扩展参数
		body.put("extend", "");                                                   //扩展域
		
		String payMode="";
		JSONObject payExtra=new JSONObject();
		
		switch(mode) {			
			case  bank_pc:    	//银行网关支付
				payMode="bank_pc";
				payExtra.put("payType", "1");				//支付类型  1-借记卡  2-贷记卡  3-借/贷记卡
				payExtra.put("bankCode", "01030000");		//银行编码,具体编码见《杉德线上支付接口规范》 附录
				break;
			case  sand_h5:    	//银行卡H5支付
				payMode="sand_h5";
				payExtra.put("“cardNo”", "6220000000000000001");	//银行卡卡号，选填
				break;
			case  sand_wx:		//微信公众号支付
				payMode="sand_wx";
				payExtra.put("subAppid", appId);				//商户公众号Appid
				payExtra.put("userId", openId);		//用户在商户公众号下得唯一标示openid
				break;
			case  sand_alipay:	//支付宝服务窗支付
				payMode="sand_alipay";
				payExtra.put("userId", "2088102150477652");				//付款支付宝用户号
				break;
			case  sand_wxsdk:	//微信APP支付
				payMode="sand_wxsdk";
				payExtra.put("appId", "wxd678efh567hg6787");		    //微信开放平台会生成APP的唯一标识
				break;
			case  sand_wxh5:	//微信H5支付
				payMode="sand_wxh5";
				payExtra.put("ip", "x.x.x.x");		    //付款人实际客户端IP地址,获取方式参照 https://pay.weixin.qq.com/wiki/doc/api/H5_sl.php?chapter=15_5
				JSONObject sceneInfo=new JSONObject();
				JSONObject h5Info=new JSONObject();
				h5Info.put("type", "Wap");
				h5Info.put("wap_url", "https://pay.qq.com");
				h5Info.put("wap_name", "腾讯充值");
				sceneInfo.put("h5_info", h5Info);
				payExtra.put("sceneInfo", sceneInfo);  //
				break;	
			case  sand_upsdk:	////银联SDK
				payMode="sand_wxsdk";
				break;
		
		}
		
		body.put("payMode", payMode);					//支付模式
		body.put("payExtra", payExtra.toJSONString());	//支付扩展域
		
		
	};
	
	
	public boolean orderCreate(String orderCode,String totalAmount,String goodsbody,String subject,String clienIp,String notifyUrl,String frontUrl,String openId,String appId) throws Exception {
		OrderCreateDemo demo=new OrderCreateDemo();
		String reqAddr="/order/pay";   //接口报文规范中获取
		
		//加载配置文件
		try {
			SDKConfig.getConfig().loadPropertiesFromSrc(SDKConfig.SDK_URLPAY);
			//加载证书
			CertUtil.init(SDKConfig.getConfig().getSandCertPath(), SDKConfig.getConfig().getSignCertPath(), SDKConfig.getConfig().getSignCertPwd());
			//设置报文头
			demo.setHeader(PayMode.sand_wx);
			//设置报文体
			demo.setBody(PayMode.sand_wx,orderCode,totalAmount, goodsbody,subject,clienIp,notifyUrl,frontUrl,openId,appId);
			
			JSONObject resp=DemoBase.requestServer(demo.header, demo.body, reqAddr);
			
			if(resp.getJSONObject("head").getString("respCode").equals("000000")) {
				logger.info("下单成功");
				logger.info("生成的支付凭证为："+resp.getJSONObject("body").getString("credential"));
				String params=JSONObject.parseObject(resp.getJSONObject("body").getString("credential")).getString("params");	
				System.out.println(params+"__________params");
				String timeStamp=JSONObject.parseObject(params).getString("timeStamp");      
				String package_=JSONObject.parseObject(params).getString("package");  
				String paySign=JSONObject.parseObject(params).getString("paySign"); 
				String signType=JSONObject.parseObject(params).getString("signType"); 
				String nonceStr=JSONObject.parseObject(params).getString("nonceStr");
				Map<String, String> ret = new HashMap();
				ret.put("appid", "wx582b72ed1897cf15");
				ret.put("package", package_);
				ret.put("nonceStr", nonceStr);
				ret.put("timeStamp", timeStamp);
				ret.put("signType", signType);
				ret.put("paySign", paySign);
				ret.put("orderCode", orderCode);
				Gson g = new Gson();
				String json = g.toJson(ret);
				HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
						.getResponse();
				response.setContentType("application/json;charset=utf-8");
				response.setHeader("Cache-Control", "no-cache");
				PrintWriter pw = null;
				try {
					pw = response.getWriter();
				} catch (IOException e) {
					System.out.println("response.getWriter();报错");
				}
				pw.print(json);
				pw.flush();
				pw.close();
				return true;
			}
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
