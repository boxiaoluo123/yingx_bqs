package com.baizhi.bqs.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {

    private String status;//状态
    private String message;//信息
    private Object data;//数据

    public CommonResult success(String status,String message,Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus(status);
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult success(String message,Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("100");
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult success(Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("100");
        commonResult.setMessage("查询成功");
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult failed(String message,Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("104");
        commonResult.setMessage(message);
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult failed(Object data){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("104");
        commonResult.setMessage("查询失败");
        commonResult.setData(data);
        return commonResult;
    }

    public CommonResult failed(){
        CommonResult commonResult = new CommonResult();
        commonResult.setStatus("104");
        commonResult.setMessage("查询失败");
        commonResult.setData(null);
        return commonResult;
    }

}
