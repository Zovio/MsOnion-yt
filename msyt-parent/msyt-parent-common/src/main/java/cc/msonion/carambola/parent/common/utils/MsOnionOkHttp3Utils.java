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
 *  
 */


package cc.msonion.carambola.parent.common.utils;

import cc.msonion.carambola.parent.common.constants.MsOnionStatusConstants;
import cc.msonion.carambola.parent.common.exception.MsOnionException;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import okio.ByteString;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *  
 *
 * @Title: HttpUtils.java
 * @Package: com.yt.erp.commons
 * @Description: okHttps3 工具类
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017年03月21日
 */


/**
 * 系统工具类
 *
 * @ClassName: MsOnionSystemUtils
 * @Description: MsOnionOkHttp3Utils
 * @Company: 广州市两棵树网络科技有限公司
 * @Author: liaoxf
 * @Date: 2017-05-05
 */
public final class MsOnionOkHttp3Utils {

    private MsOnionOkHttp3Utils() {
    }

    /**
     * OkHttpClient
     */
    private static OkHttpClient client;

    /**
     * 超时时间 (ms)
     */
    public static final int TIMEOUT = 60000;

    /**
     * application/x-www-form-urlencoded
     */
    public static final MediaType MEDIA_TYPE = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");

    /**
     * application/octet-stream
     */
    public static final MediaType MEDIA_OCTET_STREAM = MediaType.parse("application/octet-stream");

    static {
        client = new OkHttpClient();
        // 设置超时时间
        client.newBuilder().connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS).build();

    }

    /**
     * 同步发送post请求 map为body
     *
     * @param url 请求url
     * @param map 请求map参数
     * @return 返回回来的JSONObject
     * @throws MsOnionException 异常
     */
    public static JSONObject post(String url, Map<String, Object> map) throws MsOnionException {
        StringBuilder context = new StringBuilder();
        // 遍历map
        if (map != null) {
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                context.append(entry.getKey()).append("=").append(entry.getValue());
                if (iterator.hasNext()) {
                    context.append("&");
                }
            }
        }

        RequestBody body = RequestBody.create(MEDIA_TYPE, context.toString());
        Request request = new Request.Builder().url(url).post(body).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                return MsOnionJsonUtils.jsonToPojo(data, JSONObject.class);
            }
            throw new MsOnionException("服务器解析错误...", MsOnionStatusConstants.STATUS_400);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

    }


    /**
     * 同步发送post请求 map为body
     *
     * @param url 请求url
     * @param map 请求map参数
     * @return 返回回来的JSONObject
     * @throws MsOnionException 异常
     */
    public static JSONObject postFormBoby(String url, Map<String, Object> map) throws MsOnionException {
        FormBody.Builder builder = new FormBody.Builder();
        // 遍历map
        if (map != null) {
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                builder.add(entry.getKey(), entry.getValue().toString());
            }
        }
        RequestBody formBody = builder.build();
        Request request = new Request.Builder().url(url).post(formBody).build();
        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                return MsOnionJsonUtils.jsonToPojo(data, JSONObject.class);
            }
            throw new MsOnionException("服务器解析错误...", MsOnionStatusConstants.STATUS_400);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

    }


    /**
     * 同步发送post请求 文件byte[]
     *
     * @param url   请求url
     * @param map   请求map参数放入header中 (header中不能传中文，value 如果是字符串需要转码 URLEncoder.encode)
     * @param bytes 文件byte[]
     * @return 返回回来的JSONObject
     * @throws MsOnionException 异常
     */
    public static JSONObject postByteArr(String url, Map map, byte[] bytes) throws MsOnionException {
        if (map == null) {
            map = new HashMap();
        }
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .post(RequestBody.create(MEDIA_OCTET_STREAM, ByteString.of(bytes)))
                    .headers(Headers.of(map))
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                return MsOnionJsonUtils.jsonToPojo(data, JSONObject.class);
            }
            throw new MsOnionException("服务器解析错误...", MsOnionStatusConstants.STATUS_400);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

    }

    /**
     * 同步发送post请求 文件
     *
     * @param url   请求url
     * @param map   请求map参数
     * @param files 文件集合
     * @return 返回回来的JSONObject
     * @throws MsOnionException 异常
     */
    public static JSONObject postFiles(String url, Map map, File... files) throws MsOnionException {
        if (map == null) {
            map = new HashMap();
        }
        if (files.length == 0) {
            // if (null == file || !file.exists() || !file.canRead() || !file.isFile()) {
            throw new MsOnionException("上传文件非法", MsOnionStatusConstants.STATUS_400);
            // }
        }

        try {
            MultipartBody.Builder builder = new MultipartBody.Builder();
            builder.setType(MultipartBody.FORM);

            // 遍历map
            Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()) {
                Map.Entry<String, Object> entry = iterator.next();
                builder.addFormDataPart(entry.getKey(), entry.getValue() == null ? "" : entry.getValue().toString());
            }

            for (int i = 0; i < files.length; i++) {
                File file = files[i];
                builder.addFormDataPart("file" + i, file.getName(), RequestBody.create(MEDIA_OCTET_STREAM, file));
            }

            RequestBody requestBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String data = response.body().string();
                return MsOnionJsonUtils.jsonToPojo(data, JSONObject.class);
            }
            throw new MsOnionException("服务器解析错误...", MsOnionStatusConstants.STATUS_400);
        } catch (Exception e) {
            throw new MsOnionException(e);
        }

    }


}
