package com.ping.VO;

import lombok.Data;

@Data
public class RecordVO {
    //查看档案
    private Integer id;
    private  String createtime;
    private String name;
    private Long phone;
    private Integer sex;
    private Integer age;
    private String office;

    //弹窗中所需数据
    private String hospital;
    private String description;
    private String remark;
    private Integer affirm;


}
