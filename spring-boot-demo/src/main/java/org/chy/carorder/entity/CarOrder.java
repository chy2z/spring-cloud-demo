package org.chy.carorder.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * tk.mybatis
 *
 * @author admin
 * Created by chy on 2021/8/11.
 */
@Table(name="car_order")
public class CarOrder implements Serializable {
    private static final long serialVersionUID = -5007155473903409598L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY,generator = "JDBC")
    private Long id;

    @Column(name = "carNo")
    private String carNo;

    @Column(name = "orderNo")
    private String orderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}