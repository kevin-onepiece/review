package com.foo.snowflake.util;

import java.math.BigDecimal;

/**
 * @Author: foo
 * @Date: 2021-10-22 12:17
 * @description:
 */
public class WxPayUtils {

    public static Integer moneyY2F(String yuan) {
        BigDecimal bigDecimal = new BigDecimal(yuan).setScale(2);
        return bigDecimal.multiply(new BigDecimal(100)).intValue();
    }

}
