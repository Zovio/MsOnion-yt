package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemBarcodeOfficial;
import cc.msonion.carambola.item.pojo.ItemBarcodeOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemBarcodeOfficialMapper extends MsOnionBaseMapper<ItemBarcodeOfficial, ItemBarcodeOfficialExample> {
    long countByExample(ItemBarcodeOfficialExample example);

    int deleteByExample(ItemBarcodeOfficialExample example);

    int deleteByIdxesWithExample(ItemBarcodeOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemBarcodeOfficial record);

    int insertSelective(ItemBarcodeOfficial record);

    List<ItemBarcodeOfficial> selectByExample(ItemBarcodeOfficialExample example);

    ItemBarcodeOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemBarcodeOfficial record, @Param("example") ItemBarcodeOfficialExample example);

    int updateByExample(@Param("record") ItemBarcodeOfficial record, @Param("example") ItemBarcodeOfficialExample example);

    int updateByPrimaryKeySelective(ItemBarcodeOfficial record);

    int updateByPrimaryKey(ItemBarcodeOfficial record);
}