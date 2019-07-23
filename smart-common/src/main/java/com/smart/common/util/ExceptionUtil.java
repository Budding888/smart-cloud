package com.smart.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author LErry.li
 * Description:
 * 异常工具类
 * Date: 2019/7/23 14:30
 */
public class ExceptionUtil {

    private ExceptionUtil() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 获取完整的异常栈信息
     *
     * @param t Throwable
     * @return 异常信息
     */
    public static String getTrace(Throwable t) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        t.printStackTrace(writer);
        StringBuffer buffer = stringWriter.getBuffer();
        return buffer.toString();
    }
}
