package com.ping.contract;


public class GetRecord {
    private String description;
    private String remark;

    public GetRecord(String description, String remark) {
        this.description = description;
        this.remark = remark;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
