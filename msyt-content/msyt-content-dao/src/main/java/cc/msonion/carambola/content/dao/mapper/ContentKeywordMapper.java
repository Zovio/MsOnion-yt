package cc.msonion.carambola.content.dao.mapper;

import cc.msonion.carambola.content.pojo.ContentKeyword;
import cc.msonion.carambola.content.pojo.ContentKeywordExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ContentKeywordMapper extends MsOnionBaseMapper<ContentKeyword, ContentKeywordExample> {
    long countByExample(ContentKeywordExample example);

    int deleteByExample(ContentKeywordExample example);

    int deleteByIdxesWithExample(ContentKeywordExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ContentKeyword record);

    int insertSelective(ContentKeyword record);

    List<ContentKeyword> selectByExample(ContentKeywordExample example);

    ContentKeyword selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ContentKeyword record, @Param("example") ContentKeywordExample example);

    int updateByExample(@Param("record") ContentKeyword record, @Param("example") ContentKeywordExample example);

    int updateByPrimaryKeySelective(ContentKeyword record);

    int updateByPrimaryKey(ContentKeyword record);
}