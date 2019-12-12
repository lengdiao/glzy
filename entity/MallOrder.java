package com.ecard.entity;

import java.math.BigDecimal;
import java.util.Date;

public class MallOrder {
    private Long mallno;

    private Long cloudpassno;

    private Long drno;
    
    private String drName;
    private String disName;
    private String name;
    private String phone;
    
    private String drugName;
    private Double drugPrice;
    private String commonName;
    private String number ;
    private DrugCacheQr[] drugcacheqr; 
    

    private Long disno;

    private Long cacheid;
       
    private Integer orderstatus;

    private Date ordertime;

    private String address;

    private Integer paystatus;

    private Date paytime;

    private Integer shippingstatus;

    private String shippingcompany;

    private String shippingno;
    
    private String shippingContext;
    
    private Date shippingtime;

    private BigDecimal orderamount;

    private Long drugstoreno;

    private String createuser;

    private Date createtime;

    private String updateuser;

    private Date updatetime;

    private Integer version;

    public Long getMallno() {
        return mallno;
    }

    public void setMallno(Long mallno) {
        this.mallno = mallno;
    }

    public Long getCloudpassno() {
        return cloudpassno;
    }

    public void setCloudpassno(Long cloudpassno) {
        this.cloudpassno = cloudpassno;
    }

    public Long getDrno() {
        return drno;
    }

    public void setDrno(Long drno) {
        this.drno = drno;
    }

    public Long getDisno() {
        return disno;
    }

    public void setDisno(Long disno) {
        this.disno = disno;
    }

    public Long getCacheid() {
        return cacheid;
    }

    public void setCacheid(Long cacheid) {
        this.cacheid = cacheid;
    }

    public Integer getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(Integer orderstatus) {
        this.orderstatus = orderstatus;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getPaystatus() {
        return paystatus;
    }

    public void setPaystatus(Integer paystatus) {
        this.paystatus = paystatus;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Integer getShippingstatus() {
        return shippingstatus;
    }

    public void setShippingstatus(Integer shippingstatus) {
        this.shippingstatus = shippingstatus;
    }

    public String getShippingcompany() {
        return shippingcompany;
    }

    public void setShippingcompany(String shippingcompany) {
        this.shippingcompany = shippingcompany == null ? null : shippingcompany.trim();
    }

    public String getShippingno() {
        return shippingno;
    }

    public void setShippingno(String shippingno) {
        this.shippingno = shippingno == null ? null : shippingno.trim();
    }
        
    
    public String getShippingContext() {
		return shippingContext;
	}

	public void setShippingContext(String shippingContext) {
		this.shippingContext = shippingContext;
	}

	public Date getShippingtime() {
        return shippingtime;
    }

    public void setShippingtime(Date shippingtime) {
        this.shippingtime = shippingtime;
    }

    public BigDecimal getOrderamount() {
        return orderamount;
    }

    public void setOrderamount(BigDecimal orderamount) {
        this.orderamount = orderamount;
    }

    public Long getDrugstoreno() {
        return drugstoreno;
    }

    public void setDrugstoreno(Long drugstoreno) {
        this.drugstoreno = drugstoreno;
    }

    public String getCreateuser() {
        return createuser;
    }

    public void setCreateuser(String createuser) {
        this.createuser = createuser == null ? null : createuser.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUpdateuser() {
        return updateuser;
    }

    public void setUpdateuser(String updateuser) {
        this.updateuser = updateuser == null ? null : updateuser.trim();
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

	public String getDrName() {
		return drName;
	}

	public void setDrName(String drName) {
		this.drName = drName;
	}

	public String getDisName() {
		return disName;
	}

	public void setDisName(String disName) {
		this.disName = disName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDrugName() {
		return drugName;
	}

	public void setDrugName(String drugName) {
		this.drugName = drugName;
	}

	public Double getDrugPrice() {
		return drugPrice;
	}

	public void setDrugPrice(Double drugPrice) {
		this.drugPrice = drugPrice;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public DrugCacheQr[] getDrugcacheqr() {
		return drugcacheqr;
	}

	public void setDrugcacheqr(DrugCacheQr[] drugcacheqr) {
		this.drugcacheqr = drugcacheqr;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
    
	
    
}