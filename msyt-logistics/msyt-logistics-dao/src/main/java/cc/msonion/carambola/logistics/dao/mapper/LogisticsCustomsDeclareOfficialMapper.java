package cc.msonion.carambola.logistics.dao.mapper;

import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficial;
import cc.msonion.carambola.logistics.pojo.LogisticsCustomsDeclareOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface LogisticsCustomsDeclareOfficialMapper extends MsOnionBaseMapper<LogisticsCustomsDeclareOfficial, LogisticsCustomsDeclareOfficialExample> {
    long countByExample(LogisticsCustomsDeclareOfficialExample example);

    int deleteByExample(LogisticsCustomsDeclareOfficialExample example);

    int deleteByIdxesWithExample(LogisticsCustomsDeclareOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(LogisticsCustomsDeclareOfficial record);

    int insertSelective(LogisticsCustomsDeclareOfficial record);

    List<LogisticsCustomsDeclareOfficial> selectByExample(LogisticsCustomsDeclareOfficialExample example);

    LogisticsCustomsDeclareOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") LogisticsCustomsDeclareOfficial record, @Param("example") LogisticsCustomsDeclareOfficialExample example);

    int updateByExample(@Param("record") LogisticsCustomsDeclareOfficial record, @Param("example") LogisticsCustomsDeclareOfficialExample example);

    int updateByPrimaryKeySelective(LogisticsCustomsDeclareOfficial record);

    int updateByPrimaryKey(LogisticsCustomsDeclareOfficial record);
}