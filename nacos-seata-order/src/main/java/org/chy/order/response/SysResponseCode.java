package org.chy.order.response;

/**
 * Created by chy on 2021/11/16.
 */
public class SysResponseCode extends ResponseCode {
    public SysResponseCode(Integer code, String template) {
        super(code, template);
    }

    @Override
    public Integer prefixCode() {
        return 9110;
    }

    public static final ResponseCode SUCCESS = new SysResponseCode(0, "成功");
    public static final ResponseCode FAILED = new SysResponseCode(1,"失败");

    public static final ResponseCode PARAMETER_FAILED = new SysResponseCode(102, "校验失败：%s");
    public static final ResponseCode METHOD_NOT_SUPPORT = new SysResponseCode(103, "请求方式不正确");
}