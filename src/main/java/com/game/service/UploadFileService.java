package com.game.service;

import com.game.entity.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;


/**
 * 文件上传
 *
 * @author yinhang
 */
public interface UploadFileService {

	/**
	 * 单文件上传
	 * 
	 * @param file
	 * @param createUid
	 * @return
	 * @throws Exception
	 */
	UploadFile uploadOne(MultipartFile file, String createUid) throws Exception;

	/**
	 * 上传视频
	 * 
	 * @param file
	 * @param createUid
	 * @return
	 * @throws Exception
	 */
	UploadFile store(MultipartFile file, String createUid) throws Exception;

	/**
	 * 图片上传。压缩方式：分辨率不变，采用渐进式双线性插值模式
	 *
	 * @param file
	 * @param createUid
	 * @return
	 * @throws Exception
	 */
	UploadFile uploadImage(MultipartFile file, String createUid) throws Exception;
	
	/**
	 * 上传并压缩图片
	 *
	 * @param file
	 * @param createUid
	 * @return
	 * @throws Exception
	 */
	UploadFile uploadZipImage(MultipartFile file, String createUid) throws Exception;

	/**
	 * 上传文件到指定目录
	 * @param file 文件对象
	 * @param createUid 创建人编码
	 * @param path 上传目录
	 * @return
	 */
    UploadFile upload2Path(MultipartFile file, String createUid, String path);

	/**
	 * 单文件上传pdf
	 *
	 * @param file
	 * @param createUid
	 * @return
	 * @throws Exception
	 */
	Map<String, Object> uploadOnePdf(MultipartFile file, String createUid) throws Exception;
}
