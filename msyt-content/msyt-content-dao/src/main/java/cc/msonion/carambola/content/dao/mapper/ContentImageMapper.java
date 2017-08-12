package cc.msonion.carambola.content.dao.mapper;

import cc.msonion.carambola.content.pojo.ContentImage;
import cc.msonion.carambola.content.pojo.ContentImageExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentImageMapper extends MsOnionBaseMapper<ContentImage, ContentImageExample> {
    long countByExample(ContentImageExample example);

    int deleteByExample(ContentImageExample example);

    int deleteByIdxesWithExample(ContentImageExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ContentImage record);

    int insertSelective(ContentImage record);

    List<ContentImage> selectByExample(ContentImageExample example);

    ContentImage selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ContentImage record, @Param("example") ContentImageExample example);

    int updateByExample(@Param("record") ContentImage record, @Param("example") ContentImageExample example);

    int updateByPrimaryKeySelective(ContentImage record);

    int updateByPrimaryKey(ContentImage record);
}