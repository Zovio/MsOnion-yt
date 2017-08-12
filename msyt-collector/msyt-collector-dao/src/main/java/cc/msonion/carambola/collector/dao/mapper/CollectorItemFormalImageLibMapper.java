package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemFormalImageLib;
import cc.msonion.carambola.collector.pojo.CollectorItemFormalImageLibExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemFormalImageLibMapper extends MsOnionBaseMapper<CollectorItemFormalImageLib, CollectorItemFormalImageLibExample> {
    long countByExample(CollectorItemFormalImageLibExample example);

    int deleteByExample(CollectorItemFormalImageLibExample example);

    int deleteByIdxesWithExample(CollectorItemFormalImageLibExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemFormalImageLib record);

    int insertSelective(CollectorItemFormalImageLib record);

    List<CollectorItemFormalImageLib> selectByExample(CollectorItemFormalImageLibExample example);

    CollectorItemFormalImageLib selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemFormalImageLib record, @Param("example") CollectorItemFormalImageLibExample example);

    int updateByExample(@Param("record") CollectorItemFormalImageLib record, @Param("example") CollectorItemFormalImageLibExample example);

    int updateByPrimaryKeySelective(CollectorItemFormalImageLib record);

    int updateByPrimaryKey(CollectorItemFormalImageLib record);
}