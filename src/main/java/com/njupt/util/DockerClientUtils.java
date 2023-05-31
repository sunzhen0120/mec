package com.njupt.util;


import cn.hutool.json.JSONArray;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.DockerCmdExecFactory;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.DockerClientConfig;
import com.github.dockerjava.jaxrs.JerseyDockerCmdExecFactory;
import com.njupt.dto.container.CreateImagesWithPullReq;
import com.njupt.dto.container.DockerClientDTO;
import org.springframework.util.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DockerClientUtils {
    // D:/docker/tls
// "tcp://192.168.2.133:2375"
// "docker"
// "123456"
// "an23gn@163.com"
    private static DockerClient dockerClient;
    /**
     * 获取DOCKER客户端
     *
     * @param dockerClientDTO docker客户端连接信息
     * @return DockerClient
     */
    public static DockerClient getDockerClient(DockerClientDTO dockerClientDTO) {
        // 进行安全认证
        DockerClientConfig config = DefaultDockerClientConfig.createDefaultConfigBuilder()
                // 服务器ip
                .withDockerHost("tcp://" + dockerClientDTO.getHost() + ":" + dockerClientDTO.getPort())
                .withDockerTlsVerify(true)
                // 证书的本地位置
                .withDockerCertPath(dockerClientDTO.getCertAndKeyFilePath())
                // 私钥的本地位置
                //.withDockerConfig(dockerClientDTO.getCertAndKeyFilePath())
                // API版本 可通过在服务器 docker version 命令查看
                .withApiVersion("1.31")
                // 默认
                .withRegistryUrl("https://index.docker.io/v1/")
                // 默认
                .withRegistryUsername(dockerClientDTO.getRegistryUsername())
                // 默认
                .withRegistryPassword(dockerClientDTO.getRegistryPassword())
                // 默认
                .withRegistryEmail(dockerClientDTO.getRegistryEmail())
                .build();
        // docker命令执行工厂
        DockerCmdExecFactory dockerCmdExecFactory = new JerseyDockerCmdExecFactory()
                .withReadTimeout(60000)
                .withConnectTimeout(60000)
                .withMaxTotalConnections(100)
                .withMaxPerRouteConnections(10);
        dockerClient = DockerClientBuilder.getInstance(config).withDockerCmdExecFactory(dockerCmdExecFactory).build();
        return dockerClient;
    }

    public static void main(String[] args) {
        dockerClient = DockerClientUtils.getDockerClient(new DockerClientDTO("192.168.2.134", "2375", "D:/docker/tls"));
        Info info = queryClientInfo(dockerClient);
        System.out.println(info);
    }
    /**
     * 连接信息
     *
     * @param dockerClient DOCKER客户端
     * @return Object
     */
    public static Info queryClientInfo(DockerClient dockerClient) {
        return dockerClient.infoCmd().exec();
    }
    /**
     * 查看镜像
     *
     * @param dockerClient DOCKER客户端
     * @return Object
     */
    public static List<Image> queryImagesList(DockerClient dockerClient) {
        return dockerClient.listImagesCmd().exec();
    }
    /**
     * 停止容器
     *
     * @param dockerClient DOCKER客户端
     * @param container 容器ID
     * @return Object
     */
    public static Object stopContainer(DockerClient dockerClient, String container) {
        return dockerClient.stopContainerCmd(container).exec();
    }
    /**
     * 删除镜像
     *
     * @param dockerClient DOCKER客户端
     * @param imagesID 镜像ID
     * @return Object
     */
    public static Object removeImages(DockerClient dockerClient, String imagesID) {
        return dockerClient.removeImageCmd(imagesID).exec();
    }
    /**
     * 删除容器
     *
     * @param dockerClient DOCKER客户端
     * @param container 容器ID
     * @return Object
     */
    public static Object removeContainer(DockerClient dockerClient, String container) {
        return dockerClient.removeContainerCmd(container).exec();
    }
    /**
     * 创建容器
     *
     * @param dockerClient DOCKER客户端
     * @param imagesID 镜像ID
     * @return Object
     */
    public static Object createContainer(DockerClient dockerClient, String imagesID) {
        return dockerClient.createContainerCmd(imagesID).exec();
    }
    /**
     * 创建一个镜像
     *
     * @param dockerClient DOCKER客户端
     * @return Object
     * @throws FileNotFoundException 找不到文件
     */
    public static Object createImages(DockerClient dockerClient, CreateImagesWithPullReq createImagesReq) throws FileNotFoundException {
//        //仓库地址
//        String repository = createImagesReq.getRepository();
//        //镜像文件流
//        InputStream imageStream = new FileInputStream(createImagesReq.getImageFilePath());
//        return dockerClient.createImageCmd(repository, imageStream).exec();
        return null;
    }

    /**
     * 容器列表(运行中的)
     *
     * @param dockerClient DOCKER客户端
     * @return Object
     */
    public static List<Container> listContainersCmd(DockerClient dockerClient) {
        return dockerClient.listContainersCmd().exec();
    }

    public static List<String> getContainerNameList(List<Container> containerList) {
        List<String> containerNameList = new ArrayList<>();
        for (Container container : containerList) {
            String containerName = container.getNames()[0].replace("/", "");
            containerNameList.add(containerName);
        }
        return containerNameList;
    }
    /**
     * 启动容器
     *
     * @param dockerClient DOCKER客户端
     * @param containerID 容器ID
     */
    public static Object startContainerCmd(DockerClient dockerClient, String containerID) {
        return dockerClient.startContainerCmd(containerID).exec();
    }
    /**
     * 重启容器
     *
     * @param dockerClient 客户端
     * @param containerID 容器id
     * @return java.lang.Object
     * @author wzm
     * @date 2019/9/28 15:30
     */
    public static Object restartContainerCmd(DockerClient dockerClient, String containerID) {
        return dockerClient.restartContainerCmd(containerID).exec();
    }
    /**
     * 从本地上传资源到容器
     *
     * @param dockerClient 客户端
     * @param containerID 容器id
     * @param resource 本地资源路径
     * @param remote 服务器资源路径
     * @return Object
     */
    public static Object copyArchiveToContainerCmd(DockerClient dockerClient, String containerID, String resource, String remote) {
        return dockerClient.copyArchiveToContainerCmd(containerID).withHostResource(resource).withRemotePath(remote).exec();
    }
    /**
     * 从容器下载资源到本地
     *
     * @param dockerClient 客户端
     * @param containerID 容器id
     * @param local 本地路径
     * @param remote 远程容器路径
     * @return Object
     */
    public static Object copyArchiveFromContainerCmd(DockerClient dockerClient, String containerID, String local, String remote) {
        try {
// String path = "F:\\tmp\\wealth.rar"
// remote="/tmp/wealth.rar"

            InputStream input = dockerClient
                    .copyArchiveFromContainerCmd(containerID, remote)
                    .exec();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(local);
            while ((index = input.read(bytes)) != -1) {
                downloadFile.write(bytes, 0, index);
                downloadFile.flush();
            }
            input.close();
            downloadFile.close();
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    /**
     * 根据容器名获取容器ID
     *
     * @param dockerClient 容器客户端
     * @param containerName 容器名
     * @return java.lang.String
     * @author wzm
     * @date 2019/9/28 15:38
     */
    public static String getContainerIdByName(DockerClient dockerClient, String containerName) {
        try {
            String containerId = "";
            Object object = DockerClientUtils.listContainersCmd(dockerClient);
            JSONArray jsonArray = new JSONArray(object);
            for (int i = 0; i < jsonArray.size(); i++) {
                String name = jsonArray.getJSONObject(i).getStr("names");
                name = name.replace("[\"/", "").replace("\"]", "");
                if (!StringUtils.isEmpty(name) && name.equals(containerName)) {
                    containerId = jsonArray.getJSONObject(i).getStr("id");
                }
            }
            return containerId;
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
