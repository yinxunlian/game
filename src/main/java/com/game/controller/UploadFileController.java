
package com.game.controller;

import com.baomidou.mybatisplus.extension.api.ApiController;
import com.baomidou.mybatisplus.extension.api.Assert;
import com.baomidou.mybatisplus.extension.api.R;
import com.game.controller.support.ErrorCode;
import com.game.entity.UploadFile;
import com.game.service.UploadFileService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;


/**
 * <p>
 * 上传文件 前端控制器
 * </p>
 *
 * @author yinhang
 * @since 2018-05-03
 */
@RestController
@RequestMapping("/upload")
public class UploadFileController extends ApiController {

    private static Logger            logger = LoggerFactory.getLogger(UploadFileController.class);
    @Resource
    private        UploadFileService fileService;

    /**
     * 单文件上传
     *
     * @param file
     * @param createUid
     * @param isZip     是否压缩图片 1：是 0：否
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadOne")
    public R<UploadFile> uploadOne(MultipartFile file, String createUid, Integer isZip) throws Exception {
        logger.info("调用单文件上传开始");
        Assert.notNull(ErrorCode.BAD_REQUEST, file, createUid);
        UploadFile record;
        if (NumberUtils.INTEGER_ONE.equals(isZip)) {
            // 图片需要压缩
            record = fileService.uploadZipImage(file, createUid);
        } else {
            record = fileService.uploadOne(file, createUid);
        }
        logger.info("调用单文件上传结束");
        return success(record);
    }

    /**
     * 视频上传
     *
     * @param file
     * @param createUid
     * @return
     * @throws Exception
     */
    @RequestMapping("/uploadVedio")
    public R<UploadFile> handleVideoUpload(@RequestParam("file") MultipartFile file,
                @RequestParam("createUid") String createUid) throws Exception {
        logger.info("视频上传开始");
        UploadFile record = fileService.store(file, createUid);
        logger.info("视频上传结束");
        return success(record);
    }

    /**
     * 图片压缩上传。压缩方式：分辨率不变，采用渐进式双线性插值模式
     *
     * @param file
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadImage")
    public R<UploadFile> uploadImage(MultipartFile file, String createUid) throws Exception {
        logger.info("调用单文件上传开始");
        Assert.notNull(ErrorCode.BAD_REQUEST, file, createUid);
        UploadFile record = fileService.uploadImage(file, createUid);
        logger.info("调用单文件上传结束");
        return success(record);
    }

    /**
     * 图片压缩
     *
     * @param file
     * @param createUid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadZipImage")
    public R<UploadFile> uploadZipImage(MultipartFile file, String createUid) throws Exception {
        logger.info("调用单文件上传开始");
        Assert.notNull(ErrorCode.BAD_REQUEST, file, createUid);
        UploadFile record = fileService.uploadZipImage(file, createUid);
        logger.info("调用单文件上传结束");
        return success(record);
    }

    /**
     * 单文件上传（指定文件目录）
     *
     * @param file      文件对象
     * @param createUid 创建人编码
     * @param path      文件保存目录
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/upload2Path")
    public R<UploadFile> upload2Path(MultipartFile file, String createUid, String path) throws Exception {
        logger.info("调用单文件上传开始");
        Assert.notNull(ErrorCode.BAD_REQUEST, file, createUid);
        UploadFile record;
        if (StringUtils.isEmpty(path)) {
            record = fileService.uploadOne(file, createUid);
        } else {
            record = fileService.upload2Path(file, createUid, path);
        }
        logger.info("调用单文件上传结束");
        return success(record);
    }

    /**
     * 单文件上传pdf
     *
     * @param file
     * @param createUid
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadOne/pdf")
    public R<Object> uploadOnePdf(MultipartFile file, String createUid) throws Exception {
        logger.info("调用单文件上传开始");
        Assert.notNull(ErrorCode.BAD_REQUEST, file, createUid);
        Map<String, Object> record = fileService.uploadOnePdf(file, createUid);
        logger.info("调用单文件上传结束");
        return success(record);
    }
}
