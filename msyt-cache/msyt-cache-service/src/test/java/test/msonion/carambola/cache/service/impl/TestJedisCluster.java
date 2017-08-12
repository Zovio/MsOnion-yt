package test.msonion.carambola.cache.service.impl;


import cc.msonion.carambola.cache.common.constants.CacheConstants;
import cc.msonion.carambola.cache.pojo.CacheKey;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.utils.MsOnionCollectionUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionJsonUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import cc.msonion.carambola.parent.interfaces.service.MsOnionCacheService;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)  //使用junit4进行测试
@ContextConfiguration({"/spring/applicationContext-service.xml", "/spring/applicationContext-jedis.xml"})
public class TestJedisCluster extends MsOnionCacheService {

    /**
     * 测试异步获取树的子节点
     *
     * @throws Exception
     */
    @Test
    public void testSyncKeyTree() throws Exception {
        String key = "ATTRIBUTE_INFO:ATTRIBUTE";
        Set<String> allKeys = getMsOnionJedisAdapter().keys(key + CacheConstants.ALL_KEY_PATTERN);
        TreeSet<CacheKey> allTreeKeys = setToSubKeyList(allKeys, key.split(CacheConstants.KEY_PATH_SEPARATOR).length);
        System.out.print(MsOnionJsonUtils.objectToJson(allTreeKeys));
    }

