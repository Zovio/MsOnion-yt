package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.Resource;
import cc.msonion.carambola.member.pojo.ResourceExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceMapper extends MsOnionBaseMapper<Resource, ResourceExample> {
    long countByExample(ResourceExample example);

    int deleteByExample(ResourceExample example);

    int deleteByIdxesWithExample(ResourceExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(Resource record);

    int insertSelective(Resource record);

    List<Resource> selectByExample(ResourceExample example);

    Resource selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByExample(@Param("record") Resource record, @Param("example") ResourceExample example);

    int updateByPrimaryKeySelective(Resource record);

    int updateByPrimaryKey(Resource record);
}