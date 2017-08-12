package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemCollectionOfficial;
import cc.msonion.carambola.item.pojo.ItemCollectionOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemCollectionOfficialMapper extends MsOnionBaseMapper<ItemCollectionOfficial, ItemCollectionOfficialExample> {
    long countByExample(ItemCollectionOfficialExample example);

    int deleteByExample(ItemCollectionOfficialExample example);

    int deleteByIdxesWithExample(ItemCollectionOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemCollectionOfficial record);

    int insertSelective(ItemCollectionOfficial record);

    List<ItemCollectionOfficial> selectByExample(ItemCollectionOfficialExample example);

    ItemCollectionOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemCollectionOfficial record, @Param("example") ItemCollectionOfficialExample example);

    int updateByExample(@Param("record") ItemCollectionOfficial record, @Param("example") ItemCollectionOfficialExample example);

    int updateByPrimaryKeySelective(ItemCollectionOfficial record);

    int updateByPrimaryKey(ItemCollectionOfficial record);
}