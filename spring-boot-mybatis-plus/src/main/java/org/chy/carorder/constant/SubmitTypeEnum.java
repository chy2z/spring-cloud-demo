package org.chy.carorder.constant;


import java.util.Arrays;

/**
 * @author chy
 * @Title: 提交订单枚举
 * @Description:
 * @date 2022/7/2 22:44
 */
public enum SubmitTypeEnum {
    SELF(1,"自主下单"),
    REPACE(2,"代课下单")
    ;

    private Integer type;
    private String name;

    SubmitTypeEnum(Integer type,String name) {
        this.type = type;
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public static SubmitTypeEnum get(Integer type) {
        return Arrays.stream(values())
                .filter(submitTypeEnum -> submitTypeEnum.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("没有找到对应的策略枚举"));
    }
}