package org.chy.carorder.statemachine;

/**
 * @author chy
 * @Title: 订单状态事件类型
 * @Description:
 * @date 2023/4/5 22:27
 */
public enum MyOrderStateEventEnum {
    FROM_05_TO_10(5, 10, "未提交到待确认"),
    FROM_10_TO_20(10, 20, "待确认到待进场"),
    FROM_20_TO_30(20, 30, "待进场到进行中"),
    FROM_30_TO_40(30, 40, "进行中到用车完结"),
    FROM_40_TO_50(40, 50, "用车完结到已完成"),
    FROM_05_TO_90(5, 90, "未提交到已取消"),
    FROM_10_TO_90(10, 90, "待确认到已取消"),
    FROM_20_TO_90(20, 90, "待进场到已取消");

    private final Integer from;
    private final Integer to;
    private final String name;

    MyOrderStateEventEnum(Integer from, Integer to, String name) {
        this.from = from;
        this.to = to;
        this.name = name;
    }

    public Integer getCode() {
        return from;
    }

    public Integer getTo() {
        return to;
    }

    public String getName() {
        return name;
    }
}