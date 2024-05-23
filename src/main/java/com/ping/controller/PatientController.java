package com.ping.controller;


import com.ping.VO.ResultVO;
import com.ping.form.CreateForm;
import com.ping.form.LoginForm;
import com.ping.service.DoctorService;
import com.ping.service.PatientService;
import com.ping.service.RecordService;
import com.ping.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author admin
 * @since 2024-03-14
 */
@RestController
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/login")
    public ResultVO login(LoginForm loginForm) {
        ResultVO resultVO = this.patientService.login(loginForm);
        return resultVO;
    }


}

