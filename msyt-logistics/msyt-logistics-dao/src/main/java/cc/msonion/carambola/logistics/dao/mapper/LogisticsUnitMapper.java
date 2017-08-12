package cc.msonion.carambola.logistics.dao.mapper;

import cc.msonion.carambola.logistics.pojo.LogisticsUnit;
import cc.msonion.carambola.logistics.pojo.LogisticsUnitExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsUnitMapper extends MsOnionBaseMapper<LogisticsUnit, LogisticsUnitExample> {
    long countByExample(LogisticsUnitExample example);

    int deleteByExample(LogisticsUnitExample example);

    int deleteByIdxesWithExample(LogisticsUnitExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(LogisticsUnit record);

    int insertSelective(LogisticsUnit record);

    List<LogisticsUnit> selectByExample(LogisticsUnitExample example);

    LogisticsUnit selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") LogisticsUnit record, @Param("example") LogisticsUnitExample example);

    int updateByExample(@Param("record") LogisticsUnit record, @Param("example") LogisticsUnitExample example);

    int updateByPrimaryKeySelective(LogisticsUnit record);

    int updateByPrimaryKey(LogisticsUnit record);
}