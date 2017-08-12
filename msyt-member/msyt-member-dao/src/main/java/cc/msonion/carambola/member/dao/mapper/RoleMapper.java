package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.Role;
import cc.msonion.carambola.member.pojo.RoleExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper extends MsOnionBaseMapper<Role, RoleExample> {
    long countByExample(RoleExample example);

    int deleteByExample(RoleExample example);

    int deleteByIdxesWithExample(RoleExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Role record);

    int insertSelective(Role record);

    List<Role> selectByExample(RoleExample example);

    Role selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByExample(@Param("record") Role record, @Param("example") RoleExample example);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}