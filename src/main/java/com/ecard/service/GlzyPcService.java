package com.ecard.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.ecard.entity.Authority;
import com.ecard.entity.CloudPassInfo;
import com.ecard.entity.Department;
import com.ecard.entity.DrugSet;
import com.ecard.entity.DrugStore;
import com.ecard.entity.DrugStorePerson;
import com.ecard.entity.FactoryPerson;
import com.ecard.entity.MallOrder;
import com.ecard.entity.Mechanism;
import com.ecard.entity.PlatformPerson;
import com.ecard.entity.PtInfo;
import com.ecard.entity.Role;
import com.ecard.entity.RoleAuthority;
import com.ecard.entity.Sednsms;
import com.ecard.entity.UserRole;
import com.ecard.mapper.AuthorityMapper;
import com.ecard.mapper.CloudPassInfoMapper;
import com.ecard.mapper.DepartmentMapper;
import com.ecard.mapper.DrugSetMapper;
import com.ecard.mapper.DrugStoreMapper;
import com.ecard.mapper.DrugStorePersonMapper;
import com.ecard.mapper.FactoryPersonMapper;
import com.ecard.mapper.GiftMapper;
import com.ecard.mapper.MallOrderMapper;
import com.ecard.mapper.MechanismMapper;
import com.ecard.mapper.PlatformPersonMapper;
import com.ecard.mapper.PtInfoMapper;
import com.ecard.mapper.RoleAuthorityMapper;
import com.ecard.mapper.RoleMapper;
import com.ecard.mapper.SednsmsMapper;
import com.ecard.mapper.UserRoleMapper;
import com.ecard.pojo.PageInfo;
import com.ecard.pojo.Response;
import com.ecard.pojo.ResponseHasData;
import com.ecard.pojo.ResponseNoData;
import com.ecard.pojo.queryResult.AuthorityQr;
import com.ecard.pojo.queryResult.Authoritys;
import com.ecard.pojo.queryResult.CloudPassInfoQr;
import com.ecard.pojo.queryResult.DepartmentQr;
import com.ecard.pojo.queryResult.DrugStorePersonQr;
import com.ecard.pojo.queryResult.FactoryPersonQr;
import com.ecard.pojo.queryResult.MallOrderQr;
import com.ecard.pojo.queryResult.PtInfoQr;
import com.ecard.pojo.queryResult.PtInfoQrr;
import com.ecard.pojo.queryResult.RoleListQr;
import com.ecard.pojo.queryResult.RoleQr;
import com.ecard.utils.AppUtil;
import com.ecard.utils.PhotoUtils;
import com.ecard.utils.RoleUtil;
import com.ecard.utils.UploadUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
@Transactional
public class GlzyPcService {
	
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
	private DrugSetMapper drugSetMapper;
	@Autowired
	private PlatformPersonMapper platformPersonMapper;
	@Autowired
	private FactoryPersonMapper factoryPersonMapper;
	@Autowired
	private GiftMapper giftMapper;

	public Response login(String phone, String passWord) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhoneFlag(phone);

			UsernamePasswordToken token = new UsernamePasswordToken(phone, passWord);
			Subject currentUser = SecurityUtils.getSubject();
			currentUser.login(token);
			
			UserRole urRole = userRoleMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
			Map<String, Object> data = new HashMap<String, Object>();
			Long roleNo = urRole.getRoleNo();
			
			
			DrugStore drugStore = drugStoreMapper.selectByPhone(phone);
			
			List<Authority> authorities = roleAuthorityMapper.selectByRoleNo(roleNo);
			List<AuthorityQr> authorityQrs = new ArrayList<AuthorityQr>();
			for(Authority authority : authorities) {
				AuthorityQr authorityQr = new AuthorityQr();
				authorityQr.setAuthorityName(authority.getAuthorityName());
				authorityQr.setAuthorityNo(authority.getAuthorityNo());
				authorityQr.setImg(authority.getImg());
				authorityQr.setUrl(authority.getUrl());
				List<Authority> list = authorityMapper.selectByNo(authority.getAuthorityNo()+"");
				authorityQr.setChildList(list);
				authorityQrs.add(authorityQr);
			}
			
			if(drugStore!=null) {
				data.put("drugStoreNo", drugStore.getDrugStoreNo());
			}
			data.put("authorities", authorityQrs);		
			data.put("roleNo", roleNo);
			data.put("name",cloudPassInfo.getName());
			data.put("cloudPassNo",cloudPassInfo.getCloudPassNo());
			
