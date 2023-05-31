package com.njupt.config;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.njupt.dto.container.DockerClientDTO;
import com.njupt.util.DockerClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DockerClientConfig {

    @Value("${dockerClientConfig.hostIP}")
    private String hostIP;

    @Value("${dockerClientConfig.port}")
    private String port;

    @Bean
    public DockerClient getDockerClient() {
        //连接Docker服务器
        DockerClient dockerClient = DockerClientBuilder.getInstance("tcp://" + hostIP + ":" + port).build();
        dockerClient.infoCmd().exec();
        return dockerClient;
    }
}
