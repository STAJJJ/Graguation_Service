package com.ping.service;

import com.ping.VO.PageVO;
import com.ping.VO.RecordVO;
import com.ping.entity.Record;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ping.form.CreateForm;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-14
 */
public interface RecordService extends IService<Record> {

    public Boolean create(CreateForm createForm);

    public PageVO patientList( Integer page , Integer size);






}
