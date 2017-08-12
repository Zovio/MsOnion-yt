package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.Member;
import cc.msonion.carambola.member.pojo.MemberExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberMapper extends MsOnionBaseMapper<Member, MemberExample> {
    long countByExample(MemberExample example);

    int deleteByExample(MemberExample example);

    int deleteByIdxesWithExample(MemberExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Member record);

    int insertSelective(Member record);

    List<Member> selectByExample(MemberExample example);

    Member selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByExample(@Param("record") Member record, @Param("example") MemberExample example);

    int updateByPrimaryKeySelective(Member record);

    int updateByPrimaryKey(Member record);
}