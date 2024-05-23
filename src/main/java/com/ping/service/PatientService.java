package com.ping.service;

import com.ping.VO.ResultVO;
import com.ping.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;
import com.ping.form.LoginForm;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author admin
 * @since 2024-03-14
 */
public interface PatientService extends IService<Patient> {
    public ResultVO login(LoginForm loginForm);

}
