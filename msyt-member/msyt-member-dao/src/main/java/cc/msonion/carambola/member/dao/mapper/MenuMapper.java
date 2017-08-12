package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.Menu;
import cc.msonion.carambola.member.pojo.MenuExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MenuMapper extends MsOnionBaseMapper<Menu, MenuExample> {
    long countByExample(MenuExample example);

    int deleteByExample(MenuExample example);

    int deleteByIdxesWithExample(MenuExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Menu record);

    int insertSelective(Menu record);

    List<Menu> selectByExample(MenuExample example);

    Menu selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByExample(@Param("record") Menu record, @Param("example") MenuExample example);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);
}