package com.game.mapper;

import com.game.entity.UploadFile;
import org.springframework.stereotype.Repository;


/**
 * 文件上传
 * 
 * @author yinhang
 */
@Repository
public interface UploadFileMapper {

    void insertSelective(UploadFile commonFile);

}
