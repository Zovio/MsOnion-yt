package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemState;
import cc.msonion.carambola.collector.pojo.CollectorItemStateExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemStateMapper extends MsOnionBaseMapper<CollectorItemState, CollectorItemStateExample> {
    long countByExample(CollectorItemStateExample example);

    int deleteByExample(CollectorItemStateExample example);

    int deleteByIdxesWithExample(CollectorItemStateExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemState record);

    int insertSelective(CollectorItemState record);

    List<CollectorItemState> selectByExample(CollectorItemStateExample example);

    CollectorItemState selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemState record, @Param("example") CollectorItemStateExample example);

    int updateByExample(@Param("record") CollectorItemState record, @Param("example") CollectorItemStateExample example);

    int updateByPrimaryKeySelective(CollectorItemState record);

    int updateByPrimaryKey(CollectorItemState record);
}