package com.ping.controller;

import com.ping.VO.PageVO;
import com.ping.VO.ResultVO;
import com.ping.form.CreateForm;
import com.ping.service.RecordService;
import com.ping.util.ResultVOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @PostMapping("/create")
    public ResultVO create(@RequestBody CreateForm createForm){
        Boolean create = this.recordService.create(createForm);
        if (!create) {
            return ResultVOUtil.fail();
        }
        return ResultVOUtil.success(null);
    }


    @GetMapping("/patientlist/{page}/{size}")
    public ResultVO patientlist(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        PageVO resultPage = this.recordService.patientList(page, size);
        return ResultVOUtil.success(resultPage);
    }
}
