package com.ping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ping.VO.ResultVO;
import com.ping.entity.Patient;
import com.ping.entity.Patient;
import com.ping.form.LoginForm;
import com.ping.mapper.PatientMapper;
import com.ping.service.PatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2024-03-14
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements PatientService {

   @Autowired
    private PatientMapper patientMapper;

   @Override
   public ResultVO login(LoginForm loginForm) {
       //1.判断用户是否存在（手机号码）
       QueryWrapper<Patient> queryWrapper = new QueryWrapper<>();
       queryWrapper.eq("phone", loginForm.getPhone());
       Patient patient = this.patientMapper.selectOne(queryWrapper);
       ResultVO resultVO = new ResultVO();
       if (patient == null) {
           resultVO.setCode(-1);
       } else {
           //2.判断密码是否正确
           if(!patient.getPassword().equals(loginForm.getPassword())){
               resultVO.setCode(-2);
           }else{
               resultVO.setCode(0);
               resultVO.setData(patient);
           }
       }
       return resultVO;
   }




}
