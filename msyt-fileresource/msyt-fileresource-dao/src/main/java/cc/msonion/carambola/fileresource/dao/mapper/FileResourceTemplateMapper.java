package cc.msonion.carambola.fileresource.dao.mapper;

import cc.msonion.carambola.fileresource.pojo.FileResourceTemplate;
import cc.msonion.carambola.fileresource.pojo.FileResourceTemplateExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FileResourceTemplateMapper extends MsOnionBaseMapper<FileResourceTemplate, FileResourceTemplateExample> {
    long countByExample(FileResourceTemplateExample example);

    int deleteByExample(FileResourceTemplateExample example);

    int deleteByIdxesWithExample(FileResourceTemplateExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(FileResourceTemplate record);

    int insertSelective(FileResourceTemplate record);

    List<FileResourceTemplate> selectByExample(FileResourceTemplateExample example);

    FileResourceTemplate selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") FileResourceTemplate record, @Param("example") FileResourceTemplateExample example);

    int updateByExample(@Param("record") FileResourceTemplate record, @Param("example") FileResourceTemplateExample example);

    int updateByPrimaryKeySelective(FileResourceTemplate record);

    int updateByPrimaryKey(FileResourceTemplate record);
}