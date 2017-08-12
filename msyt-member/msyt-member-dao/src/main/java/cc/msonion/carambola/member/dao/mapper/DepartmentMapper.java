package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.Department;
import cc.msonion.carambola.member.pojo.DepartmentExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper extends MsOnionBaseMapper<Department, DepartmentExample> {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByIdxesWithExample(DepartmentExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Department record);

    int insertSelective(Department record);

    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}