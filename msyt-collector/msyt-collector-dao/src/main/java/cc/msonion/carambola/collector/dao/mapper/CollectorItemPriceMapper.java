package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemPrice;
import cc.msonion.carambola.collector.pojo.CollectorItemPriceExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemPriceMapper extends MsOnionBaseMapper<CollectorItemPrice, CollectorItemPriceExample> {
    long countByExample(CollectorItemPriceExample example);

    int deleteByExample(CollectorItemPriceExample example);

    int deleteByIdxesWithExample(CollectorItemPriceExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemPrice record);

    int insertSelective(CollectorItemPrice record);

    List<CollectorItemPrice> selectByExample(CollectorItemPriceExample example);

    CollectorItemPrice selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemPrice record, @Param("example") CollectorItemPriceExample example);

    int updateByExample(@Param("record") CollectorItemPrice record, @Param("example") CollectorItemPriceExample example);

    int updateByPrimaryKeySelective(CollectorItemPrice record);

    int updateByPrimaryKey(CollectorItemPrice record);
}