package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorCategoryAttributeGroup;
import cc.msonion.carambola.collector.pojo.CollectorCategoryAttributeGroupExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorCategoryAttributeGroupMapper extends MsOnionBaseMapper<CollectorCategoryAttributeGroup, CollectorCategoryAttributeGroupExample> {
    long countByExample(CollectorCategoryAttributeGroupExample example);

    int deleteByExample(CollectorCategoryAttributeGroupExample example);

    int insert(CollectorCategoryAttributeGroup record);

    int insertSelective(CollectorCategoryAttributeGroup record);

    List<CollectorCategoryAttributeGroup> selectByExample(CollectorCategoryAttributeGroupExample example);

    int updateByExampleSelective(@Param("record") CollectorCategoryAttributeGroup record, @Param("example") CollectorCategoryAttributeGroupExample example);

    int updateByExample(@Param("record") CollectorCategoryAttributeGroup record, @Param("example") CollectorCategoryAttributeGroupExample example);
}