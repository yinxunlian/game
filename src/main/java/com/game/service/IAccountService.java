package com.game.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.game.entity.Account;


/**
 * <p>
 * 账号表 服务类
 * </p>
 *
 * @author jobob
 * @since 2018-12-25
 */
public interface IAccountService extends IService<Account> {

    /**
     * 登录
     * @param account
     * @return
     */
    Account login(Account account);

    /**
     * 注册
     * @param account
     * @return
     */
    void register(Account account);
}
