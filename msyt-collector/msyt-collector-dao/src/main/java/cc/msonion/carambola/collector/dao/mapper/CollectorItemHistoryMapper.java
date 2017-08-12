package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemHistory;
import cc.msonion.carambola.collector.pojo.CollectorItemHistoryExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemHistoryMapper extends MsOnionBaseMapper<CollectorItemHistory, CollectorItemHistoryExample> {
    long countByExample(CollectorItemHistoryExample example);

    int deleteByExample(CollectorItemHistoryExample example);

    int deleteByIdxesWithExample(CollectorItemHistoryExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemHistory record);

    int insertSelective(CollectorItemHistory record);

    List<CollectorItemHistory> selectByExample(CollectorItemHistoryExample example);

    CollectorItemHistory selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemHistory record, @Param("example") CollectorItemHistoryExample example);

    int updateByExample(@Param("record") CollectorItemHistory record, @Param("example") CollectorItemHistoryExample example);

    int updateByPrimaryKeySelective(CollectorItemHistory record);

    int updateByPrimaryKey(CollectorItemHistory record);
}