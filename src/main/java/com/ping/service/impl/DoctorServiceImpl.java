package com.ping.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ping.VO.ResultVO;
import com.ping.entity.Doctor;
import com.ping.form.LoginForm;
import com.ping.mapper.DoctorMapper;
import com.ping.service.DoctorService;
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
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements DoctorService {


    @Autowired
    private DoctorMapper doctorMapper;

    @Override
    public ResultVO login(LoginForm loginForm) {
        //1.判断用户是否存在（手机号码）
        QueryWrapper<Doctor> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", loginForm.getPhone());
        Doctor doctor = this.doctorMapper.selectOne(queryWrapper);
        ResultVO resultVO = new ResultVO();
        if (doctor == null) {
            resultVO.setCode(-1);
        } else {
            //2.判断密码是否正确
            if(!doctor.getPassword().equals(loginForm.getPassword())){
                resultVO.setCode(-2);
            }else{
                resultVO.setCode(0);
                resultVO.setData(doctor);
            }
        }
        return resultVO;
    }







}
