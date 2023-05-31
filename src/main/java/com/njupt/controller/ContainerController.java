package com.njupt.controller;

import com.njupt.dto.container.*;
import com.njupt.service.ContainerService;
import com.njupt.util.RestUtil;
import io.minio.errors.ErrorResponseException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;


@RestController
@Api(tags = "容器管理", value = "容器管理")
public class ContainerController {

    @Autowired
    private ContainerService containerService;

    @PostMapping("containers/getInfo")
    @ApiOperation("获取连接信息")
    public void getInfo(HttpServletResponse response) {
        RestUtil.printData(response, containerService.getInfo());
    }

    @PostMapping("containers/create")
    @ApiOperation("创建容器")
    public void createContainers(HttpServletResponse response, @RequestBody CreateContainersReq createContainersReq) {
        RestUtil.printData(response, containerService.createContainers(createContainersReq));
    }

    @PostMapping("containers/start")
    @ApiOperation("启动容器")
    public void startContainers(HttpServletResponse response, @RequestBody StartContainersReq startContainersReq) {
        RestUtil.printData(response, containerService.startContainers(startContainersReq));
    }

    @PostMapping("containers/list")
    @ApiOperation("列出所有运行中的容器")
    public void listContainers(HttpServletResponse response) {
        RestUtil.printData(response, containerService.listContainers());
    }

    @PostMapping("containers/restart")
    @ApiOperation("重启容器")
    public void restartContainers(HttpServletResponse response, @RequestBody RestartContainersReq restartContainersReq) {
        RestUtil.printData(response, containerService.restartContainers(restartContainersReq));
    }

    @PostMapping("containers/getIdByName")
    @ApiOperation("查询容器id")
    public void getContainerIdByName(HttpServletResponse response, @RequestParam String containerName) {
        RestUtil.printData(response, containerService.getContainerIdByName(containerName));
    }

    @PostMapping("containers/remove")
    @ApiOperation("删除容器")
    public void removeContainers(HttpServletResponse response, @RequestBody RemoveContainersReq removeContainersReq) {
        RestUtil.printData(response, containerService.removeContainers(removeContainersReq));
        /*
            删除前先判断是否是停止状态
            否则返回 500：You cannot remove a running container a98e9384b50e8c251dfdcd4e3ec7da28867729696b25af1c8f3bf7223350a048.
        */
    }

    @PostMapping("containers/stop")
    @ApiOperation("停止容器")
    public void stopContainers(HttpServletResponse response, @RequestBody StopContainersReq stopContainersReq) {
        RestUtil.printData(response, containerService.stopContainers(stopContainersReq));
    }

    @PostMapping("images/list")
    @ApiOperation("列出所有镜像")
    public void listImages(HttpServletResponse response) {
        RestUtil.printData(response, containerService.listImages());
    }

    @PostMapping("images/search")
    @ApiOperation("搜索镜像")
    public void searchImages(HttpServletResponse response, @RequestBody SearchImagesReq searchImagesReq) {
        RestUtil.printData(response, containerService.searchImages(searchImagesReq));
    }

    @PostMapping("images/create/pull")
    @ApiOperation("创建镜像（pull）")
    public void createImagesWithPull(HttpServletResponse response, @RequestBody CreateImagesWithPullReq createImagesWithPullReq) throws Exception {
        RestUtil.printData(response, containerService.createImagesWithPull(createImagesWithPullReq));
        /*
        pull started
        Pulling from library/hello-world
        Digest: sha256:62af9efd515a25f84961b70f973a798d2eca956b1b2b026d0a4a63a3b0b6a3f2
        Status: Image is up to date for hello-world:latest
        pull finished
        */
    }

    /*@PostMapping("images/create/upload")
    @ApiOperation("创建镜像（upload）")
    public void createImagesWithUpload(HttpServletResponse response, @RequestBody CreateImagesWithUploadReq createImagesWithUploadReq) throws Exception {
        RestUtil.printData(response, containerService.createImagesWithUpload(createImagesWithUploadReq));
    }*/

    @PostMapping("images/create/upload")
    @ApiOperation("创建镜像（upload）")
    public void createImagesWithUpload(HttpServletResponse response,
                                       @RequestPart("imageFile") MultipartFile multipartFile,
                                       ////REPOSITORY：表示镜像的仓库源
                                       @RequestParam String repository) throws Exception {
        RestUtil.printData(response, containerService.createImagesWithUpload1(multipartFile, repository));
    }

    @PostMapping("images/remove")
    @ApiOperation("删除镜像")
    public void removeImages(HttpServletResponse response, @RequestBody RemoveImagesReq removeImagesReq) {
        RestUtil.printData(response, containerService.removeImages(removeImagesReq));
        /*conflict: unable to delete 2d389e545974 (cannot be forced) - image is being used by running container 2d2e02d84201*/
    }

//    @PostMapping("images/save")
//    @ApiOperation("保存镜像")
//    public void saveImages(HttpServletResponse response, @RequestBody SaveImagesReq saveImagesReq) throws IOException {
//        RestUtil.printData(response, containerService.saveImages(saveImagesReq));
//        /*conflict: unable to delete 2d389e545974 (cannot be forced) - image is being used by running container 2d2e02d84201*/
//    }

    @PostMapping("images/save")
    @ApiOperation("保存镜像")
    public void saveImages(HttpServletResponse response, @RequestBody SaveImagesReq saveImagesReq) throws IOException {
        containerService.saveImages(response, saveImagesReq);
    }



    @PostMapping("copyArchiveToContainer")
    @ApiOperation("从本地上传资源到容器")
    public void copyArchiveToContainer(HttpServletResponse response, @RequestBody CopyArchiveToContainerReq copyArchiveToContainerReq) {
        RestUtil.printData(response, containerService.copyArchiveToContainer(copyArchiveToContainerReq));
        /*
        [root@zkSpringCloud docker_certs]# docker exec -it sny_hello /bin/bash
        root@2d2e02d84201:/# cd /opt/
        root@2d2e02d84201:/opt# ls
        test.txt
        */
    }

    @PostMapping("copyArchiveFromContainer")
    @ApiOperation("从容器下载资源到本地")
    public void copyArchiveFromContainer(HttpServletResponse response, @RequestBody CopyArchiveToContainerReq copyArchiveToContainerReq) {
        RestUtil.printData(response, containerService.copyArchiveFromContainer(copyArchiveToContainerReq));
    }
}
