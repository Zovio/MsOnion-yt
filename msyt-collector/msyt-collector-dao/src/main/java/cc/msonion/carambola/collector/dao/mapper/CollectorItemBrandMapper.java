package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemBrand;
import cc.msonion.carambola.collector.pojo.CollectorItemBrandExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemBrandMapper extends MsOnionBaseMapper<CollectorItemBrand, CollectorItemBrandExample> {
    long countByExample(CollectorItemBrandExample example);

    int deleteByExample(CollectorItemBrandExample example);

    int deleteByIdxesWithExample(CollectorItemBrandExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemBrand record);

    int insertSelective(CollectorItemBrand record);

    List<CollectorItemBrand> selectByExample(CollectorItemBrandExample example);

    CollectorItemBrand selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemBrand record, @Param("example") CollectorItemBrandExample example);

    int updateByExample(@Param("record") CollectorItemBrand record, @Param("example") CollectorItemBrandExample example);

    int updateByPrimaryKeySelective(CollectorItemBrand record);

    int updateByPrimaryKey(CollectorItemBrand record);
}