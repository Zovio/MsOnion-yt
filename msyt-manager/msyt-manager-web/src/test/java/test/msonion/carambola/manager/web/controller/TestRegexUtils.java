package test.msonion.carambola.manager.web.controller;

import cc.msonion.carambola.parent.common.utils.MsOnionRegexUtils;
import org.junit.Test;

public class TestRegexUtils {


    @Test
    public void testGTZero(){


        System.out.println(MsOnionRegexUtils.isInteger("0"));
    }
}
