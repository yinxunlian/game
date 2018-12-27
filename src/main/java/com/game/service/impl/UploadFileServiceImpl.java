package com.game.service.impl;

import com.baomidou.mybatisplus.extension.api.Assert;
import com.game.common.util.ImageZipUtil;
import com.game.config.ConfigParams;
import com.game.entity.UploadFile;
import com.game.mapper.UploadFileMapper;
import com.game.service.UploadFileService;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.resizers.configurations.ScalingMode;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


/**
 * 文件上传实现类
 *
 * @author yinhang
 */
@Service
public class UploadFileServiceImpl implements UploadFileService {

    private static Logger           logger = LoggerFactory.getLogger(UploadFileServiceImpl.class);
    @Autowired
    private        UploadFileMapper fileMapper;
    @Autowired
    private        ConfigParams     systemProperties;

    @Override
    public UploadFile uploadOne(MultipartFile file, String createUid) {
        String directory = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Path rootLocation = Paths.get(getUploadPath(), directory);
        String originalFilename = file.getOriginalFilename();
        Long fileSize = file.getSize();
        logger.info("文件名 filename = {}，文件大小 fileSize = {} k", originalFilename, file.getSize() / 1000);
        String filename = StringUtils.cleanPath(originalFilename);
        logger.info("洗涤后的文件名 filename = {}", filename);
        if (file.isEmpty() || fileSize <= 0) {
            Assert.fail("文件不能为空");
        }
        if (filename.contains("..")) {
            Assert.fail("文件不能使用相对路径");
        }
        if (fileSize > (50 * 1024 * 1024)) {
            Assert.fail("文件大小不能超过50M");
        }
        try {
            // 小写文件扩展名
            String suffixLowerCase = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            // 物理文件名，后缀小写
            String newFileName = getUUID() + suffixLowerCase;
            logger.info("存储在服务器内的物理文件名 absolutename = {}", newFileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(rootLocation);
                Files.copy(inputStream, rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
                return saveFileRecord(filename, newFileName, directory, createUid);
            }
        } catch (Exception e) {
            Assert.fail("文件上传错误");
        }
        return null;
    }

    @Override
    public UploadFile store(MultipartFile file, String createUid) {
        String directory = "video";
        Path rootLocation = Paths.get(getUploadPath(), directory);
        Long fileSize = file.getSize();
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        if (file.isEmpty() || fileSize <= 0) {
            Assert.fail("文件不能为空");
        }
        if (filename.contains("..")) {
            Assert.fail("文件不能使用相对路径");
        }
        if (fileSize > (50 * 1024 * 1024)) {
            Assert.fail("文件大小不能超过20M");
        }
        try {
            // 小写文件扩展名
            String suffixLowerCase = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            // 物理文件名，后缀小写
            String newFileName = getUUID() + suffixLowerCase;
            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(rootLocation);
                Files.copy(inputStream, rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
                return saveFileRecord(filename, newFileName, directory, createUid);
            }
        } catch (Exception e) {
            Assert.fail("视频上传错误");
        }
        return null;
    }

    @Override
    public UploadFile uploadImage(MultipartFile file, String createUid) throws Exception {
        String directory = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Path rootLocation = Paths.get(getUploadPath(), directory);
        String originalFilename = file.getOriginalFilename();
        Long fileSize = file.getSize();
        logger.info("文件名 filename = {}，文件大小 fileSize = {} k", originalFilename, file.getSize() / 1000);
        String filename = StringUtils.cleanPath(originalFilename);
        logger.info("洗涤后的文件名 filename = {}", filename);
        if (file.isEmpty() || fileSize <= 0) {
            Assert.fail("文件不能为空");
        }
        if (filename.contains("..")) {
            Assert.fail("文件不能使用相对路径");
        }
        if (fileSize > (20 * 1024 * 1024)) {
            Assert.fail("文件大小不能超过20M");
        }
        // TODO 这里没有校验文件格式
        try {
            // 小写文件扩展名
            String suffixLowerCase = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            // 物理文件名，后缀小写
            String uuid = getUUID();
            String newFileName = uuid + suffixLowerCase;
            // 压缩后的文件名
            String compressFileName = uuid + "_1" + suffixLowerCase;
            String compressPath = rootLocation.toString() + File.separator + compressFileName;
            File compressFile = new File(compressPath);
            logger.info("存储在服务器内的物理源文件名 absolutename = {}", newFileName);
            Files.createDirectories(rootLocation);
            // 物理存储源图片
            Files.copy(file.getInputStream(), rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
            // 物理存储压缩图片
            Thumbnails.of(file.getInputStream()).scalingMode(ScalingMode.PROGRESSIVE_BILINEAR).scale(1)
                        .toFile(compressFile);
            return saveFileRecord(filename, compressFileName, directory, createUid);
        } catch (Exception e) {
            Assert.fail("文件上传错误");
        }
        return null;
    }

    @Override
    public UploadFile uploadZipImage(MultipartFile file, String createUid) throws Exception {
        String directory = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Path rootLocation = Paths.get(getUploadPath(), directory);
        String originalFilename = file.getOriginalFilename();
        Long fileSize = file.getSize();
        logger.info("文件名 filename = {}，文件大小 fileSize = {} k", originalFilename, file.getSize() / 1000);
        String filename = StringUtils.cleanPath(originalFilename);
        logger.info("洗涤后的文件名 filename = {}", filename);
        if (file.isEmpty() || fileSize <= 0) {
            Assert.fail("文件不能为空");
        }
        if (filename.contains("..")) {
            Assert.fail("文件不能使用相对路径");
        }
        if (fileSize > (20 * 1024 * 1024)) {
            Assert.fail("文件大小不能超过20M");
        }
        try {
            // 小写文件扩展名
            String suffixLowerCase = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            // 物理文件名，后缀小写
            String newFileName = getUUID() + suffixLowerCase;
            logger.info("存储在服务器内的物理文件名 absolutename = {}", newFileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(rootLocation);
                // 大于1M压缩
                if (fileSize > (1 * 1024 * 1024)) {
                    File oldFile = converFile(file);
                    String result = ImageZipUtil
                                .zipImageFile(oldFile, rootLocation.resolve(newFileName).toFile(), 400, 0);
                    if (result == null) {
                        Assert.fail("文件上传错误");
                    }
                } else {
                    Files.copy(inputStream, rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
                }
                return saveFileRecord(filename, newFileName, directory, createUid);
            }
        } catch (Exception e) {
            logger.error("文件上传错误", e);
            Assert.fail("文件上传错误");
        }
        return null;
    }

    /**
     * 上传文件到指定目录
     *
     * @param file
     * @param createUid
     * @param path
     * @return
     */
    @Override
    public UploadFile upload2Path(MultipartFile file, String createUid, String path) {
        Path rootLocation = Paths.get(getUploadPath(), path);
        String originalFilename = null;
        try {
            originalFilename = URLDecoder.decode(file.getOriginalFilename(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Assert.fail("文件上传错误");
        }
        Long fileSize = file.getSize();
        logger.info("文件名 filename = {}，文件大小 fileSize = {} k", originalFilename, file.getSize() / 1000);
        String filename = StringUtils.cleanPath(originalFilename);
        logger.info("洗涤后的文件名 filename = {}", filename);
        if (file.isEmpty() || fileSize <= 0) {
            Assert.fail("文件不能为空");
        }
        if (filename.contains("..")) {
            Assert.fail("文件不能使用相对路径");
        }
        if (fileSize > (20 * 1024 * 1024)) {
            Assert.fail("文件大小不能超过20M");
        }
        try {
            logger.info("存储在服务器内的物理文件名 absolutename = {}", filename);
            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(rootLocation);
                Files.copy(inputStream, rootLocation.resolve(filename), StandardCopyOption.REPLACE_EXISTING);
                return saveFileRecord(filename, filename, path, createUid);
            }
        } catch (Exception e) {
            Assert.fail("文件上传错误");
        }
        return null;
    }

    /**
     * 获得uuid
     *
     * @return
     */
    private String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 获得文件访问uri
     *
     * @return
     */
    private String getAccessUri() {
        String uri = systemProperties.getResource().getAccessUri();
        if (!uri.endsWith("/")) {
            return uri + "/";
        } else {
            return uri;
        }
    }

    /**
     * 获得文件上传地址
     *
     * @return
     */
    private String getUploadPath() {
        String path = systemProperties.getResource().getUploadPath();
        if (!path.endsWith("/")) {
            return path + "/";
        } else {
            return path;
        }
    }

    /**
     * 转换File
     *
     * @param file
     * @return
     */
    public File converFile(MultipartFile file) {
        File f = null;
        try {
            f = File.createTempFile("tmp", null);
            file.transferTo(f);
            f.deleteOnExit();
        } catch (Exception e) {
            logger.error("转换File错误", e);
        }
        return f;
    }

    /**
     * 保存文件记录
     *
     * @param filename
     * @param newFileName
     * @param directory
     * @param createUid
     * @return
     */
    private UploadFile saveFileRecord(String filename, String newFileName, String directory, String createUid) {
        String fileUrl = directory + "/" + newFileName;
        if (StringUtils.isEmpty(createUid)) {
            Assert.fail("创建人不能为空");
        }
        UploadFile uploadFile = new UploadFile(filename, fileUrl, createUid);
        fileMapper.insertSelective(uploadFile);
        uploadFile.setUrl(fileUrl);
        uploadFile.setAccessUrl(getAccessUri() + fileUrl);
        return uploadFile;
    }

    @Override
    public Map<String, Object> uploadOnePdf(MultipartFile file, String createUid) throws Exception {
        String directory = new SimpleDateFormat("yyyyMMdd").format(new Date());
        Path rootLocation = Paths.get(getUploadPath(), directory);
        String originalFilename = file.getOriginalFilename();
        Long fileSize = file.getSize();
        logger.info("文件名 filename = {}，文件大小 fileSize = {} k", originalFilename, file.getSize() / 1000);
        String filename = StringUtils.cleanPath(originalFilename);
        logger.info("洗涤后的文件名 filename = {}", filename);
        if (file.isEmpty() || fileSize <= 0) {
            Assert.fail("文件不能为空");
        }
        if (filename.contains("..")) {
            Assert.fail("文件不能使用相对路径");
        }
        if (fileSize > (50 * 1024 * 1024)) {
            Assert.fail("文件大小不能超过50M");
        }
        try {
            // 小写文件扩展名
            String suffixLowerCase = filename.substring(filename.lastIndexOf(".")).toLowerCase();
            // 物理文件名，后缀小写
            String uuid = getUUID();
            String newFileName = uuid + suffixLowerCase;
            logger.info("存储在服务器内的物理文件名 absolutename = {}", newFileName);
            try (InputStream inputStream = file.getInputStream()) {
                Files.createDirectories(rootLocation);
                Files.copy(inputStream, rootLocation.resolve(newFileName), StandardCopyOption.REPLACE_EXISTING);
                UploadFile uploadFile = saveFileRecord(filename, newFileName, directory, createUid);
                File pdfFile = rootLocation.resolve(newFileName).toFile();
                PDDocument document = PDDocument.load(pdfFile, (String) null);
                int size = document.getNumberOfPages();
                PDFRenderer reader = new PDFRenderer(document);
                File htmlDir = new File(rootLocation + "/" + uuid);
                if (!htmlDir.exists()) {
                    htmlDir.mkdirs();
                }
                StringBuffer buffer = new StringBuffer();
                buffer.append("<!doctype html>\r\n");
                buffer.append("<head>\r\n");
                buffer.append("<meta charset=\"UTF-8\">\r\n");
                buffer.append("</head>\r\n");
                buffer.append("<body style=\"background-color:gray;\">\r\n");
                buffer.append("<style>\r\n");
                buffer.append(
                            "img {background-color:#fff; text-align:center; width:100%; max-width:100%;margin-top:6px;}\r\n");
                buffer.append("</style>\r\n");
                FileOutputStream out;
                for (int i = 0; i < size; i++) {
                    BufferedImage image = reader.renderImage(i, 0.8f);
                    //生成图片,保存位置
                    String imagePath = rootLocation + "/" + uuid + "/image" + "_" + i + ".jpg";
                    logger.info("存储在服务器内pdf转换成图片的路径 imagePath = {}", imagePath);
                    out = new FileOutputStream(imagePath);
                    ImageIO.write(image, "png", out); //使用png的清晰度
                    //将图片路径追加到网页文件里
                    buffer.append("<img src=\"" + getAccessUri() + directory + "/" + uuid + "/image" + "_" + i
                                + ".jpg\"/>\r\n");
                    out.flush();
                    out.close();
                }
                document.close();
                buffer.append("</body>\r\n");
                buffer.append("</html>");
                //生成网页文件
                String htmlPath = rootLocation + "/" + uuid + "/" + uuid + ".html";
                logger.info("存储在服务器内pdf转换成html的路径 htmlPath = {}", htmlPath);
                FileOutputStream fos = new FileOutputStream(htmlPath);
                fos.write(buffer.toString().getBytes());
                fos.flush();
                fos.close();
                buffer.setLength(0);
                logger.info("pdf转换html完毕");
                Map<String, Object> result = new HashMap();
                result.put("accessUrl", uploadFile.getAccessUrl());
                result.put("accessUrlHtml", getAccessUri() + directory + "/" + uuid + "/" + uuid + ".html");
                return result;
            }
        } catch (Exception e) {
            Assert.fail("文件上传错误");
        }
        return null;
    }
}
