package org.chy.carorder.service.factory;

import org.chy.carorder.constant.SubmitTypeEnum;
import org.chy.carorder.service.CarOrderSubmitServices;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chy
 * @Title: 提交订单工厂策略模式
 * @Description:
 * @date 2022/7/2 22:38
 */
@Component
public class CarOrderSubmitFactory implements ApplicationContextAware {
    /**
     * 本地工厂缓存
     */
    private Map<SubmitTypeEnum, CarOrderSubmitServices> carOrderSubmitFactory = new HashMap<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, CarOrderSubmitServices> carOrderSubmitServicesMap =  applicationContext.getBeansOfType(CarOrderSubmitServices.class);
        carOrderSubmitServicesMap.values().forEach(carOrderSubmitServices -> {
            carOrderSubmitFactory.put(carOrderSubmitServices.getType(),carOrderSubmitServices);
        });
    }

    /**
     * 根据类型获取对应的bean
     * @param type
     * @return
     */
    public CarOrderSubmitServices getBean(SubmitTypeEnum type) {
        return carOrderSubmitFactory.get(type);
    }
}