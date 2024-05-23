package com.ping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ping.VO.PageVO;
import com.ping.VO.RecordVO;
import com.ping.entity.Doctor;
import com.ping.entity.Patient;
import com.ping.entity.Record;
import com.ping.form.CreateForm;
import com.ping.mapper.DoctorMapper;
import com.ping.mapper.PatientMapper;
import com.ping.mapper.RecordMapper;
import com.ping.service.RecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ping.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-03-14
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
    @Autowired
    private PatientMapper patientMapper;
    @Autowired
    private RecordMapper recordMapper;
    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public Boolean create(CreateForm createForm) {
        // 判断用户是否存在
        QueryWrapper<Patient> patientQueryWrapper = new QueryWrapper<>();
        patientQueryWrapper.eq("phone", createForm.getPhone());
        Patient patient = this.patientMapper.selectOne(patientQueryWrapper);

        if (patient == null) {
            // 患者不存在
            return false;
        }

        // 手机号码和姓名是否一致
        if (!patient.getName().equals(createForm.getName())) {
            // 患者姓名和电话不一致
            return false;
        }

        // 判断医生是否存在
        QueryWrapper<Doctor> doctorQueryWrapper = new QueryWrapper<>();
        doctorQueryWrapper.eq("id", createForm.getDoctorid());
        Doctor doctor = this.doctorMapper.selectOne(doctorQueryWrapper);

        if (doctor == null) {
            // 医生不存在
            return false;
        }

        // 用户存在：查询病人id+新增数据
        Record record = new Record();
        record.setPatientId(patient.getId());
        record.setDoctorId(createForm.getDoctorid());
        record.setDescriptionEnbyp(createForm.getDescription());
        record.setRemarkEnbyp(createForm.getRemark());
        record.setAffirm(0);
        record.setCreatetime(CommonUtil.createDate());

        int insert = this.recordMapper.insert(record);
        return insert == 1;
    }

    @Override
    public PageVO patientList(Integer page, Integer size) {
        Page<Record> recordPage = new Page<>(page, size);

        QueryWrapper<Record> queryWrapper = new QueryWrapper<>();
        Page<Record> resultPage = this.recordMapper.selectPage(recordPage, queryWrapper);

        List<Record> recordList = resultPage.getRecords();
        List<RecordVO> recordVOList = new ArrayList<>();

        for (Record record : recordList) {
            RecordVO recordVO = new RecordVO();
            Patient patient = this.patientMapper.selectById(record.getPatientId());
            Doctor doctor = this.doctorMapper.selectById(record.getDoctorId());

            recordVO.setId(record.getId());
            recordVO.setCreatetime(record.getCreatetime());
            recordVO.setName(patient.getName());
            recordVO.setPhone(patient.getPhone());
            recordVO.setSex(patient.getSex());
            recordVO.setAge(patient.getAge());
            recordVO.setOffice(doctor.getOffice());

            // 弹框中的数据
            recordVO.setHospital(doctor.getHospital());
            recordVO.setAffirm(record.getAffirm());
            recordVO.setDescription(record.getDescriptionEnbyp());
            recordVO.setRemark(record.getRemarkEnbyp());

            recordVOList.add(recordVO);
        }

        PageVO pageVO = new PageVO();
        pageVO.setData(recordVOList);
        pageVO.setTotal(recordPage.getTotal());
        return pageVO;
    }
}
