package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.Button;
import cc.msonion.carambola.member.pojo.ButtonExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ButtonMapper extends MsOnionBaseMapper<Button, ButtonExample> {
    long countByExample(ButtonExample example);

    int deleteByExample(ButtonExample example);

    int deleteByIdxesWithExample(ButtonExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Button record);

    int insertSelective(Button record);

    List<Button> selectByExample(ButtonExample example);

    Button selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Button record, @Param("example") ButtonExample example);

    int updateByExample(@Param("record") Button record, @Param("example") ButtonExample example);

    int updateByPrimaryKeySelective(Button record);

    int updateByPrimaryKey(Button record);
}