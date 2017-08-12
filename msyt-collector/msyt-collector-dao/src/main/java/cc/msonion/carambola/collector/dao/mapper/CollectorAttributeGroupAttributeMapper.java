package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupAttribute;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupAttributeExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorAttributeGroupAttributeMapper extends MsOnionBaseMapper<CollectorAttributeGroupAttribute, CollectorAttributeGroupAttributeExample> {
    long countByExample(CollectorAttributeGroupAttributeExample example);

    int deleteByExample(CollectorAttributeGroupAttributeExample example);

    int deleteByIdxesWithExample(CollectorAttributeGroupAttributeExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorAttributeGroupAttribute record);

    int insertSelective(CollectorAttributeGroupAttribute record);

    List<CollectorAttributeGroupAttribute> selectByExample(CollectorAttributeGroupAttributeExample example);

    CollectorAttributeGroupAttribute selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorAttributeGroupAttribute record, @Param("example") CollectorAttributeGroupAttributeExample example);

    int updateByExample(@Param("record") CollectorAttributeGroupAttribute record, @Param("example") CollectorAttributeGroupAttributeExample example);

    int updateByPrimaryKeySelective(CollectorAttributeGroupAttribute record);

    int updateByPrimaryKey(CollectorAttributeGroupAttribute record);
}