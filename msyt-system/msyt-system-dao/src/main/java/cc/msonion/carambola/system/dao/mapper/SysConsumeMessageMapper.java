package cc.msonion.carambola.system.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.system.pojo.SysConsumeMessage;
import cc.msonion.carambola.system.pojo.SysConsumeMessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysConsumeMessageMapper extends MsOnionBaseMapper<SysConsumeMessage, SysConsumeMessageExample> {
    long countByExample(SysConsumeMessageExample example);

    int deleteByExample(SysConsumeMessageExample example);

    int deleteByIdxesWithExample(SysConsumeMessageExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(SysConsumeMessage record);

    int insertSelective(SysConsumeMessage record);

    List<SysConsumeMessage> selectByExample(SysConsumeMessageExample example);

    SysConsumeMessage selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") SysConsumeMessage record, @Param("example") SysConsumeMessageExample example);

    int updateByExample(@Param("record") SysConsumeMessage record, @Param("example") SysConsumeMessageExample example);

    int updateByPrimaryKeySelective(SysConsumeMessage record);

    int updateByPrimaryKey(SysConsumeMessage record);
}