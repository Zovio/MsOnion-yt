package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemAttribute;
import cc.msonion.carambola.collector.pojo.CollectorItemAttributeExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemAttributeMapper extends MsOnionBaseMapper<CollectorItemAttribute, CollectorItemAttributeExample> {
    long countByExample(CollectorItemAttributeExample example);

    int deleteByExample(CollectorItemAttributeExample example);

    int deleteByIdxesWithExample(CollectorItemAttributeExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemAttribute record);

    int insertSelective(CollectorItemAttribute record);

    List<CollectorItemAttribute> selectByExample(CollectorItemAttributeExample example);

    CollectorItemAttribute selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemAttribute record, @Param("example") CollectorItemAttributeExample example);

    int updateByExample(@Param("record") CollectorItemAttribute record, @Param("example") CollectorItemAttributeExample example);

    int updateByPrimaryKeySelective(CollectorItemAttribute record);

    int updateByPrimaryKey(CollectorItemAttribute record);
}