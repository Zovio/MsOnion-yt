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
package cc.msonion.carambola.cache.service.impl;

import cc.msonion.carambola.cache.common.constants.CacheConstants;
import cc.msonion.carambola.cache.ext.utils.CacheKeyTreeToMenu;
import cc.msonion.carambola.cache.pojo.CacheKey;
import cc.msonion.carambola.cache.pojo.CacheObject;
import cc.msonion.carambola.cache.service.CacheService;
import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.common.utils.security.MsOnionSecurityUtils;
import cc.msonion.carambola.parent.interfaces.service.MsOnionCacheService;
import cc.msonion.carambola.parent.pojo.MsOnionApiVersion;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.*;


/**
 * @Title: CacheService.java
 * @Package: cc.msonion.carambola.cache.service
 * @Description: 缓存中心接口实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月10日 15:38
 * @Version: V2.0.0
 * @Modify-by: DorenWu DorenWu@msyc.cc
 * @Modify-date: 2017年5月10日 15:38
 * @Modify-version: V2.0.0
 * @Modify-description: 缓存中心接口实现
 */

/**
 * @ClassName: CacheService
 * @Description: 缓存中心接口实现
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: DorenWu DorenWu@msyc.cc
 * @Date: 2017年5月10日 15:38
 */
@Service
public class CacheServiceImpl extends MsOnionCacheService implements CacheService {

