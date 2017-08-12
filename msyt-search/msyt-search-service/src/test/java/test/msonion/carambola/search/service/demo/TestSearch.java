package test.msonion.carambola.search.service.demo;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Test;

import java.lang.reflect.Array;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Created by HeroCao on 2017/6/10.
 */
public class TestSearch {

    @Test
    public void test01() {

        System.out.println("test01 # beginning ...");

        Settings settings = Settings.builder().put("cluster.name", "es.msyc.dev").build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);


        System.out.println("test01 # client=" + client);

    }


    @Test
    public void test02() throws UnknownHostException {

        System.out.println("test02 # beginning ...");

        Settings settings = Settings.builder().put("cluster.name", "es.msyc.dev").build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);

//        client.addTransportAddresses()

        TransportAddress transportAddress = null;

        InetSocketTransportAddress ista = new InetSocketTransportAddress(InetAddress.getByName("es.msyc.dev"),
                Integer.parseInt("9300"));


        client.addTransportAddresses(ista);

// test02 # client=org.elasticsearch.transport.client.PreBuiltTransportClient@3162743f
        System.out.println("test02 # client=" + client);

    }

    @Test
    public void test02_1() throws UnknownHostException {

        System.out.println("test02 # beginning ...");

        Settings settings = Settings.builder().put("cluster.name", "es.msyc.dev").build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);

//        client.addTransportAddresses()

        TransportAddress transportAddress = null;

        InetSocketTransportAddress ista = new InetSocketTransportAddress(InetAddress.getByName("es01-dev"),
                Integer.parseInt("9300"));


        client.addTransportAddresses(ista);

// test02 # client=org.elasticsearch.transport.client.PreBuiltTransportClient@3162743f
        System.out.println("test02 # client=" + client);

    }

    @Test
    public void test03() throws UnknownHostException {

        System.out.println("test03 # beginning ...");

        Settings settings = Settings.builder().put("cluster.name", "es.msyc.dev").build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);

//        client.addTransportAddresses()

        TransportAddress transportAddress = null;

        InetSocketTransportAddress ista = new InetSocketTransportAddress(InetAddress.getByName("es01-dev"),
                Integer.parseInt("9300"));
//
//
        client.addTransportAddress(ista);


        System.out.println("test03 # client=" + client);

    }

    @Test
    public void test04() throws UnknownHostException {

        System.out.println("test04 # beginning ...");

        Settings settings = Settings.builder().put("cluster.name", "es.msyc.dev").build();
        PreBuiltTransportClient client = new PreBuiltTransportClient(settings);

//        client.addTransportAddresses()

        TransportAddress transportAddress = null;

        // es01-dev:9300;es02-dev:9300;es03-dev:9300;es04-dev:9300

        InetSocketTransportAddress ista1 = new InetSocketTransportAddress(InetAddress.getByName("es01-dev"),
                Integer.parseInt("9300"));

        InetSocketTransportAddress ista2 = new InetSocketTransportAddress(InetAddress.getByName("es02-dev"),
                Integer.parseInt("9300"));

        InetSocketTransportAddress ista3 = new InetSocketTransportAddress(InetAddress.getByName("es04-dev"),
                Integer.parseInt("9300"));


//        client.addTransportAddress(ista1, ista2, ista3);

        client.addTransportAddresses(ista1, ista2, ista3);


        System.out.println("test04 # client=" + client);

    }


    @Test
    public void test05() {

        String[] str = {"11", "22"};

        System.out.println("str=" + str);

        Class<? extends String[]> aClass = str.getClass();

        System.out.println("aClass=" + aClass);

        if (str instanceof String[]) {
            System.out.println("str instanceof String[]");
        } else {
            System.out.println("非 str instanceof String[]");
        }

    }

}
