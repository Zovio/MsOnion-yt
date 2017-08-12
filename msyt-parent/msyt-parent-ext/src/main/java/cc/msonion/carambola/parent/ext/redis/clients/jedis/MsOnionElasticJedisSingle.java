/**   
* 广州市两棵树网络科技有限公司版权所有
* DT Group Technology & commerce Co., LtdAll rights reserved.   
*   
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
*   
* 洋葱商城：http://m.msyc.cc/wx/indexView?tmn=1 
*
* 洋桃商城：http://www.yunyangtao.com
* 
*/
     

package cc.msonion.carambola.parent.ext.redis.clients.jedis;

import cc.msonion.carambola.parent.common.exception.MsOnionException;
import cc.msonion.carambola.parent.common.exception.MsOnionIllegalArgumentException;
import cc.msonion.carambola.parent.common.utils.MsOnionLogAnalyzerUtils;
import cc.msonion.carambola.parent.common.utils.MsOnionStringUtils;
import cc.msonion.carambola.parent.interfaces.log.analyze.MsOnionLogAnalyzerAdapter;
import cc.msonion.carambola.parent.interfaces.redis.clients.jedis.MsOnionJedisAdapter;
import cc.msonion.carambola.parent.pojo.MsOnionResult;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.*;
import redis.clients.jedis.params.geo.GeoRadiusParam;
import redis.clients.jedis.params.sortedset.ZAddParams;
import redis.clients.jedis.params.sortedset.ZIncrByParams;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * @Title: MsOnionElasticJedisSingle.java
 * @Package: cc.msonion.carambola.parent.ext.redis.clients.jedis
 * @Description: 封装Redis单机版本Jedis
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月16日 下午8:10:22
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月16日 下午8:10:22
 * @Modify-version: V2.0.0
 * @Modify-description: 新增：相关实现，修改：包名
 */

/** 
* @ClassName: MsOnionElasticJedisSingle
* @Description: 封装Redis单机版本Jedis
* @Company: 广州市两棵树网络科技有限公司
* @Author: HeroCao hero-cao@msyc.cc
* @Date: 2017年2月16日 下午8:10:22 
*  
*/
public class MsOnionElasticJedisSingle implements MsOnionJedisAdapter {

	/**
	 * JedisPool
	 */
	@Autowired // Spring 框架 @ Autowrited 注解
	private JedisPool jedisPool;
	
	/** 
	* @Fields logAnalyzer : 日志分析器
	*/
	private MsOnionLogAnalyzerAdapter logAnalyzer;
	
	public MsOnionElasticJedisSingle() {
		super();
	}
	
	public MsOnionElasticJedisSingle(MsOnionLogAnalyzerAdapter logAnalyzer) {
		super();
		this.logAnalyzer = logAnalyzer;
	}
	
	/** 
	 * @Title: getJedis 
	 * @Description: 获取Jedis实例对象
	 * @author HeroCao hero-cao@msyc.cc
	 * @date 2017年2月16日 下午12:13:49
	 *
	 * @return     
	 */
	private Jedis getJedis() {
		Jedis jedis = jedisPool.getResource();
		return jedis;
	}

	/** 
	* @Title: close 
	* @Description: 关闭连接
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 下午12:17:39
	*
	* @param jedis     
	*/
	private void close(Jedis jedis) {
		// 关闭当前Jedis连接，因为每一次都获取
		jedis.close();

		// 连接池是否需要关闭，因为使用Spring，bean 是单例的（默认），因为不需要关闭！！！
//		jedisPool.close();
	}
	
	/** 
	* @Title: msonion_set 
	* @Description: TODO(这里用一句话描述这个方法的作用) 
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午9:45:24
	*
	* @param key
	* @param value
	* @return     
	*/
	private String msonion_set(String key, String value) {
		Jedis jedis = this.getJedis();
		String result = jedis.set(key, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc) 
	 *  
	 *  
	 * @param key
	 * @param value
	 * @return 
	 * @see redis.clients.jedis.JedisCommands#set(String, String)
	 */
	@Override
	public String set(String key, String value) {
		if (null == this.logAnalyzer) {
			return msonion_set(key, value);
		}
		try {
			return msonion_set(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value));
			return null;
		}
	}

