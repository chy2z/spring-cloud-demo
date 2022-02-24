package org.chy.carorder.controller;

import com.github.pagehelper.PageHelper;
import org.chy.carorder.service.CarOrderServices;
import org.chy.carorder.service.UserService;
import org.chy.carorder.entity.CarOrder;
import org.chy.carorder.entity.Users;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author chy
 * @date 2021/7/14
 */
@RestController
@RequestMapping("/config")
public class ConfigController {
    private final static Logger LOGGER = LoggerFactory.getLogger(ConfigController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CarOrderServices carOrderServices;


    /**
     * 测试获取配置
     * http://localhost:8080/config/getUser
     *
     * @return
     */
    @GetMapping("/getUser")
    public ResponseEntityDTO<String> getUser() {
        Users u = userService.selectByPrimaryKey(1);
        return ResponseEntityDTO.success(u.getName());
    }

    /**
     * 测试获取配置
     * http://localhost:8080/config/getUserAll
     *
     * @return
     */
    @GetMapping("/getUserAll")
    public ResponseEntityDTO<List<Users>> getUserAll() {
        List<Users> users = userService.selectAll();
        return ResponseEntityDTO.success(users);
    }

    /**
     * 测试获取配置
     * http://localhost:8080/config/getPageUsers?page=2&limit=2
     *
     * @return
     */
    @GetMapping("/getPageUsers")
    public ResponseEntityDTO<List<Users>> getPageUsers(Integer page ,Integer limit) {
        PageHelper.startPage(page, limit);
        List<Users> users = userService.selectAll();
        return ResponseEntityDTO.success(users);
    }

    /**
     * 测试获取配置
     * http://localhost:8080/config/getCarOrder
     *
     * @return
     */
    @GetMapping("/getCarOrder")
    public ResponseEntityDTO<CarOrder> getCarOrder() {
        return ResponseEntityDTO.success(carOrderServices.selectByPrimaryKey(1));
    }

    /**
     * 测试获取配置
     * http://localhost:8080/config/getCarOrderAll
     *
     * @return
     */
    @GetMapping("/getCarOrderAll")
    public ResponseEntityDTO<List<CarOrder>> getCarOrderAll() {
        List<CarOrder> carOrders = carOrderServices.selectAll();
        return ResponseEntityDTO.success(carOrders);
    }

    /**
     * 测试获取配置
     * http://localhost:8080/config/getPageCarOrders?page=2&limit=2
     *
     * @return
     */
    @GetMapping("/getPageCarOrders")
    public ResponseEntityDTO<List<CarOrder>> getPageCarOrders(Integer page ,Integer limit) {
        PageHelper.startPage(page, limit);
        List<CarOrder> carOrders = carOrderServices.selectAll();
        return ResponseEntityDTO.success(carOrders);
    }
}