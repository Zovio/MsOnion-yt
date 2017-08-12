package cc.msonion.carambola.system.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.system.pojo.SysSetting;
import cc.msonion.carambola.system.pojo.SysSettingExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysSettingMapper extends MsOnionBaseMapper<SysSetting, SysSettingExample> {
    long countByExample(SysSettingExample example);

    int deleteByExample(SysSettingExample example);

    int deleteByIdxesWithExample(SysSettingExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(SysSetting record);

    int insertSelective(SysSetting record);

    List<SysSetting> selectByExample(SysSettingExample example);

    SysSetting selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") SysSetting record, @Param("example") SysSettingExample example);

    int updateByExample(@Param("record") SysSetting record, @Param("example") SysSettingExample example);

    int updateByPrimaryKeySelective(SysSetting record);

    int updateByPrimaryKey(SysSetting record);
}