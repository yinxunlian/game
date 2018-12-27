package com.game.controller.support;

import com.baomidou.mybatisplus.extension.api.IErrorCode;


/**
 * 错误类型。
 *
 * @author yinhang
 */
public enum ErrorCode implements IErrorCode {
    /** ============================ 通用错误码 ========================== **/
    /**
     * 禁止访问 303
     **/
    PARAMS_NOT(303, "参数缺失"),
    /**
     * 错误请求 400
     **/
    BAD_REQUEST(400, "错误请求"),
    /**
     * 未经授权 401
     **/
    UNAUTHORIZED(401, "未经授权"),
    /**
     * 记录不存在 402
     **/
    EMPTY_DATA(402, "记录不存在"),
    /**
     * 禁止访问 403
     **/
    FORBIDDEN(403, "禁止访问"),
    /**
     * 内部服务器错误 500
     **/
    INTERNAL_SERVER_ERROR(500, "内部服务器错误"),
    /**
     * 操作频繁，请稍后再试
     */
    SEND_MSG_CONTINUALLY(501, "操作频繁，请稍后再试"),
    /**
     * ============================ 业务错误码 ==========================
     **/
    USER_NOT_EXIST(10001, "用户不存在"),
    PASSWORD_ERROR(10002, "输入密码不正确"),
    USER_EXIST(10003, "用户已存在");
    private long   code;
    private String msg;

    ErrorCode(final long code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
