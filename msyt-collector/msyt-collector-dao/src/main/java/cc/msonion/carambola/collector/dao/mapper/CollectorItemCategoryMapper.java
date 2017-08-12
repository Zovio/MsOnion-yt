package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemCategory;
import cc.msonion.carambola.collector.pojo.CollectorItemCategoryExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemCategoryMapper extends MsOnionBaseMapper<CollectorItemCategory, CollectorItemCategoryExample> {
    long countByExample(CollectorItemCategoryExample example);

    int deleteByExample(CollectorItemCategoryExample example);

    int deleteByIdxesWithExample(CollectorItemCategoryExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemCategory record);

    int insertSelective(CollectorItemCategory record);

    List<CollectorItemCategory> selectByExample(CollectorItemCategoryExample example);

    CollectorItemCategory selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemCategory record, @Param("example") CollectorItemCategoryExample example);

    int updateByExample(@Param("record") CollectorItemCategory record, @Param("example") CollectorItemCategoryExample example);

    int updateByPrimaryKeySelective(CollectorItemCategory record);

    int updateByPrimaryKey(CollectorItemCategory record);
}