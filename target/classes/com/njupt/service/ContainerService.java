package com.njupt.service;

import com.njupt.dto.container.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

public interface ContainerService {

    Object getInfo();

    Object createContainers(CreateContainersReq createContainersReq);

    Object startContainers(StartContainersReq startContainersReq);

    Object listContainers();





    Object createImagesWithPull(CreateImagesWithPullReq createImagesReq) throws Exception;

    Object createImagesWithUpload(CreateImagesWithUploadReq createImagesReq) throws Exception;

    Object createImagesWithUpload1(MultipartFile multipartFile, String repository) throws IOException;


    Object searchImages(SearchImagesReq searchImagesReq);

    Object listImages();

    Object restartContainers(RestartContainersReq restartContainersReq);

    Object getContainerIdByName(String containerName);


    Object removeContainers(RemoveContainersReq removeContainersReq);

    Object stopContainers(StopContainersReq stopContainersReq);

    Object removeImages(RemoveImagesReq removeImagesReq);

    Object copyArchiveToContainer(CopyArchiveToContainerReq copyArchiveToContainerReq);

    Object copyArchiveFromContainer(CopyArchiveToContainerReq copyArchiveToContainerReq);

//    Object saveImages(SaveImagesReq saveImagesReq) throws IOException;

    void saveImages(HttpServletResponse response, SaveImagesReq saveImagesReq) throws IOException;
}
