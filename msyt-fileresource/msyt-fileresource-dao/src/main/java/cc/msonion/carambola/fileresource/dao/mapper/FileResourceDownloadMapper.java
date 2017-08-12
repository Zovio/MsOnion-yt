package cc.msonion.carambola.fileresource.dao.mapper;

import cc.msonion.carambola.fileresource.pojo.FileResourceDownload;
import cc.msonion.carambola.fileresource.pojo.FileResourceDownloadExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface FileResourceDownloadMapper extends MsOnionBaseMapper<FileResourceDownload, FileResourceDownloadExample> {
    long countByExample(FileResourceDownloadExample example);

    int deleteByExample(FileResourceDownloadExample example);

    int deleteByIdxesWithExample(FileResourceDownloadExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(FileResourceDownload record);

    int insertSelective(FileResourceDownload record);

    List<FileResourceDownload> selectByExample(FileResourceDownloadExample example);

    FileResourceDownload selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") FileResourceDownload record, @Param("example") FileResourceDownloadExample example);

    int updateByExample(@Param("record") FileResourceDownload record, @Param("example") FileResourceDownloadExample example);

    int updateByPrimaryKeySelective(FileResourceDownload record);

    int updateByPrimaryKey(FileResourceDownload record);
}