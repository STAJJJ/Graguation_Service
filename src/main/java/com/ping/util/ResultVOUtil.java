package com.ping.util;

import com.ping.VO.ResultVO;

public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setData(object);
        return resultVO;

    }

    public static ResultVO fail(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(-1);
        return resultVO;

    }
}
