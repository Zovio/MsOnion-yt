package cc.msonion.carambola.warehouse.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.warehouse.pojo.WarehouseType;
import cc.msonion.carambola.warehouse.pojo.WarehouseTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseTypeMapper extends MsOnionBaseMapper<WarehouseType, WarehouseTypeExample> {
    long countByExample(WarehouseTypeExample example);

    int deleteByExample(WarehouseTypeExample example);

    int deleteByIdxesWithExample(WarehouseTypeExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(WarehouseType record);

    int insertSelective(WarehouseType record);

    List<WarehouseType> selectByExample(WarehouseTypeExample example);

    WarehouseType selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") WarehouseType record, @Param("example") WarehouseTypeExample example);

    int updateByExample(@Param("record") WarehouseType record, @Param("example") WarehouseTypeExample example);

    int updateByPrimaryKeySelective(WarehouseType record);

    int updateByPrimaryKey(WarehouseType record);
}