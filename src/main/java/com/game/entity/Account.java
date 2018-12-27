package com.game.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;


/**
 * <p>
 * 账号表
 * </p>
 *
 * @author jobob
 * @since 2018-12-25
 */
@Data
@Accessors(chain = true)
@TableName("user_account")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private static final long    serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId(type = IdType.UUID)
    private              String  userId;
    /**
     * 用户名
     */
    private              String  username;
    /**
     * 密码
     */
    @JsonIgnore
    private              String  password;
    /**
     * 加密盐
     */
    @JsonIgnore
    private              String  salt;
    /**
     * 最后一次登陆时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private              Date    lastLoginTime;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private              Date    createTime;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private              Date    updateTime;
    /**
     * 删除标识：0未删除，1删除
     */
    private              Integer deleted;
}
