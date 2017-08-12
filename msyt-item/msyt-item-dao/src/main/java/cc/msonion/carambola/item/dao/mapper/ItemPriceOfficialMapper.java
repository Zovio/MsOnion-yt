package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemPriceOfficial;
import cc.msonion.carambola.item.pojo.ItemPriceOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemPriceOfficialMapper extends MsOnionBaseMapper<ItemPriceOfficial, ItemPriceOfficialExample> {
    long countByExample(ItemPriceOfficialExample example);

    int deleteByExample(ItemPriceOfficialExample example);

    int deleteByIdxesWithExample(ItemPriceOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemPriceOfficial record);

    int insertSelective(ItemPriceOfficial record);

    List<ItemPriceOfficial> selectByExample(ItemPriceOfficialExample example);

    ItemPriceOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemPriceOfficial record, @Param("example") ItemPriceOfficialExample example);

    int updateByExample(@Param("record") ItemPriceOfficial record, @Param("example") ItemPriceOfficialExample example);

    int updateByPrimaryKeySelective(ItemPriceOfficial record);

    int updateByPrimaryKey(ItemPriceOfficial record);
}