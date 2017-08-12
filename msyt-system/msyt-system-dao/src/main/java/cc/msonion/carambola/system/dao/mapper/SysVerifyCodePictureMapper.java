package cc.msonion.carambola.system.dao.mapper;

import cc.msonion.carambola.parent.interfaces.mapper.base.MsOnionBaseMapper;
import cc.msonion.carambola.system.pojo.SysVerifyCodePicture;
import cc.msonion.carambola.system.pojo.SysVerifyCodePictureExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SysVerifyCodePictureMapper extends MsOnionBaseMapper<SysVerifyCodePicture, SysVerifyCodePictureExample> {
    long countByExample(SysVerifyCodePictureExample example);

    int deleteByExample(SysVerifyCodePictureExample example);

    int deleteByIdxesWithExample(SysVerifyCodePictureExample example);

    int deleteByPrimaryKey(Long idx);

    int insert(SysVerifyCodePicture record);

    int insertSelective(SysVerifyCodePicture record);

    List<SysVerifyCodePicture> selectByExample(SysVerifyCodePictureExample example);

    SysVerifyCodePicture selectByPrimaryKey(Long idx);

    int updateByExampleSelective(@Param("record") SysVerifyCodePicture record, @Param("example") SysVerifyCodePictureExample example);

    int updateByExample(@Param("record") SysVerifyCodePicture record, @Param("example") SysVerifyCodePictureExample example);

    int updateByPrimaryKeySelective(SysVerifyCodePicture record);

    int updateByPrimaryKey(SysVerifyCodePicture record);
}