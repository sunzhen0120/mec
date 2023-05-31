package com.njupt.dto.container;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CreateImagesWithUploadReq {

    @ApiModelProperty("镜像名")
    private String imageName;

    @ApiModelProperty("镜像版本号")
    private String version;
//
//    @ApiModelProperty("镜像文件")
//    private MultipartFile imageFile;

    @ApiModelProperty("镜像文件路径")
    private String imageFilePath;

    @ApiModelProperty("镜像仓库")
    private String repository;
}
