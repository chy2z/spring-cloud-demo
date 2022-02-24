package org.chy.carorder.controller;

import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.service.CarOrderServices;
import org.chy.carorder.sys.interceptor.ForbidDuplicateSubmit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @author chy
 * @date 2021/7/14
 */
@RestController
@RequestMapping("/car/order/")
public class CarOrderController {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarOrderController.class);

    @Autowired
    private CarOrderServices carOrderServices;

    /**
     * 测试获取配置
     * http://localhost:8080/car/order/get
     *
     * @return
     */
    @GetMapping("get")
    public ResponseEntityDTO<CarOrderEntity> getCarOrder() {
        return ResponseEntityDTO.success(carOrderServices.selectByPrimaryKey(1));
    }

    /**
     * 测试获取配置
     * http://localhost:9090/car/order/getAll
     *
     * @return
     */
    @GetMapping("getAll")
    public ResponseEntityDTO<List<CarOrderEntity>> getCarOrderAll() {
        List<CarOrderEntity> carOrderEntities = carOrderServices.selectAll();
        return ResponseEntityDTO.success(carOrderEntities);
    }

    /**
     * 测试获取配置
     * http://localhost:8080/car/order/getPage?page=2&limit=2
     *
     * @return
     */
    @GetMapping("getPage")
    public ResponseEntityDTO<List<CarOrderEntity>> getPageCarOrders(Long page , Long limit) {
        List<CarOrderEntity> carOrderEntities = carOrderServices.selectPage(page,limit);
        return ResponseEntityDTO.success(carOrderEntities);
    }

    /**
     * 测试获取配置
     * http://localhost:8080/car/order/search
     *
     * @return
     */
    @ForbidDuplicateSubmit
    @PostMapping("search")
    public ResponseEntityDTO<CarOderSearchRespDto> search(@RequestBody CarOderSearchReqDto reqDto) {
        CarOderSearchRespDto carOrderEntities = carOrderServices.search(reqDto);
        return ResponseEntityDTO.success(carOrderEntities);
    }
}