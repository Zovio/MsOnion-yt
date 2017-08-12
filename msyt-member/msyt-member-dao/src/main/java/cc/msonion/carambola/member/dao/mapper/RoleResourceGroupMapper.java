package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.RoleResourceGroup;
import cc.msonion.carambola.member.pojo.RoleResourceGroupExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleResourceGroupMapper extends MsOnionBaseMapper<RoleResourceGroup, RoleResourceGroupExample> {
    long countByExample(RoleResourceGroupExample example);

    int deleteByExample(RoleResourceGroupExample example);

    int deleteByIdxesWithExample(RoleResourceGroupExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(RoleResourceGroup record);

    int insertSelective(RoleResourceGroup record);

    List<RoleResourceGroup> selectByExample(RoleResourceGroupExample example);

    RoleResourceGroup selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") RoleResourceGroup record, @Param("example") RoleResourceGroupExample example);

    int updateByExample(@Param("record") RoleResourceGroup record, @Param("example") RoleResourceGroupExample example);

    int updateByPrimaryKeySelective(RoleResourceGroup record);

    int updateByPrimaryKey(RoleResourceGroup record);
}