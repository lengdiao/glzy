package com.ecard.service;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import java.util.Random;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.DocumentHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ecard.entity.CloudPassInfo;
import com.ecard.entity.DrugSet;
import com.ecard.entity.MallOrder;
import com.ecard.entity.PtInfo;
import com.ecard.entity.PtOpen;
import com.ecard.entity.WeiXinUser;
import com.ecard.gateway.demo.OrderCreateDemo;
import com.ecard.mapper.CloudPassInfoMapper;
import com.ecard.mapper.DrugSetMapper;
import com.ecard.mapper.MallOrderMapper;
import com.ecard.mapper.PtInfoMapper;
import com.ecard.mapper.PtOpenMapper;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.utils.AppUtil;
import com.ecard.utils.GetOpenIdUtils;

import java.io.BufferedReader;

import java.io.InputStreamReader;

import java.math.BigDecimal;

import org.apache.commons.codec.digest.DigestUtils;
import org.dom4j.Document;

import org.dom4j.Element;


// 通过appId、appSecret找openId，在微信平台用申请过公众号的手机登录，就会出现微信给这个注册过的公众号一个appid和appSecret.
@Service
@Transactional
public class WxPayService {
	@Autowired
	private MallOrderMapper mallOrderMapper;
	@Autowired
	private DrugSetMapper drugSetMapper;
	@Autowired
	private PtInfoMapper ptInfoMapper;
	@Autowired
	private PtOpenMapper ptOpenMapper;
	@Autowired
	private CloudPassInfoMapper cloudPassInfoMapper;
	
	public void wxPay(String mallNo) throws Exception {
		ResponseHasData response = new ResponseHasData();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		HttpSession session = request.getSession(); 
		//String openId = (String) session.getAttribute("openId");
		//String openId = "001";
		
		double sum=0;
		
		MallOrder mallOrder=mallOrderMapper.selectByPrimaryKey(Long.parseLong(mallNo));
		
		PtInfo ptInfo = ptInfoMapper.selectByCloudPassNo(mallOrder.getCloudPassNo());
		
		PtOpen ptOpen = ptOpenMapper.findByPtNo(ptInfo.getPtNo());
		
		DrugSet drugSet = drugSetMapper.selectByPrimaryKey(mallOrder.getDrugId());
		
		CloudPassInfo info = cloudPassInfoMapper.selectByPrimaryKey(mallOrder.getCloudPassNo());
		/*
		 * if(info.getDisableFlag()==1) { response.setMsg("该患者已有购买记录");
		 * response.setStatus(1); return response; }
		 */
		
		if(drugSet.getDiscount().equals("B3|P2")) {
			double price=drugSet.getDrugPrice().doubleValue();
			int number=mallOrder.getNumber();
			if(number>=3) {
				int i = number/3;
				int j = number%3;
				sum = price*2+j*price;
			}
		}
	
		System.out.println(sum+"____总价");
		String total_fee = String.valueOf((int)(sum*100));
		//String total_fee = String.valueOf((int)(0.01*100));
		for(int i=total_fee.length();i<12;i++) {
			total_fee="0"+total_fee;
		}
		
		mallOrder.setPayAmount(new BigDecimal((int)sum));
		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
		
		String newMallNo=mallNo+getRandomString(5);
		OrderCreateDemo ocd=new OrderCreateDemo();
		ocd.orderCreate(newMallNo, total_fee,  "礼愈人生", "礼愈人生", "47.111.28.24", "https://www.yizhenyun.com.cn", "",ptOpen.getOpenId(),"wx582b72ed1897cf15");
	}
	
	
	public Response wxPay1(String mallNo) throws Exception {
		ResponseHasData response = new ResponseHasData();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		HttpSession session = request.getSession(); 
		String openId = (String) session.getAttribute("openId");
		//String openId = "001";
		
		double sum=0;
		
		MallOrder mallOrder=mallOrderMapper.selectByPrimaryKey(Long.parseLong(mallNo));
		
		PtInfo ptInfo = ptInfoMapper.selectByCloudPassNo(mallOrder.getCloudPassNo());
		
		//PtOpen ptOpen = ptOpenMapper.findByPtNo(ptInfo.getPtNo());
		
		DrugSet drugSet = drugSetMapper.selectByPrimaryKey(mallOrder.getDrugId());
		
		CloudPassInfo info = cloudPassInfoMapper.selectByPrimaryKey(mallOrder.getCloudPassNo());
	
		  if(info.getDisableFlag()==1) { 
			  response.setMsg("该患者已有购买记录");
			  response.setStatus(1); 
			  return response; 
		  }
	 
		
		if(drugSet.getDiscount().equals("B3|P2")) {
			double price=drugSet.getDrugPrice().doubleValue();
			int number=mallOrder.getNumber();
			if(number>=3) {
				int i = number/3;
				int j = number%3;
				sum = price*2+j*price;
			}
		}
	
		System.out.println(sum+"____总价");
		String total_fee = String.valueOf((int)(sum*100));
		//String total_fee = String.valueOf((int)(0.01*100));
		for(int i=total_fee.length();i<12;i++) {
			total_fee="0"+total_fee;
		}
		
		mallOrder.setPayAmount(new BigDecimal((int)sum));
		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
		
		String newMallNo=mallNo+getRandomString(5);
		OrderCreateDemo ocd=new OrderCreateDemo();
		boolean b = ocd.orderCreate(newMallNo, total_fee,  "礼愈人生", "礼愈人生", "47.111.28.24", "https://www.yizhenyun.com.cn", "",openId,"wx582b72ed1897cf15");
		if(b) {
			response.setMsg("成功");
			response.setStatus(0); 
			return response;
		}else {
			response.setMsg("失败");
			response.setStatus(1); 
			return response;
		}
		
	}
	

