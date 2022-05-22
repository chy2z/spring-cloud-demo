package org.chy.carorder.bo;

/**
 *
 * @author chy
 * @date 2022/5/22
 */
public class OrderRuleBo {
    private Long count;

    private String type;

    public OrderRuleBo(Long count,String type) {
        this.count = count;
        this.type = type;
    }

    public OrderRuleBo() {

    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}