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


package cc.msonion.carambola.fileresource.service.ext;

/**
 * @Title: AblumTask.java
 * @Package: cc.msonion.carambola.fileresource.service.task
 * @Description: 相册图片fark join操作任务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月15日
 * @Version: V2.0.0
 * @Modify-by: liaoxf
 * @Modify-date: 2017年05月15日
 * @Modify-version: V2.0.0
 * @Modify-description:
 *
 */

import cc.msonion.carambola.fileresource.common.viewobject.AlbumViewObject;
import cc.msonion.carambola.fileresource.pojo.FileResourceUpload;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;

/**
 * @ClassName: AblumTask
 * @Description: 相册图片fark join(jdk1.7)操作任务
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年05月15日
 *
 */
public class AblumTask extends RecursiveTask<ArrayList<AlbumViewObject>> {

    /**
     * 文件对象集合
     */
    private List<FileResourceUpload> list;

    /**
     * 上传根目录
     */
    private String  uploadRootDir;

    public AblumTask(List<FileResourceUpload> list, String uploadRootDir) {
        this.list = list;
        this.uploadRootDir = uploadRootDir;
    }

    /**
     * 相册对象集合
     * @param fileResourceUpload 文件资源对象
     * @return 集合
     */
    private ArrayList<AlbumViewObject> handleAlbumData(FileResourceUpload fileResourceUpload) {
        ArrayList<AlbumViewObject> albumList = new ArrayList<>();

        String path = fileResourceUpload.getPath();
        String[] ablumArr = StringUtils.split(path, File.separator);
        // 去掉数组的第一个元素就是我们需要的相册目录和文件
       // System.out.println(Arrays.toString(ablumArr));
        for (int i = 1; i < ablumArr.length; i++) {
            // 循环构造目录 和 文件
            AlbumViewObject album = new AlbumViewObject();
            album.setAlbumType(fileResourceUpload.getAlbumType());
            album.setFileIdx(fileResourceUpload.getIdx());
            album.setMessageId(fileResourceUpload.getMessageId().toString());
            album.setParentName(ablumArr[i - 1]);
            album.setName(ablumArr[i]);
            album.setCreateTime(fileResourceUpload.getCreateTime());
            album.setRelativePath(StringUtils.substring(path, 0, path.indexOf(ablumArr[i]) + ablumArr[i].length()));

            // 到了文件这一级时需要 记录文件真实名称 和 remark
            if (i == (ablumArr.length - 1)) {
                album.setRemark(fileResourceUpload.getRemark());
                album.setName(fileResourceUpload.getName());
            }
            albumList.add(album);
        }


        return albumList;
    }

    /**
     * The main computation performed by this task.
     *
     * @return the result of the computation
     */
    @Override
    protected ArrayList<AlbumViewObject> compute() {
        ArrayList<AlbumViewObject> albumList = new ArrayList<>();
        if (Optional.ofNullable(list).isPresent()) {
            if (list.size() < 20) {
                list.forEach(fileResourceUpload -> {
                    albumList.addAll(handleAlbumData(fileResourceUpload));
                });
            } else {
                int middle = list.size() / 2;
                AblumTask left = new AblumTask(list.subList(0, middle), uploadRootDir);
                AblumTask right = new AblumTask(list.subList(middle, list.size()), uploadRootDir);
                left.fork();
                right.fork();
                albumList.addAll(left.join());
                albumList.addAll(right.join());
            }
        }
        // 根据名称去重
        HashSet set = new HashSet(albumList);
        albumList.clear();
        albumList.addAll(set);
        // 循环统计各个文件夹的文件数量和总大小
        List<AlbumViewObject> collectList = albumList.parallelStream().peek(s -> {
            try {
                FindFileVisitor findJavaVisitor = new FindFileVisitor();
                String relativePath = uploadRootDir + s.getRelativePath();
                Path path = Paths.get(relativePath);
                if (!path.toFile().exists()) {
                    return;
                }
                Files.walkFileTree(path, findJavaVisitor);
                s.setTotalNum(findJavaVisitor.getFilenameList().size());
                s.setTotalSize(findJavaVisitor.getTotalSize());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).collect(Collectors.toList());
        return (ArrayList<AlbumViewObject>) collectList;
    }
}