	public static String getRandomString(int length){
	     String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	     Random random=new Random();
	     StringBuffer sb=new StringBuffer();
	     for(int i=0;i<length;i++){
	       int number=random.nextInt(62);
	       sb.append(str.charAt(number));
	     }
	     return sb.toString();
	 }
	
	
	public String sjResultPayWx() throws Exception {
		String xml = "";
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			ServletInputStream ins = request.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(ins, "UTF-8"));

			StringBuffer req = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				req.append(line);
			}
			rd.close();
			System.out.println("数据流" + req.toString());

			Document doc = DocumentHelper.parseText(req.toString());
			Element rootRes = doc.getRootElement();
			List<String> param_wx_list = new ArrayList();

			String return_code_wx = rootRes.element("return_code") == null ? ""
					: AppUtil.getStr(rootRes.element("return_code").getData());
			String return_msg_res = rootRes.element("return_msg") == null ? ""
					: AppUtil.getStr(rootRes.element("return_msg").getData());
			String trade_state_res = rootRes.element("trade_state") == null ? ""
					: AppUtil.getStr(rootRes.element("trade_state").getData());
			String appid_wx = rootRes.element("appid") == null ? ""
					: AppUtil.getStr(rootRes.element("appid").getData());
			String mch_id_wx = rootRes.element("mch_id") == null ? ""
					: AppUtil.getStr(rootRes.element("mch_id").getData());
			String device_info_wx = rootRes.element("device_info") == null ? ""
					: AppUtil.getStr(rootRes.element("device_info").getData());
			String nonce_str_wx = rootRes.element("nonce_str") == null ? ""
					: AppUtil.getStr(rootRes.element("nonce_str").getData());
			String sign_wx = rootRes.element("sign") == null ? "" : AppUtil.getStr(rootRes.element("sign").getData());
			String result_code_wx = rootRes.element("result_code") == null ? ""
					: AppUtil.getStr(rootRes.element("result_code").getData());
			String err_code_wx = rootRes.element("err_code") == null ? ""
					: AppUtil.getStr(rootRes.element("err_code").getData());
			String err_code_des_wx = rootRes.element("err_code_des") == null ? ""
					: AppUtil.getStr(rootRes.element("err_code_des").getData());
			String openid_wx = rootRes.element("openid") == null ? ""
					: AppUtil.getStr(rootRes.element("openid").getData());
			String is_subscribe_wx = rootRes.element("is_subscribe") == null ? ""
					: AppUtil.getStr(rootRes.element("is_subscribe").getData());
			String trade_type_wx = rootRes.element("trade_type") == null ? ""
					: AppUtil.getStr(rootRes.element("trade_type").getData());
			String bank_type_wx = rootRes.element("bank_type") == null ? ""
					: AppUtil.getStr(rootRes.element("bank_type").getData());
			String total_fee_wx = rootRes.element("total_fee") == null ? ""
					: AppUtil.getStr(rootRes.element("total_fee").getData());
			String fee_type_wx = rootRes.element("fee_type") == null ? ""
					: AppUtil.getStr(rootRes.element("fee_type").getData());
			String cash_fee_wx = rootRes.element("cash_fee") == null ? ""
					: AppUtil.getStr(rootRes.element("cash_fee").getData());
			String cash_fee_type_wx = rootRes.element("cash_fee_type") == null ? ""
					: AppUtil.getStr(rootRes.element("cash_fee_type").getData());
			String coupon_fee_wx = rootRes.element("coupon_fee") == null ? ""
					: AppUtil.getStr(rootRes.element("coupon_fee").getData());
			String coupon_count_wx = rootRes.element("coupon_count") == null ? ""
					: AppUtil.getStr(rootRes.element("coupon_count").getData());
			String coupon_id_$n_wx = rootRes.element("coupon_id_$n") == null ? ""
					: AppUtil.getStr(rootRes.element("coupon_id_$n").getData());
			String coupon_fee_$n_wx = rootRes.element("coupon_fee_$n") == null ? ""
					: AppUtil.getStr(rootRes.element("coupon_fee_$n").getData());
			String transaction_id_wx = rootRes.element("transaction_id") == null ? ""
					: AppUtil.getStr(rootRes.element("transaction_id").getData());
			String out_trade_no_wx = rootRes.element("out_trade_no") == null ? ""
					: AppUtil.getStr(rootRes.element("out_trade_no").getData());
			String attach_wx = rootRes.element("attach") == null ? ""
					: AppUtil.getStr(rootRes.element("attach").getData());
			String time_end_wx = rootRes.element("time_end") == null ? ""
					: AppUtil.getStr(rootRes.element("time_end").getData());

			param_wx_list.add("return_code=" + return_code_wx + "&");
			param_wx_list.add("return_msg=" + return_msg_res + "&");
			param_wx_list.add("appid=" + appid_wx + "&");
			param_wx_list.add("mch_id=" + mch_id_wx + "&");
			param_wx_list.add("device_info=" + device_info_wx + "&");
			param_wx_list.add("nonce_str=" + nonce_str_wx + "&");
			param_wx_list.add("result_code=" + result_code_wx + "&");
			param_wx_list.add("err_code=" + err_code_wx + "&");
			param_wx_list.add("err_code_des=" + err_code_wx + "&");
			param_wx_list.add("openid=" + openid_wx + "&");
			param_wx_list.add("is_subscribe=" + is_subscribe_wx + "&");
			param_wx_list.add("trade_type=" + trade_type_wx + "&");
			param_wx_list.add("bank_type=" + bank_type_wx + "&");
			param_wx_list.add("total_fee=" + total_fee_wx + "&");
			param_wx_list.add("fee_type=" + fee_type_wx + "&");
			param_wx_list.add("cash_fee=" + cash_fee_wx + "&");
			param_wx_list.add("cash_fee_type=" + cash_fee_type_wx + "&");
			param_wx_list.add("coupon_fee=" + coupon_fee_wx + "&");
			param_wx_list.add("coupon_count=" + coupon_count_wx + "&");
			param_wx_list.add("cash_fee=" + cash_fee_wx + "&");
			param_wx_list.add("coupon_id_$n=" + coupon_id_$n_wx + "&");
			param_wx_list.add("coupon_fee_$n=" + coupon_fee_$n_wx + "&");
			param_wx_list.add("transaction_id=" + transaction_id_wx + "&");
			param_wx_list.add("out_trade_no=" + out_trade_no_wx + "&");
			param_wx_list.add("attach=" + attach_wx + "&");
			param_wx_list.add("time_end=" + time_end_wx + "&");

			String[] param = (String[]) param_wx_list.toArray(new String[param_wx_list.size()]);
			Arrays.sort(param);
			String _param = "";
			for (int i = 0; i < param.length; i++) {
				String[] value = param[i].split("=");
				if (!value[1].equals("&")) {
					_param = _param + param[i];
				}
			}
			_param = _param + "key=" + "Miaoning2O18YizhenyunDevilSimPay";
			String _sign_wx = DigestUtils.md5Hex(_param.getBytes());
			if (!_sign_wx.equalsIgnoreCase(sign_wx)) {
				System.out.print("签名正确");
			}
			if ("SUCCESS".equals(return_code_wx)) {
				if ("SUCCESS".equals(result_code_wx)) {
	
					System.out.println("成功");
				} else {
					return null;
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();

			AppUtil.outXml(xml);
		}
		return null;
	}


	

	

}
