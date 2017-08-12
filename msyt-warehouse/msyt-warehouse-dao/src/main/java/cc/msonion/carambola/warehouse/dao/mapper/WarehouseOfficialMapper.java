package cc.msonion.carambola.warehouse.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficial;
import cc.msonion.carambola.warehouse.pojo.WarehouseOfficialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseOfficialMapper extends MsOnionBaseMapper<WarehouseOfficial, WarehouseOfficialExample> {
    long countByExample(WarehouseOfficialExample example);

    int deleteByExample(WarehouseOfficialExample example);

    int deleteByIdxesWithExample(WarehouseOfficialExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(WarehouseOfficial record);

    int insertSelective(WarehouseOfficial record);

    List<WarehouseOfficial> selectByExample(WarehouseOfficialExample example);

    WarehouseOfficial selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") WarehouseOfficial record, @Param("example") WarehouseOfficialExample example);

    int updateByExample(@Param("record") WarehouseOfficial record, @Param("example") WarehouseOfficialExample example);

    int updateByPrimaryKeySelective(WarehouseOfficial record);

    int updateByPrimaryKey(WarehouseOfficial record);
}