	/**
	* @Title: msonion_set
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午9:48:16
	*
	* @param key
	* @param value
	* @param nxxx
	* @param expx
	* @param time
	* @return
	*/
	private String msonion_set(String key, String value, String nxxx, String expx, long time) {
		Jedis jedis = this.getJedis();
		String result = jedis.set(key, value, nxxx, expx, time);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param value
	 * @param nxxx
	 * @param expx
	 * @param time
	 * @return
	 * @see redis.clients.jedis.JedisCommands#set(String, String, String, String, long)
	 */
	@Override
	public String set(String key, String value, String nxxx, String expx, long time) {
		if (null == this.logAnalyzer) {
			return msonion_set(key, value, nxxx, expx, time);
		}
		try {
			return msonion_set(key, value, nxxx, expx, time);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "value", value, "nxxx", nxxx, "expx", expx, "time", time +""));
			return null;
		}
	}

	/**
	* @Title: msonion_get
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:26:37
	*
	* @param key
	* @return
	*/
	private String msonion_get(String key) {
		Jedis jedis = this.getJedis();
		String result = jedis.get(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#get(String)
	 */
	@Override
	public String get(String key) {
		if (null == this.logAnalyzer) {
			return msonion_get(key);
		}
		try {
			return msonion_get(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_exists
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:29:33
	*
	* @param key
	* @return
	*/
	private Boolean msonion_exists(String key) {
		Jedis jedis = this.getJedis();
		Boolean result = jedis.exists(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#exists(String)
	 */
	@Override
	public Boolean exists(String key) {
		if (null == this.logAnalyzer) {
			return msonion_exists(key);
		}
		try {
			return msonion_exists(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_persist
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:31:52
	*
	* @param key
	* @return
	*/
	private Long msonion_persist(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.persist(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#persist(String)
	 */
	@Override
	public Long persist(String key) {
		if (null == this.logAnalyzer) {
			return msonion_persist(key);
		}
		try {
			return msonion_persist(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_type
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:34:49
	*
	* @param key
	* @return
	*/
	private String msonion_type(String key) {
		Jedis jedis = this.getJedis();
		String result = jedis.type(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#type(String)
	 */
	@Override
	public String type(String key) {
		if (null == this.logAnalyzer) {
			return msonion_type(key);
		}
		try {
			return msonion_type(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_expire
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:39:23
	*
	* @param key
	* @param seconds
	* @return
	*/
	private Long msonion_expire(String key, int seconds) {
		Jedis jedis = this.getJedis();
		Long result = jedis.expire(key, seconds);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param seconds
	 * @return
	 * @see redis.clients.jedis.JedisCommands#expire(String, int)
	 */
	@Override
	public Long expire(String key, int seconds) {
		if (null == this.logAnalyzer) {
			return msonion_expire(key, seconds);
		}
		try {
			return msonion_expire(key, seconds);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, seconds));
			return null;
		}
	}

	/**
	* @Title: msonion_pexpire
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:40:02
	*
	* @param key
	* @param milliseconds
	* @return
	*/
	private Long msonion_pexpire(String key, long milliseconds) {
		Jedis jedis = this.getJedis();
		Long result = jedis.pexpire(key, milliseconds);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param milliseconds
	 * @return
	 * @see redis.clients.jedis.JedisCommands#pexpire(String, long)
	 */
	@Override
	public Long pexpire(String key, long milliseconds) {
		if (null == this.logAnalyzer) {
			return msonion_pexpire(key, milliseconds);
		}
		try {
			return msonion_pexpire(key, milliseconds);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, milliseconds));
			return null;
		}
	}

	/**
	* @Title: msonion_expireAt
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:42:25
	*
	* @param key
	* @param unixTime
	* @return
	*/
	private Long msonion_expireAt(String key, long unixTime) {
		Jedis jedis = this.getJedis();
		Long result = jedis.expireAt(key, unixTime);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param unixTime
	 * @return
	 * @see redis.clients.jedis.JedisCommands#expireAt(String, long)
	 */
	@Override
	public Long expireAt(String key, long unixTime) {
		if (null == this.logAnalyzer) {
			return msonion_expireAt(key, unixTime);
		}
		try {
			return msonion_expireAt(key, unixTime);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "unixTime", unixTime +""));
			return null;
		}
	}

	/**
	* @Title: msonion_pexpireAt
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:45:56
	*
	* @param key
	* @param millisecondsTimestamp
	* @return
	*/
	private Long msonion_pexpireAt(String key, long millisecondsTimestamp) {
		Jedis jedis = this.getJedis();
		Long result = jedis.pexpireAt(key, millisecondsTimestamp);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param millisecondsTimestamp
	 * @return
	 * @see redis.clients.jedis.JedisCommands#pexpireAt(String, long)
	 */
	@Override
	public Long pexpireAt(String key, long millisecondsTimestamp) {
		if (null == this.logAnalyzer) {
			return msonion_pexpireAt(key, millisecondsTimestamp);
		}
		try {
			return msonion_pexpireAt(key, millisecondsTimestamp);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "millisecondsTimestamp", millisecondsTimestamp +""));
			return null;
		}
	}

	/**
	* @Title: msonion_ttl
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:48:45
	*
	* @param key
	* @return
	*/
	private Long msonion_ttl(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.ttl(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#ttl(String)
	 */
	@Override
	public Long ttl(String key) {
		if (null == this.logAnalyzer) {
			return msonion_ttl(key);
		}
		try {
			return msonion_ttl(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_setbit
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:50:32
	*
	* @param key
	* @param offset
	* @param value
	* @return
	*/
	private Boolean msonion_setbit(String key, long offset, boolean value) {
		Jedis jedis = this.getJedis();
		Boolean result = jedis.setbit(key, offset, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#setbit(String, long, boolean)
	 */
	@Override
	public Boolean setbit(String key, long offset, boolean value) {
		if (null == this.logAnalyzer) {
			return msonion_setbit(key, offset, value);
		}
		try {
			return msonion_setbit(key, offset, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +"", "value", value +""));
			return null;
		}
	}

	/**
	* @Title: ttsetbit
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:52:59
	*
	* @param key
	* @param offset
	* @param value
	* @return
	*/
	private Boolean msonion_setbit(String key, long offset, String value) {
		Jedis jedis = this.getJedis();
		Boolean result = jedis.setbit(key, offset, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#setbit(String, long, String)
	 */
	@Override
	public Boolean setbit(String key, long offset, String value) {
		if (null == this.logAnalyzer) {
			return msonion_setbit(key, offset, value);
		}
		try {
			return msonion_setbit(key, offset, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +"", "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_getbit
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:57:53
	*
	* @param key
	* @param offset
	* @return
	*/
	private Boolean msonion_getbit(String key, long offset) {
		Jedis jedis = this.getJedis();
		Boolean result = jedis.getbit(key, offset);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param offset
	 * @return
	 * @see redis.clients.jedis.JedisCommands#getbit(String, long)
	 */
	@Override
	public Boolean getbit(String key, long offset) {
		if (null == this.logAnalyzer) {
			return msonion_getbit(key, offset);
		}
		try {
			return msonion_getbit(key, offset);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +""));
			return null;
		}
	}

	/**
	* @Title: msonion_setrange
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2016年8月8日 下午11:59:02
	*
	* @param key
	* @param offset
	* @param value
	* @return
	*/
	private Long msonion_setrange(String key, long offset, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.setrange(key, offset, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param offset
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#setrange(String, long, String)
	 */
	@Override
	public Long setrange(String key, long offset, String value) {
		if (null == this.logAnalyzer) {
			return msonion_setrange(key, offset, value);
		}
		try {
			return msonion_setrange(key, offset, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +"", "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_getrange
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午12:02:02
	*
	* @param key
	* @param startOffset
	* @param endOffset
	* @return
	*/
	private String msonion_getrange(String key, long startOffset, long endOffset) {
		Jedis jedis = this.getJedis();
		String result = jedis.getrange(key, startOffset, endOffset);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param startOffset
	 * @param endOffset
	 * @return
	 * @see redis.clients.jedis.JedisCommands#getrange(String, long, long)
	 */
	@Override
	public String getrange(String key, long startOffset, long endOffset) {
		if (null == this.logAnalyzer) {
			return msonion_getrange(key, startOffset, endOffset);
		}
		try {
			return msonion_getrange(key, startOffset, endOffset);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "startOffset", startOffset +"", "endOffset", endOffset +""));
			return null;
		}
	}

	/**
	* @Title: msonion_getSet
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午12:04:32
	*
	* @param key
	* @param value
	* @return
	*/
	private String msonion_getSet(String key, String value) {
		Jedis jedis = this.getJedis();
		String result = jedis.getSet(key, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#getSet(String, String)
	 */
	@Override
	public String getSet(String key, String value) {
		if (null == this.logAnalyzer) {
			return msonion_getSet(key, value);
		}
		try {
			return msonion_getSet(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value));
			return null;
		}
	}

	/**
	* @Title: msonion_setnx
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午12:06:41
	*
	* @param key
	* @param value
	* @return
	*/
	private Long msonion_setnx(String key, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.setnx(key, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#setnx(String, String)
	 */
	@Override
	public Long setnx(String key, String value) {
		if (null == this.logAnalyzer) {
			return msonion_setnx(key, value);
		}
		try {
			return msonion_setnx(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value));
			return null;
		}
	}

	/**
	* @Title: msonion_setex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午12:08:42
	*
	* @param key
	* @param seconds
	* @param value
	* @return
	*/
	private String msonion_setex(String key, int seconds, String value) {
		Jedis jedis = this.getJedis();
		String result = jedis.setex(key, seconds, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param seconds
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#setex(String, int, String)
	 */
	@Override
	public String setex(String key, int seconds, String value) {
		if (null == this.logAnalyzer) {
			return msonion_setex(key, seconds, value);
		}
		try {
			return msonion_setex(key, seconds, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "seconds", seconds +"", "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_decrBy
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:07:01
	*
	* @param key
	* @param integer
	* @return
	*/
	private Long msonion_decrBy(String key, long integer) {
		Jedis jedis = this.getJedis();
		Long result = jedis.decrBy(key, integer);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param integer
	 * @return
	 * @see redis.clients.jedis.JedisCommands#decrBy(String, long)
	 */
	@Override
	public Long decrBy(String key, long integer) {
		if (null == this.logAnalyzer) {
			return msonion_decrBy(key, integer);
		}
		try {
			return msonion_decrBy(key, integer);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "integer", integer +""));
			return null;
		}
	}

	/**
	* @Title: msonion_decr
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:10:31
	*
	* @param key
	* @return
	*/
	private Long msonion_decr(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.decr(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#decr(String)
	 */
	@Override
	public Long decr(String key) {
		if (null == this.logAnalyzer) {
			return msonion_decr(key);
		}
		try {
			return msonion_decr(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_incrBy
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:13:51
	*
	* @param key
	* @param integer
	* @return
	*/
	private Long msonion_incrBy(String key, long integer) {
		Jedis jedis = this.getJedis();
		Long result = jedis.incrBy(key, integer);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param integer
	 * @return
	 * @see redis.clients.jedis.JedisCommands#incrBy(String, long)
	 */
	@Override
	public Long incrBy(String key, long integer) {
		if (null == this.logAnalyzer) {
			return msonion_incrBy(key, integer);
		}
		try {
			return msonion_incrBy(key, integer);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "integer", integer +""));
			return null;
		}
	}

	/**
	* @Title: msonion_incrByFloat
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:15:44
	*
	* @param key
	* @param value
	* @return
	*/
	private Double msonion_incrByFloat(String key, double value) {
		Jedis jedis = this.getJedis();
		Double result = jedis.incrByFloat(key, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#incrByFloat(String, double)
	 */
	@Override
	public Double incrByFloat(String key, double value) {
		if (null == this.logAnalyzer) {
			return msonion_incrByFloat(key, value);
		}
		try {
			return msonion_incrByFloat(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value +""));
			return null;
		}
	}

	/**
	* @Title: msonion_incr
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:18:06
	*
	* @param key
	* @return
	*/
	private Long msonion_incr(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.incr(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#incr(String)
	 */
	@Override
	public Long incr(String key) {
		if (null == this.logAnalyzer) {
			return msonion_incr(key);
		}
		try {
			return msonion_incr(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_append
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:20:17
	*
	* @param key
	* @param value
	* @return
	*/
	private Long msonion_append(String key, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.append(key, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#append(String, String)
	 */
	@Override
	public Long append(String key, String value) {
		if (null == this.logAnalyzer) {
			return msonion_append(key, value);
		}
		try {
			return msonion_append(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value));
			return null;
		}
	}

	/**
	* @Title: msonion_substr
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:22:10
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private String msonion_substr(String key, int start, int end) {
		Jedis jedis = this.getJedis();
		String result = jedis.substr(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#substr(String, int, int)
	 */
	@Override
	public String substr(String key, int start, int end) {
		if (null == this.logAnalyzer) {
			return msonion_substr(key, start, end);
		}
		try {
			return msonion_substr(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_hset
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:23:55
	*
	* @param key
	* @param field
	* @param value
	* @return
	*/
	private Long msonion_hset(String key, String field, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.hset(key, field, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hset(String, String, String)
	 */
	@Override
	public Long hset(String key, String field, String value) {
		if (null == this.logAnalyzer) {
			return msonion_hset(key, field, value);
		}
		try {
			return msonion_hset(key, field, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field, "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_hget
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:25:54
	*
	* @param key
	* @param field
	* @return
	*/
	private String msonion_hget(String key, String field) {
		Jedis jedis = this.getJedis();
		String result = jedis.hget(key, field);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param field
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hget(String, String)
	 */
	@Override
	public String hget(String key, String field) {
		if (null == this.logAnalyzer) {
			return msonion_hget(key, field);
		}
		try {
			return msonion_hget(key, field);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field));
			return null;
		}
	}

	/**
	* @Title: msonion_hsetnx
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:28:08
	*
	* @param key
	* @param field
	* @param value
	* @return
	*/
	private Long msonion_hsetnx(String key, String field, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.hsetnx(key, field, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hsetnx(String, String, String)
	 */
	@Override
	public Long hsetnx(String key, String field, String value) {
		if (null == this.logAnalyzer) {
			return msonion_hsetnx(key, field, value);
		}
		try {
			return msonion_hsetnx(key, field, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field, "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_hmset
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:30:18
	*
	* @param key
	* @param hash
	* @return
	*/
	private String msonion_hmset(String key, Map<String, String> hash) {
		Jedis jedis = this.getJedis();
		String result = jedis.hmset(key, hash);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param hash
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hmset(String, Map)
	 */
	@Override
	public String hmset(String key, Map<String, String> hash) {
		if (null == this.logAnalyzer) {
			return msonion_hmset(key, hash);
		}
		try {
			return msonion_hmset(key, hash);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "hash", hash));
			return null;
		}
	}

	/**
	* @Title: msonion_hmget
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:32:53
	*
	* @param key
	* @param fields
	* @return
	*/
	private List<String> msonion_hmget(String key, String... fields) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.hmget(key, fields);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param fields
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hmget(String, String[])
	 */
	@Override
	public List<String> hmget(String key, String... fields) {
		if (null == this.logAnalyzer) {
			return msonion_hmget(key, fields);
		}
		try {
			return msonion_hmget(key, fields);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "fields", fields));
			return null;
		}
	}

	/**
	* @Title: msonion_hincrBy
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:35:04
	*
	* @param key
	* @param field
	* @param value
	* @return
	*/
	private Long msonion_hincrBy(String key, String field, long value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.hincrBy(key, field, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hincrBy(String, String, long)
	 */
	@Override
	public Long hincrBy(String key, String field, long value) {
		if (null == this.logAnalyzer) {
			return msonion_hincrBy(key, field, value);
		}
		try {
			return msonion_hincrBy(key, field, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field, "value", value +""));
			return null;
		}
	}

	/**
	* @Title: msonion_hexists
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:36:42
	*
	* @param key
	* @param field
	* @return
	*/
	private Boolean msonion_hexists(String key, String field) {
		Jedis jedis = this.getJedis();
		Boolean result = jedis.hexists(key, field);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param field
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hexists(String, String)
	 */
	@Override
	public Boolean hexists(String key, String field) {
		if (null == this.logAnalyzer) {
			return msonion_hexists(key, field);
		}
		try {
			return msonion_hexists(key, field);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "field", field));
			return null;
		}
	}

	/**
	* @Title: msonion_hdel
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:45:10
	*
	* @param key
	* @param field
	* @return
	*/
	private Long msonion_hdel(String key, String... field) {
		Jedis jedis = this.getJedis();
		Long result = jedis.hdel(key, field);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param field
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hdel(String, String[])
	 */
	@Override
	public Long hdel(String key, String... field) {
		if (null == this.logAnalyzer) {
			return msonion_hdel(key, field);
		}
		try {
			return msonion_hdel(key, field);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "field", field));
			return null;
		}
	}

	/**
	* @Title: msonion_hlen
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:47:21
	*
	* @param key
	* @return
	*/
	private Long msonion_hlen(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.hlen(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hlen(String)
	 */
	@Override
	public Long hlen(String key) {
		if (null == this.logAnalyzer) {
			return msonion_hlen(key);
		}
		try {
			return msonion_hlen(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_hkeys
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:48:46
	*
	* @param key
	* @return
	*/
	private Set<String> msonion_hkeys(String key) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.hkeys(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hkeys(String)
	 */
	@Override
	public Set<String> hkeys(String key) {
		if (null == this.logAnalyzer) {
			return msonion_hkeys(key);
		}
		try {
			return msonion_hkeys(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_hvals
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:50:23
	*
	* @param key
	* @return
	*/
	private List<String> msonion_hvals(String key) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.hvals(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hvals(String)
	 */
	@Override
	public List<String> hvals(String key) {
		if (null == this.logAnalyzer) {
			return msonion_hvals(key);
		}
		try {
			return msonion_hvals(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_hgetAll
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:51:33
	*
	* @param key
	* @return
	*/
	private Map<String, String> msonion_hgetAll(String key) {
		Jedis jedis = this.getJedis();
		Map<String, String> result = jedis.hgetAll(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hgetAll(String)
	 */
	@Override
	public Map<String, String> hgetAll(String key) {
		if (null == this.logAnalyzer) {
			return msonion_hgetAll(key);
		}
		try {
			return msonion_hgetAll(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_rpush
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:53:30
	*
	* @param key
	* @param string
	* @return
	*/
	private Long msonion_rpush(String key, String... string) {
		Jedis jedis = this.getJedis();
		Long result = jedis.rpush(key, string);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param string
	 * @return
	 * @see redis.clients.jedis.JedisCommands#rpush(String, String[])
	 */
	@Override
	public Long rpush(String key, String... string) {
		if (null == this.logAnalyzer) {
			return msonion_rpush(key, string);
		}
		try {
			return msonion_rpush(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
	}

	/**
	* @Title: msonion_lpush
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:54:40
	*
	* @param key
	* @param string
	* @return
	*/
	private Long msonion_lpush(String key, String... string) {
		Jedis jedis = this.getJedis();
		Long result = jedis.lpush(key, string);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param string
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lpush(String, String[])
	 */
	@Override
	public Long lpush(String key, String... string) {
		if (null == this.logAnalyzer) {
			return msonion_lpush(key, string);
		}
		try {
			return msonion_lpush(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
	}

	/**
	* @Title: msonion_llen
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:55:43
	*
	* @param key
	* @return
	*/
	private Long msonion_llen(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.llen(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#llen(String)
	 */
	@Override
	public Long llen(String key) {
		if (null == this.logAnalyzer) {
			return msonion_llen(key);
		}
		try {
			return msonion_llen(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_lrange
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:57:15
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private List<String> msonion_lrange(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.lrange(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lrange(String, long, long)
	 */
	@Override
	public List<String> lrange(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_lrange(key, start, end);
		}
		try {
			return msonion_lrange(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_ltrim
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午7:58:51
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private String msonion_ltrim(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		String result = jedis.ltrim(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#ltrim(String, long, long)
	 */
	@Override
	public String ltrim(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_ltrim(key, start, end);
		}
		try {
			return msonion_ltrim(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_lindex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:00:26
	*
	* @param key
	* @param index
	* @return
	*/
	private String msonion_lindex(String key, long index) {
		Jedis jedis = this.getJedis();
		String result = jedis.lindex(key, index);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param index
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lindex(String, long)
	 */
	@Override
	public String lindex(String key, long index) {
		if (null == this.logAnalyzer) {
			return msonion_lindex(key, index);
		}
		try {
			return msonion_lindex(key, index);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "index", index +""));
			return null;
		}
	}

	/**
	* @Title: msonion_lset
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:02:19
	*
	* @param key
	* @param index
	* @param value
	* @return
	*/
	private String msonion_lset(String key, long index, String value) {
		Jedis jedis = this.getJedis();
		String result = jedis.lset(key, index, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lset(String, long, String)
	 */
	@Override
	public String lset(String key, long index, String value) {
		if (null == this.logAnalyzer) {
			return msonion_lset(key, index, value);
		}
		try {
			return msonion_lset(key, index, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "index", index +"", "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_lrem
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:03:46
	*
	* @param key
	* @param count
	* @param value
	* @return
	*/
	private Long msonion_lrem(String key, long count, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.lrem(key, count, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param count
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lrem(String, long, String)
	 */
	@Override
	public Long lrem(String key, long count, String value) {
		if (null == this.logAnalyzer) {
			return msonion_lrem(key, count, value);
		}
		try {
			return msonion_lrem(key, count, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "count", count +"", "value", value));
			return null;
		}
	}

	/**
	* @Title: msonion_lpop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:05:51
	*
	* @param key
	* @return
	*/
	private String msonion_lpop(String key) {
		Jedis jedis = this.getJedis();
		String result = jedis.lpop(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lpop(String)
	 */
	@Override
	public String lpop(String key) {
		if (null == this.logAnalyzer) {
			return msonion_lpop(key);
		}
		try {
			return msonion_lpop(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_rpop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:07:11
	*
	* @param key
	* @return
	*/
	private String msonion_rpop(String key) {
		Jedis jedis = this.getJedis();
		String result = jedis.rpop(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#rpop(String)
	 */
	@Override
	public String rpop(String key) {
		if (null == this.logAnalyzer) {
			return msonion_rpop(key);
		}
		try {
			return msonion_rpop(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_sadd
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:08:23
	*
	* @param key
	* @param member
	* @return
	*/
	private Long msonion_sadd(String key, String... member) {
		Jedis jedis = this.getJedis();
		Long result = jedis.sadd(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#sadd(String, String[])
	 */
	@Override
	public Long sadd(String key, String... member) {
		if (null == this.logAnalyzer) {
			return msonion_sadd(key, member);
		}
		try {
			return msonion_sadd(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_smembers
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:09:39
	*
	* @param key
	* @return
	*/
	private Set<String> msonion_smembers(String key) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.smembers(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#smembers(String)
	 */
	@Override
	public Set<String> smembers(String key) {
		if (null == this.logAnalyzer) {
			return msonion_smembers(key);
		}
		try {
			return msonion_smembers(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_srem
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:12:41
	*
	* @param key
	* @param member
	* @return
	*/
	private Long msonion_srem(String key, String... member) {
		Jedis jedis = this.getJedis();
		Long result = jedis.srem(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#srem(String, String[])
	 */
	@Override
	public Long srem(String key, String... member) {
		if (null == this.logAnalyzer) {
			return msonion_srem(key, member);
		}
		try {
			return msonion_srem(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_spop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:15:14
	*
	* @param key
	* @return
	*/
	private String msonion_spop(String key) {
		Jedis jedis = this.getJedis();
		String result = jedis.spop(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#spop(String)
	 */
	@Override
	public String spop(String key) {
		if (null == this.logAnalyzer) {
			return msonion_spop(key);
		}
		try {
			return msonion_spop(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_spop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:16:17
	*
	* @param key
	* @param count
	* @return
	*/
	private Set<String> msonion_spop(String key, long count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.spop(key, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#spop(String, long)
	 */
	@Override
	public Set<String> spop(String key, long count) {
		if (null == this.logAnalyzer) {
			return msonion_spop(key, count);
		}
		try {
			return msonion_spop(key, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_scard
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:18:14
	*
	* @param key
	* @return
	*/
	private Long msonion_scard(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.scard(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#scard(String)
	 */
	@Override
	public Long scard(String key) {
		if (null == this.logAnalyzer) {
			return msonion_scard(key);
		}
		try {
			return msonion_scard(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_sismember
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:19:20
	*
	* @param key
	* @param member
	* @return
	*/
	private Boolean msonion_sismember(String key, String member) {
		Jedis jedis = this.getJedis();
		Boolean result = jedis.sismember(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#sismember(String, String)
	 */
	@Override
	public Boolean sismember(String key, String member) {
		if (null == this.logAnalyzer) {
			return msonion_sismember(key, member);
		}
		try {
			return msonion_sismember(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_srandmember
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:21:37
	*
	* @param key
	* @return
	*/
	private String msonion_srandmember(String key) {
		Jedis jedis = this.getJedis();
		String result = jedis.srandmember(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#srandmember(String)
	 */
	@Override
	public String srandmember(String key) {
		if (null == this.logAnalyzer) {
			return msonion_srandmember(key);
		}
		try {
			return msonion_srandmember(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_srandmember
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:22:50
	*
	* @param key
	* @param count
	* @return
	*/
	private List<String> msonion_srandmember(String key, int count) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.srandmember(key, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#srandmember(String, int)
	 */
	@Override
	public List<String> srandmember(String key, int count) {
		if (null == this.logAnalyzer) {
			return msonion_srandmember(key, count);
		}
		try {
			return msonion_srandmember(key, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_strlen
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:24:46
	*
	* @param key
	* @return
	*/
	private Long msonion_strlen(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.strlen(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#strlen(String)
	 */
	@Override
	public Long strlen(String key) {
		if (null == this.logAnalyzer) {
			return msonion_strlen(key);
		}
		try {
			return msonion_strlen(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_zadd
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:26:04
	*
	* @param key
	* @param score
	* @param member
	* @return
	*/
	private Long msonion_zadd(String key, double score, String member) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zadd(key, score, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zadd(String, double, String)
	 */
	@Override
	public Long zadd(String key, double score, String member) {
		if (null == this.logAnalyzer) {
			return msonion_zadd(key, score, member);
		}
		try {
			return msonion_zadd(key, score, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "score", score +"", "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_zadd
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:27:36
	*
	* @param key
	* @param scoreMembers
	* @return
	*/
	private Long msonion_zadd(String key, Map<String, Double> scoreMembers) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zadd(key, scoreMembers);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param scoreMembers
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zadd(String, Map)
	 */
	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers) {
		if (null == this.logAnalyzer) {
			return msonion_zadd(key, scoreMembers);
		}
		try {
			return msonion_zadd(key, scoreMembers);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "scoreMembers", scoreMembers));
			return null;
		}
	}

	/**
	* @Title: msonion_zrange
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:30:03
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Set<String> msonion_zrange(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrange(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrange(String, long, long)
	 */
	@Override
	public Set<String> zrange(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_zrange(key, start, end);
		}
		try {
			return msonion_zrange(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrem
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:31:53
	*
	* @param key
	* @param member
	* @return
	*/
	private Long msonion_zrem(String key, String... member) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zrem(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrem(String, String[])
	 */
	@Override
	public Long zrem(String key, String... member) {
		if (null == this.logAnalyzer) {
			return msonion_zrem(key, member);
		}
		try {
			return msonion_zrem(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_zincrby
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:33:21
	*
	* @param key
	* @param score
	* @param member
	* @return
	*/
	private Double msonion_zincrby(String key, double score, String member) {
		Jedis jedis = this.getJedis();
		Double result = jedis.zincrby(key, score, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param score
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zincrby(String, double, String)
	 */
	@Override
	public Double zincrby(String key, double score, String member) {
		if (null == this.logAnalyzer) {
			return msonion_zincrby(key, score, member);
		}
		try {
			return msonion_zincrby(key, score, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "score", score +"", "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_zrank
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:35:23
	*
	* @param key
	* @param member
	* @return
	*/
	private Long msonion_zrank(String key, String member) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zrank(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrank(String, String)
	 */
	@Override
	public Long zrank(String key, String member) {
		if (null == this.logAnalyzer) {
			return msonion_zrank(key, member);
		}
		try {
			return msonion_zrank(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrank
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:36:55
	*
	* @param key
	* @param member
	* @return
	*/
	private Long msonion_zrevrank(String key, String member) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zrevrank(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrank(String, String)
	 */
	@Override
	public Long zrevrank(String key, String member) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrank(key, member);
		}
		try {
			return msonion_zrevrank(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrange
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:38:02
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Set<String> msonion_zrevrange(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrange(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrange(String, long, long)
	 */
	@Override
	public Set<String> zrevrange(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrange(key, start, end);
		}
		try {
			return msonion_zrevrange(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:39:34
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Set<Tuple> msonion_zrangeWithScores(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrangeWithScores(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeWithScores(String, long, long)
	 */
	@Override
	public Set<Tuple> zrangeWithScores(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeWithScores(key, start, end);
		}
		try {
			return msonion_zrangeWithScores(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:40:57
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Set<Tuple> msonion_zrevrangeWithScores(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrevrangeWithScores(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeWithScores(String, long, long)
	 */
	@Override
	public Set<Tuple> zrevrangeWithScores(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeWithScores(key, start, end);
		}
		try {
			return msonion_zrevrangeWithScores(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zcard
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:42:18
	*
	* @param key
	* @return
	*/
	private Long msonion_zcard(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zcard(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zcard(String)
	 */
	@Override
	public Long zcard(String key) {
		if (null == this.logAnalyzer) {
			return msonion_zcard(key);
		}
		try {
			return msonion_zcard(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_zscore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:43:24
	*
	* @param key
	* @param member
	* @return
	*/
	private Double msonion_zscore(String key, String member) {
		Jedis jedis = this.getJedis();
		Double result = jedis.zscore(key, member);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param member
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zscore(String, String)
	 */
	@Override
	public Double zscore(String key, String member) {
		if (null == this.logAnalyzer) {
			return msonion_zscore(key, member);
		}
		try {
			return msonion_zscore(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "member", member));
			return null;
		}
	}

	/**
	* @Title: msonion_sort
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:45:15
	*
	* @param key
	* @return
	*/
	private List<String> msonion_sort(String key) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.sort(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#sort(String)
	 */
	@Override
	public List<String> sort(String key) {
		if (null == this.logAnalyzer) {
			return msonion_sort(key);
		}
		try {
			return msonion_sort(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_sort
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:46:40
	*
	* @param key
	* @param sortingParameters
	* @return
	*/
	private List<String> msonion_sort(String key, SortingParams sortingParameters) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.sort(key, sortingParameters);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param sortingParameters
	 * @return
	 * @see redis.clients.jedis.JedisCommands#sort(String, SortingParams)
	 */
	@Override
	public List<String> sort(String key, SortingParams sortingParameters) {
		if (null == this.logAnalyzer) {
			return msonion_sort(key, sortingParameters);
		}
		try {
			return msonion_sort(key, sortingParameters);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, sortingParameters));
			return null;
		}
	}

	/**
	* @Title: msonion_zcount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:48:19
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Long msonion_zcount(String key, double min, double max) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zcount(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zcount(String, double, double)
	 */
	@Override
	public Long zcount(String key, double min, double max) {
		if (null == this.logAnalyzer) {
			return msonion_zcount(key, min, max);
		}
		try {
			return msonion_zcount(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zcount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:51:31
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Long msonion_zcount(String key, String min, String max) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zcount(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zcount(String, String, String)
	 */
	@Override
	public Long zcount(String key, String min, String max) {
		if (null == this.logAnalyzer) {
			return msonion_zcount(key, min, max);
		}
		try {
			return msonion_zcount(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:53:25
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Set<String> msonion_zrangeByScore(String key, double min, double max) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrangeByScore(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScore(String, double, double)
	 */
	@Override
	public Set<String> zrangeByScore(String key, double min, double max) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScore(key, min, max);
		}
		try {
			return msonion_zrangeByScore(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午8:54:47
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Set<String> msonion_zrangeByScore(String key, String min, String max) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrangeByScore(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScore(String, String, String)
	 */
	@Override
	public Set<String> zrangeByScore(String key, String min, String max) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScore(key, min, max);
		}
		try {
			return msonion_zrangeByScore(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:02:00
	*
	* @param key
	* @param max
	* @param min
	* @return
	*/
	private Set<String> msonion_zrevrangeByScore(String key, double max, double min) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrangeByScore(key, max, min);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScore(String, double, double)
	 */
	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScore(key, max, min);
		}
		try {
			return msonion_zrevrangeByScore(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:04:24
	*
	* @param key
	* @param min
	* @param max
	* @param offset
	* @param count
	* @return
	*/
	private Set<String> msonion_zrangeByScore(String key, double min, double max, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrangeByScore(key, min, max, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScore(String, double, double, int, int)
	 */
	@Override
	public Set<String> zrangeByScore(String key, double min, double max, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScore(key, min, max, offset, count);
		}
		try {
			return msonion_zrangeByScore(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +"", "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:06:56
	*
	* @param key
	* @param max
	* @param min
	* @return
	*/
	private Set<String> msonion_zrevrangeByScore(String key, String max, String min) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrangeByScore(key, max, min);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScore(String, String, String)
	 */
	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScore(key, max, min);
		}
		try {
			return msonion_zrevrangeByScore(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:09:11
	*
	* @param key
	* @param min
	* @param max
	* @param offset
	* @param count
	* @return
	*/
	private Set<String> msonion_zrangeByScore(String key, String min, String max, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrangeByScore(key, min, max, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScore(String, String, String, int, int)
	 */
	@Override
	public Set<String> zrangeByScore(String key, String min, String max, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScore(key, min, max, offset, count);
		}
		try {
			return msonion_zrangeByScore(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max, "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:11:44
	*
	* @param key
	* @param max
	* @param min
	* @param offset
	* @param count
	* @return
	*/
	private Set<String> msonion_zrevrangeByScore(String key, double max, double min, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrangeByScore(key, max, min, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScore(String, double, double, int, int)
	 */
	@Override
	public Set<String> zrevrangeByScore(String key, double max, double min, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScore(key, max, min, offset, count);
		}
		try {
			return msonion_zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +"", "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:13:51
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Set<Tuple> msonion_zrangeByScoreWithScores(String key, double min, double max) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScoreWithScores(String, double, double)
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScoreWithScores(key, min, max);
		}
		try {
			return msonion_zrangeByScoreWithScores(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:15:24
	*
	* @param key
	* @param max
	* @param min
	* @return
	*/
	private Set<Tuple> msonion_zrevrangeByScoreWithScores(String key, double max, double min) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScoreWithScores(String, double, double)
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScoreWithScores(key, max, min);
		}
		try {
			return msonion_zrevrangeByScoreWithScores(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:17:47
	*
	* @param key
	* @param min
	* @param max
	* @param offset
	* @param count
	* @return
	*/
	private Set<Tuple> msonion_zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScoreWithScores(String, double, double, int, int)
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, double min, double max, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScoreWithScores(key, min, max, offset, count);
		}
		try {
			return msonion_zrangeByScoreWithScores(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +"", "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:19:40
	*
	* @param key
	* @param max
	* @param min
	* @param offset
	* @param count
	* @return
	*/
	private Set<String> msonion_zrevrangeByScore(String key, String max, String min, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrangeByScore(key, max, min, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScore(String, String, String, int, int)
	 */
	@Override
	public Set<String> zrevrangeByScore(String key, String max, String min, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScore(key, max, min, offset, count);
		}
		try {
			return msonion_zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min, "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:21:52
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Set<Tuple> msonion_zrangeByScoreWithScores(String key, String min, String max) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScoreWithScores(String, String, String)
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScoreWithScores(key, min, max);
		}
		try {
			return msonion_zrangeByScoreWithScores(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:23:29
	*
	* @param key
	* @param max
	* @param min
	* @return
	*/
	private Set<Tuple> msonion_zrevrangeByScoreWithScores(String key, String max, String min) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScoreWithScores(String, String, String)
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScoreWithScores(key, max, min);
		}
		try {
			return msonion_zrevrangeByScoreWithScores(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:25:20
	*
	* @param key
	* @param min
	* @param max
	* @param offset
	* @param count
	* @return
	*/
	private Set<Tuple> msonion_zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrangeByScoreWithScores(key, min, max, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByScoreWithScores(String, String, String, int, int)
	 */
	@Override
	public Set<Tuple> zrangeByScoreWithScores(String key, String min, String max, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByScoreWithScores(key, min, max, offset, count);
		}
		try {
			return msonion_zrangeByScoreWithScores(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max, "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:26:58
	*
	* @param key
	* @param max
	* @param min
	* @param offset
	* @param count
	* @return
	*/
	private Set<Tuple> msonion_zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScoreWithScores(String, double, double, int, int)
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, double max, double min, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScoreWithScores(key, max, min, offset, count);
		}
		try {
			return msonion_zrevrangeByScoreWithScores(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +"", "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByScoreWithScores
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:28:28
	*
	* @param key
	* @param max
	* @param min
	* @param offset
	* @param count
	* @return
	*/
	private Set<Tuple> msonion_zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<Tuple> result = jedis.zrevrangeByScoreWithScores(key, max, min, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByScoreWithScores(String, String, String, int, int)
	 */
	@Override
	public Set<Tuple> zrevrangeByScoreWithScores(String key, String max, String min, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByScoreWithScores(key, max, min, offset, count);
		}
		try {
			return msonion_zrevrangeByScoreWithScores(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min, "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zremrangeByRank
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:30:22
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Long msonion_zremrangeByRank(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zremrangeByRank(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zremrangeByRank(String, long, long)
	 */
	@Override
	public Long zremrangeByRank(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_zremrangeByRank(key, start, end);
		}
		try {
			return msonion_zremrangeByRank(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zremrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:32:02
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Long msonion_zremrangeByScore(String key, double start, double end) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zremrangeByScore(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zremrangeByScore(String, double, double)
	 */
	@Override
	public Long zremrangeByScore(String key, double start, double end) {
		if (null == this.logAnalyzer) {
			return msonion_zremrangeByScore(key, start, end);
		}
		try {
			return msonion_zremrangeByScore(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zremrangeByScore
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:33:19
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Long msonion_zremrangeByScore(String key, String start, String end) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zremrangeByScore(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zremrangeByScore(String, String, String)
	 */
	@Override
	public Long zremrangeByScore(String key, String start, String end) {
		if (null == this.logAnalyzer) {
			return msonion_zremrangeByScore(key, start, end);
		}
		try {
			return msonion_zremrangeByScore(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start, "end", end));
			return null;
		}
	}

	/**
	* @Title: msonion_zlexcount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:35:03
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Long msonion_zlexcount(String key, String min, String max) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zlexcount(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zlexcount(String, String, String)
	 */
	@Override
	public Long zlexcount(String key, String min, String max) {
		if (null == this.logAnalyzer) {
			return msonion_zlexcount(key, min, max);
		}
		try {
			return msonion_zlexcount(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByLex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:36:13
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Set<String> msonion_zrangeByLex(String key, String min, String max) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrangeByLex(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByLex(String, String, String)
	 */
	@Override
	public Set<String> zrangeByLex(String key, String min, String max) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByLex(key, min, max);
		}
		try {
			return msonion_zrangeByLex(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
	}

	/**
	* @Title: msonion_zrangeByLex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:37:32
	*
	* @param key
	* @param min
	* @param max
	* @param offset
	* @param count
	* @return
	*/
	private Set<String> msonion_zrangeByLex(String key, String min, String max, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrangeByLex(key, min, max, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrangeByLex(String, String, String, int, int)
	 */
	@Override
	public Set<String> zrangeByLex(String key, String min, String max, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrangeByLex(key, min, max, offset, count);
		}
		try {
			return msonion_zrangeByLex(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max, "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByLex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:39:09
	*
	* @param key
	* @param max
	* @param min
	* @return
	*/
	private Set<String> msonion_zrevrangeByLex(String key, String max, String min) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrangeByLex(key, max, min);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByLex(String, String, String)
	 */
	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByLex(key, max, min);
		}
		try {
			return msonion_zrevrangeByLex(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min));
			return null;
		}
	}

	/**
	* @Title: msonion_zrevrangeByLex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:40:56
	*
	* @param key
	* @param max
	* @param min
	* @param offset
	* @param count
	* @return
	*/
	private Set<String> msonion_zrevrangeByLex(String key, String max, String min, int offset, int count) {
		Jedis jedis = this.getJedis();
		Set<String> result = jedis.zrevrangeByLex(key, max, min, offset, count);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param max
	 * @param min
	 * @param offset
	 * @param count
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zrevrangeByLex(String, String, String, int, int)
	 */
	@Override
	public Set<String> zrevrangeByLex(String key, String max, String min, int offset, int count) {
		if (null == this.logAnalyzer) {
			return msonion_zrevrangeByLex(key, max, min, offset, count);
		}
		try {
			return msonion_zrevrangeByLex(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min, "offset", offset +"", "count", count +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zremrangeByLex
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:42:27
	*
	* @param key
	* @param min
	* @param max
	* @return
	*/
	private Long msonion_zremrangeByLex(String key, String min, String max) {
		Jedis jedis = this.getJedis();
		Long result = jedis.zremrangeByLex(key, min, max);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param min
	 * @param max
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zremrangeByLex(String, String, String)
	 */
	@Override
	public Long zremrangeByLex(String key, String min, String max) {
		if (null == this.logAnalyzer) {
			return msonion_zremrangeByLex(key, min, max);
		}
		try {
			return msonion_zremrangeByLex(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
	}

	/**
	* @Title: msonion_linsert
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:44:11
	*
	* @param key
	* @param where
	* @param pivot
	* @param value
	* @return
	*/
	private Long msonion_linsert(String key, LIST_POSITION where, String pivot, String value) {
		Jedis jedis = this.getJedis();
		Long result = jedis.linsert(key, where, pivot, value);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param where
	 * @param pivot
	 * @param value
	 * @return
	 * @see redis.clients.jedis.JedisCommands#linsert(String, LIST_POSITION, String, String)
	 */
	@Override
	public Long linsert(String key, LIST_POSITION where, String pivot, String value) {
		if (null == this.logAnalyzer) {
			return msonion_linsert(key, where, pivot, value);
		}
		try {
			return msonion_linsert(key, where, pivot, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, where, pivot, value));
			return null;
		}
	}

	/**
	* @Title: msonion_lpushx
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:45:48
	*
	* @param key
	* @param string
	* @return
	*/
	private Long msonion_lpushx(String key, String... string) {
		Jedis jedis = this.getJedis();
		Long result = jedis.lpushx(key, string);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param string
	 * @return
	 * @see redis.clients.jedis.JedisCommands#lpushx(String, String[])
	 */
	@Override
	public Long lpushx(String key, String... string) {
		if (null == this.logAnalyzer) {
			return msonion_lpushx(key, string);
		}
		try {
			return msonion_lpushx(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
	}

	/**
	* @Title: msonion_rpushx
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:47:49
	*
	* @param key
	* @param string
	* @return
	*/
	private Long msonion_rpushx(String key, String... string) {
		Jedis jedis = this.getJedis();
		Long result = jedis.rpushx(key, string);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param string
	 * @return
	 * @see redis.clients.jedis.JedisCommands#rpushx(String, String[])
	 */
	@Override
	public Long rpushx(String key, String... string) {
		if (null == this.logAnalyzer) {
			return msonion_rpushx(key, string);
		}
		try {
			return msonion_rpushx(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
	}

	/**
	* @Title: msonion_blpop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:49:11
	*
	* @param arg
	* @return
	*/
	private List<String> msonion_blpop(String arg) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.blpop(arg);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param arg
	 * @return
	 * @deprecated
	 * @see redis.clients.jedis.JedisCommands#blpop(String)
	 */
	@Override
	public List<String> blpop(String arg) {
		if (null == this.logAnalyzer) {
			return msonion_blpop(arg);
		}
		try {
			return msonion_blpop(arg);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByOthers("arg", arg));
			return null;
		}
	}

	/**
	* @Title: msonion_blpop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:50:57
	*
	* @param timeout
	* @param key
	* @return
	*/
	private List<String> msonion_blpop(int timeout, String key) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.blpop(timeout, key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param timeout
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#blpop(int, String)
	 */
	@Override
	public List<String> blpop(int timeout, String key) {
		if (null == this.logAnalyzer) {
			return msonion_blpop(timeout, key);
		}
		try {
			return msonion_blpop(timeout, key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("timeout", timeout +"", "key", key));
			return null;
		}
	}

	/**
	* @Title: msonion_brpop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:53:13
	*
	* @param arg
	* @return
	*/
	private List<String> msonion_brpop(String arg) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.brpop(arg);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param arg
	 * @return
	 * @deprecated
	 * @see redis.clients.jedis.JedisCommands#brpop(String)
	 */
	@Override
	public List<String> brpop(String arg) {
		if (null == this.logAnalyzer) {
			return msonion_brpop(arg);
		}
		try {
			return msonion_brpop(arg);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByOthers("arg", arg));
			return null;
		}
	}

	/**
	* @Title: msonion_brpop
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:54:46
	*
	* @param timeout
	* @param key
	* @return
	*/
	private List<String> msonion_brpop(int timeout, String key) {
		Jedis jedis = this.getJedis();
		List<String> result = jedis.brpop(timeout, key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param timeout
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#brpop(int, String)
	 */
	@Override
	public List<String> brpop(int timeout, String key) {
		if (null == this.logAnalyzer) {
			return msonion_brpop(timeout, key);
		}
		try {
			return msonion_brpop(timeout, key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("timeout", timeout +"", "key", key));
			return null;
		}
	}

	/**
	* @Title: msonion_del
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:57:13
	*
	* @param key
	* @return
	*/
	private Long msonion_del(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.del(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#del(String)
	 */
	@Override
	public Long del(String key) {
		if (null == this.logAnalyzer) {
			return msonion_del(key);
		}
		try {
			return msonion_del(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_echo
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午9:59:09
	*
	* @param string
	* @return
	*/
	private String msonion_echo(String string) {
		Jedis jedis = this.getJedis();
		String result = jedis.echo(string);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param string
	 * @return
	 * @see redis.clients.jedis.JedisCommands#echo(String)
	 */
	@Override
	public String echo(String string) {
		if (null == this.logAnalyzer) {
			return msonion_echo(string);
		}
		try {
			return msonion_echo(string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByOthers("string", string));
			return null;
		}
	}

	/**
	* @Title: msonion_move
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:01:46
	*
	* @param key
	* @param dbIndex
	* @return
	*/
	private Long msonion_move(String key, int dbIndex) {
		Jedis jedis = this.getJedis();
		Long result = jedis.move(key, dbIndex);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param dbIndex
	 * @return
	 * @see redis.clients.jedis.JedisCommands#move(String, int)
	 */
	@Override
	public Long move(String key, int dbIndex) {
		if (null == this.logAnalyzer) {
			return msonion_move(key, dbIndex);
		}
		try {
			return msonion_move(key, dbIndex);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "dbIndex", dbIndex +""));
			return null;
		}
	}

	/**
	* @Title: msonion_bitcount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:03:04
	*
	* @param key
	* @return
	*/
	private Long msonion_bitcount(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.bitcount(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#bitcount(String)
	 */
	@Override
	public Long bitcount(String key) {
		if (null == this.logAnalyzer) {
			return msonion_bitcount(key);
		}
		try {
			return msonion_bitcount(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
	}

	/**
	* @Title: msonion_bitcount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:04:36
	*
	* @param key
	* @param start
	* @param end
	* @return
	*/
	private Long msonion_bitcount(String key, long start, long end) {
		Jedis jedis = this.getJedis();
		Long result = jedis.bitcount(key, start, end);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param start
	 * @param end
	 * @return
	 * @see redis.clients.jedis.JedisCommands#bitcount(String, long, long)
	 */
	@Override
	public Long bitcount(String key, long start, long end) {
		if (null == this.logAnalyzer) {
			return msonion_bitcount(key, start, end);
		}
		try {
			return msonion_bitcount(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
	}

	/**
	* @Title: msonion_hscan
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:06:24
	*
	* @param key
	* @param cursor
	* @return
	*/
	private ScanResult<Entry<String, String>> msonion_hscan(String key, int cursor) {
		Jedis jedis = this.getJedis();
		ScanResult<Entry<String, String>> result = jedis.hscan(key, cursor);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param cursor
	 * @return
	 * @deprecated
	 * @see redis.clients.jedis.JedisCommands#hscan(String, int)
	 */
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, int cursor) {
		if (null == this.logAnalyzer) {
			return msonion_hscan(key, cursor);
		}
		try {
			return msonion_hscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "cursor", cursor +""));
			return null;
		}
	}

	/**
	* @Title: msonion_sscan
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:08:08
	*
	* @param key
	* @param cursor
	* @return
	*/
	private ScanResult<String> msonion_sscan(String key, int cursor) {
		Jedis jedis = this.getJedis();
		ScanResult<String> result = jedis.sscan(key, cursor);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param cursor
	 * @return
	 * @deprecated
	 * @see redis.clients.jedis.JedisCommands#sscan(String, int)
	 */
	@Override
	public ScanResult<String> sscan(String key, int cursor) {
		if (null == this.logAnalyzer) {
			return msonion_sscan(key, cursor);
		}
		try {
			return msonion_sscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "cursor", cursor +""));
			return null;
		}
	}

	/**
	* @Title: msonion_zscan
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:09:25
	*
	* @param key
	* @param cursor
	* @return
	*/
	private ScanResult<Tuple> msonion_zscan(String key, int cursor) {
		Jedis jedis = this.getJedis();
		ScanResult<Tuple> result = jedis.zscan(key, cursor);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param cursor
	 * @return
	 * @deprecated
	 * @see redis.clients.jedis.JedisCommands#zscan(String, int)
	 */
	@Override
	public ScanResult<Tuple> zscan(String key, int cursor) {
		if (null == this.logAnalyzer) {
			return msonion_zscan(key, cursor);
		}
		try {
			return msonion_zscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "cursor", cursor +""));
			return null;
		}
	}

	/**
	* @Title: msonion_hscan
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:10:34
	*
	* @param key
	* @param cursor
	* @return
	*/
	private ScanResult<Entry<String, String>> msonion_hscan(String key, String cursor) {
		Jedis jedis = this.getJedis();
		ScanResult<Entry<String, String>> result = jedis.hscan(key, cursor);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param cursor
	 * @return
	 * @see redis.clients.jedis.JedisCommands#hscan(String, String)
	 */
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor) {
		if (null == this.logAnalyzer) {
			return msonion_hscan(key, cursor);
		}
		try {
			return msonion_hscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "cursor", cursor));
			return null;
		}
	}

	/**
	* @Title: msonion_sscan
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:11:52
	*
	* @param key
	* @param cursor
	* @return
	*/
	private ScanResult<String> msonion_sscan(String key, String cursor) {
		Jedis jedis = this.getJedis();
		ScanResult<String> result = jedis.sscan(key, cursor);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param cursor
	 * @return
	 * @see redis.clients.jedis.JedisCommands#sscan(String, String)
	 */
	@Override
	public ScanResult<String> sscan(String key, String cursor) {
		if (null == this.logAnalyzer) {
			return msonion_sscan(key, cursor);
		}
		try {
			return msonion_sscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "cursor", cursor));
			return null;
		}
	}

	/**
	* @Title: msonion_zscan
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:13:02
	*
	* @param key
	* @param cursor
	* @return
	*/
	private ScanResult<Tuple> msonion_zscan(String key, String cursor) {
		Jedis jedis = this.getJedis();
		ScanResult<Tuple> result = jedis.zscan(key, cursor);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param cursor
	 * @return
	 * @see redis.clients.jedis.JedisCommands#zscan(String, String)
	 */
	@Override
	public ScanResult<Tuple> zscan(String key, String cursor) {
		if (null == this.logAnalyzer) {
			return msonion_zscan(key, cursor);
		}
		try {
			return msonion_zscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "cursor", cursor));
			return null;
		}
	}

	/**
	* @Title: msonion_pfadd
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:14:09
	*
	* @param key
	* @param elements
	* @return
	*/
	private Long msonion_pfadd(String key, String... elements) {
		Jedis jedis = this.getJedis();
		Long result = jedis.pfadd(key, elements);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @param elements
	 * @return
	 * @see redis.clients.jedis.JedisCommands#pfadd(String, String[])
	 */
	@Override
	public Long pfadd(String key, String... elements) {
		if (null == this.logAnalyzer) {
			return msonion_pfadd(key, elements);
		}
		try {
			return msonion_pfadd(key, elements);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "elements", elements));
			return null;
		}
	}

	/**
	* @Title: msonion_pfcount
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author HeroCao hero-cao@msyc.cc
	* @date 2017年2月16日 上午10:15:23
	*
	* @param key
	* @return
	*/
	private long msonion_pfcount(String key) {
		Jedis jedis = this.getJedis();
		Long result = jedis.pfcount(key);
		this.close(jedis);
		return result;
	}

	/**
	 * (non-Javadoc)
	 *
	 *
	 * @param key
	 * @return
	 * @see redis.clients.jedis.JedisCommands#pfcount(String)
	 */
	@Override
	public long pfcount(String key) {
		if (null == this.logAnalyzer) {
			return msonion_pfcount(key);
		}
		try {
			return msonion_pfcount(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return 0L;
		}
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param value
	* @param nxxx
	* @return
	* @see redis.clients.jedis.JedisCommands#set(String, String, String)
	*/
	@Override
	public String set(String key, String value, String nxxx) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @return
	* @see redis.clients.jedis.JedisCommands#pttl(String)
	*/
	@Override
	public Long pttl(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param milliseconds
	* @param value
	* @return
	* @see redis.clients.jedis.JedisCommands#psetex(String, long, String)
	*/
	@Override
	public String psetex(String key, long milliseconds, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param field
	* @param value
	* @return
	* @see redis.clients.jedis.JedisCommands#hincrByFloat(String, String, double)
	*/
	@Override
	public Double hincrByFloat(String key, String field, double value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param score
	* @param member
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#zadd(String, double, String, ZAddParams)
	*/
	@Override
	public Long zadd(String key, double score, String member, ZAddParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param scoreMembers
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#zadd(String, Map, ZAddParams)
	*/
	@Override
	public Long zadd(String key, Map<String, Double> scoreMembers, ZAddParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param score
	* @param member
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#zincrby(String, double, String, ZIncrByParams)
	*/
	@Override
	public Double zincrby(String key, double score, String member, ZIncrByParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param value
	* @return
	* @see redis.clients.jedis.JedisCommands#bitpos(String, boolean)
	*/
	@Override
	public Long bitpos(String key, boolean value) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param value
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#bitpos(String, boolean, BitPosParams)
	*/
	@Override
	public Long bitpos(String key, boolean value, BitPosParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param cursor
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#hscan(String, String, ScanParams)
	*/
	@Override
	public ScanResult<Entry<String, String>> hscan(String key, String cursor, ScanParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param cursor
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#sscan(String, String, ScanParams)
	*/
	@Override
	public ScanResult<String> sscan(String key, String cursor, ScanParams params) {
		// TODO Auto-generated method stub
		Jedis jedis = this.getJedis();
		ScanResult<String> returnList = jedis.sscan(key, cursor, params);
		jedis.close();
		return returnList;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param cursor
	* @param params
	* @return
	* @see redis.clients.jedis.JedisCommands#zscan(String, String, ScanParams)
	*/
	@Override
	public ScanResult<Tuple> zscan(String key, String cursor, ScanParams params) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param longitude
	* @param latitude
	* @param member
	* @return
	* @see redis.clients.jedis.JedisCommands#geoadd(String, double, double, String)
	*/
	@Override
	public Long geoadd(String key, double longitude, double latitude, String member) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param memberCoordinateMap
	* @return
	* @see redis.clients.jedis.JedisCommands#geoadd(String, Map)
	*/
	@Override
	public Long geoadd(String key, Map<String, GeoCoordinate> memberCoordinateMap) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param member1
	* @param member2
	* @return
	* @see redis.clients.jedis.JedisCommands#geodist(String, String, String)
	*/
	@Override
	public Double geodist(String key, String member1, String member2) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param member1
	* @param member2
	* @param unit
	* @return
	* @see redis.clients.jedis.JedisCommands#geodist(String, String, String, GeoUnit)
	*/
	@Override
	public Double geodist(String key, String member1, String member2, GeoUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param members
	* @return
	* @see redis.clients.jedis.JedisCommands#geohash(String, String[])
	*/
	@Override
	public List<String> geohash(String key, String... members) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param members
	* @return
	* @see redis.clients.jedis.JedisCommands#geopos(String, String[])
	*/
	@Override
	public List<GeoCoordinate> geopos(String key, String... members) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param longitude
	* @param latitude
	* @param radius
	* @param unit
	* @return
	* @see redis.clients.jedis.JedisCommands#georadius(String, double, double, double, GeoUnit)
	*/
	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius,
			GeoUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param longitude
	* @param latitude
	* @param radius
	* @param unit
	* @param param
	* @return
	* @see redis.clients.jedis.JedisCommands#georadius(String, double, double, double, GeoUnit, GeoRadiusParam)
	*/
	@Override
	public List<GeoRadiusResponse> georadius(String key, double longitude, double latitude, double radius, GeoUnit unit,
			GeoRadiusParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param member
	* @param radius
	* @param unit
	* @return
	* @see redis.clients.jedis.JedisCommands#georadiusByMember(String, String, double, GeoUnit)
	*/
	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param member
	* @param radius
	* @param unit
	* @param param
	* @return
	* @see redis.clients.jedis.JedisCommands#georadiusByMember(String, String, double, GeoUnit, GeoRadiusParam)
	*/
	@Override
	public List<GeoRadiusResponse> georadiusByMember(String key, String member, double radius, GeoUnit unit,
			GeoRadiusParam param) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	* (non-Javadoc)
	*
	*
	* @param key
	* @param arguments
	* @return
	* @see redis.clients.jedis.JedisCommands#bitfield(String, String[])
	*/
	@Override
	public List<Long> bitfield(String key, String... arguments) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 根据字符串匹配查找KEY的集合
	 *
	 * @param pattern 字符串
	 * @return KEY的集合
	 */
	@Override
	public Set<String> keys(String pattern) {
		if (MsOnionStringUtils.isBlank(pattern)) {
			return null;
		}
		Jedis jedis = getJedis();
		Set<String> returnList = jedis.keys(pattern);
		this.close(jedis);
		return returnList;
	}

	/**
	 * 根据字符串匹配查找KEY的集合
	 *
	 * @param pattern 字符串
	 * @return the ms onion result
	 * @throws MsOnionIllegalArgumentException the ms onion illegal argument exception
	 * @throws MsOnionException                the ms onion exception
	 */
	@Override
	public MsOnionResult keysForResult(String pattern) throws MsOnionIllegalArgumentException, MsOnionException {
		try {
			Set<String> keys = keys(pattern);
			return MsOnionResult.ok(keys);
		} catch (Exception ex) {
			throw new MsOnionException(ex);
		}
	}
}
