package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorAttribute;
import cc.msonion.carambola.collector.pojo.CollectorAttributeExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorAttributeMapper extends MsOnionBaseMapper<CollectorAttribute, CollectorAttributeExample> {
    long countByExample(CollectorAttributeExample example);

    int deleteByExample(CollectorAttributeExample example);

    int deleteByIdxesWithExample(CollectorAttributeExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorAttribute record);

    int insertSelective(CollectorAttribute record);

    List<CollectorAttribute> selectByExample(CollectorAttributeExample example);

    CollectorAttribute selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorAttribute record, @Param("example") CollectorAttributeExample example);

    int updateByExample(@Param("record") CollectorAttribute record, @Param("example") CollectorAttributeExample example);

    int updateByPrimaryKeySelective(CollectorAttribute record);

    int updateByPrimaryKey(CollectorAttribute record);
}