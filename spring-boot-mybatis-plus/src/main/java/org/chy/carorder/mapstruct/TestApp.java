package org.chy.carorder.mapstruct;

import cn.hutool.json.JSONUtil;
import org.chy.carorder.mapstruct.bo.Customer;
import org.chy.carorder.mapstruct.bo.OrderItem;
import org.chy.carorder.mapstruct.dto.CustomerDto;
import org.chy.carorder.mapstruct.dto.OrderItemDto;
import org.chy.carorder.mapstruct.dto.OrderItemKeyDto;
import org.chy.carorder.mapstruct.mapper.CustomerMapper;
import org.chy.carorder.mapstruct.mapper.MapStructCloner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/6 17:50
 */
public class TestApp {
    public static void main(String[] args) {
        testEntityDtoToDto();
    }

    /**
     * 深度克隆
     */
    public static void testDeepClone(){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId( 10L );
        customerDto.setCustomerName("Jaques" );

        OrderItemDto order1 = new OrderItemDto();
        order1.setName ("Table" );
        order1.setQuantity( 2L );
        customerDto.setOrders( new ArrayList<>( Collections.singleton( order1 ) ) );

        OrderItemKeyDto key = new OrderItemKeyDto();
        key.setStockNumber( 5 );
        Map stock = new HashMap();
        stock.put( key, order1 );
        customerDto.setStock( stock );

        CustomerDto customerClone = MapStructCloner.MAPPER.clone(customerDto);

        System.out.println(customerDto.hashCode());
        System.out.println(customerClone.hashCode());

        System.out.println(JSONUtil.toJsonStr(customerDto));
        System.out.println(JSONUtil.toJsonStr(customerClone));
    }


    /**
     * dto转换实体
     */
    public static void testMapDtoToEntity() {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(10L);
        // 根据属性名称转换，类型不一致也会转换
        customerDto.setAge("20");
        customerDto.setCustomerName("chy");
        customerDto.setMan(true);

        OrderItemDto order1 = new OrderItemDto();
        order1.setName("node");
        order1.setQuantity(2L);
        customerDto.setOrders(new ArrayList<>(Collections.singleton(order1)));

        OrderItemKeyDto key = new OrderItemKeyDto();
        key.setStockNumber(6);
        Map stock = new HashMap();
        stock.put( key, order1 );
        customerDto.setStock(stock);

        Customer customer = CustomerMapper.MAPPER.toCustomer(customerDto);
        System.out.println(JSONUtil.toJsonStr(customer));
        System.out.println(JSONUtil.toJsonStr(customerDto));
    }


    /**
     * 实体转换dto
     */
    public static void testEntityDtoToDto() {
        Customer customer = new Customer();
        customer.setId(10L);
        customer.setName("chy");
        customer.setMan(true);
        customer.setAge(30);

        OrderItem order1 = new OrderItem();
        order1.setName("node");
        order1.setQuantity(2L);
        customer.setOrderItems(new ArrayList<>(Collections.singleton(order1)));

        OrderItemKeyDto key = new OrderItemKeyDto();
        key.setStockNumber(10);
        Map stock = new HashMap();
        stock.put( key, order1 );
        customer.setStock(stock);

        CustomerDto customerDto = CustomerMapper.MAPPER.fromCustomer(customer);
        System.out.println(JSONUtil.toJsonStr(customer));
        System.out.println(JSONUtil.toJsonStr(customerDto));
    }
}