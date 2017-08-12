package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemCollection;
import cc.msonion.carambola.collector.pojo.CollectorItemCollectionExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemCollectionMapper extends MsOnionBaseMapper<CollectorItemCollection, CollectorItemCollectionExample> {
    long countByExample(CollectorItemCollectionExample example);

    int deleteByExample(CollectorItemCollectionExample example);

    int deleteByIdxesWithExample(CollectorItemCollectionExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemCollection record);

    int insertSelective(CollectorItemCollection record);

    List<CollectorItemCollection> selectByExample(CollectorItemCollectionExample example);

    CollectorItemCollection selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemCollection record, @Param("example") CollectorItemCollectionExample example);

    int updateByExample(@Param("record") CollectorItemCollection record, @Param("example") CollectorItemCollectionExample example);

    int updateByPrimaryKeySelective(CollectorItemCollection record);

    int updateByPrimaryKey(CollectorItemCollection record);
}