    /**
     * 设置CacheKey对象的KEY根目录集合
     *
     * @param allKeys  从缓存中心查询出来的全部key集合
     * @param pathSize 目录路径的长度
     * @return CacheKey对象的集合
     * @throws MsOnionException MsOnion异常类
     */
    private TreeSet<CacheKey> setToSubKeyList(Set<String> allKeys, Integer pathSize) throws MsOnionException {
        TreeSet<CacheKey> cacheKeyList = new TreeSet<CacheKey>();
        //循环设置缓存KEY的根目录
        for (String keys : allKeys) {
            String[] rootKeyArr = keys.split(CacheConstants.KEY_PATH_SEPARATOR);
            String rootKey = rootKeyArr[pathSize];
            if (rootKey.equals(CacheConstants.KEY_INNER_KEYS)) {
                continue;
            }
            boolean isIn = hasAdd(cacheKeyList, rootKey);
            if (!isIn) {
                CacheKey ck = new CacheKey();
                ck.setKey(rootKey);
                if (rootKeyArr.length == (pathSize + 1)) {
                    ck.setLeaf(true);
                }
                if (pathSize == 0) {
                    ck.setPath(rootKey);
                    ck.setSetKey(rootKey + CacheConstants.KEY_PATH_SEPARATOR + CacheConstants.KEY_INNER_KEYS);
                    String beginKey = rootKey + CacheConstants.KEY_PATH_SEPARATOR + CacheConstants.KEY_INNER_KEYS;
                    String setkey = getKeysString(allKeys, beginKey);
                    if (StringUtils.isNotEmpty(setkey)) {
                        Long countNum = getMsOnionJedisAdapter().scard(setkey);
                        ck.setSetKey(setkey);
                        ck.setCountNum(countNum);
                    }
                } else {
                    ck.setPath(getPathPre(rootKeyArr, pathSize));
                }
                cacheKeyList.add(ck);
            }
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
     * 循环设置SETKEY
     *
     * @param allKeys     从缓存中心查询出来的全部key集合
     * @param beginString SETKEY前缀
     * @return SETKEY
     */
    private String getKeysString(Set<String> allKeys, String beginString) {
        final int setKeyArrayLength = 3;
        for (String keyString : allKeys) {
            if (keyString.startsWith(beginString)
                    && (keyString.split(CacheConstants.KEY_PATH_SEPARATOR).length == setKeyArrayLength)) {
                return keyString;
            }
        }
        return null;
    }

    /**
     * 测试删除key
     */
    @Test
    public void deleteKeys() {
        String key = "RESOURCE_INFO:KEYS:RESOURCE";
        Long delIndex = getMsOnionJedisAdapter().del(key);
        System.out.print("delIndex====" + delIndex);
    }

    /**
     * 测试一次获取所有key的树形对象集合
     *
     * @throws Exception
     */
    @Test
    public void getAllRootKeys() throws Exception {

        Set<String> allKeys = getMsOnionJedisAdapter().keys("*");
        TreeSet<CacheKey> cacheKeyList = setToKeyList(allKeys);
        System.out.println(MsOnionJsonUtils.objectToJson(cacheKeyList));
    }

    private TreeSet<CacheKey> setToKeyList(Set<String> allKeys) throws MsOnionException {
        TreeSet<CacheKey> cacheKeyList = new TreeSet<CacheKey>();
        for (String keys : allKeys) {
            String[] rootKeyArr = keys.split(":");
            String rootKey = rootKeyArr[0];
            boolean isIn = hasAdd(cacheKeyList, rootKey);
            if (!isIn) {
                CacheKey ck = new CacheKey(rootKey, rootKey);
                cacheKeyList.add(ck);
            }
        }

        for (CacheKey ck : cacheKeyList) {
            loopKeyList(ck, allKeys);
        }
        System.out.print(MsOnionJsonUtils.objectToJson(cacheKeyList));
        return cacheKeyList;
    }

    private static boolean hasAdd(TreeSet<CacheKey> cacheKeyList, String r) {
        boolean isIN = false;
        for (CacheKey ck : cacheKeyList) {
            if (ck.getKey().equals(r)) {
                isIN = true;
            }
        }
        return isIN;
    }

    private static void loopKeyList(CacheKey ck, Set<String> allKeys) {
        for (String keys : allKeys) {
            if (keys.indexOf(ck.getPath()) == 0) {
                if (keys.length() > ck.getPath().length()) {
                    String endStr = keys.substring(keys.indexOf(ck.getPath())
                            + ck.getPath().length() + 1, keys.length());
                    String[] endArr = endStr.split(":");
                    String bS = endArr[0];

                    if (bS.equals("KEYS")) {
                        ck.setSetKey(ck.getPath() + ":" + endStr);
                        continue;
                    }

                    TreeSet<CacheKey> subList = ck.getSubList();
                    if (subList == null) {
                        subList = new TreeSet<CacheKey>();
                    }
                    String newPath = ck.getPath() + ":" + bS;
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
     * 测试树路径分页获取key
     *
     * @throws Exception
     */
    @Test
    public void testPage() throws Exception {
        // 长度获取
        String key = "TEST_INFO:KEYS:TEST";
        Long length = getMsOnionJedisAdapter().scard(key);
        System.out.println("=======length=================" + length);


        String keyPath = "TEST_INFO:KEYS:TEST";

        // TEST_INFO:KEYS:TEST
        // TEST_INFO:TEST:1
        // TEST_INFO:TEST:2
        // TEST_INFO:TEST:COUNT:1
        // TEST_INFO:TEST:EXAMPLE:1
        // MENU_INFO:MENU:EXAMPLE:MENUEXAMPLE:7DE5CB501B9A91ABF6BA424941C46E80
        // MENU_INFO:MENU:EXAMPLE:MENUEXAMPLE:3742F48750210432A7F3BE90351CFDD5

        String regex = "";

//        keyPath.contains(":")

        String targetInfo = "TEST_INFO";
        String target = "TEST";

        // SB
//        StringBuffer
//        keyPath.contains(String.format("%s%s", targetInfo, target))

        String targetPath = String.format("%s:%s:", targetInfo, target);

        // 	^[1-9]\d{4,10}$
        // \d : 匹配一个数字字符。等价于[0-9]。
        String pattern = String.format("^%s\\d$", targetPath);

        boolean result = MsOnionRegexUtils.regular(keyPath, pattern);


        System.out.print("结果 # result=" + result);


        // key分页
        ScanParams sp = new ScanParams();
//        sp.count(10);
        sp.count(20);
//        sp.match("*");

        //sscan
//        String str = "".contains("");
        //sp.match("TEST_INFO:TEST:!*[:]*}");
//        sp.match("^TEST_INFO:TEST:\\d$");

//        sp.match("TEST_INFO:TEST:*");  // OK

//        sp.match("TEST_INFO:TEST:*[:]*");


//        sp.match("TEST_INFO:TEST:[0-9]*"); // MD5

//        sp.match("TEST_INFO:TEST:[0-9A-Za-z]");


        // {0,1}
//        sp.match("TEST_INFO:TEST:[0-9]{1,19}");

//        sp.match("TEST_INFO:TEST:[A-Z]"); // 不支持 ！！！

        sp.match("TEST_INFO:TEST:[A-Z]");

        ScanResult<String> allKeys = getMsOnionJedisAdapter().sscan(keyPath, "0", sp);
        if (allKeys != null && allKeys.getResult() != null && allKeys.getResult().size() > 0) {
            List<String> keyList = allKeys.getResult();
            System.out.println("===" + keyList.size());
            for (String s : keyList) {
//                System.out.println(MsOnionJsonUtils.objectToJson(MsOnionSecurityUtils.decodeForRedis(s)));
                System.out.println("===" + s);
            }
            System.out.println("===" + allKeys.getStringCursor());
        }

//        sp.match()

    }

    @Test
    public void testMulDelKeyTree() {
        String key = "MENU:EXAMPLE*";
        Set<String> keys=getMsOnionJedisAdapter().keys(key);
        if(MsOnionCollectionUtils.isNotEmpty(keys)){
            for(String k : keys){
                getMsOnionJedisAdapter().del(k);
            }
        }


    }


    @Test
    public void testRegex() {

//        String keyPath = "TEST_INFO:KEYS:TEST";

        String keyPath = "TEST_INFO:TEST:EXAMPLE:1";

        // TEST_INFO:KEYS:TEST
        // TEST_INFO:TEST:5
        // TEST_INFO:TEST:2
        // TEST_INFO:TEST:COUNT:1
        // TEST_INFO:TEST:EXAMPLE:1
        // MENU_INFO:MENU:EXAMPLE:MENUEXAMPLE:7DE5CB501B9A91ABF6BA424941C46E80
        // MENU_INFO:MENU:EXAMPLE:MENUEXAMPLE:3742F48750210432A7F3BE90351CFDD5

        String regex = "";

//        keyPath.contains(":")

        String targetInfo = "TEST_INFO";
        String target = "TEST";

        // SB
//        StringBuffer
//        keyPath.contains(String.format("%s%s", targetInfo, target))

        String targetPath = String.format("%s:%s:", targetInfo, target);

        // 	^[1-9]\d{4,10}$
        // \d : 匹配一个数字字符。等价于[0-9]。
        String pattern = String.format("^%s\\d$", targetPath);

        boolean result = MsOnionRegexUtils.regular(keyPath, pattern);


        System.out.print("结果 # result=" + result);

    }

}
