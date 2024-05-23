package com.ping.controller;


import com.ping.VO.ResultVO;
import com.ping.entity.Record;
import com.ping.form.CreateForm;
import com.ping.form.LoginForm;
import com.ping.service.DoctorService;
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
@RequestMapping("/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/login")

    public ResultVO login(LoginForm loginForm) {
        ResultVO resultVO = this.doctorService.login(loginForm);
        return resultVO;

    }


    @Autowired
    private RecordService recordService;

    @PostMapping("/create")
    public ResultVO create(@RequestBody Record record){
        Boolean create = this.recordService.save(record);
        if(!create) return ResultVOUtil.fail();
        return ResultVOUtil.success(null);

    }



}

