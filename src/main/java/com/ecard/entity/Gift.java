package com.ecard.entity;

public class Gift {
    private Long id;

    private String drugA;

    private String drugB;

    private Long mallNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugA() {
        return drugA;
    }

    public void setDrugA(String drugA) {
        this.drugA = drugA == null ? null : drugA.trim();
    }

    public String getDrugB() {
        return drugB;
    }

    public void setDrugB(String drugB) {
        this.drugB = drugB == null ? null : drugB.trim();
    }

    public Long getMallNo() {
        return mallNo;
    }

    public void setMallNo(Long mallNo) {
        this.mallNo = mallNo;
    }
}