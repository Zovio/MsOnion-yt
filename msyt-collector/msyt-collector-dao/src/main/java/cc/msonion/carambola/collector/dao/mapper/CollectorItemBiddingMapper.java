package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemBidding;
import cc.msonion.carambola.collector.pojo.CollectorItemBiddingExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemBiddingMapper extends MsOnionBaseMapper<CollectorItemBidding, CollectorItemBiddingExample> {
    long countByExample(CollectorItemBiddingExample example);

    int deleteByExample(CollectorItemBiddingExample example);

    int deleteByIdxesWithExample(CollectorItemBiddingExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemBidding record);

    int insertSelective(CollectorItemBidding record);

    List<CollectorItemBidding> selectByExample(CollectorItemBiddingExample example);

    CollectorItemBidding selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemBidding record, @Param("example") CollectorItemBiddingExample example);

    int updateByExample(@Param("record") CollectorItemBidding record, @Param("example") CollectorItemBiddingExample example);

    int updateByPrimaryKeySelective(CollectorItemBidding record);

    int updateByPrimaryKey(CollectorItemBidding record);
}