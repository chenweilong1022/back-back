package com.ozygod.base;

import cn.hutool.http.HttpUtil;
import com.ozygod.base.utils.HttpRequestUtil;
import com.ozygod.base.utils.WeChatUtil;
import com.ozygod.main.OzygodPmMainApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author chenweilong
 * @email 1433471850@qq.com
 * @date 2020-01-13 16:52
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OzygodPmMainApplication.class)
public class WeChatUtilTest {

    @Autowired
    private WeChatUtil weChatUtil;

    @Test
    public void getWeChatShortUrl1() {

        weChatUtil.getWeChatShortUrl1("https://studygolang.com/dl");
    }

    @Test
    public void isBlockedTest() {

        boolean blocked = weChatUtil.isBlocked("https://www.jianshu.com/p/e51ced8134a4");
        System.out.println(blocked);

    }

//    public static void main(String[] args) {
//
//
////        String s = HttpUtil.get("https://w.url.cn/s/AqoWJi7");
//
//
////        HttpUtil.get
//
//
//        String url = "https://w.url.cn/s/Ae3BgbK";
//
//
//    }
}
