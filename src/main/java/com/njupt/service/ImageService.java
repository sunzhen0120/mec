package com.njupt.service;

import com.njupt.dto.image.CreateImageGlanceReq;
import com.njupt.res.CommonResult;

import java.io.InputStream;

public interface ImageService {

    CommonResult createImage(InputStream inputStream, String containerFormat, String diskFormat, String name);

    CommonResult showImageDetail(String imageId);

    CommonResult showImagesList();

    CommonResult deleteImage(String imageId);

    CommonResult deactivateImage(String imageId);

    CommonResult reactivateImage(String imageId);

    CommonResult uploadImageData(String imageId);

    CommonResult downloadImageData(String imageId);

    CommonResult addImageTag(String imageId, String tag);

    CommonResult deleteImageTag(String imageId, String tag);
}
