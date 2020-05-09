package com.cloud.payment.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.cloud.common.serializable.ResultCode;
import com.cloud.common.serializable.ResultJson;

/**
 * @Description
 * @Project spring-cloud-application
 * @Package com.cloud.payment.handler
 * @Author xuzhenkui
 * @Date 2020/5/4 11:09
 */
public class CustomBlockHandler {

    public static ResultJson handleException1(BlockException e){
        return new ResultJson(ResultCode.BAD_REQUEST,"BAD_REQUEST");
    }

    public static ResultJson handleException2(BlockException e){
        return new ResultJson(ResultCode.FORBIDDEN,"FORBIDDEN");
    }
}
