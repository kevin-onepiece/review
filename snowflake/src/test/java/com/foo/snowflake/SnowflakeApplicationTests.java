package com.foo.snowflake;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import com.foo.snowflake.util.WxPayUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class SnowflakeApplicationTests {
     private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    void contextLoads() {

        for (int i = 0; i < 500; i++) {
            Snowflake snowflake = IdUtil.getSnowflake();
            String idStr = snowflake.nextIdStr();
            System.out.println("idStr = " + idStr);
            assert idStr.length() == 19;
        }
    }

    @Test
    void testWxPay() {
        System.out.println(WxPayUtils.moneyY2F("2.5"));
    }

    @Test
    void testLog() {
        String aa = "foo";
        logger.info("hello: {}", aa.toString());
    }

}
