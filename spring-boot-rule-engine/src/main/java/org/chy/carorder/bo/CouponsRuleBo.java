package org.chy.carorder.bo;

/**
 * Created by chy on 2022/5/22.
 */
public class CouponsRuleBo {
    private Long money;

    private String type;

    public CouponsRuleBo(Long money,String type) {
        this.money = money;
        this.type = type;
    }

    public CouponsRuleBo() {

    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}