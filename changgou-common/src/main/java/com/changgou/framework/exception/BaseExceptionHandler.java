package com.changgou.framework.exception;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName BaseExceptionHandler
 * @Description 统一异常的处理
 * @Author 传智播客
 * @Date 15:16 2020/12/8
 * @Version 2.1
 **/
@ControllerAdvice       // 对controller方法进行增强（controller出错，执行该程序）
public class BaseExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
//        return new Result(false, StatusCode.ERROR, "程序出错了");
        // 开发阶段：程序员自己看原生的错误信息
        return new Result(false, StatusCode.ERROR, e.getMessage());
    }
}
