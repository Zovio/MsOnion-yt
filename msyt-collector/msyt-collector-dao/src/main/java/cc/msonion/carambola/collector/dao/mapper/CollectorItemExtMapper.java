package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemExt;
import cc.msonion.carambola.collector.pojo.CollectorItemExtExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemExtMapper extends MsOnionBaseMapper<CollectorItemExt, CollectorItemExtExample> {
    long countByExample(CollectorItemExtExample example);

    int deleteByExample(CollectorItemExtExample example);

    int deleteByIdxesWithExample(CollectorItemExtExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemExt record);

    int insertSelective(CollectorItemExt record);

    List<CollectorItemExt> selectByExample(CollectorItemExtExample example);

    CollectorItemExt selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemExt record, @Param("example") CollectorItemExtExample example);

    int updateByExample(@Param("record") CollectorItemExt record, @Param("example") CollectorItemExtExample example);

    int updateByPrimaryKeySelective(CollectorItemExt record);

    int updateByPrimaryKey(CollectorItemExt record);
}