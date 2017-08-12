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

/**   
 * @Title: MsOnionElasticJedisCluster.java
 * @Package: cc.msonion.carambola.parent.impl.redis.clients.jedis 
 * @Description: TODO(描述该文件做什么) 
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc  
 * @Date: 2017年2月15日 下午11:41:13 
 * @Version: V2.0.0
 * @Modify-by: HeroCao hero-cao@msyc.cc
 * @Modify-date: 2017年2月15日 下午11:41:13  
 * @Modify-version: V2.0.0
 * @Modify-description: TODO(描述修改什么内容) 
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

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/** 
 * @ClassName: MsOnionElasticJedisCluster
 * @Description: 封装Redis集群版本Jedis
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: HeroCao hero-cao@msyc.cc
 * @Date: 2017年2月15日 下午11:41:13 
 *  
 */
public class MsOnionElasticJedisCluster implements MsOnionJedisAdapter {

	/**
	 * JedisCluster
	 */
	@Autowired
	private JedisCluster jedisCluster;

	/**
	 * MsOnionLogAnalyzerAdapter
	 */
	//	@Autowired  // LogAnalyzer 是一个接口，可以吗？ 使用构造方法的参数
	private MsOnionLogAnalyzerAdapter logAnalyzer;

	public MsOnionElasticJedisCluster() {
		super();
	}

	/**
	 * @param logAnalyzer 日志分析器
	 */
	public MsOnionElasticJedisCluster(MsOnionLogAnalyzerAdapter logAnalyzer) {
		super();
		this.logAnalyzer = logAnalyzer;
	}

