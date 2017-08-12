package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemContent;
import cc.msonion.carambola.collector.pojo.CollectorItemContentExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemContentMapper extends MsOnionBaseMapper<CollectorItemContent, CollectorItemContentExample> {
    long countByExample(CollectorItemContentExample example);

    int deleteByExample(CollectorItemContentExample example);

    int deleteByIdxesWithExample(CollectorItemContentExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemContent record);

    int insertSelective(CollectorItemContent record);

    List<CollectorItemContent> selectByExample(CollectorItemContentExample example);

    CollectorItemContent selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemContent record, @Param("example") CollectorItemContentExample example);

    int updateByExample(@Param("record") CollectorItemContent record, @Param("example") CollectorItemContentExample example);

    int updateByPrimaryKeySelective(CollectorItemContent record);

    int updateByPrimaryKey(CollectorItemContent record);
}