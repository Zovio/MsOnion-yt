package cc.msonion.carambola.logistics.dao.mapper;

import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclare;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsCustomsDeclareMapper extends MsOnionBaseMapper<LogisticsCustomsDeclare, LogisticsCustomsDeclareExample> {
    long countByExample(LogisticsCustomsDeclareExample example);

    int deleteByExample(LogisticsCustomsDeclareExample example);

    int deleteByIdxesWithExample(LogisticsCustomsDeclareExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(LogisticsCustomsDeclare record);

    int insertSelective(LogisticsCustomsDeclare record);

    List<LogisticsCustomsDeclare> selectByExample(LogisticsCustomsDeclareExample example);

    LogisticsCustomsDeclare selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") LogisticsCustomsDeclare record, @Param("example") LogisticsCustomsDeclareExample example);

    int updateByExample(@Param("record") LogisticsCustomsDeclare record, @Param("example") LogisticsCustomsDeclareExample example);

    int updateByPrimaryKeySelective(LogisticsCustomsDeclare record);

    int updateByPrimaryKey(LogisticsCustomsDeclare record);
}