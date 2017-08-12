/**
 * 广州市两棵树网络科技有限公司版权所有
 * DT Group Technology & commerce Co., LtdAll rights reserved.
 * <p>
 * 广州市两棵树网络科技有限公司，创立于2009年。旗下运营品牌洋葱小姐。
 * 洋葱小姐（Ms.Onion） 下属三大业务模块 [洋葱海外仓] , [洋葱DSP] , [洋葱海外聚合供应链]
 * [洋葱海外仓]（DFS）系中国海关批准的跨境电商自营平台(Cross-border ecommerce platform)，
 * 合法持有海外直邮保税模式的跨境电商营运资格。是渠道拓展，平台营运，渠道营运管理，及客户服务等前端业务模块。
 * [洋葱DSP]（DSP）系拥有1.3亿消费者大数据分析模型。 是基于客户的消费行为，消费轨迹，及多维度云算法(MDPP)
 * 沉淀而成的精准消费者模型。洋葱DSP能同时为超过36种各行业店铺 及200万个销售端口
 * 进行多店铺高精度配货，并能预判消费者购物需求进行精准推送。同时为洋葱供应链提供更前瞻的商品采买需求模型 。
 * [洋葱海外聚合供应链]（Super Supply Chain）由中国最大的进口贸易集团共同
 * 合资成立，拥有20余年的海外供应链营运经验。并已入股多家海外贸易企业，与欧美澳等9家顶级全球供应商达成战略合作伙伴关系。
 * 目前拥有835个国际品牌直接采买权，12万个单品的商品供应库。并已建设6大海外直邮仓库，为国内客户提供海外商品采买集货供应，
 * 跨境 物流，保税清关三合一的一体化模型。目前是中国唯一多模式聚合的海外商品供应链 。
 * <p>
 * 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1
 * <p>
 * 洋桃商城：http://www.yunyangtao.com
 */


package cc.msonion.carambola.fileresource.api.ext;

/**
 * @Title: FileResourcePersistUtil.java
 * @Package: cc.msonion.carambola.fileresource.api.ext
 * @Description: 文件资源中 持久化util
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月10日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月10日
 * @Modify-version: V2.0.0
 * @Modify-description:
 */

import cc.msonion.carambola.commons.common.enums.MsOnionTableRecordStatus;
import cc.msonion.carambola.fileresource.pojo.FileResourceInfo;
import cc.msonion.carambola.fileresource.common.constants.FileResourceConstants;
import cc.msonion.carambola.fileresource.interfaces.AblumLibService;
import cc.msonion.carambola.fileresource.interfaces.FileResourceUploadService;
import cc.msonion.carambola.fileresource.pojo.AblumLib;
import cc.msonion.carambola.fileresource.pojo.AblumLibExample;
import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import cc.msonion.carambola.parent.common.constants.MsOnionConstants;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.file.MsOnionFileUtils;
import cc.msonion.carambola.parent.ext.log.MsOnionLogger;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @ClassName: FileResourcePersistUtil
 * @Description: 文件资源中 持久化util
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月10日
 *
 */
public final class FileResourcePersistUtils {

    private FileResourcePersistUtils() {

    }

    /**
     * 保存上传结果
     * @param msOnionApiVersion  版本号
     * @param fileResourceInfos  实体集合，多线程传单个对象需要final
     * @param fileResourceUploadService  文件资源 上传service
     * @param msOnionLogger 日志
     * @param ablumLibService 相册service
     * @return 结果
     * @throws MsOnionException 异常
     */
    public static MsOnionResult persistUpload(MsOnionApiVersion msOnionApiVersion, List<FileResourceInfo> fileResourceInfos,
                                              FileResourceUploadService fileResourceUploadService,
                                              MsOnionLogger msOnionLogger, AblumLibService ablumLibService) throws MsOnionException {
        FileResourceInfo fileResourceInfo = fileResourceInfos.get(0);
        //  上传成功才持久化
        if (FileResourceConstants.UPLOAD_SUC.shortValue() == fileResourceInfo.getUploadStatus().shortValue()
                || FileResourceConstants.UPLOAD_ING.shortValue() == fileResourceInfo.getUploadStatus().shortValue()) {
            FileResourceUpload fileResourceUpload = new FileResourceUpload();
            BeanUtils.copyProperties(fileResourceInfo, fileResourceUpload);

            fileResourceUpload.setMessageId(Long.valueOf(fileResourceInfo.getMessageId()));

            if (fileResourceUpload.getPath().startsWith(MsOnionConstants.ALBUM_COLLECT_PATH_START)) {
                fileResourceUpload.setAlbumType(MsOnionConstants.ALBUM_COLLECT);
            } else if (fileResourceUpload.getPath().startsWith(MsOnionConstants.ALBUM_FORMAL_PATH_START)) {
                fileResourceUpload.setAlbumType(MsOnionConstants.ALBUM_FORMAL);
            }
            MsOnionResult msOnionResult = fileResourceUploadService.saveFileResourceDownload(msOnionApiVersion, fileResourceUpload);
            if (msOnionResult.getStatus() != MsOnionStatusConstants.STATUS_200) {
                msOnionLogger.error(FileResourcePersistUtils.class.getName(),
                        "### uuid:" + fileResourceInfo.getMessageId() + ",插入数据库失败原因：" + msOnionResult.getMsg());
                msOnionLogger.info(FileResourcePersistUtils.class.getName(),
                        "### 文件上传成功、但是数据库插入失败，需要通过其他手段补上这条数据。uuid：" + fileResourceInfo.getMessageId());
            } else {
                // 插入相册记录
                FileResourceUpload fru = (FileResourceUpload) msOnionResult.getData();
                saveAblumRecord(msOnionApiVersion, msOnionLogger, ablumLibService, fru);
            }
            return msOnionResult;
        }
        return MsOnionResult.ok();
    }

