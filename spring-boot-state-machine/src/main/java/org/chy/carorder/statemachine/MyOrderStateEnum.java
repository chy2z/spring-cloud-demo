package org.chy.carorder.statemachine;

/**
 * @author chy
 * @Title: 订单状态枚举
 * @Description:
 * @date 2023/4/5 22:26
 */
public enum MyOrderStateEnum {
    NOT_SUBMIT(5, "未提交"),
    WAIT_CONFIRMING(10, "待确认"),
    WAIT_ENTERING(20, "待进场"),
    PERFORMING(30, "进行中"),
    CAR_FINISHED(40, "用车完结"),
    ORDER_FINISHED(50, "已完成"),
    CANCELED(90, "已取消");

    private final Integer code;
    private final String name;

    MyOrderStateEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public static String getValue(Integer code) {
        for (MyOrderStateEnum tradeStatusEnum : values()) {
            if (tradeStatusEnum.getCode().equals(code)) {
                return tradeStatusEnum.getName();
            }
        }
        return "";
    }
}
