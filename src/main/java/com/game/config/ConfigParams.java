package com.game.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


/**
 * 系统配置属性
 *
 * @author yinhang
 */
@Configuration
@ConfigurationProperties(prefix = "game")
public class ConfigParams {

    private String   environment;
    private Resource resource;

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    /**
     * 资源属性
     */
    public static class Resource {

        /**
         * 资源上传绝对路径。
         */
        private String uploadPath    = "/tmp/game/upload";
        /**
         * 资源访问 URI。
         */
        private String accessUri     = "http://oss.game.com/upload";
        /**
         * 当前环境
         */
        private String profileActive = "prod";

        public String getUploadPath() {
            return uploadPath;
        }

        public void setUploadPath(String uploadPath) {
            this.uploadPath = uploadPath;
        }

        public void setAccessUri(String accessUri) {
            this.accessUri = accessUri;
        }

        public String getAccessUri() {
            if (!accessUri.endsWith("/")) {
                accessUri += "/";
            }
            return accessUri;
        }

        public String getProfileActive() {
            return profileActive;
        }

        public void setProfileActive(String profileActive) {
            this.profileActive = profileActive;
        }
    }
}
