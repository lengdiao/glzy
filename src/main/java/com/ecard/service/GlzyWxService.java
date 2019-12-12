package com.ecard.service;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ecard.ProjectConst;
import com.ecard.entity.AccessToken;
import com.ecard.entity.Authority;
import com.ecard.entity.CloudPassInfo;
import com.ecard.entity.Department;
import com.ecard.entity.DrugSet;
import com.ecard.entity.DrugStore;
import com.ecard.entity.DrugStorePerson;
import com.ecard.entity.Gift;
import com.ecard.entity.MallOrder;
import com.ecard.entity.Mechanism;
import com.ecard.entity.PtInfo;
import com.ecard.entity.PtOpen;
import com.ecard.entity.Role;
import com.ecard.entity.RoleAuthority;
import com.ecard.entity.Sednsms;
import com.ecard.entity.UserRole;
import com.ecard.entity.WeiXinUser;
import com.ecard.mapper.AuthorityMapper;
import com.ecard.mapper.CloudPassInfoMapper;
import com.ecard.mapper.DepartmentMapper;
import com.ecard.mapper.DrugSetMapper;
import com.ecard.mapper.DrugStoreMapper;
import com.ecard.mapper.DrugStorePersonMapper;
import com.ecard.mapper.GiftMapper;
import com.ecard.mapper.MallOrderMapper;
import com.ecard.mapper.MechanismMapper;
import com.ecard.mapper.PtInfoMapper;
import com.ecard.mapper.PtOpenMapper;
import com.ecard.mapper.RoleAuthorityMapper;
import com.ecard.mapper.RoleMapper;
import com.ecard.mapper.SednsmsMapper;
import com.ecard.mapper.UserRoleMapper;
import com.ecard.pojo.PageInfo;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.ResponseNoData;
import com.ecard.pojo.queryResult.Authoritys;
import com.ecard.pojo.queryResult.DepartmentQr;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.RoleListQr;
import com.ecard.pojo.queryResult.RoleQr;
import com.ecard.utils.AppUtil;
import com.ecard.utils.GetOpenIdUtils;
import com.ecard.utils.PhotoUtils;
import com.ecard.utils.RoleUtil;
import com.ecard.utils.Sha1;
import com.ecard.utils.WeiXinUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import net.sf.json.JSONObject;

@Service
@Transactional
public class GlzyWxService {
	
	@Autowired
	private CloudPassInfoMapper cloudPassInfoMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleAuthorityMapper roleAuthorityMapper;
	@Autowired
	private MechanismMapper mechanismMapper;
	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private DrugStoreMapper drugStoreMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private DrugStorePersonMapper drugStorePersonMapper;
	@Autowired
	private PtInfoMapper ptInfoMapper;
	@Autowired
	private SednsmsMapper sednsmsMapper;
	@Autowired
	private MallOrderMapper mallOrderMapper;
	@Autowired
	private PtOpenMapper ptOpenMapper;
	@Autowired
	private DrugSetMapper drugSetMapper;
	@Autowired
	private GiftMapper giftMapper;
	
