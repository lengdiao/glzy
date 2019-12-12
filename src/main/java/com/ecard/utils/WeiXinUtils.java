package com.ecard.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.ecard.ProjectConst;
import com.ecard.entity.AccessToken;
import com.ecard.entity.Template;
import com.ecard.entity.TemplateParam;

import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
 
/**
 * @author scw
 * @create 2018-01-17 14:13
 * @desc 用户获取access_token,众号调用各接口时都需使用access_token
 **/
public class WeiXinUtils {
	
    /**
     * Get请求，方便到一个url接口来获取结果
     * @param url
     * @return
     */
    public static JSONObject doGetStr(String url){
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try{
            HttpResponse response = defaultHttpClient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
    
    

     /**
        * 获取access_token
     * @return
     */
    public static AccessToken getAccessToken(){
        AccessToken accessToken = new AccessToken();
        String url = ProjectConst.ACCESS_TOKEN_URL.replace("APPID" ,ProjectConst.PROJECT_APPID).replace("APPSECRET",ProjectConst.PROJECT_APPSECRET);
        System.out.println("url"+url);
        JSONObject jsonObject = doGetStr(url);
        if(jsonObject !=null){
            accessToken.setToken(jsonObject.getString("access_token"));
            accessToken.setExpireIn(jsonObject.getInt("expires_in"));
        }
        return accessToken;
    }
    /**
     * 获取Jsapi_ticket
     * @param access_token 
  * @return
  */
    public static String getJsapi_ticket(String access_token){
        String url = ProjectConst.jsapi_ticket_URL.replace("ACCESS_TOKEN" ,access_token);
        System.out.println("url"+url);
        JSONObject jsonObject = doGetStr(url);
        String jsapi_ticket=null;
        if(jsonObject !=null){
           jsapi_ticket = jsonObject.getString("ticket");
        }
        return jsapi_ticket;
    }
    
    
    
    
    
    
    
    
    
    /**
     * 用药提醒
     */
	public static void remind(String openId, String drugName, String adminRoute, String adminDose, String patientDrugAdvice, String date ,String name) {
		try {
		AccessToken access_token = getAccessToken();
		URL tmpurl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token.getToken());
		Template tp = new Template();
		tp.setTemplateId("G9H329C6ORxZpBahwZJxdG4c8Is_ao0jwek5vRx4vv8");
		tp.setTopColor("#00DD00");
		tp.setToUser(openId);
		tp.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46b24e18fab3cd03&redirect_uri=http://www.yizhenyun.net.cn/blcfwp/hisptWx/tologin/userinfo?status=2&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", "服药提醒", "#173177"));
		paras.add(new TemplateParam("keyword1", drugName, "#173177"));
		paras.add(new TemplateParam("keyword2",adminDose, "#173177"));
		paras.add(new TemplateParam("keyword3", date,"#173177"));
		paras.add(new TemplateParam("keyword4", name,"#173177"));
		paras.add(new TemplateParam("keyword5", patientDrugAdvice,"#173177"));
		paras.add(new TemplateParam("remark","", "#173177"));
		tp.setTemplateParamList(paras);																
		
			String response = PostUtil.sendPost(tmpurl, "application/x-www-form-urlencoded;charset=utf-8",
					tp.toJSON());
		} catch (Exception e) {
			System.out.println("消息模板推送发送错误");
			e.printStackTrace();
		}				
	}
	
	
	/**
     * 取药提醒
     */
	public static void remind(String openId, String recipeId, String name) {
		try {
		AccessToken access_token = getAccessToken();
		URL tmpurl = new URL("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=" + access_token.getToken());
		Template tp = new Template();
		tp.setTemplateId("9GpOelP-3BLu90SD7ieWPI522kkJo7V_dyDh4sHMYUk");
		tp.setTopColor("#00DD00");
		tp.setToUser(openId);
		tp.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx46b24e18fab3cd03&redirect_uri=http://www.yizhenyun.net.cn/blcfwp/hisptWx/selectByOpenId?status=2&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect");
		List<TemplateParam> paras = new ArrayList<TemplateParam>();
		paras.add(new TemplateParam("first", name+"您好，您的处方单续配成功", "#173177"));
		paras.add(new TemplateParam("keyword1", recipeId, "#173177"));
		paras.add(new TemplateParam("keyword2",name, "#173177"));
		paras.add(new TemplateParam("keyword3",new Date().toString(), "#173177"));
		paras.add(new TemplateParam("remark","请三天内到附近药店取药", "#173177"));
		tp.setTemplateParamList(paras);																
		
			String response = PostUtil.sendPost(tmpurl, "application/x-www-form-urlencoded;charset=utf-8",
					tp.toJSON());
		} catch (Exception e) {
			System.out.println("消息模板推送发送错误");
			e.printStackTrace();
		}				
	}

}
