
package com.game.common;

/**
 * 常量
 *
 * @author yinxu
 * @ClassName: Constants
 * @date 2018年3月26日 下午6:25:46
 */
public interface Constants {

    /**
     * 字典key
     *
     * @author yinxu
     * @ClassName: Dict
     * @date 2018年3月26日 下午6:27:07
     */
    interface Dict {

        /**
         * 系统配置项
         */
        String SYS_CONFIG = "sysConfig";
    }


    /**
     * 系統配置项
     *
     * @author yinxu
     * @ClassName: SysConfig
     * @date 2018年3月26日 下午6:27:07
     */
    interface SysConfig {

        /**
         * 短信频道，1:阿里云 2:253
         */
        String SMS_CHANNEL = "sms_channel";
    }


    /**
     * 删除标识：0未删除，1删除
     *
     * @author Administrator
     * @ClassName: deleted
     * @Description: 删除标记位
     * @date 2018年5月3日 上午9:01:51
     */
    interface Deleted {

        byte DELETED     = 1;
        byte NOT_DELETED = 0;
    }
}
