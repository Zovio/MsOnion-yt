package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemOrigin;
import cc.msonion.carambola.collector.pojo.CollectorItemOriginExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemOriginMapper extends MsOnionBaseMapper<CollectorItemOrigin, CollectorItemOriginExample> {
    long countByExample(CollectorItemOriginExample example);

    int deleteByExample(CollectorItemOriginExample example);

    int deleteByIdxesWithExample(CollectorItemOriginExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemOrigin record);

    int insertSelective(CollectorItemOrigin record);

    List<CollectorItemOrigin> selectByExample(CollectorItemOriginExample example);

    CollectorItemOrigin selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemOrigin record, @Param("example") CollectorItemOriginExample example);

    int updateByExample(@Param("record") CollectorItemOrigin record, @Param("example") CollectorItemOriginExample example);

    int updateByPrimaryKeySelective(CollectorItemOrigin record);

    int updateByPrimaryKey(CollectorItemOrigin record);
}