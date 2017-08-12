package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.MemberCate;
import cc.msonion.carambola.member.pojo.MemberCateExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MemberCateMapper extends MsOnionBaseMapper<MemberCate, MemberCateExample> {
    long countByExample(MemberCateExample example);

    int deleteByExample(MemberCateExample example);

    int deleteByIdxesWithExample(MemberCateExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(MemberCate record);

    int insertSelective(MemberCate record);

    List<MemberCate> selectByExample(MemberCateExample example);

    MemberCate selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") MemberCate record, @Param("example") MemberCateExample example);

    int updateByExample(@Param("record") MemberCate record, @Param("example") MemberCateExample example);

    int updateByPrimaryKeySelective(MemberCate record);

    int updateByPrimaryKey(MemberCate record);
}