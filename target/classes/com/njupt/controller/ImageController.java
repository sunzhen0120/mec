package com.njupt.controller;

import com.alibaba.fastjson.JSON;
import com.njupt.dto.image.CreateImageGlanceReq;
import com.njupt.res.CommonResult;
import com.njupt.service.ImageService;
import com.njupt.util.RestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping("image")
public class ImageController {

    private static final Logger logger = LoggerFactory.getLogger(ImageController.class);

    @Autowired
    private ImageService imageService;

    @PostMapping("createImage")
    public void createImage(HttpServletRequest request,
                            HttpServletResponse response,
                            @RequestParam(value = "file", required = false) MultipartFile file,
                            @RequestParam(value = "containerFormat") String containerFormat,
                            @RequestParam(value = "diskFormat") String diskFormat,
                            @RequestParam(value = "name") String name) {
        try {
            InputStream inputStream = file.getInputStream();
            CommonResult commonResult = imageService.createImage(inputStream, containerFormat, diskFormat, name);
            RestUtil.printData(response, commonResult);
        } catch (IOException e) {
            logger.error("createImage error", e);
        }
    }

    @PostMapping("showImageDetail/{imageId}")
    public void showImageDetail(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("imageId") String imageId) {
        CommonResult commonResult = imageService.showImageDetail(imageId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("showImagesList")
    public void showImageList(HttpServletRequest request,
                              HttpServletResponse response) {
        CommonResult commonResult = imageService.showImagesList();
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("deleteImage/{imageId}")
    public void deleteImage(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable("imageId") String imageId) {
        CommonResult commonResult = imageService.deleteImage(imageId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("deactivateImage/{imageId}")
    public void deactivateImage(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("imageId") String imageId) {
        CommonResult commonResult = imageService.deactivateImage(imageId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("reactivateImage/{imageId}")
    public void reactivateImage(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("imageId") String imageId) {
        CommonResult commonResult = imageService.reactivateImage(imageId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("uploadImageData/{imageId}")
    public void uploadImageData(HttpServletRequest request,
                                HttpServletResponse response,
                                @PathVariable("imageId") String imageId) {
        CommonResult commonResult = imageService.uploadImageData(imageId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("downloadImageData/{imageId}")
    public void downloadImageData(HttpServletRequest request,
                                  HttpServletResponse response,
                                  @PathVariable("imageId") String imageId) {
        CommonResult commonResult = imageService.downloadImageData(imageId);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("addImageTag/{imageId}/{tag}")
    public void addImageTag(HttpServletRequest request,
                            HttpServletResponse response,
                            @PathVariable("imageId") String imageId,
                            @PathVariable("tag") String tag) {
        CommonResult commonResult = imageService.addImageTag(imageId, tag);
        RestUtil.printData(response, commonResult);
    }

    @PostMapping("deleteImageTag/{imageId}/{tag}")
    public void deleteImageTag(HttpServletRequest request,
                               HttpServletResponse response,
                               @PathVariable("imageId") String imageId,
                               @PathVariable("tag") String tag) {
        CommonResult commonResult = imageService.deleteImageTag(imageId, tag);
        RestUtil.printData(response, commonResult);
    }
}
