package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemBarcode;
import cc.msonion.carambola.collector.pojo.CollectorItemBarcodeExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemBarcodeMapper extends MsOnionBaseMapper<CollectorItemBarcode, CollectorItemBarcodeExample> {
    long countByExample(CollectorItemBarcodeExample example);

    int deleteByExample(CollectorItemBarcodeExample example);

    int deleteByIdxesWithExample(CollectorItemBarcodeExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemBarcode record);

    int insertSelective(CollectorItemBarcode record);

    List<CollectorItemBarcode> selectByExample(CollectorItemBarcodeExample example);

    CollectorItemBarcode selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemBarcode record, @Param("example") CollectorItemBarcodeExample example);

    int updateByExample(@Param("record") CollectorItemBarcode record, @Param("example") CollectorItemBarcodeExample example);

    int updateByPrimaryKeySelective(CollectorItemBarcode record);

    int updateByPrimaryKey(CollectorItemBarcode record);
}