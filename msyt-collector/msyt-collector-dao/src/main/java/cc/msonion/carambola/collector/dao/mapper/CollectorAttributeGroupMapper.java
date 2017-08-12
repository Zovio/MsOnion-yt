package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorAttributeGroup;
import cc.msonion.carambola.collector.pojo.CollectorAttributeGroupExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorAttributeGroupMapper extends MsOnionBaseMapper<CollectorAttributeGroup, CollectorAttributeGroupExample> {
    long countByExample(CollectorAttributeGroupExample example);

    int deleteByExample(CollectorAttributeGroupExample example);

    int deleteByIdxesWithExample(CollectorAttributeGroupExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorAttributeGroup record);

    int insertSelective(CollectorAttributeGroup record);

    List<CollectorAttributeGroup> selectByExample(CollectorAttributeGroupExample example);

    CollectorAttributeGroup selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorAttributeGroup record, @Param("example") CollectorAttributeGroupExample example);

    int updateByExample(@Param("record") CollectorAttributeGroup record, @Param("example") CollectorAttributeGroupExample example);

    int updateByPrimaryKeySelective(CollectorAttributeGroup record);

    int updateByPrimaryKey(CollectorAttributeGroup record);
}