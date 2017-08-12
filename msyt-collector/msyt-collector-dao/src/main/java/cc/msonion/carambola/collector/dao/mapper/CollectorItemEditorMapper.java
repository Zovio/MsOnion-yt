package cc.msonion.carambola.collector.dao.mapper;

import cc.msonion.carambola.collector.pojo.CollectorItemEditor;
import cc.msonion.carambola.collector.pojo.CollectorItemEditorExample;
import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CollectorItemEditorMapper extends MsOnionBaseMapper<CollectorItemEditor, CollectorItemEditorExample> {
    long countByExample(CollectorItemEditorExample example);

    int deleteByExample(CollectorItemEditorExample example);

    int deleteByIdxesWithExample(CollectorItemEditorExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(CollectorItemEditor record);

    int insertSelective(CollectorItemEditor record);

    List<CollectorItemEditor> selectByExample(CollectorItemEditorExample example);

    CollectorItemEditor selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") CollectorItemEditor record, @Param("example") CollectorItemEditorExample example);

    int updateByExample(@Param("record") CollectorItemEditor record, @Param("example") CollectorItemEditorExample example);

    int updateByPrimaryKeySelective(CollectorItemEditor record);

    int updateByPrimaryKey(CollectorItemEditor record);
}