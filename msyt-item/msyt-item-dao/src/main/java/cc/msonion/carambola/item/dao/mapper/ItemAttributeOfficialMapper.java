package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemAttributeOfficial;
import cc.msonion.carambola.item.pojo.ItemAttributeOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemAttributeOfficialMapper extends MsOnionBaseMapper<ItemAttributeOfficial, ItemAttributeOfficialExample> {
    long countByExample(ItemAttributeOfficialExample example);

    int deleteByExample(ItemAttributeOfficialExample example);

    int deleteByIdxesWithExample(ItemAttributeOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemAttributeOfficial record);

    int insertSelective(ItemAttributeOfficial record);

    List<ItemAttributeOfficial> selectByExample(ItemAttributeOfficialExample example);

    ItemAttributeOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemAttributeOfficial record, @Param("example") ItemAttributeOfficialExample example);

    int updateByExample(@Param("record") ItemAttributeOfficial record, @Param("example") ItemAttributeOfficialExample example);

    int updateByPrimaryKeySelective(ItemAttributeOfficial record);

    int updateByPrimaryKey(ItemAttributeOfficial record);
}