    /**
     * @param apiVersion api版本
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title getAllCacheKeys
     * @Description 查询缓存中心获取所有缓存Key的对象集合
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    @Override
    public MsOnionResult getAllCacheKeys(MsOnionApiVersion apiVersion) throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 检查版本
            inspectParameters(apiVersion);
            // 查询所有KEY的集合
            Set<String> allKeys = getMsOnionJedisAdapter().keys(CacheConstants.ALL_KEY_PATTERN);
            this.getMsOnionLogger().info(this.getClass().getName(), "##获取所有缓存键====" + allKeys.size());
            // 设置CacheKey对象的集合
            TreeSet<CacheKey> cacheKeyList = setToKeyList(allKeys);
            return MsOnionResult.ok(cacheKeyList);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param apiVersion api版本
     * @param cacheKey   缓存key对象
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 根据Key的路径查找所有KEY的集合
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月12日 下午13:44:20
     */
    @Override
    public MsOnionResult getCacheKeyList(MsOnionApiVersion apiVersion, CacheKey cacheKey)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 检查版本
            inspectParameters(apiVersion);
            int pathSize = 0;
            String parentPath = CacheConstants.ALL_KEY_PATTERN;
            String keyPath = cacheKey.getPath();
            if (StringUtils.isEmpty(keyPath)) {
                keyPath = CacheConstants.ALL_KEY_PATTERN;
            } else {
                parentPath = cacheKey.getPath();
                keyPath = keyPath + CacheConstants.ALL_KEY_PATTERN;
                pathSize = keyPath.split(CacheConstants.KEY_PATH_SEPARATOR).length;
            }
            // 查询所有KEY的集合
            Set<String> allKeys = getMsOnionJedisAdapter().keys(keyPath);
            TreeSet<CacheKey> cacheKeyList = setToSubKeyList(allKeys, pathSize, parentPath);
            return MsOnionResult.ok(cacheKeyList);
        } catch (Exception ex) {
            throw new MsOnionException(ex);
        }
    }

    /**
     * 设置CacheKey对象的KEY根目录集合
     *
     * @param allKeys  从缓存中心查询出来的全部key集合
     * @param pathSize 目录路径的长度
     * @param keyPath 父级目录的路劲
     * @return CacheKey 对象的集合
     * @throws MsOnionException MsOnion异常类
     */
    private TreeSet<CacheKey> setToSubKeyList(Set<String> allKeys, Integer pathSize, String keyPath) {
            TreeSet<CacheKey> cacheKeyList = new TreeSet<CacheKey>();
            // 循环设置缓存KEY的根目录
            for (String keys : allKeys) {
                // 分割字符串
                String[] rootKeyArr = keys.split(CacheConstants.KEY_PATH_SEPARATOR);
                // 当前目录的名字
                if (rootKeyArr.length - 1 < pathSize) {
                    continue;
                }
                String rootKey = rootKeyArr[pathSize];
                // 如果不是以父目录的路劲开头的跳过
                if (!keyPath.equals(CacheConstants.ALL_KEY_PATTERN)
                        && !keys.startsWith(keyPath + CacheConstants.KEY_PATH_SEPARATOR)) {
                    continue;
                }
                // 如果当前目录的名字是KEYS，不处理
                if (rootKey.equals(CacheConstants.KEY_INNER_KEYS)) {
                    continue;
                }
                // 循环判断这个集合是否已经把这个名字加进去了
                boolean isIn = hasAdd(cacheKeyList, rootKey);
                // 没有把名字加进集合，就继续处理
                if (isIn) {
                    continue;
                }
                CacheKey ck = new CacheKey();
                ck.setKey(rootKey);
                if (rootKeyArr.length == (pathSize + 1)) {
                    // 是叶子节点
                    ck.setLeaf(true);
                }

                // pathSize == 0 根目录
                if (pathSize == 0) {
                    ck.setPath(rootKey);
                    // 拼接KEYS键的前缀 例如 BUTTON_INFO:KEYS:BUTTON 中的 BUTTON_INFO:KEYS
                    String beginKey = rootKey + CacheConstants.KEY_PATH_SEPARATOR + CacheConstants.KEY_INNER_KEYS;
                    // 根据 BUTTON_INFO:KEYS
                    // 查询所有叶子节点的数量
                    Long countNum = getMsOnionJedisAdapter().scard(beginKey);
                    // setKey 这个主要是用于根目录查询所有叶子节点的数量
                    ck.setSetKey(beginKey);
                    // 所有子叶子节点的数量
                    ck.setCountNum(countNum);
                } else {
                    // 设置key的path
                    if (keyPath.endsWith(CacheConstants.PAGE)) {
                        // 如果是PAGE，需要特殊处理
                        ck.setLeaf(true);
                        String endString = keys.substring(keys.indexOf(keyPath), keys.length());
                        ck.setPath(endString);
                    } else {
                        ck.setPath(getPathPre(rootKeyArr, pathSize));
                    }
                }
                cacheKeyList.add(ck);
            }
            return cacheKeyList;
    }

    /**
     * 返回多少长度的数组
     *
     * @param rootKeyArr 字符串数组
     * @param pathSize   长度
     * @return 返回多少长度的数组
     */
    private String getPathPre(String[] rootKeyArr, Integer pathSize) {
        String[] newArr = new String[pathSize + 1];
        for (int i = 0; i <= pathSize; i++) {
            newArr[i] = rootKeyArr[i];
        }
        return StringUtils.join(newArr, CacheConstants.KEY_PATH_SEPARATOR);
    }


    /**
     * 设置CacheKey对象的集合
     *
     * @param allKeys 从缓存中心查询出来的全部key集合
     * @return CacheKey对象的集合
     * @throws MsOnionException MsOnion异常类
     */
    private TreeSet<CacheKey> setToKeyList(Set<String> allKeys) throws MsOnionException {
        TreeSet<CacheKey> cacheKeyList = new TreeSet<CacheKey>();
        // 循环设置缓存KEY的根目录
        for (String keys : allKeys) {
            String[] rootKeyArr = keys.split(CacheConstants.KEY_PATH_SEPARATOR);
            String rootKey = rootKeyArr[0];
            boolean isIn = hasAdd(cacheKeyList, rootKey);
            if (!isIn) {
                CacheKey ck = new CacheKey(rootKey, rootKey);
                cacheKeyList.add(ck);
            }
        }
        // 递归设置子集
        for (CacheKey ck : cacheKeyList) {
            loopKeyList(ck, allKeys);
        }
        return cacheKeyList;
    }

    /**
     * 循环查找判断集合中是否已经存在缓存Key路径等于R的字符串的对象
     *
     * @param cacheKeyList CacheKey对象的集合
     * @param keyPath      缓存Key路径
     * @return 返回是否存在 false-不存在 true-存在
     */
    private boolean hasAdd(TreeSet<CacheKey> cacheKeyList, String keyPath) {
        boolean isIN = false;
        for (CacheKey ck : cacheKeyList) {
            if (ck.getPath().equals(keyPath)) {
                isIN = true;
            }
        }
        return isIN;
    }

    /**
     * 循环设置CacheKey对象的子集合
     *
     * @param ck      CacheKey对象
     * @param allKeys 从缓存中心查询出来的全部key集合
     */
    private void loopKeyList(CacheKey ck, Set<String> allKeys) {
        for (String keys : allKeys) {
            if (keys.indexOf(ck.getPath()) == 0) {
                if (keys.length() > ck.getPath().length()) {
                    String endStr = keys.substring(keys.indexOf(ck.getPath())
                            + ck.getPath().length() + 1, keys.length());
                    String[] endArr = endStr.split(CacheConstants.KEY_PATH_SEPARATOR);
                    String bS = endArr[0];

                    if (bS.equals(CacheConstants.KEY_INNER_KEYS)) {
                        ck.setSetKey(ck.getPath() + CacheConstants.KEY_PATH_SEPARATOR + endStr);
                        continue;
                    }

                    TreeSet<CacheKey> subList = ck.getSubList();
                    if (subList == null) {
                        subList = new TreeSet<CacheKey>();
                    }
                    String newPath = ck.getPath() + CacheConstants.KEY_PATH_SEPARATOR + bS;
                    CacheKey nck = new CacheKey(bS, newPath);
                    if (!hasAdd(subList, newPath)) {
                        subList.add(nck);
                        ck.setSubList(subList);
                    }
                    loopKeyList(nck, allKeys);
                }
            }

        }
    }

    /**
     * @param apiVersion   api版本
     * @param cacheKeyList 指定需要删除的缓存key对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 根据键删除缓存对象
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    @Override
    public MsOnionResult deleteCacheByKeys(MsOnionApiVersion apiVersion, List<CacheKey> cacheKeyList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 检查版本
            inspectParameters(apiVersion);
            // 参数为空直接返回错误
            if (cacheKeyList == null
                    || cacheKeyList.size() == 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.DELETE_CACHE_PARAMS_NULL_MSG);
            }

            // 返回的数据map
            Map<String, Object> dataMap = new HashMap<String, Object>();

            // 第一个对象
            CacheKey firstCacheKey = null;

            List<String> sbList = new ArrayList<String>();

            for (int i = 0; i < cacheKeyList.size(); i++) {
                if (i == 0) {
                    firstCacheKey = cacheKeyList.get(i);
                }
                try {
                    CacheKey cacheKey = cacheKeyList.get(i);
                    // 删除key
                    long delectReturnValue = getMsOnionJedisAdapter().del(cacheKey.getPath());
                    if (delectReturnValue == 0) {
                        this.getMsOnionLogger().info(this.getClass().getName(), "#删除缓存key：" + cacheKey.getKey() + " 失败");
                        String resultMsg = String.format(CacheConstants.DEL_KEY_PARAMS_ERROR_MSG, cacheKey.getKey());
                        sbList.add(resultMsg);
                        continue;
                    }
                    // 删除keys中的key
                    String keysKey = getKeysKey(cacheKey.getPath());
                    String encodeKey = MsOnionSecurityUtils.encodeForRedis(cacheKey.getPath());
                    getMsOnionJedisAdapter().srem(keysKey, encodeKey);
                } catch (MsOnionException e) {
                    continue;
                }
            }
            dataMap.put("deleteTips", sbList);

            // 删除完后重新获取一下这个目录的数据
            String[] keyArr = firstCacheKey.getPath().split(CacheConstants.KEY_PATH_SEPARATOR);
            // 得到上一级目录路径
            String parentPath = getSelectPath(keyArr);
            String keyPath =  parentPath + CacheConstants.ALL_KEY_PATTERN;
            int pathSize = keyArr.length - 1;
            Set<String> allKeys = getMsOnionJedisAdapter().keys(keyPath);
            TreeSet<CacheKey> newCacheKeyList = setToSubKeyList(allKeys, pathSize, parentPath);
            TreeSet<CacheKey> treeSetData = new TreeSet<CacheKey>();
            CacheKeyTreeToMenu.treeDataSeparation(treeSetData, newCacheKeyList);
            dataMap.put("newCacheKeyList", treeSetData);
            return MsOnionResult.ok(dataMap);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * 获取key的Keys
     * @param keyPath key
     * @return Keys字符串
     */
    private String getKeysKey(String keyPath) {
        String[] keyPart = keyPath.split(CacheConstants.KEY_PATH_SEPARATOR);
        if (keyPart.length <= 0) {
            return null;
        }
        return keyPart[0] + CacheConstants.KEY_PATH_SEPARATOR
                + CacheConstants.KEY_INNER_KEYS;
    }

    /**
     * 返回上一级目录
     * @param keyPathArr 当前目录数组
     * @return 上一级目录的路径字符串
     */
    private String getSelectPath(String[] keyPathArr) {
        String[] newArr = new String[keyPathArr.length - 1];
        for (int i = 0; i < (keyPathArr.length - 1); i++) {
            newArr[i] = keyPathArr[i];
        }
        return StringUtils.join(newArr, CacheConstants.KEY_PATH_SEPARATOR);
    }


    /**
     * @param apiVersion  api版本
     * @param cacheObject 缓存对象
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByKeys
     * @Description 根据Key查询缓存对象详情
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月12日 下午13:44:20
     */
    @Override
    public MsOnionResult getCacheObjectDetail(MsOnionApiVersion apiVersion, CacheObject cacheObject)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 检查版本
            inspectParameters(apiVersion);

            // 检查参数
            if (cacheObject == null
                    || MsOnionStringUtils.isBlank(cacheObject.getKey())) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.GET_CACHE_KEY_PARAMS_NULL_MSG);
            }

            // 获取值
            String key = cacheObject.getKey().trim();
            String valueString = getMsOnionJedisAdapter().get(key);
            if (MsOnionStringUtils.isBlank(valueString)) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.GET_CACHE_VALUE_NULL_MSG);
            }

            cacheObject.setValue(valueString);
            long ttl = getMsOnionJedisAdapter().ttl(key);
            cacheObject.setTtl(ttl);
            String decodeText = MsOnionSecurityUtils.decodeForRedis(valueString);
            cacheObject.setDecodeValue(decodeText);

            return new MsOnionResult().ok(cacheObject);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }

    /**
     * @param apiVersion   api版本
     * @param cacheKeyList 指定需要删除的缓存树的节点对象集合
     * @return the ms onion result
     * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
     * @throws MsOnionException                the ms onion exception
     * @Title deleteCacheByNodes
     * @Description 根据键删除缓存对象
     * @Author DorenWu DorenWu@msyc.cc
     * @Date 2017年5月11日 下午14:20:20
     */
    @Override
    public MsOnionResult deleteCacheByNodes(MsOnionApiVersion apiVersion, List<CacheKey> cacheKeyList)
            throws MsOnionIllegalArgumentException, MsOnionException {
        try {
            // 检查版本
            inspectParameters(apiVersion);
            // 参数为空直接返回错误
            if (cacheKeyList == null
                    || cacheKeyList.size() == 0) {
                return MsOnionResult.build(MsOnionStatusConstants.STATUS_400, CacheConstants.DELETE_CACHE_PARAMS_NULL_MSG);
            }

            // 返回的数据map
            Map<String, Object> dataMap = new HashMap<String, Object>();

            // 获取节点的子集合
            Set<String> keySet = new HashSet<>();
            for (int i = 0; i < cacheKeyList.size(); i++) {
                CacheKey cacheKey = cacheKeyList.get(i);
                keySet.addAll(getMsOnionJedisAdapter().keys(cacheKey.getPath()
                        + CacheConstants.KEY_PATH_SEPARATOR + CacheConstants.ALL_KEY_PATTERN));
            }
            List<String> sbList = new ArrayList<String>();
            if (MsOnionCollectionUtils.isNotEmpty(keySet)) {
                for (String key : keySet) {
                    try {
                        String keys = CacheConstants.KEY_PATH_SEPARATOR + CacheConstants.KEY_INNER_KEYS;
                        if (key.contains(keys)) {
                            continue;
                        }
                        // 删除key
                        long delectReturnValue = getMsOnionJedisAdapter().del(key);
                        if (delectReturnValue == 0) {
                            this.getMsOnionLogger().info(this.getClass().getName(), "#删除缓存key：" + key + " 失败");
                            String resultMsg = String.format(CacheConstants.DEL_KEY_PARAMS_ERROR_MSG, key);
                            sbList.add(resultMsg);
                            continue;
                        }
                        // 删除keys中的key
                        String keysKey = getKeysKey(key);
                        String encodeKey = MsOnionSecurityUtils.encodeForRedis(key);
                        getMsOnionJedisAdapter().srem(keysKey, encodeKey);
                    } catch (MsOnionException e) {
                        continue;
                    }
                }
            }
            dataMap.put("deleteTips", sbList);
            return MsOnionResult.ok(dataMap);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }
    }
}
