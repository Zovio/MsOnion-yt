package cc.msonion.carambola.member.dao.mapper;

import cc.msonion.carambola.member.pojo.ResourceGroup;
import cc.msonion.carambola.member.pojo.ResourceGroupExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ResourceGroupMapper extends MsOnionBaseMapper<ResourceGroup, ResourceGroupExample> {
    long countByExample(ResourceGroupExample example);

    int deleteByExample(ResourceGroupExample example);

    int deleteByIdxesWithExample(ResourceGroupExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(ResourceGroup record);

    int insertSelective(ResourceGroup record);

    List<ResourceGroup> selectByExample(ResourceGroupExample example);

    ResourceGroup selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") ResourceGroup record, @Param("example") ResourceGroupExample example);

    int updateByExample(@Param("record") ResourceGroup record, @Param("example") ResourceGroupExample example);

    int updateByPrimaryKeySelective(ResourceGroup record);

    int updateByPrimaryKey(ResourceGroup record);
}