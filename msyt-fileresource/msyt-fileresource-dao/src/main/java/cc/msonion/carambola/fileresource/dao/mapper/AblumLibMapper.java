package cc.msonion.carambola.fileresource.dao.mapper;

import cc.msonion.carambola.fileresource.pojo.AblumLib;
import cc.msonion.carambola.fileresource.pojo.AblumLibExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AblumLibMapper extends MsOnionBaseMapper<AblumLib, AblumLibExample> {
    long countByExample(AblumLibExample example);

    int deleteByExample(AblumLibExample example);

    int deleteByIdxesWithExample(AblumLibExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(AblumLib record);

    int insertSelective(AblumLib record);

    List<AblumLib> selectByExample(AblumLibExample example);

    AblumLib selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") AblumLib record, @Param("example") AblumLibExample example);

    int updateByExample(@Param("record") AblumLib record, @Param("example") AblumLibExample example);

    int updateByPrimaryKeySelective(AblumLib record);

    int updateByPrimaryKey(AblumLib record);
}