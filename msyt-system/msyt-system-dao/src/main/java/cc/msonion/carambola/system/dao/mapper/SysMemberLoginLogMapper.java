package cc.msonion.carambola.system.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.system.pojo.SysMemberLoginLog;
import cc.msonion.carambola.system.pojo.SysMemberLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysMemberLoginLogMapper extends MsOnionBaseMapper<SysMemberLoginLog, SysMemberLoginLogExample> {
    long countByExample(SysMemberLoginLogExample example);

    int deleteByExample(SysMemberLoginLogExample example);

    int deleteByIdxesWithExample(SysMemberLoginLogExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(SysMemberLoginLog record);

    int insertSelective(SysMemberLoginLog record);

    List<SysMemberLoginLog> selectByExample(SysMemberLoginLogExample example);

    SysMemberLoginLog selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") SysMemberLoginLog record, @Param("example") SysMemberLoginLogExample example);

    int updateByExample(@Param("record") SysMemberLoginLog record, @Param("example") SysMemberLoginLogExample example);

    int updateByPrimaryKeySelective(SysMemberLoginLog record);

    int updateByPrimaryKey(SysMemberLoginLog record);
}