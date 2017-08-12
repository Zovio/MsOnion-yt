package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemBasicOfficial;
import cc.msonion.carambola.item.pojo.ItemBasicOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemBasicOfficialMapper extends MsOnionBaseMapper<ItemBasicOfficial, ItemBasicOfficialExample> {
    long countByExample(ItemBasicOfficialExample example);

    int deleteByExample(ItemBasicOfficialExample example);

    int deleteByIdxesWithExample(ItemBasicOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemBasicOfficial record);

    int insertSelective(ItemBasicOfficial record);

    List<ItemBasicOfficial> selectByExample(ItemBasicOfficialExample example);

    ItemBasicOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemBasicOfficial record, @Param("example") ItemBasicOfficialExample example);

    int updateByExample(@Param("record") ItemBasicOfficial record, @Param("example") ItemBasicOfficialExample example);

    int updateByPrimaryKeySelective(ItemBasicOfficial record);

    int updateByPrimaryKey(ItemBasicOfficial record);
}