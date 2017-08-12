package test.msonion.carambola.cache.service.impl;

import cc.msonion.carambola.cache.service.DynamicCacheService;
import cc.msonion.carambola.cache.service.redis.key.impl.DynamicCacheRedisKeyGeneratorImpl;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import org.junit.Test;

/**
 * Created by HeroCao on 2017/7/4.
 */
public class TestDynamicCacheServiceImpl {

    private DynamicCacheRedisKeyGeneratorImpl dynamicCacheRedisKeyGenerator;

    private DynamicCacheService dynamicCacheService;

    @Test
    public void test01() throws MsOnionException {


//        dynamicCacheRedisKeyGenerator.getKeysName()


//        dynamicCacheService.getFromRedis()

//        dynamicCacheService.delKeyForRedis()

//        dynamicCacheService.setForRedis()


        String key = "";
        String value = "";
        String result = dynamicCacheService.setForRedisWithSecurity(key, value);


        String value2 = dynamicCacheService.getFromRedisWithSecurity(key);

    }


}
