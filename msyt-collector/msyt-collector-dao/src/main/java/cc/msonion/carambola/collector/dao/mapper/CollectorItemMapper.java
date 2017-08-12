package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItem;
import cc.msonion.carambola.collector.pojo.CollectorItemExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemMapper extends MsOnionBaseMapper<CollectorItem, CollectorItemExample> {
    long countByExample(CollectorItemExample example);

    int deleteByExample(CollectorItemExample example);

    int deleteByIdxesWithExample(CollectorItemExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItem record);

    int insertSelective(CollectorItem record);

    List<CollectorItem> selectByExample(CollectorItemExample example);

    CollectorItem selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItem record, @Param("example") CollectorItemExample example);

    int updateByExample(@Param("record") CollectorItem record, @Param("example") CollectorItemExample example);

    int updateByPrimaryKeySelective(CollectorItem record);

    int updateByPrimaryKey(CollectorItem record);
}