    /**
     * 保存相册记录
     * @param msOnionApiVersion 版本号
     * @param msOnionLogger 日志service
     * @param ablumLibService  相册库service
     * @param fileResourceUpload 上传记录
     * @throws MsOnionException 异常
     * @return MsOnionResult
     */
    public static MsOnionResult saveAblumRecord(MsOnionApiVersion msOnionApiVersion, MsOnionLogger msOnionLogger, AblumLibService ablumLibService,
                                                FileResourceUpload fileResourceUpload) throws MsOnionException {
        if (fileResourceUpload.getAlbumType() > 0L) {
            String[] arr = fileResourceUpload.getPath().split(MsOnionFileUtils.WINDOWS_PLATFORM_CURRENT_PROJECT_ROOT_DIR);

            final String path = fileResourceUpload.getPath();
            long pid = 0;
            for (int i = 0; i < arr.length; i++) {
                if (StringUtils.isBlank(arr[i])) {
                    continue;
                }
                AblumLibExample example = new AblumLibExample();
                AblumLibExample.Criteria criteria = example.createCriteria();
                criteria.andStatusEqualTo(MsOnionTableRecordStatus.NORMAL.getValue());
                criteria.andNameEqualTo(arr[i]);
                criteria.andPidxEqualTo(pid);
                criteria.andCategoryEqualTo((short) (i));
                List<AblumLib> ablumLibs = ablumLibService.queryByExample(msOnionApiVersion, example);
                if (MsOnionCollectionUtils.isNotEmpty(ablumLibs)) {
                    AblumLib al = ablumLibs.get(0);
                    pid = al.getIdx();
                    // 更新文件个数 和总大小
                    /*FindFileVisitor findJavaVisitor = new FindFileVisitor();
                    String relativePath = NormalFileUtils.getUploadRootDir() + al.getRelativePath();
                    Path path2 = Paths.get(relativePath);
                    if (path2.toFile().exists()) {
                        try {
                            Files.walkFileTree(path2, findJavaVisitor);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        al.setTotalNum(findJavaVisitor.getFilenameList().size());
                        al.setTotalSize(findJavaVisitor.getTotalSize());
                    }
                    ablumLibService.updateByPrimaryKey(msOnionApiVersion, al);*/
                    continue;
                }

                AblumLib ablumLib = new AblumLib();
                ablumLib.setParentName(i == 1 ? "BUCKET" : arr[i - 1]);
                ablumLib.setPidx(pid);
                ablumLib.setCategory((short) i);
                // 便于下载
                ablumLib.setRelativePath(StringUtils.substring(path, 0, path.indexOf(arr[i]) + arr[i].length()));
                ablumLib.setName(arr[i]);
                ablumLib.setAblumType(fileResourceUpload.getAlbumType());

                // 最后一个肯定是文件，需要保存文件属性
                if (i == (arr.length - 1)) {
                    ablumLib.setRemark(fileResourceUpload.getRemark());
                    ablumLib.setFileIdx(fileResourceUpload.getIdx());
                    ablumLib.setTotalSize(fileResourceUpload.getTotalSize());
                    ablumLib.setTotalNum(1);
                    ablumLib.setDirType((short) MsOnionConstants.ONE);
                    ablumLib.setExt(fileResourceUpload.getName());
                }

                MsOnionResult result = ablumLibService.saveAblumLib(msOnionApiVersion, ablumLib);
                AblumLib data = (AblumLib) result.getData();
                pid = data.getIdx();
            }


        }
        return MsOnionResult.ok();
    }

}
