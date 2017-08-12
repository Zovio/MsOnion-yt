package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.MemberRole;
import cc.msonion.carambola.member.pojo.MemberRoleExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberRoleMapper extends MsOnionBaseMapper<MemberRole, MemberRoleExample> {
    long countByExample(MemberRoleExample example);

    int deleteByExample(MemberRoleExample example);

    int deleteByIdxesWithExample(MemberRoleExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(MemberRole record);

    int insertSelective(MemberRole record);

    List<MemberRole> selectByExample(MemberRoleExample example);

    MemberRole selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") MemberRole record, @Param("example") MemberRoleExample example);

    int updateByExample(@Param("record") MemberRole record, @Param("example") MemberRoleExample example);

    int updateByPrimaryKeySelective(MemberRole record);

    int updateByPrimaryKey(MemberRole record);
}