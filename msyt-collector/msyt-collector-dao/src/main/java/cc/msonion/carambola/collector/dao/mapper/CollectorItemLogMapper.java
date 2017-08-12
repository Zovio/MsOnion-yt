package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemLog;
import cc.msonion.carambola.collector.pojo.CollectorItemLogExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemLogMapper extends MsOnionBaseMapper<CollectorItemLog, CollectorItemLogExample> {
    long countByExample(CollectorItemLogExample example);

    int deleteByExample(CollectorItemLogExample example);

    int deleteByIdxesWithExample(CollectorItemLogExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemLog record);

    int insertSelective(CollectorItemLog record);

    List<CollectorItemLog> selectByExample(CollectorItemLogExample example);

    CollectorItemLog selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemLog record, @Param("example") CollectorItemLogExample example);

    int updateByExample(@Param("record") CollectorItemLog record, @Param("example") CollectorItemLogExample example);

    int updateByPrimaryKeySelective(CollectorItemLog record);

    int updateByPrimaryKey(CollectorItemLog record);
}