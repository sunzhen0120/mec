package com.njupt.dto.container;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * @author wzm
 * @version 1.0.0
 * @date 2019/7/25 10:06
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class DockerClientDTO {
    /**
     * 私钥和证书文件路径
     */
    private String certAndKeyFilePath;
    /**
     * 主机ip
     */
    private String host;

    /**
     * 端口
     */
    private String port;

    /**
     * 注册用户名
     */
    private String registryUsername;

    /**
     * 注册密码
     */
    private String registryPassword;

    /**
     * 注册邮箱
     */
    private String registryEmail;

    public DockerClientDTO(String host, String port, String certAndKeyFilePath) {
        this.host = host;
        this.port = port;
        this.certAndKeyFilePath = certAndKeyFilePath;
    }

    public DockerClientDTO(String host, String port) {
        this.host = host;
        this.port = port;
    }
}