package cc.msonion.carambola.fileresource.dao.mapper;

import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import cc.msonion.carambola.fileresource.pojo.FileResourceUploadExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileResourceUploadMapper extends MsOnionBaseMapper<FileResourceUpload, FileResourceUploadExample> {
    long countByExample(FileResourceUploadExample example);

    int deleteByExample(FileResourceUploadExample example);

    int deleteByIdxesWithExample(FileResourceUploadExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(FileResourceUpload record);

    int insertSelective(FileResourceUpload record);

    List<FileResourceUpload> selectByExample(FileResourceUploadExample example);

    FileResourceUpload selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") FileResourceUpload record, @Param("example") FileResourceUploadExample example);

    int updateByExample(@Param("record") FileResourceUpload record, @Param("example") FileResourceUploadExample example);

    int updateByPrimaryKeySelective(FileResourceUpload record);

    int updateByPrimaryKey(FileResourceUpload record);
}