	public Response addMallOrder(String name, String phone, String idCard, String addressName, String addressPhone,
			String province, String city, String area, String address, String volunteerName, String volPhone,String code) {
		System.out.println("volunteerName:"+volunteerName);
		System.out.println("volPhone:"+volPhone);
		ResponseHasData response = new ResponseHasData();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
			    .getRequest();
		HttpSession session = request.getSession();
		try {
			String openId = GetOpenIdUtils.getOpenId(code);
			//String openId = "001";
			session.setAttribute("openId", openId);
			
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByNameAndIdCard(name,idCard);
			if(cloudPassInfo==null) {
				response.setMsg("此用户不在赠药范围内");
				response.setStatus(1);
				return response;
			}
			
			CloudPassInfo cloudInfo = cloudPassInfoMapper.selectByPhoneAndPassNo(phone, cloudPassInfo.getCloudPassNo());
			if(cloudInfo!=null) {
				response.setMsg("此手机号已存在");
				response.setStatus(1);
				return response;
			}
			
			cloudPassInfo.setPhone(phone);
			
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			List<MallOrderQr> list = mallOrderMapper.findMallOrderByCloudNo(cloudPassInfo.getCloudPassNo());
			if(list.size()!=0) {
				response.setMsg("该患者已存在订单");
				response.setStatus(1);
				return response;
			}
			
			Long mallNo = mallOrderMapper.generateMallno();//调用存储过程生成mallno
			PtInfo ptInfo = ptInfoMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
			
			ptInfo.setAddress(province+"/"+city+"/"+area+"/"+address);
			ptInfo.setName(addressName);
			ptInfo.setPhone(addressPhone);
			ptInfo.setVolPhone(volPhone);
			ptInfo.setVolunteerName(volunteerName);
			
			ptInfoMapper.updateByPrimaryKeySelective(ptInfo);
			
			MallOrder mallOrder = new MallOrder();
			mallOrder.setCloudPassNo(cloudPassInfo.getCloudPassNo());
			mallOrder.setOrderTime(new Date());
			mallOrder.setMallNo(mallNo);
			mallOrder.setCheckStatus(0);
			
			mallOrderMapper.insertSelective(mallOrder);
			
			PtOpen ptOpens = ptOpenMapper.findByPtOpenId(openId);
			if(ptOpens!=null) {
				ptOpens.setOpenId(openId);
				ptOpens.setPtNo(ptInfo.getPtNo());
				ptOpenMapper.updateByPrimaryKeySelective(ptOpens);
			}else {
				PtOpen ptOpen = new PtOpen();
				ptOpen.setOpenId(openId);
				ptOpen.setPtNo(ptInfo.getPtNo());
				
				
				ptOpenMapper.insertSelective(ptOpen);
			}
			
			Gift gift = new Gift();
			gift.setMallNo(mallNo);
			giftMapper.insertSelective(gift);
			
			
			response.setMsg("订单创建成功");
			response.setStatus(0);
			response.setData(mallOrder.getMallNo());
		} catch (Exception e) {
			response.setMsg("订单创建失败");
			response.setStatus(1);
			e.printStackTrace();
		}	
		return response;
	}

	
	public Response addImg(MultipartFile file , String name) {
		ResponseHasData response = new ResponseHasData();
		
		System.out.println("执行upload");
        //request.setCharacterEncoding("UTF-8");
        System.out.println("执行图片上传");
        //String user = request.getParameter("user");
        //System.out.println("user:"+user);
        if(!file.isEmpty()) {
        	System.out.println("成功获取照片");        
            // 项目在容器中实际发布运行的根路径
            String realPath = "../../../../webapps/glzy/WEB-INF/classes/static/images/";
            // 自定义的文件名称
            String trueFileName = name;
            // 设置存放图片文件的路径
            String path = realPath + trueFileName+".jpg";
            System.out.println("存放图片文件的路径:" + path);
            try {
				file.transferTo(new File(path));
			} catch (Exception e) {
				response.setMsg("上传失败");
                response.setStatus(1);
				e.printStackTrace();
				return response;
			} 
            //列名
            String type = name.substring(13,name.length()-1);
            System.out.println("type:"+type);
            //订单号
            String orderNo = name.substring(0,13);
            System.out.println("orderNo:"+orderNo);
            //是否存在路径
            int l = 0;
            
            if(type.equals("idCard")) {
            	MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(orderNo));
            	String idCardUrl = mallOrder.getIdCardImg();
            	System.out.println("idCardUrl:"+idCardUrl);
            	if(idCardUrl==null) {
            		System.out.println("idCardUrl==null");
            		mallOrder.setIdCardImg("http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            	}else {
            		String[] str = idCardUrl.split("\\|");
            		System.out.println("str[]"+str);
            		for(int i=0 ; i<str.length ; i++) {
            			System.out.println("str[i]"+str[i]);
            			String imgName = str[i].substring(40,str[i].length()-4);
            			System.out.println("imgName"+imgName);
            			if(imgName.equals(name)) {
            				l=1;
            			}
            		}
            		System.out.println("l:"+l);
            		if(l==0) {
            			System.out.println("l==0");
            			mallOrder.setIdCardImg(idCardUrl+"|"+"http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            		}
            	}
            }else if(type.equals("agree")) {
            	MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(orderNo));
            	String idCardUrl = mallOrder.getAgreeImg();
            	if(idCardUrl==null) {
            		mallOrder.setAgreeImg("http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            	}else {
            		String[] str = idCardUrl.split("\\|");
            		for(int i=0 ; i<str.length ; i++) {
            			String imgName = str[i].substring(40,str[i].length()-4);
            			if(imgName.equals(name)) {
            				l=1;
            			}
            		}
            		if(l==0) {
            			mallOrder.setAgreeImg(idCardUrl+"|"+"http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            		}
            	}
            }else if(type.equals("record")) {
            	MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(orderNo));
            	String idCardUrl = mallOrder.getRecordImg();
            	if(idCardUrl==null) {
            		mallOrder.setRecordImg("http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            	}else {
            		String[] str = idCardUrl.split("\\|");
            		for(int i=0 ; i<str.length ; i++) {
            			String imgName = str[i].substring(40,str[i].length()-4);
            			if(imgName.equals(name)) {
            				l=1;
            			}
            		}
            		if(l==0) {
            			mallOrder.setRecordImg(idCardUrl+"|"+"http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            		}
            	}
            }else if(type.equals("recipe")) {
            	MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(orderNo));
            	String idCardUrl = mallOrder.getRecipeImg();
            	if(idCardUrl==null) {
            		mallOrder.setRecipeImg("http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            	}else {
            		String[] str = idCardUrl.split("\\|");
            		for(int i=0 ; i<str.length ; i++) {
            			String imgName = str[i].substring(40,str[i].length()-4);
            			if(imgName.equals(name)) {
            				l=1;
            			}
            		}
            		if(l==0) {
            			mallOrder.setRecipeImg(idCardUrl+"|"+"http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            		}
            	}
            }else if(type.equals("diagnosis")) {
            	MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(orderNo));
            	String idCardUrl = mallOrder.getDiagnosisImg();
            	if(idCardUrl==null) {
            		mallOrder.setDiagnosisImg("http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            		mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            	}else {
            		String[] str = idCardUrl.split("\\|");
            		for(int i=0 ; i<str.length ; i++) {
            			String imgName = str[i].substring(40,str[i].length()-4);
            			if(imgName.equals(name)) {
            				l=1;
            			}
            		}
            		if(l==0) {
            			mallOrder.setDiagnosisImg(idCardUrl+"|"+"http://www.yizhenyun.com.cn/glzy/images/"+name+".jpg");
            			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
            		}
            	}
            }
            System.out.println("文件成功上传到指定目录下");
            response.setMsg("上传成功");
            response.setStatus(0);
            response.setData("http://www.yizhenyun.com.cn/glzy/images/"+trueFileName);
            return response;
        }else {
        	System.out.println("未获取到图片");
            response.setMsg("未获取到图片");
            response.setStatus(1);
            return response;
        }
      
        
    }
	
	public Response addDrugSet(Long mallNo, Long drugNo, int number) {
		ResponseHasData response = new ResponseHasData();
		try {
			MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(mallNo);
			DrugSet drugSet = drugSetMapper.selectByPrimaryKey(drugNo);
			
			double sum=0;
			double sums=0;
			if(drugSet.getDiscount().equals("B3|P2")) {
				double price=drugSet.getDrugPrice().doubleValue();
				if(number>=3) {
					int i = number/3;
					int j = number%3;
					sum = price*2+j*price;
				}else {
					sum = price*number;
				}
				sums=price*number;
			}
			
			mallOrder.setDrugId(drugNo);
			mallOrder.setNumber(number);
			mallOrder.setOrderAmount(new BigDecimal(sums));
			mallOrder.setPayAmount(new BigDecimal(Double.toString(sum)));
			mallOrder.setOrderStatus(1);
			mallOrder.setPayStatus(1);
			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
			response.setMsg("更新订单成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("更新订单成失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		
		return response;
	}
	
	
	public Response findMallOrderByCode(String name, String phone, String code) {
		ResponseHasData response = new ResponseHasData();
		Sednsms sednsms = sednsmsMapper.selectByPhone(phone);
		if (sednsms == null) {
			response.setStatus(1);
			response.setMsg("请点击获取验证码");
			return response;
		}
		if (!sednsms.getCode().equals(code)) {
			response.setStatus(1);
			response.setMsg("验证码错误，修改失败！");
			return response;
		}
		if (sednsms.getExpiryDate().getTime() < new Date().getTime()) {
			response.setStatus(1);
			response.setMsg("验证码失效，修改失败！");
			return response;
		}
		List<MallOrderQr> piq = mallOrderMapper.findMallOrderByCode(phone,name);
		
		response.setStatus(0);
		response.setMsg("查询成功");
		response.setData(piq);
		
		return response;
	}
	
	
	public Response findMallOrderByOpenId(String code) {
		ResponseHasData response = new ResponseHasData();
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		HttpSession session = request.getSession(); 
		String openId = GetOpenIdUtils.getOpenId(code);
		//String openId = "001";
		
		try {
			PtOpen ptOpen = ptOpenMapper.findByPtOpenId(openId);
			
			if(ptOpen!=null) {
				PtInfo ptInfo = ptInfoMapper.selectByPrimaryKey(ptOpen.getPtNo());
				List<MallOrderQr> piq = mallOrderMapper.findMallOrderByCloudNo(ptInfo.getCloudPassNo());
				if(piq.size()!=0){
					response.setData(piq);
					response.setMsg(openId);
					response.setStatus(0);
				}else{
					response.setMsg(openId);
					response.setStatus(1);
				}

			}else {
				response.setMsg(openId);
				response.setStatus(1);
			}
		} catch (Exception e) {
			response.setMsg("查询失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}
	

	public Response notice(String mallNo) {
		ResponseHasData response = new ResponseHasData();	
		try {
			MallOrder mallOrder = mallOrderMapper.selectByPrimaryKey(Long.valueOf(mallNo));
			mallOrder.setOrderStatus(0);
			mallOrder.setPayStatus(0);
			mallOrder.setPayTime(new Date());
			mallOrder.setShippingStatus(1);
			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
			
			PtInfo ptInfo = ptInfoMapper.selectByCloudPassNo(mallOrder.getCloudPassNo());
			
			ptOpenMapper.deleteByPtNo(ptInfo.getPtNo());
			
			response.setMsg("通知成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("通知失败");
			response.setStatus(1);
			e.printStackTrace();
		}	
		return response;
	}
	
	
	public Response updateMallOrder(Long mallNo, String name, String phone, String idCard, String addressName,
			String addressPhone, String province, String city, String area, String address, String volunteerName,
			String volPhone,String code) {
		ResponseHasData response = new ResponseHasData();	
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		HttpSession session = request.getSession(); 
		String openId = GetOpenIdUtils.getOpenId(code);
		//String openId = "001";
		
		try {
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByNameAndIdCard(name,idCard);
			if(cloudPassInfo==null) {
				response.setMsg("此用户不在赠药范围内");
				response.setStatus(1);
				return response;
			}
			cloudPassInfo.setPhone(phone);
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			//Long mallNo = mallOrderMapper.generateMallno();//调用存储过程生成mallno
			PtInfo ptInfo = ptInfoMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
			
			ptInfo.setAddress(province+"/"+city+"/"+area+"/"+address);
			ptInfo.setName(addressName);
			ptInfo.setPhone(addressPhone);
			ptInfo.setVolPhone(volPhone);
			ptInfo.setVolunteerName(volunteerName);
			
			ptInfoMapper.updateByPrimaryKeySelective(ptInfo);
			
			MallOrder mallOrder = new MallOrder();
			mallOrder.setCloudPassNo(cloudPassInfo.getCloudPassNo());
			mallOrder.setOrderTime(new Date());
			mallOrder.setMallNo(mallNo);
			
			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
			
			PtOpen ptOpens = ptOpenMapper.findByPtOpenId(openId);
			if(ptOpens!=null) {
				ptOpens.setOpenId(openId);
				ptOpens.setPtNo(ptInfo.getPtNo());
				ptOpenMapper.updateByPrimaryKeySelective(ptOpens);
			}
			
			response.setMsg("订单更新成功");
			response.setStatus(0);
			response.setData(mallOrder.getMallNo());
		} catch (Exception e) {
			response.setMsg("订单更新失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		
		
		return response;
	}
	
	
	
	
	
	
	
	
	
	
	 /**
	  * 获取微信用户的信息
     * @param accessToken
     * @param openId
     * @return
     */
    public WeiXinUser getUserInfo(String accessToken, String openId) {
        WeiXinUser weixinUserInfo = null;
        // 拼接获取用户信息接口的请求地址
        String requestUrl = ProjectConst.GET_WEIXIN_USER_URL.replace("ACCESS_TOKEN", accessToken).replace(
                "OPENID", openId);
        // 获取用户信息(返回的是Json格式内容)
        JSONObject jsonObject = WeiXinUtils.doGetStr(requestUrl);
 
        if (null != jsonObject) {
            try {
                //封装获取到的用户信息
                weixinUserInfo = new WeiXinUser();
                // 用户的标识
                weixinUserInfo.setOpenId(jsonObject.getString("openid"));
                // 昵称
                weixinUserInfo.setNickname(jsonObject.getString("nickname"));
                // 用户的性别（1是男性，2是女性，0是未知）
                weixinUserInfo.setSex(jsonObject.getInt("sex"));
                // 用户所在国家
                weixinUserInfo.setCountry(jsonObject.getString("country"));
                // 用户所在省份
                weixinUserInfo.setProvince(jsonObject.getString("province"));
                // 用户所在城市
                weixinUserInfo.setCity(jsonObject.getString("city"));
                // 用户头像
                weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
            } catch (Exception e) {
                if (0 == weixinUserInfo.getSubscribe()) {
                    System.out.println("用户并没有关注本公众号");
                } else {
                    int errorCode = jsonObject.getInt("errcode");
                    String errorMsg = jsonObject.getString("errmsg");
                    System.out.println("由于"+errorCode +"错误码；错误信息为："+errorMsg+"；导致获取用户信息失败");
                }
            }
        }
        return weixinUserInfo;
    }
 
    /**
     * 进行用户授权，获取到需要的授权字段，比如openId
     * @param code 识别得到用户id必须的一个值
     * 得到网页授权凭证和用户id
     * @return
     */
    public Map<String, String> oauth2GetOpenid(String code) {
        //自己的配置appid（公众号进行查阅）
        String appid = ProjectConst.PROJECT_APPID;
        //自己的配置APPSECRET;（公众号进行查阅）
        String appsecret = ProjectConst.PROJECT_APPSECRET;
        //拼接用户授权接口信息
        String requestUrl = ProjectConst.GET_WEBAUTH_URL.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
        //存储获取到的授权字段信息
        Map<String, String> result = new HashMap<String, String>();
        try {
            JSONObject OpenidJSONO = WeiXinUtils.doGetStr(requestUrl);
            //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope
            String Openid = String.valueOf(OpenidJSONO.get("openid"));
            String AccessToken = String.valueOf(OpenidJSONO.get("access_token"));
            System.out.println("AccessToken"+AccessToken);
            //用户保存的作用域
            String Scope = String.valueOf(OpenidJSONO.get("scope"));
            String refresh_token = String.valueOf(OpenidJSONO.get("refresh_token"));
            result.put("Openid", Openid);
            result.put("AccessToken", AccessToken);
            result.put("scope", Scope);
            result.put("refresh_token", refresh_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
 
    /**
     * 获取到微信用户的唯一的OpendID
     * @param code  这是要获取OpendId的必须的一个参数
     * @return
     */
    public Map<String , String> getAuthInfo(String code) {
        //进行授权验证，获取到OpenID字段等信息
        Map<String, String> result = oauth2GetOpenid(code);
        // 从这里可以得到用户openid
        String openId = result.get("Openid");
        System.out.println("openId="+openId);
        return result;
    }
    
    
    public void selectByOpenId(int status) {
		try {
			ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
			HttpServletRequest request = servletRequestAttributes.getRequest();
			HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getResponse();
			HttpSession session = request.getSession(); 
			WeiXinUser user = (WeiXinUser) session.getAttribute("currentUser");
			//String openId = user.getOpenId();
			String openId = "oJTU_09M70xDvS7xTU8hxrGujtxs";
			
			PtOpen open = ptOpenMapper.findByPtOpenId(openId);
			if(open==null) {
				String url = "http://www.yizhenyun.net.cn/blcfwp/wxregister/index.html#/register";
				response.sendRedirect(url);
			}else {
				if(status==0) {
					String url = "http://www.yizhenyun.net.cn/blcfwp/wxregister/index.html#/register";
					response.sendRedirect(url);
				}else if(status==1) {
					String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/myPrescription";
					response.sendRedirect(url);
				}else if(status==2) {
					String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/guidance";
					response.sendRedirect(url);
				}else if(status==3) {
					String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/myPrescription";
					response.sendRedirect(url);
				}else if(status==4) {
					String url = "http://www.yizhenyun.net.cn/blcfwp/wx/index.html#/mine";
					response.sendRedirect(url);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    
    
    public Response getSign(String url) {
		String nonce_str = randomStr(32);
		String timestamp = create_timestamp();
		ResponseHasData response = new ResponseHasData();	
		AccessToken accessToken=WeiXinUtils.getAccessToken();
		String jsapi_ticket = WeiXinUtils.getJsapi_ticket(accessToken.getToken());
		System.out.println("jsapi_ticket="+jsapi_ticket);
		String string1 ="jsapi_ticket="+jsapi_ticket+"&noncestr="+nonce_str+"&timestamp="+timestamp+"&url="+url;
		String signature = Sha1.getSha1(string1);
		Map<String , String > map = new HashMap<String,String>();
		map.put("signature", signature);
		map.put("noncestr", nonce_str);
		map.put("timestamp", timestamp);
		map.put("appid", "wx46b24e18fab3cd03");
		response.setData(map);
		response.setMsg("获取成功");
		response.setStatus(0);
		return response;
	}
    
    
    public static String randomStr(int n) {
		String base = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 32; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
	
	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000L);
	}


	


	




}
