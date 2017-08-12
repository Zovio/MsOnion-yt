package cc.msonion.carambola.system.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.system.pojo.SysDataDictionary;
import cc.msonion.carambola.system.pojo.SysDataDictionaryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysDataDictionaryMapper extends MsOnionBaseMapper<SysDataDictionary, SysDataDictionaryExample> {
    long countByExample(SysDataDictionaryExample example);

    int deleteByExample(SysDataDictionaryExample example);

    int deleteByIdxesWithExample(SysDataDictionaryExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(SysDataDictionary record);

    int insertSelective(SysDataDictionary record);

    List<SysDataDictionary> selectByExample(SysDataDictionaryExample example);

    SysDataDictionary selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") SysDataDictionary record, @Param("example") SysDataDictionaryExample example);

    int updateByExample(@Param("record") SysDataDictionary record, @Param("example") SysDataDictionaryExample example);

    int updateByPrimaryKeySelective(SysDataDictionary record);

    int updateByPrimaryKey(SysDataDictionary record);
}