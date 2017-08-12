package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemAttributeImageOfficial;
import cc.msonion.carambola.item.pojo.ItemAttributeImageOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemAttributeImageOfficialMapper extends MsOnionBaseMapper<ItemAttributeImageOfficial, ItemAttributeImageOfficialExample> {
    long countByExample(ItemAttributeImageOfficialExample example);

    int deleteByExample(ItemAttributeImageOfficialExample example);

    int deleteByIdxesWithExample(ItemAttributeImageOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemAttributeImageOfficial record);

    int insertSelective(ItemAttributeImageOfficial record);

    List<ItemAttributeImageOfficial> selectByExample(ItemAttributeImageOfficialExample example);

    ItemAttributeImageOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemAttributeImageOfficial record, @Param("example") ItemAttributeImageOfficialExample example);

    int updateByExample(@Param("record") ItemAttributeImageOfficial record, @Param("example") ItemAttributeImageOfficialExample example);

    int updateByPrimaryKeySelective(ItemAttributeImageOfficial record);

    int updateByPrimaryKey(ItemAttributeImageOfficial record);
}