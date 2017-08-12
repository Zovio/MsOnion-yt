package cc.msonion.carambola.content.dao.mapper;

import cc.msonion.carambola.content.pojo.Content;
import cc.msonion.carambola.content.pojo.ContentExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentMapper extends MsOnionBaseMapper<Content, ContentExample> {
    long countByExample(ContentExample example);

    int deleteByExample(ContentExample example);

    int deleteByIdxesWithExample(ContentExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Content record);

    int insertSelective(Content record);

    List<Content> selectByExample(ContentExample example);

    Content selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByExample(@Param("record") Content record, @Param("example") ContentExample example);

    int updateByPrimaryKeySelective(Content record);

    int updateByPrimaryKey(Content record);
}