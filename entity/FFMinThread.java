package com.ecard.entity;

import org.springframework.beans.factory.annotation.Autowired;

import com.ecard.mapper.CacheMapper;
import com.ecard.mapper.DrugStoreMapper;
import com.ecard.mapper.MallorderMapper;
import com.ecard.mapper.MessageMapper;
import com.ecard.utils.OpenIdUtil;

public class FFMinThread  extends Thread{
	
	@Autowired
	private Long mallNo;
	@Autowired
	private MallorderMapper mallOrderMapper;
	@Autowired
	private CacheMapper cacheMapper;
	@Autowired
	private MessageMapper messageMapper;
	@Autowired
	private DrugStoreMapper drugStoreMapper;
	
	
	 public FFMinThread(Long mallNo,MallorderMapper mallOrderMapper,CacheMapper cacheMapper,MessageMapper messageMapper,DrugStoreMapper drugStoreMapper) {
		 this.mallNo = mallNo;
		 this.mallOrderMapper=mallOrderMapper;
		 this.cacheMapper=cacheMapper;
		 this.messageMapper=messageMapper;
		 this.drugStoreMapper=drugStoreMapper;
	    }
	 
	 
	 
		public void run() {
			int millis=2*60*1000;
	            try {
	            System.out.println(mallNo);
	                sleep(millis);
	            MallOrder mallOrder= mallOrderMapper.selectByPrimaryKey(mallNo);
	           if (mallOrder!=null) {
	        	   if(mallOrder.getShippingstatus()==3) {
	        		   OpenIdUtil opu=new OpenIdUtil();
	        		   opu.getOpenidsms(mallNo,1,mallOrderMapper,cacheMapper,messageMapper,drugStoreMapper);
	        	   }else {
	        		   OpenIdUtil opu=new OpenIdUtil();
	        		   opu.getOpenidsms(mallNo,2,mallOrderMapper,cacheMapper,messageMapper,drugStoreMapper);
	        	   }
	           }
	            		stop(); 
	             System.out.println("关闭方法");
	            } catch (InterruptedException e) {
	                e.printStackTrace(); 
	            }
	      
	}

}