	//	private void close() {
	//		// 由于Spring 容器中bean 默认是单例，整个应用就一个实例，不可以关闭！！！
	////		this.jedisCluster.close();
	//	}

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
			return this.jedisCluster.set(key, value);
		}
		try {
			return this.jedisCluster.set(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value));
			return null;
		}
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
			return this.jedisCluster.set(key, value, nxxx, expx, time);
		}
		try {
			return this.jedisCluster.set(key, value, nxxx, expx, time);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "value", value, "nxxx", nxxx, "expx", expx, "time", time +""));
			return null;
		}
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
			return this.jedisCluster.get(key);
		}
		try {
			return this.jedisCluster.get(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.exists(key);
		}
		try {
			return this.jedisCluster.exists(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.persist(key);
		}
		try {
			return this.jedisCluster.persist(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.type(key);
		}
		try {
			return this.jedisCluster.type(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.expire(key, seconds);
		}
		try {
			return this.jedisCluster.expire(key, seconds);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, seconds));
			return null;
		}
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
			return this.jedisCluster.pexpire(key, milliseconds);
		}
		try {
			return this.jedisCluster.pexpire(key, milliseconds);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, milliseconds));
			return null;
		}
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
			return this.jedisCluster.expireAt(key, unixTime);
		}
		try {
			return this.jedisCluster.expireAt(key, unixTime);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "unixTime", unixTime +""));
			return null;
		}
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
			return this.jedisCluster.pexpireAt(key, millisecondsTimestamp);
		}
		try {
			return this.jedisCluster.pexpireAt(key, millisecondsTimestamp);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "millisecondsTimestamp", millisecondsTimestamp +""));
			return null;
		}
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
			return this.jedisCluster.ttl(key);
		}
		try {
			return this.jedisCluster.ttl(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.setbit(key, offset, value);
		}
		try {
			return this.jedisCluster.setbit(key, offset, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +"", "value", value +""));
			return null;
		}
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
			return this.jedisCluster.setbit(key, offset, value);
		}
		try {
			return this.jedisCluster.setbit(key, offset, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +"", "value", value));
			return null;
		}
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
			return this.jedisCluster.getbit(key, offset);
		}
		try {
			return this.jedisCluster.getbit(key, offset);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +""));
			return null;
		}
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
			return this.jedisCluster.setrange(key, offset, value);
		}
		try {
			return this.jedisCluster.setrange(key, offset, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "offset", offset +"", "value", value));
			return null;
		}
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
			return this.jedisCluster.getrange(key, startOffset, endOffset);
		}
		try {
			return this.jedisCluster.getrange(key, startOffset, endOffset);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "startOffset", startOffset +"", "endOffset", endOffset +""));
			return null;
		}
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
			return this.jedisCluster.getSet(key, value);
		}
		try {
			return this.jedisCluster.getSet(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "value", value));
			return null;
		}
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
			return this.jedisCluster.setnx(key, value);
		}
		try {
			return this.jedisCluster.setnx(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "value", value));
			return null;
		}
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
			return this.jedisCluster.setex(key, seconds, value);
		}
		try {
			return this.jedisCluster.setex(key, seconds, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "seconds", seconds +"",  "value", value));
			return null;
		}
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
			return this.jedisCluster.decrBy(key, integer);
		}
		try {
			return this.jedisCluster.decrBy(key, integer);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "integer", integer +""));
			return null;
		}
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
			return this.jedisCluster.decr(key);
		}
		try {
			return this.jedisCluster.decr(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.incrBy(key, integer);
		}
		try {
			return this.jedisCluster.incrBy(key, integer);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "integer", integer +""));
			return null;
		}
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
			return this.jedisCluster.incrByFloat(key, value);
		}
		try {
			return this.jedisCluster.incrByFloat(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "value", value +""));
			return null;
		}
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
			return this.jedisCluster.incr(key);
		}
		try {
			return this.jedisCluster.incr(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.append(key, value);
		}
		try {
			return this.jedisCluster.append(key, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, value));
			return null;
		}
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
			return this.jedisCluster.substr(key, start, end);
		}
		try {
			return this.jedisCluster.substr(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.hset(key, field, value);
		}
		try {
			return this.jedisCluster.hset(key, field, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field, "value", value));
			return null;
		}
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
			return this.jedisCluster.hget(key, field);
		}
		try {
			return this.jedisCluster.hget(key, field);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field));
			return null;
		}
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
			return this.jedisCluster.hsetnx(key, field, value);
		}
		try {
			return this.jedisCluster.hsetnx(key, field, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field, "value", value));
			return null;
		}
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
			return this.jedisCluster.hmset(key, hash);
		}
		try {
			return this.jedisCluster.hmset(key, hash);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, "hash", hash));
			return null;
		}
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
			return this.jedisCluster.hmget(key, fields);
		}
		try {
			return this.jedisCluster.hmget(key, fields);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "fields", fields));
			return null;
		}
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
			return this.jedisCluster.hincrBy(key, field, value);
		}
		try {
			return this.jedisCluster.hincrBy(key, field, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field, "value", value +""));
			return null;
		}
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
			return this.jedisCluster.hexists(key, field);
		}
		try {
			return this.jedisCluster.hexists(key, field);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "field", field));
			return null;
		}
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
			return this.jedisCluster.hdel(key, field);
		}
		try {
			return this.jedisCluster.hdel(key, field);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "field", field));
			return null;
		}
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
			return this.jedisCluster.hlen(key);
		}
		try {
			return this.jedisCluster.hlen(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.hkeys(key);
		}
		try {
			return this.jedisCluster.hkeys(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.hvals(key);
		}
		try {
			return this.jedisCluster.hvals(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.hgetAll(key);
		}
		try {
			return this.jedisCluster.hgetAll(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.rpush(key, string);
		}
		try {
			return this.jedisCluster.rpush(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
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
			return this.jedisCluster.lpush(key, string);
		}
		try {
			return this.jedisCluster.lpush(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
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
			return this.jedisCluster.llen(key);
		}
		try {
			return this.jedisCluster.llen(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.lrange(key, start, end);
		}
		try {
			return this.jedisCluster.lrange(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.ltrim(key, start, end);
		}
		try {
			return this.jedisCluster.ltrim(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.lindex(key, index);
		}
		try {
			return this.jedisCluster.lindex(key, index);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "index", index +""));
			return null;
		}
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
			return this.jedisCluster.lset(key, index, value);
		}
		try {
			return this.jedisCluster.lset(key, index, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "index", index +"", "value", value));
			return null;
		}
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
			return this.jedisCluster.lrem(key, count, value);
		}
		try {
			return this.jedisCluster.lrem(key, count, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "count", count +"", "value", value));
			return null;
		}
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
			return this.jedisCluster.lpop(key);
		}
		try {
			return this.jedisCluster.lpop(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.rpop(key);
		}
		try {
			return this.jedisCluster.rpop(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.sadd(key, member);
		}
		try {
			return this.jedisCluster.sadd(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "member", member));
			return null;
		}
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
			return this.jedisCluster.smembers(key);
		}
		try {
			return this.jedisCluster.smembers(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.srem(key, member);
		}
		try {
			return this.jedisCluster.srem(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "member", member));
			return null;
		}
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
			return this.jedisCluster.spop(key);
		}
		try {
			return this.jedisCluster.spop(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.spop(key, count);
		}
		try {
			return this.jedisCluster.spop(key, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "count", count +""));
			return null;
		}
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
			return this.jedisCluster.scard(key);
		}
		try {
			return this.jedisCluster.scard(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.sismember(key, member);
		}
		try {
			return this.jedisCluster.sismember(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "member", member));
			return null;
		}
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
			return this.jedisCluster.srandmember(key);
		}
		try {
			return this.jedisCluster.srandmember(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.srandmember(key, count);
		}
		try {
			return this.jedisCluster.srandmember(key, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "count", count +""));
			return null;
		}
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
			return this.jedisCluster.strlen(key);
		}
		try {
			return this.jedisCluster.strlen(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.zadd(key, score, member);
		}
		try {
			return this.jedisCluster.zadd(key, score, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "score", score +"", "member", member));
			return null;
		}
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
			return this.jedisCluster.zadd(key, scoreMembers);
		}
		try {
			return this.jedisCluster.zadd(key, scoreMembers);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "scoreMembers", scoreMembers));
			return null;
		}
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
			return this.jedisCluster.zrange(key, start, end);
		}
		try {
			return this.jedisCluster.zrange(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.zrem(key, member);
		}
		try {
			return this.jedisCluster.zrem(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "member", member));
			return null;
		}
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
			return this.jedisCluster.zincrby(key, score, member);
		}
		try {
			return this.jedisCluster.zincrby(key, score, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "score", score +"", "member", member));
			return null;
		}
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
			return this.jedisCluster.zrank(key, member);
		}
		try {
			return this.jedisCluster.zrank(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "member", member));
			return null;
		}
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
			return this.jedisCluster.zrevrank(key, member);
		}
		try {
			return this.jedisCluster.zrevrank(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "member", member));
			return null;
		}
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
			return this.jedisCluster.zrevrange(key, start, end);
		}
		try {
			return this.jedisCluster.zrevrange(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.zrangeWithScores(key, start, end);
		}
		try {
			return this.jedisCluster.zrangeWithScores(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeWithScores(key, start, end);
		}
		try {
			return this.jedisCluster.zrevrangeWithScores(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.zcard(key);
		}
		try {
			return this.jedisCluster.zcard(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.zscore(key, member);
		}
		try {
			return this.jedisCluster.zscore(key, member);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "member", member));
			return null;
		}
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
			return this.jedisCluster.sort(key);
		}
		try {
			return this.jedisCluster.sort(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.sort(key, sortingParameters);
		}
		try {
			return this.jedisCluster.sort(key, sortingParameters);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, sortingParameters));
			return null;
		}
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
			return this.jedisCluster.zcount(key, min, max);
		}
		try {
			return this.jedisCluster.zcount(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
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
			return this.jedisCluster.zcount(key, min, max);
		}
		try {
			return this.jedisCluster.zcount(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
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
			return this.jedisCluster.zrangeByScore(key, min, max);
		}
		try {
			return this.jedisCluster.zrangeByScore(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
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
			return this.jedisCluster.zrangeByScore(key, min, max);
		}
		try {
			return this.jedisCluster.zrangeByScore(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScore(key, max, min);
		}
		try {
			return this.jedisCluster.zrevrangeByScore(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
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
			return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
		}
		try {
			return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScore(key, max, min);
		}
		try {
			return this.jedisCluster.zrevrangeByScore(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min));
			return null;
		}
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
			return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
		}
		try {
			return this.jedisCluster.zrangeByScore(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
		}
		try {
			return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
		}
		try {
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
		}
		try {
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +""));
			return null;
		}
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
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		try {
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
		}
		try {
			return this.jedisCluster.zrevrangeByScore(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
		}
		try {
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
		}
		try {
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +""));
			return null;
		}
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
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
		}
		try {
			return this.jedisCluster.zrangeByScoreWithScores(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min +"", "max", max +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
		}
		try {
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
		}
		try {
			return this.jedisCluster.zrevrangeByScoreWithScores(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max +"", "min", min +"", "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zremrangeByRank(key, start, end);
		}
		try {
			return this.jedisCluster.zremrangeByRank(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.zremrangeByScore(key, start, end);
		}
		try {
			return this.jedisCluster.zremrangeByScore(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.zremrangeByScore(key, start, end);
		}
		try {
			return this.jedisCluster.zremrangeByScore(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start, "end", end));
			return null;
		}
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
			return this.jedisCluster.zlexcount(key, min, max);
		}
		try {
			return this.jedisCluster.zlexcount(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
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
			return this.jedisCluster.zrangeByLex(key, min, max);
		}
		try {
			return this.jedisCluster.zrangeByLex(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
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
			return this.jedisCluster.zrangeByLex(key, min, max, offset, count);
		}
		try {
			return this.jedisCluster.zrangeByLex(key, min, max, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max, "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByLex(key, max, min);
		}
		try {
			return this.jedisCluster.zrevrangeByLex(key, max, min);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min));
			return null;
		}
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
			return this.jedisCluster.zrevrangeByLex(key, max, min, offset, count);
		}
		try {
			return this.jedisCluster.zrevrangeByLex(key, max, min, offset, count);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "max", max, "min", min, "offset", offset +"", "count", count +""));
			return null;
		}
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
			return this.jedisCluster.zremrangeByLex(key, min, max);
		}
		try {
			return this.jedisCluster.zremrangeByLex(key, min, max);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "min", min, "max", max));
			return null;
		}
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
			return this.jedisCluster.linsert(key, where, pivot, value);
		}
		try {
			return this.jedisCluster.linsert(key, where, pivot, value);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key, where, pivot, value));
			return null;
		}
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
			return this.jedisCluster.lpushx(key, string);
		}
		try {
			return this.jedisCluster.lpushx(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
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
			return this.jedisCluster.rpushx(key, string);
		}
		try {
			return this.jedisCluster.rpushx(key, string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "string", string));
			return null;
		}
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
			return this.jedisCluster.blpop(arg);
		}
		try {
			return this.jedisCluster.blpop(arg);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByOthers("arg", arg));
			return null;
		}
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
			return this.jedisCluster.blpop(timeout, key);
		}
		try {
			return this.jedisCluster.blpop(timeout, key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("timeout", timeout +"", "key", key));
			return null;
		}
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
			return this.jedisCluster.brpop(arg);
		}
		try {
			return this.jedisCluster.brpop(arg);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByOthers("arg", arg));
			return null;
		}
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
			return this.jedisCluster.brpop(timeout, key);
		}
		try {
			return this.jedisCluster.brpop(timeout, key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("timeout", timeout +"", "key", key));
			return null;
		}
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
			return this.jedisCluster.del(key);
		}
		try {
			return this.jedisCluster.del(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.echo(string);
		}
		try {
			return this.jedisCluster.echo(string);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByOthers("string", string));
			return null;
		}
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
			return this.jedisCluster.move(key, dbIndex);
		}
		try {
			return this.jedisCluster.move(key, dbIndex);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "dbIndex", dbIndex +""));
			return null;
		}
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
			return this.jedisCluster.bitcount(key);
		}
		try {
			return this.jedisCluster.bitcount(key);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson(key));
			return null;
		}
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
			return this.jedisCluster.bitcount(key, start, end);
		}
		try {
			return this.jedisCluster.bitcount(key, start, end);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "start", start +"", "end", end +""));
			return null;
		}
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
			return this.jedisCluster.hscan(key, cursor);
		}
		try {
			return this.jedisCluster.hscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "cursor", cursor +""));
			return null;
		}
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
			return this.jedisCluster.sscan(key, cursor);
		}
		try {
			return this.jedisCluster.sscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "cursor", cursor +""));
			return null;
		}
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
			return this.jedisCluster.zscan(key, cursor);
		}
		try {
			return this.jedisCluster.zscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "cursor", cursor +""));
			return null;
		}
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
			return this.jedisCluster.hscan(key, cursor);
		}
		try {
			return this.jedisCluster.hscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "cursor", cursor));
			return null;
		}
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
			return this.jedisCluster.sscan(key, cursor);
		}
		try {
			return this.jedisCluster.sscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "cursor", cursor));
			return null;
		}
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
			return this.jedisCluster.zscan(key, cursor);
		}
		try {
			return this.jedisCluster.zscan(key, cursor);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJson("key", key, "cursor", cursor));
			return null;
		}
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
			return this.jedisCluster.pfadd(key, elements);
		}
		try {
			return this.jedisCluster.pfadd(key, elements);
		} catch (Exception ex) {
			this.logAnalyzer.analyze(ex, MsOnionLogAnalyzerUtils.getLogAnalyzerJsonByArray(key, "elements", elements));
			return null;
		}
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
			return this.jedisCluster.pfcount(key);
		}
		try {
			return this.jedisCluster.pfcount(key);
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
		pattern = pattern.trim();

		Set<String> keySets = new HashSet<String>();
		Map<String, JedisPool> allJedisClusterNodes = jedisCluster.getClusterNodes();
		for (String node : allJedisClusterNodes.keySet()) {
			JedisPool jp = allJedisClusterNodes.get(node);
			Jedis connection = jp.getResource();
			keySets.addAll(connection.keys(pattern));
			connection.close();
		}
		return keySets;

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
