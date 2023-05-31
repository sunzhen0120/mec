package com.njupt.service.impl;


import cn.hutool.json.JSONArray;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.async.ResultCallback;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.command.SaveImageCmd;
import com.github.dockerjava.api.model.Info;
import com.github.dockerjava.api.model.PullResponseItem;
import com.github.dockerjava.api.model.SearchItem;
import com.njupt.dto.container.*;
import com.njupt.res.CommonResult;
import com.njupt.service.ContainerService;
import com.njupt.util.DockerClientUtils;
import io.minio.errors.ErrorResponseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class ContainerServiceImpl implements ContainerService {

    @Autowired
    DockerClient dockerClient;

    @Override
    public Object getInfo() {
        Info info = DockerClientUtils.queryClientInfo(dockerClient);
        return info;
    }

    @Override
    public Object listImages() {
        return dockerClient.listImagesCmd().exec();
    }

    @Override
    public Object searchImages(SearchImagesReq searchImagesReq) {
        List<SearchItem> dockerSearch = dockerClient.searchImagesCmd(searchImagesReq.getImageName()).exec();
        return dockerSearch;
    }

    @Override
    public Object createImagesWithPull(CreateImagesWithPullReq createImagesWithPullReq) {

        StringBuilder result = new StringBuilder();

        dockerClient.pullImageCmd(createImagesWithPullReq.getImageName() + ":" + createImagesWithPullReq.getVersion())
                .exec(new ResultCallback<PullResponseItem>() {
                    @Override
                    public void onStart(Closeable closeable) {
//                        result.append("pull started");
                        System.out.println("pull started");
                    }

                    @Override
                    public void onNext(PullResponseItem pullResponseItem) {
//                        result.append(pullResponseItem.getStatus());
                        System.out.println(pullResponseItem.getStatus());
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        throwable.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
//                        result.append("pull finished");
                        System.out.println("pull finished");
                    }

                    @Override
                    public void close() throws IOException {

                    }
                });

        return result;
    }

    @Override
    public Object createImagesWithUpload1(MultipartFile multipartFile, String repository) throws IOException {

        //镜像文件流
        InputStream imageStream = multipartFile.getInputStream();

        return dockerClient.createImageCmd(repository, imageStream).exec();
    }

    @Override
    public Object createImagesWithUpload(CreateImagesWithUploadReq createImagesWithUploadReq) throws Exception {

//        MultipartFile imageFile = createImagesWithUploadReq.getImageFile();
//        InputStream imageStream = imageFile.getInputStream();
        //仓库地址
        String repository = createImagesWithUploadReq.getRepository();
        //镜像文件流
        InputStream imageStream = new FileInputStream(createImagesWithUploadReq.getImageFilePath());
        return dockerClient.createImageCmd(repository, imageStream).exec();

//        public static String loadImage(String imageFilePath, String currImageId) {
//            String uploadImageName = "";
//            File file = new File(imageFilePath);
//            try (InputStream inputStream = new FileInputStream(file)) {
//                System.out.println("Start LoadImage ====>" +  imageFilePath);
//                DockerUtils.loadImage(docekrClient, inputStream);
//                List<Image> imageList = DockerUtils.imageList(docekrClient);
//                for (Image image : imageList) {
//                    if (currImageId.contains(image.getId())) {
//                        uploadImageName = image.getRepoTags()[0];
//                    }
//                }
//
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return uploadImageName;

        }

    @Override
    public Object createContainers(CreateContainersReq createContainersReq) {
        CreateContainerResponse container = dockerClient.createContainerCmd(createContainersReq.getImagesName())
                .withName(createContainersReq.getContainerName())
                .exec();
        return container;
    }

    @Override
    public Object removeImages(RemoveImagesReq removeImagesReq) {
        return dockerClient.removeImageCmd(removeImagesReq.getImagesId()).exec();
    }


    @Value("${dockerSaveImagePath}")
    private String path;

//    @Override
//    public Object saveImages(SaveImagesReq saveImagesReq) throws IOException {
//        InputStream stream = dockerClient.saveImageCmd(saveImagesReq.getImagesName()).withTag(saveImagesReq.getVersion()).exec();
//        writeToLocal(path + "/" + saveImagesReq.getImagesName() + ".tar", stream);
//        return CommonResult.success("镜像保存路径：" + path);
//    }

    @Override
    public void saveImages(HttpServletResponse response, SaveImagesReq saveImagesReq) throws IOException {

        InputStream file = dockerClient.saveImageCmd(saveImagesReq.getImagesName()).withTag(saveImagesReq.getVersion()).exec();
        String filename = new String(saveImagesReq.getImagesName().getBytes(StandardCharsets.UTF_8), "ISO8859-1");
        response.setHeader("Content-Disposition", "attachment;filename=" + filename);
        ServletOutputStream servletOutputStream = response.getOutputStream();
        int len;
        byte[] buffer = new byte[1024];
        while ((len = file.read(buffer)) > 0) {
            servletOutputStream.write(buffer, 0, len);
        }
        servletOutputStream.flush();
        file.close();
        servletOutputStream.close();
    }

    /**
     * 将InputStream写入本地文件
     * @param destination 写入本地目录
     * @param input 输入流
     * @throws IOException IOException
     */
    public static void writeToLocal(String destination, InputStream input)
            throws IOException {
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        input.close();
        downloadFile.close();

    }


    @Override
    public Object startContainers(StartContainersReq startContainersReq) {
        dockerClient.startContainerCmd(startContainersReq.getContainerId()).exec();
        return dockerClient;
    }

    @Override
    public Object listContainers() {
        return dockerClient.listContainersCmd().withShowAll(true).exec();
    }

    @Override
    public Object restartContainers(RestartContainersReq restartContainersReq) {
        return dockerClient.restartContainerCmd(restartContainersReq.getContainerId()).exec();
    }

    @Override
    public Object getContainerIdByName(String containerName) {
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

    @Override
    public Object removeContainers(RemoveContainersReq removeContainersReq) {
        return dockerClient.removeContainerCmd(removeContainersReq.getContainerId()).exec();
    }

    @Override
    public Object stopContainers(StopContainersReq stopContainersReq) {
        return dockerClient.stopContainerCmd(stopContainersReq.getContainerId()).exec();
    }

    @Override
    public Object copyArchiveToContainer(CopyArchiveToContainerReq copyArchiveToContainerReq) {
        return dockerClient.copyArchiveToContainerCmd(copyArchiveToContainerReq.getContainerId())
                .withHostResource(copyArchiveToContainerReq.getResource())
                .withRemotePath(copyArchiveToContainerReq.getRemote())
                .exec();
    }

    @Override
    public Object copyArchiveFromContainer(CopyArchiveToContainerReq copyArchiveToContainerReq) {
        try {
            // String path = "F:\\tmp\\wealth.rar"
            // remote="/tmp/wealth.rar"

            InputStream input = dockerClient
                    .copyArchiveFromContainerCmd(copyArchiveToContainerReq.getContainerId(), copyArchiveToContainerReq.getRemote())
                    .exec();
            int index;
            byte[] bytes = new byte[1024];
            FileOutputStream downloadFile = new FileOutputStream(copyArchiveToContainerReq.getResource());
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
}
