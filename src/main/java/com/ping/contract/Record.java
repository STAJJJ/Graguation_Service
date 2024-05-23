package com.ping.contract;

public class Record {
    private String recordId;
    private String description;
    private String remark;

    public Record() {
    }

    public Record(String recordId, String description, String remark) {
        this.recordId = recordId;
        this.description = description;
        this.remark = remark;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
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

    @Override
    public String toString() {
        return "Record{" +
                "recordId='" + recordId + '\'' +
                ", description='" + description + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}

