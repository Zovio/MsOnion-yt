package cc.msonion.carambola.item.dao.mapper;

import cc.msonion.carambola.item.pojo.ItemOfficial;
import cc.msonion.carambola.item.pojo.ItemOfficialExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemOfficialMapper extends MsOnionBaseMapper<ItemOfficial, ItemOfficialExample> {
    long countByExample(ItemOfficialExample example);

    int deleteByExample(ItemOfficialExample example);

    int deleteByIdxesWithExample(ItemOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ItemOfficial record);

    int insertSelective(ItemOfficial record);

    List<ItemOfficial> selectByExample(ItemOfficialExample example);

    ItemOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ItemOfficial record, @Param("example") ItemOfficialExample example);

    int updateByExample(@Param("record") ItemOfficial record, @Param("example") ItemOfficialExample example);

    int updateByPrimaryKeySelective(ItemOfficial record);

    int updateByPrimaryKey(ItemOfficial record);
}