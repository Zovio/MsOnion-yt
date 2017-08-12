package test.msonion.carambola.parent.ext.service.member.dao.mapper;

//import cc.msonion.carambola.member.pojo.TestMember;
//import cc.msonion.carambola.member.pojo.TestMemberExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import org.apache.ibatis.annotations.Param;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMember;
import test.msonion.carambola.parent.ext.service.member.pojo.TestMemberExample;

import java.util.List;

public interface TestMemberMapper extends MsOnionBaseMapper<TestMember, TestMemberExample> {
    long countByExample(TestMemberExample example);

    int deleteByExample(TestMemberExample example);

    int deleteByIdxesWithExample(TestMemberExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(TestMember record);

    int insertSelective(TestMember record);

    List<TestMember> selectByExample(TestMemberExample example);

    TestMember selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") TestMember record, @Param("example") TestMemberExample example);

    int updateByExample(@Param("record") TestMember record, @Param("example") TestMemberExample example);

    int updateByPrimaryKeySelective(TestMember record);

    int updateByPrimaryKey(TestMember record);
}