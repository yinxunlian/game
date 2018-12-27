package com.game.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.R;
import com.game.controller.support.ErrorCode;
import com.game.entity.Account;
import com.game.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


/**
 * <p>
 * 账号表 前端控制器
 * </p>
 *
 * @author yinhang
 * @since 2018-12-25 11:00
 */
@RestController
@RequestMapping("account")
public class AccountController extends ApiController {

    @Autowired
    private IAccountService accountService;

    /**
     * 登录
     *
     * @param account
     * @return
     */
    @PostMapping("login")
    public R<Account> login(Account account) {
        Account accountInfo = accountService.login(account);
        return success(accountInfo);
    }

    /**
     * 注册
     *
     * @param account
     * @return
     */
    @PostMapping("register")
    public R<Object> register(Account account) {
        accountService.register(account);
        return success(true);
    }

    /**
     * 修改
     *
     * @param account
     * @return
     */
    @PostMapping("update")
    public R<Object> update(Account account) {
        Assert.notNull(ErrorCode.PARAMS_NOT, account, account.getUserId());
        account.setUpdateTime(new Date());
        accountService.updateById(account);
        return success(true);
    }
}

