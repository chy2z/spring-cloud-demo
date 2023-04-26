package org.chy.carorder.controller;

import org.chy.carorder.dto.req.CarOderSearchReqDto;
import org.chy.carorder.dto.resp.CarOderSearchRespDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.service.CarOrderServices;
import org.chy.carorder.statemachine.MyOrderStateEnum;
import org.chy.carorder.statemachine.MyOrderStateEventEnum;
import org.chy.carorder.statemachine.MyStateMachineContext;
import org.chy.carorder.statemachine.squirrelfoundation.SquirrelFoundationStateMachine;
import org.chy.carorder.statemachine.squirrelfoundation.SquirrelFoundationStateMachineDeclarativeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.squirrelframework.foundation.fsm.StateMachineBuilder;
import org.squirrelframework.foundation.fsm.StateMachineBuilderFactory;

import java.util.List;

/**
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
    public ResponseEntityDTO<List<CarOrderEntity>> getPageCarOrders(Long page, Long limit) {
        List<CarOrderEntity> carOrderEntities = carOrderServices.selectPage(page, limit);
        return ResponseEntityDTO.success(carOrderEntities);
    }

    /**
     * 测试获取配置
     * http://localhost:8080/car/order/search
     *
     * @return
     */
    @PostMapping("search")
    public ResponseEntityDTO<CarOderSearchRespDto> search(@RequestBody CarOderSearchReqDto reqDto) {
        CarOderSearchRespDto carOrderEntities = carOrderServices.search(reqDto);
        return ResponseEntityDTO.success(carOrderEntities);
    }

    /**
     * http://localhost:9090/car/order/changeState
     * @return
     */
    @GetMapping("changeState")
    public ResponseEntityDTO<Boolean> changeState() {
        StateMachineBuilder builder =
            StateMachineBuilderFactory
                .create(SquirrelFoundationStateMachine.class, MyOrderStateEnum.class,
                    MyOrderStateEventEnum.class, MyStateMachineContext.class);

        /**
         * 条件为：
         *
         * 当触发事件类型为MyOrderStateEventEnum.FROM_10_TO_20时
         * 并且content.getDel为0的时候转换状态，并执行defaultHandle()方法
         */
        builder.externalTransition().from(MyOrderStateEnum.WAIT_CONFIRMING)
            .to(MyOrderStateEnum.WAIT_ENTERING).on(MyOrderStateEventEnum.FROM_10_TO_20)
            .whenMvel(
                "myCondition:::(context!=null && context.getDel()!=null && context.getDel().equals(0))")
            .callMethod("defaultHandle");

        // 创建状态机
        SquirrelFoundationStateMachine machine = (SquirrelFoundationStateMachine) builder
            .newStateMachine(MyOrderStateEnum.WAIT_CONFIRMING);
        machine.addDeclarativeListener(new SquirrelFoundationStateMachineDeclarativeListener());
        machine.start();

        System.out.println("currentState is " + machine.getCurrentState());
        MyStateMachineContext context = new MyStateMachineContext();
        context.setDel(0);
        // 触发状态事件
        machine.fire(MyOrderStateEventEnum.FROM_10_TO_20, context);
        System.out.println("currentState is " + machine.getCurrentState());
        return ResponseEntityDTO.success(true);
    }
}