			response.setData(data);
			response.setMsg("登陆成功");
			response.setStatus(0);
		} catch (AuthenticationException e) {
			e.printStackTrace();
			response.setMsg("登陆失败");
			response.setStatus(1);
		}
		
		return response;
	}

	public Response addUser(String phone, String name, String sex ,Long departmentNo) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhone(phone);
			if(cloudPassInfo!=null) {
				response.setMsg("手机号存在重复");
				response.setStatus(1);
				return response;
			}
			CloudPassInfo cloudPass = new CloudPassInfo();
			cloudPass.setPhone(phone);
			cloudPass.setName(name);
			cloudPass.setSex(sex);
			cloudPass.setDisableFlag(0);
			cloudPass.setPassword("123456");
			cloudPassInfoMapper.insertSelective(cloudPass);
			
			PlatformPerson platformPerson = new PlatformPerson();
			platformPerson.setCloudPassNo(cloudPass.getCloudPassNo());
			platformPerson.setDepartmentNo(departmentNo);
			platformPersonMapper.insertSelective(platformPerson);
			
			RoleUtil.accredit(cloudPass.getCloudPassNo(), "平台人员");
			response.setMsg("添加平台人员成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("添加平台人员失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response updateUser(Long cloudPassNo, String phone, String name, String sex, Long departmentNo ,Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo = new CloudPassInfo();
			cloudPassInfo.setPhone(phone);
			cloudPassInfo.setName(name);
			cloudPassInfo.setSex(sex);
			cloudPassInfo.setCloudPassNo(cloudPassNo);
			cloudPassInfo.setDisableFlag(disableFlag);
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			PlatformPerson platformPerson = platformPersonMapper.selectByCloudPassNo(cloudPassNo);
			platformPerson.setDepartmentNo(departmentNo);
			platformPersonMapper.updateByPrimaryKeySelective(platformPerson);
			
			response.setMsg("修改平台人员成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("修改平台人员失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		
		return response;
	}

	public Response findUser(String phone, String name, String departName, Integer page, Integer rows) {
		ResponseHasData response = new ResponseHasData();	
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<CloudPassInfoQr> piq = 
					cloudPassInfoMapper.findUser(phone,name,departName);
			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<CloudPassInfoQr> pageData = new PageInfo<CloudPassInfoQr>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}
		
		return response;
	}

	public Response addMechanism(String mechanismName, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();	
		try {
			List<Mechanism> mec = mechanismMapper.findMechanism(mechanismName);
			if(mec.size()!=0) {
				response.setMsg("机构名称重复");
				response.setStatus(1);
				return response;
			}
			
			Mechanism mechanism = new Mechanism();
			mechanism.setCreateTime(new Date());
			mechanism.setMechanismName(mechanismName);
			mechanism.setDisableFlag(disableFlag);
			
			mechanismMapper.insertSelective(mechanism);
			
			response.setStatus(0);
			response.setMsg("新增机构成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("新增机构失败");
			e.printStackTrace();
		}
		return response;
	}

	public Response updateMechanism(Long id, String mechanismName, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			Mechanism mechanism = new Mechanism();
			mechanism.setMechanismName(mechanismName);
			mechanism.setDisableFlag(disableFlag);
			mechanism.setId(id);
			
			Mechanism mec = mechanismMapper.selectByIdAndName(mechanism);
			if(mec!=null) {
				response.setMsg("已存在该机构");
				response.setStatus(1);
				return response;
			}
			
			mechanismMapper.updateByPrimaryKeySelective(mechanism);
			
			response.setStatus(0);
			response.setMsg("更新机构成功");
		} catch (Exception e) {
			response.setStatus(0);
			response.setMsg("更新机构失败");
			e.printStackTrace();
		}
		return response;
	}

	public Response findMechanism(String mechanismName, Integer page, Integer rows) {
		ResponseHasData response = new ResponseHasData();	
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<Mechanism> piq = 
					mechanismMapper.findMechanism(mechanismName);
			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<Mechanism> pageData = new PageInfo<Mechanism>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}	
		return response;
	}

	public Response addDepartment(Long mechanismId, String name, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			Department dep = departmentMapper.selectByName(name);
			if(dep!=null) {
				response.setMsg("部门重复");
				response.setStatus(1);
				return response;
			}
			Department department = new Department();
			department.setMechanismId(mechanismId);
			department.setCreateTime(new Date());
			department.setName(name);
			department.setDisableFlag(disableFlag);
			
			departmentMapper.insertSelective(department);
			
			response.setStatus(0);
			response.setMsg("新增部门成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("新增部门失败");
			e.printStackTrace();
		}
		return response;
	}

	public Response updateDepartment(Long id, String name, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			Department department = new Department();
			department.setId(id);
			department.setUpdateTime(new Date());
			department.setDisableFlag(disableFlag);
			department.setName(name);
			
			Department dep = departmentMapper.selectByIdAndName(department);
			
			departmentMapper.updateByPrimaryKeySelective(department);
			
			response.setStatus(0);
			response.setMsg("新增部门成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("新增部门失败");
			e.printStackTrace();
		}
		return response;
	}

	public Response findDepartment(String mechanismName, String departmentName, Integer disableFlag, Integer page,
			Integer rows) {
		ResponseHasData response = new ResponseHasData();
		Page<?> pa =  PageHelper.startPage(page, rows);
		List<DepartmentQr> piq = 
				departmentMapper.findDepartment(mechanismName,departmentName,disableFlag);
		//查询结果总数
		long total = pa.getTotal();
		//创建分页条件
		PageInfo<DepartmentQr> pageData = new PageInfo<DepartmentQr>(piq,total);
		
		response.setStatus(0);
		response.setMsg("查询成功");
		response.setData(pageData);
			
		return response;
	}

	public Response addDrugStore(String drugStoreName, String contact, String contactPhone, String address,
			Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhone(contactPhone);
			if(cloudPassInfo!=null) {
				response.setMsg("手机号存在重复");
				response.setStatus(1);
				return response;
			}
			CloudPassInfo cloud = new CloudPassInfo();
			cloud.setDisableFlag(0);
			cloud.setName(contact);
			cloud.setPassword("123456");
			cloud.setPhone(contactPhone);
			
			cloudPassInfoMapper.insertSelective(cloud);
			
			DrugStore drugStore = new DrugStore();
			drugStore.setCloudPassNo(cloud.getCloudPassNo());
			drugStore.setContact(contact);
			drugStore.setContactPhone(contactPhone);
			drugStore.setCreateTime(new Date());
			drugStore.setDisableFlag(disableFlag);
			drugStore.setAddress(address);
			drugStore.setDrugStoreName(drugStoreName);
			
			drugStoreMapper.insertSelective(drugStore);
			
			RoleUtil.accredit(cloud.getCloudPassNo(), "药店");
			
			response.setStatus(0);
			response.setMsg("新增成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("新增失败");
			e.printStackTrace();
		}
		
		return response;
	}

	public Response updateDrugStore(Long drugStoreNo, String drugStoreName, String contact, String contactPhone,
			String address, Integer disableFlag, String oldCode) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhoneAndStoreNo(contactPhone,drugStoreNo);
			if(contactPhone!=oldCode) {
				if(cloudPassInfo!=null) {
					response.setMsg("手机号存在重复");
					response.setStatus(1);
					return response;
				}
			}	
			cloudPassInfo.setName(contact);
			cloudPassInfo.setDisableFlag(disableFlag);
			cloudPassInfo.setPhone(contactPhone);
			cloudPassInfo.setUpdateTime(new Date());
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			DrugStore drugStore = drugStoreMapper.selectByCloudPassNo(cloudPassInfo.getCloudPassNo());
			drugStore.setAddress(address);
			drugStore.setContact(contact);
			drugStore.setContactPhone(contactPhone);
			drugStore.setDisableFlag(disableFlag);
			drugStore.setDrugStoreName(drugStoreName);
			drugStoreMapper.updateByPrimaryKeySelective(drugStore);
			
			response.setStatus(0);
			response.setMsg("更新成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("更新成功");
			e.printStackTrace();
		}
		return response;
	}

	public Response findDrugStore(String drugStoreName, Integer disableFlag, 
			Integer page, Integer rows) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<DrugStore> piq = 
					drugStoreMapper.findDrugStore(drugStoreName,disableFlag);
			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<DrugStore> pageData = new PageInfo<DrugStore>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(0);
			response.setMsg("查询失败");
			e.printStackTrace();
		}
			
		return response;
	}

	public Response list(String roleName, Integer page, Integer rows) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<RoleListQr> piq = 
					roleMapper.selectRoleList(roleName);
			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<RoleListQr> pageData = new PageInfo<RoleListQr>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
			return response;
		}
		return response;
	}

	public Response selectByNo(String roleNo) {
		ResponseHasData response = new ResponseHasData();
		try {
			RoleQr roleQr = roleMapper.selectByNo(Integer.parseInt(roleNo));
			List<Authoritys> authoritys = authorityMapper.selectByRoleno(Integer.parseInt(roleNo));
			//List<Authoritys> allAuthoritys = authorityMapper.selectAllauths();
			roleQr.setAuthoritys(authoritys);
			//roleQr.setAllAuthoritys(allAuthoritys);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(roleQr);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			return response;
		}
		return response;
	}

	public Response save(RoleQr roleQr) {
		ResponseNoData response = new ResponseNoData();
		try {
			//新增role
			Role role = new Role();
			role.setRoleName(roleQr.getRoleName());
			role.setCreateTime(new Date());
			roleMapper.insert(role);
			Long roleNo = role.getRoleNo();
			//如果authoritys不为空，怎么循环新增roleAuthority
			List<Authoritys> authoritys = roleQr.getAuthoritys();
			if (authoritys!=null) {
				for (Authoritys authority:authoritys) {
					RoleAuthority roleAuthority = new RoleAuthority();
					roleAuthority.setRoleNo(roleNo);
					roleAuthority.setAuthorityNo(authority.getAuthorityNo());
					roleAuthorityMapper.insert(roleAuthority);
				}
			}			
			response.setStatus(0);
			response.setMsg("新增角色成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("新增角色失败");
			return response;
		}
		return response;
	}

	public Response update(RoleQr roleQr) {
		ResponseNoData response = new ResponseNoData();
		try {
			//修改role
			Role role = roleMapper.selectByPrimaryKey(roleQr.getRoleNo());
			role.setRoleName(roleQr.getRoleName());
			role.setUpdateTime(new Date());
			roleMapper.updateByPrimaryKey(role);
			//先删除原来的角色权限关系
			roleAuthorityMapper.deleteByRoleNo(roleQr.getRoleNo());
			//如果authoritys不为空，怎么循环新增roleAuthority
			List<Authoritys> authoritys = roleQr.getAuthoritys();
			if (authoritys!=null) {
				for (Authoritys authority:authoritys) {
					RoleAuthority roleAuthority = new RoleAuthority();
					roleAuthority.setRoleNo(roleQr.getRoleNo());
					roleAuthority.setAuthorityNo(authority.getAuthorityNo());
					roleAuthorityMapper.insert(roleAuthority);
				}
			}			
			response.setStatus(0);
			response.setMsg("修改成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("修改失败");
			e.printStackTrace();
			return response;
		}
		return response;
	}

	public Response delete(Long roleNo) {
		ResponseHasData response = new ResponseHasData();
		try {
			UserRole userRole = userRoleMapper.selectByRoleNo(roleNo);
			if (userRole!=null) {
				response.setStatus(1);
				response.setMsg("该角色有关联用户，不能进行删除操作");
				return response;
			}
			roleMapper.deleteByPrimaryKey(roleNo);
			roleAuthorityMapper.deleteByRoleNo(roleNo);
			
			response.setStatus(0);
			response.setMsg("删除成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("删除失败");
			return response;
		}
		return response;
	}

	public Response addDrugStorePerson(String name, String phone, Long drugStoreNo) {
		ResponseHasData response = new ResponseHasData();
		
		try {
			CloudPassInfo cloudPassInfo1 = cloudPassInfoMapper.selectByPhone(phone);
			if(cloudPassInfo1!=null) {
				response.setMsg("手机号存在重复");
				response.setStatus(1);
				return response;
			}
			
			CloudPassInfo cloudPassInfo = new CloudPassInfo();
			cloudPassInfo.setDisableFlag(0);
			cloudPassInfo.setName(name);
			cloudPassInfo.setPassword("123456");
			cloudPassInfo.setPhone(phone);
			cloudPassInfoMapper.insertSelective(cloudPassInfo);
			
			DrugStorePerson drugStorePerson = new DrugStorePerson();
			drugStorePerson.setCloudPassNo(cloudPassInfo.getCloudPassNo());
			drugStorePerson.setDrugStoreNo(drugStoreNo);
			drugStorePerson.setName(name);
			drugStorePerson.setPhone(phone);
			
			drugStorePersonMapper.insertSelective(drugStorePerson);
			
			RoleUtil.accredit(cloudPassInfo.getCloudPassNo(), "药店人员");
			
			response.setMsg("新增药店人员成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("新增药店人员失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response updateDrugStorePerson(Long id,Long cloudPassNo, String name, String phone, Long drugStoreNo,
			String oldCode,Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo Info = cloudPassInfoMapper.selectByPhoneAndPassNo(phone,cloudPassNo);
			if(phone!=oldCode) {
				if(Info!=null) {
					response.setMsg("手机号存在重复");
					response.setStatus(1);
					return response;
				}
			}	
			
			CloudPassInfo cloudPassInfo = new CloudPassInfo(); 
			cloudPassInfo.setCloudPassNo(cloudPassNo);
			cloudPassInfo.setName(name);
			cloudPassInfo.setPhone(phone);
			cloudPassInfo.setDisableFlag(disableFlag);
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			DrugStorePerson drugStorePerson = new DrugStorePerson();
			drugStorePerson.setCloudPassNo(cloudPassNo);
			drugStorePerson.setDrugStoreNo(drugStoreNo);
			drugStorePerson.setId(id);
			drugStorePerson.setName(name);
			drugStorePerson.setPhone(phone);
			drugStorePersonMapper.updateByPrimaryKeySelective(drugStorePerson);
			
			response.setMsg("修改成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("修改失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response findDrugStorePerson(String name, String phone, Long drugStoreNo,Integer page,
			Integer rows) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<DrugStorePersonQr> piq = drugStorePersonMapper.selectByNamePhoneNo(name,phone,drugStoreNo);

			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<DrugStorePersonQr> pageData = new PageInfo<DrugStorePersonQr>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}	
		return response;
	}

	public Response addPtInfo(String name, Long cloudPassNo, String idCard) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo1 = cloudPassInfoMapper.selectByNameAndIdCard1(name, idCard);
			if(cloudPassInfo1!=null) {
				response.setMsg("新增患者失败,已存在该患者");
				response.setStatus(1);
				return response;
			}
			
			CloudPassInfo cloudPassInfo = new CloudPassInfo();
			cloudPassInfo.setName(name);
			cloudPassInfo.setIdNo(idCard);
			cloudPassInfo.setDisableFlag(0);
			cloudPassInfoMapper.insertSelective(cloudPassInfo);
			
			PtInfo ptInfo = new PtInfo();
			ptInfo.setName(name);
			ptInfo.setCloudPassNo(cloudPassInfo.getCloudPassNo());
			ptInfo.setDrugStorePersonNo(cloudPassNo);
			ptInfo.setIdCard(idCard);
			ptInfoMapper.insertSelective(ptInfo);
			
			response.setMsg("新增患者成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("新增患者失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response updatePtInfo(Long ptNo, Long cloudPassNo, String name, Long cloudPassNo1, String idCard,
			Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			
			if(ptNo==null) {
				response.setMsg("ptNo为null");
				response.setStatus(1);
				return response;
			}
			if(cloudPassNo==null) {
				response.setMsg("cloudPassNo为null");
				response.setStatus(1);
				return response;
			}
			CloudPassInfo cloudPassInfo = new CloudPassInfo();
			cloudPassInfo.setName(name);
			cloudPassInfo.setIdNo(idCard);
			cloudPassInfo.setDisableFlag(disableFlag);
			cloudPassInfo.setCloudPassNo(cloudPassNo);
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			PtInfo ptInfo = ptInfoMapper.selectByCloudPassNo(cloudPassNo);
			ptInfo.setName(name);
			ptInfo.setDrugStorePersonNo(cloudPassNo1);
			ptInfo.setIdCard(idCard);
			ptInfoMapper.updateByPrimaryKeySelective(ptInfo);
			
			response.setMsg("更新患者信息成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("更新患者信息失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response findPtInfo(String name, Long cloudPassNo, 
			String idCard, Integer page, Integer rows, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<PtInfoQr> piq = ptInfoMapper.findPtInfo(name,cloudPassNo,idCard,disableFlag);

			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<PtInfoQr> pageData = new PageInfo<PtInfoQr>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}	
		return response;
	}


	public Response updateByPhoneAndPass(String phone, String oldPassword, String newPassword) {
		ResponseHasData response = new ResponseHasData();
		try {
			if(phone==null||"".equals(phone)) {
				response.setStatus(1);
				response.setMsg("电话号码为空");
			}else if(oldPassword==null||"".equals(oldPassword)) {
				response.setStatus(1);
				response.setMsg("原密码为空");
			}else if(newPassword==null||"".equals(newPassword)) {
				response.setStatus(1);
				response.setMsg("新密码为空");
			}else {
				CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhone(phone);
				if(oldPassword.endsWith(cloudPassInfo.getPassword())) {
					cloudPassInfo.setPassword(newPassword);
					cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
					response.setStatus(0);
					response.setMsg("修改成功");
				}else {
					response.setStatus(0);
					response.setMsg("原密码错误");
				}
			}
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("修改失败");
		}
		return response;
	}

	public Response sendCodeSms(String phone) {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		Response response = new ResponseHasData();
		Sednsms sednsms = null;
		if (!StringUtils.isEmpty(phone) && phone.length() == 11) {
			try {
				String code = String.valueOf(Math.round(((Math.random() * 9 + 1) * 100000)));
				String content = "您的注册验证码为：" + code;

				// 发送验证码
				boolean sms = AppUtil.sendSms(phone, content);
				if (sms) {
					HttpSession session = request.getSession();
					session.setAttribute(phone, code);
					sednsms = sednsmsMapper.selectByPhone(phone);
					Sednsms sednsmsObject = new Sednsms();
					sednsmsObject.setCode(code);
					sednsmsObject.setExpiryDate(new Date(new Date().getTime() + 600000));
					if (sednsms == null) {
						sednsmsObject.setPhone(phone);
						sednsmsObject.setCreateTime(new Date());
						sednsmsMapper.save(sednsmsObject);
						response.setMsg(phone + "发送注册验证码成功-----");
					} else {
						sednsms.setCode(code);
						sednsms.setPhone(phone);
						sednsms.setExpiryDate(new Date(new Date().getTime() + 600000));
						sednsmsMapper.update(sednsms);
						response.setMsg(phone + "更新验证码成功-----");
					}
					return response;
				} else {
					response.setMsg(phone + "发送注册验证码失败-----");
					response.setStatus(1);
					return response;
				}

			} catch (Exception e) {
				e.printStackTrace();
				response.setMsg(phone + "发送注册验证码失败-----");
				response.setStatus(1);
				return response;
			}
		} else {
			response.setMsg("手机号码有误");
			response.setStatus(1);
			return response;
		}
	}

	public Response updateByCode(String phone, String code, String newCode) {
		Response response = new ResponseNoData();
		CloudPassInfo cloudPassInfo = new CloudPassInfo();
		Sednsms sednsms = new Sednsms();
		try {
			if (StringUtils.isEmpty(phone)) {
				response.setStatus(1);
				response.setMsg("手机号不能为空");
				return response;
			}
			cloudPassInfo = cloudPassInfoMapper.selectByPhone(phone);
			if(cloudPassInfo==null) {
				response.setStatus(1);
				response.setMsg("没有此用户");
				return response;
			}
			sednsms = sednsmsMapper.selectByPhone(phone);
			if (sednsms == null) {
				response.setStatus(1);
				response.setMsg("请点击获取验证码");
				return response;
			}
			if (!sednsms.getCode().equals(code)) {
				response.setStatus(1);
				response.setMsg("验证码错误！");
				return response;
			}
			System.out.println("截止时间查看 ： " + sednsms.getExpiryDate());
			if (sednsms.getExpiryDate().getTime() < new Date().getTime()) {
				response.setStatus(1);
				response.setMsg("验证码失效！");
				return response;
			}
			if (StringUtils.isNotEmpty(newCode)) {
				cloudPassInfo.setPassword(newCode);
				cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
				response.setStatus(0);
				response.setMsg("修改成功！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(1);
			response.setMsg("填写新密码失败");
			return response;
		}
		response.setStatus(0);
		response.setMsg("填写新密码成功");
		return response;

	}

	public Response findMallOrder(Long mallNo, String startTime, String stopTime, Integer checkStatus,
			Integer shippingStatus,Integer orderStatus,Integer page,Integer rows) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<MallOrderQr> piq = mallOrderMapper.findMallOrder(mallNo,startTime,stopTime,
					checkStatus,shippingStatus,orderStatus);
			
			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<MallOrderQr> pageData = new PageInfo<MallOrderQr>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}	
		return response;
	}

	public Response addDrugSet(String drugName, String describe, BigDecimal drugPrice, String unit, String discount,
			Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			DrugSet drugSet = new DrugSet();
			drugSet.setDrugName(drugName);
			drugSet.setDescribe(describe);
			drugSet.setDrugPrice(drugPrice);
			drugSet.setUnit(unit);
			drugSet.setDiscount(discount);
			drugSet.setDisableFlag(disableFlag);
			drugSetMapper.insertSelective(drugSet);
			drugSet.setDetailsImg("http://www.yizhenyun.com.cn/glzy/images/"+drugSet.getDrugNo()+"订单详情页图.jpg");
			drugSet.setListImg("http://www.yizhenyun.com.cn/glzy/images/"+drugSet.getDrugNo()+"订单列表页图.jpg");
			drugSet.setPropagandaImg("http://www.yizhenyun.com.cn/glzy/images/"+drugSet.getDrugNo()+"宣传图.jpg");
			drugSetMapper.updateByPrimaryKeySelective(drugSet);
			
			response.setMsg("新增商品成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("新增商品失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response addImg(String img, String name, int id) {
		ResponseHasData response = new ResponseHasData();
		System.out.println(img);
		try {
			if(id==0) {
				if(null==drugSetMapper.selectMaxId()) {
					Long ids = (long) 1;
					UploadUtil.generateImage(img,"webapps/glzy/WEB-INF/classes/static/images/"+ids+name+".jpg");
					response.setMsg("保存图片成功");
					response.setStatus(0);
					response.setData("http://www.yizhenyun.com.cn/glzy/images/"+ids);
				}else {
					Long ids = drugSetMapper.selectMaxId()+1;
					UploadUtil.generateImage(img,"webapps/glzy/WEB-INF/classes/static/images/"+ids+name+".jpg");
					response.setMsg("保存图片成功");
					response.setStatus(0);
					response.setData("http://www.yizhenyun.com.cn/glzy/images/"+ids);
				}
				
			}else {
				UploadUtil.generateImage(img,"webapps/glzy/WEB-INF/classes/static/images/"+id+name+".jpg");
				response.setMsg("修改图片成功");
				response.setStatus(0);
				response.setData("http://www.yizhenyun.com.cn/glzy/images/"+id);
			}
			
		} catch (Exception e) {
			response.setMsg("保存图片失败");
			response.setStatus(1);
			e.printStackTrace();
		}
			
		return response;
	}

	public Response updateDrugSet(Long drugNo, String drugName, String describe, BigDecimal drugPrice, String unit,
			String discount, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			DrugSet drugSet = new DrugSet();
			drugSet.setDrugNo(drugNo);
			drugSet.setDrugName(drugName);
			drugSet.setDescribe(describe);
			drugSet.setDrugPrice(drugPrice);
			drugSet.setUnit(unit);
			drugSet.setDiscount(discount);
			drugSet.setDisableFlag(disableFlag);
			drugSetMapper.updateByPrimaryKeySelective(drugSet);
			
			response.setMsg("更新产品成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("更新产品失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		return response;
	}

	public Response updateImg(Long drugNo, String img, String name) {
		ResponseHasData response = new ResponseHasData();
		StringBuffer buffer = new StringBuffer(img);

		PhotoUtils photoUtils = new PhotoUtils(buffer,drugNo+"",name);
		String savepath = photoUtils.open();
		
		if(savepath!=null) {	
			response.setMsg("保存图片成功");
			response.setStatus(0);
		}else {
			response.setMsg("保存图片失败");
			response.setStatus(1);
		}
		return response;
	}

	public Response findDrugSet(String drugName, Integer disableFlag,Integer page,Integer rows) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<DrugSet> piq = drugSetMapper.selectByNameAndFlag(drugName,disableFlag);

			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<DrugSet> pageData = new PageInfo<DrugSet>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}	
		return response;
	}

	public Response updateMallOrder(Long mallNo, String shippingCompany, String shippingNo, Long drugStoreNo,
			String drugStorePerson, Integer checkStatus, String cause, String drugA, String drugB) {
		ResponseHasData response = new ResponseHasData();
		try {
			MallOrder mallOrder = new MallOrder();
			mallOrder.setMallNo(mallNo);
			mallOrder.setShippingCompany(shippingCompany);
			mallOrder.setShippingNo(shippingNo);
			mallOrder.setDrugStoreNo(drugStoreNo);
			mallOrder.setDrugStorePerson(drugStorePerson);
			mallOrder.setCheckStatus(checkStatus);
			mallOrder.setCause(cause);
			if(checkStatus==0) {
				mallOrder.setOrderStatus(0);
			}
			if(shippingNo!=null&&!"".equals(shippingNo)) {
				mallOrder.setShippingTime(new Date());
				mallOrder.setShippingStatus(0);
			}else {
				mallOrder.setShippingStatus(1);
			}
			mallOrderMapper.updateByPrimaryKeySelective(mallOrder);
			
			giftMapper.updateByMallNo(mallNo,drugA,drugB);
			
			if(checkStatus==1) {
				MallOrder order = mallOrderMapper.selectByPrimaryKey(mallNo);
				CloudPassInfo info = cloudPassInfoMapper.selectByPrimaryKey(order.getCloudPassNo());
				info.setDisableFlag(1);
				cloudPassInfoMapper.updateByPrimaryKeySelective(info);
			}
			
			response.setMsg("更新订单成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("更新订单失败");
			response.setStatus(1);
			e.printStackTrace();
		}
	
		return response;
	}

	public Response addFactoryPerson(String name, String phone, String sex, Long mechanismNo, Long departmentNo,
			Long departmentNo2, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			CloudPassInfo cloudPassInfo = cloudPassInfoMapper.selectByPhone(phone);
			if(cloudPassInfo!=null) {
				response.setMsg("手机号存在重复");
				response.setStatus(1);
				return response;
			}
			CloudPassInfo cloud = new CloudPassInfo();
			cloud.setDisableFlag(0);
			cloud.setName(name);
			cloud.setPassword("123456");
			cloud.setPhone(phone);
			
			cloudPassInfoMapper.insertSelective(cloud);
			
			FactoryPerson factoryPerson = new FactoryPerson();
			factoryPerson.setCloudPassNo(cloud.getCloudPassNo());
			factoryPerson.setDepartmentNo(departmentNo);
			factoryPerson.setDisableFlag(disableFlag);
			factoryPerson.setMechanismNo(mechanismNo);
			factoryPerson.setName(name);
			factoryPerson.setPhone(phone);
			factoryPerson.setSex(sex);
			
			factoryPersonMapper.insertSelective(factoryPerson);
			
			RoleUtil.accredit(cloud.getCloudPassNo(), "厂家人员");
			
			response.setStatus(0);
			response.setMsg("新增成功");
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("新增失败");
			e.printStackTrace();
		}
		return response;
	}

	public Response updateFactoryPerson(Long id, String name, String phone, String sex,
			Long mechanismNo, Long departmentNo, Integer disableFlag) {
		ResponseHasData response = new ResponseHasData();
		try {
			FactoryPerson factoryPerson = factoryPersonMapper.selectByPhoneId(id,phone);
			if(factoryPerson!=null) {
				response.setMsg("手机号有重复");
				response.setStatus(1);
				return response;
			}
			FactoryPerson person = factoryPersonMapper.selectByPrimaryKey(id);
			person.setDepartmentNo(departmentNo);
			person.setDisableFlag(disableFlag);
			person.setMechanismNo(mechanismNo);
			person.setName(name);
			person.setPhone(phone);
			person.setSex(sex);
			factoryPersonMapper.updateByPrimaryKeySelective(person);
			
			CloudPassInfo cloudPassInfo = new CloudPassInfo();
			cloudPassInfo.setCloudPassNo(person.getCloudPassNo());
			cloudPassInfo.setDisableFlag(disableFlag);
			cloudPassInfo.setName(name);
			cloudPassInfo.setPhone(phone);
			cloudPassInfo.setSex(sex);
			cloudPassInfoMapper.updateByPrimaryKeySelective(cloudPassInfo);
			
			response.setMsg("更新成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("更新失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		
		return response;
	}

	public Response findFactoryperson(String name, String phone, Long mechanismNo, Long departmentNo, Integer page,
			Integer rows) {
		ResponseHasData response = new ResponseHasData();
		try {
			Page<?> pa =  PageHelper.startPage(page, rows);
			List<FactoryPersonQr> piq = factoryPersonMapper.selectByNamePhoneNo(name,phone,mechanismNo,departmentNo);

			//查询结果总数
			long total = pa.getTotal();
			//创建分页条件
			PageInfo<FactoryPersonQr> pageData = new PageInfo<FactoryPersonQr>(piq,total);
			
			response.setStatus(0);
			response.setMsg("查询成功");
			response.setData(pageData);
		} catch (Exception e) {
			response.setStatus(1);
			response.setMsg("查询失败");
			e.printStackTrace();
		}	
		return response;
	}

	public Response findAllAuthority() {
		ResponseHasData response = new ResponseHasData();
		try {
			List<Authority> authorities = authorityMapper.selectAllauths();
			response.setData(authorities);
			response.setMsg("查询成功");
			response.setStatus(0);
		} catch (Exception e) {
			response.setMsg("查询失败");
			response.setStatus(1);
			e.printStackTrace();
		}
		
		
		return response;
	}

	


	

	


}
