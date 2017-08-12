package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemAttributeImage;
import cc.msonion.carambola.collector.pojo.CollectorItemAttributeImageExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemAttributeImageMapper extends MsOnionBaseMapper<CollectorItemAttributeImage, CollectorItemAttributeImageExample> {
    long countByExample(CollectorItemAttributeImageExample example);

    int deleteByExample(CollectorItemAttributeImageExample example);

    int deleteByIdxesWithExample(CollectorItemAttributeImageExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemAttributeImage record);

    int insertSelective(CollectorItemAttributeImage record);

    List<CollectorItemAttributeImage> selectByExample(CollectorItemAttributeImageExample example);

    CollectorItemAttributeImage selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemAttributeImage record, @Param("example") CollectorItemAttributeImageExample example);

    int updateByExample(@Param("record") CollectorItemAttributeImage record, @Param("example") CollectorItemAttributeImageExample example);

    int updateByPrimaryKeySelective(CollectorItemAttributeImage record);

    int updateByPrimaryKey(CollectorItemAttributeImage record);
}