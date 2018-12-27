package com.game.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.game.common.util.RandomUtil;
import com.game.common.util.security.MD5;
import com.game.controller.support.ErrorCode;
import com.game.entity.Account;
import com.game.mapper.AccountMapper;
import com.game.service.IAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


/**
 * <p>
 * 账号表 服务实现类
 * </p>
 *
 * @author yinxunlian
 * @since 2018-12-25
 */
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    /**
     * 登录
     *
     * @param account
     * @return
     */
    @Override
    public Account login(Account account) {
        // 参数校验
        if (account == null || StringUtils.isEmpty(account.getUsername()) || StringUtils
                    .isEmpty(account.getPassword())) {
            Assert.fail(ErrorCode.PARAMS_NOT);
        }
        // 用户名获取用户信息
        Wrapper<Account> wrapper = new QueryWrapper<Account>().lambda().eq(Account::getUsername, account.getUsername());
        Account accountInfo = this.baseMapper.selectOne(wrapper);
        // 用户不存在
        Assert.fail(accountInfo == null, ErrorCode.USER_NOT_EXIST);
        // 密码错误
        if (!accountInfo.getPassword().equals(MD5.md5(account.getPassword() + accountInfo.getSalt()))) {
            Assert.fail(ErrorCode.PASSWORD_ERROR);
        }
        // 更新最后一次登录时间
        Account accountUpd = new Account().builder().userId(accountInfo.getUserId()).lastLoginTime(new Date()).build();
        this.baseMapper.updateById(accountUpd);
        return accountInfo;
    }

    /**
     * 注册
     *
     * @param account
     */
    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void register(Account account) {
        // 参数校验
        if (account == null || StringUtils.isEmpty(account.getUsername()) || StringUtils
                    .isEmpty(account.getPassword())) {
            Assert.fail(ErrorCode.PARAMS_NOT);
        }
        // 判断账号是否存在
        Wrapper<Account> wrapper = new QueryWrapper<Account>().lambda().eq(Account::getUsername, account.getUsername());
        Account accountInfo = this.baseMapper.selectOne(wrapper);
        // 存在抛异常
        Assert.isNull(ErrorCode.USER_EXIST, accountInfo);
        // 获取加密盐
        String salt = RandomUtil.generateRandomCode(6);
        // 当前日期
        Date date = new Date();
        // 密码：MD%(明文+盐)
        account.setCreateTime(date).setLastLoginTime(date).setSalt(salt)
                    .setPassword(MD5.md5(account.getPassword() + salt));
        this.baseMapper.insert(account);
    }
}
