package org.chy.carorder.service.impl;

import org.chy.carorder.constant.SubmitTypeEnum;
import org.chy.carorder.dao.CarOrderDao;
import org.chy.carorder.dto.req.CarOrderSubmitReqDto;
import org.chy.carorder.entity.CarOrderEntity;
import org.chy.carorder.service.CarOrderSubmitServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * @author chy
 * @Title: 测试声明式事务
 * @Description:
 * @date 2022/7/2 22:49
 */
@Service
public class CarOrderSelfSubmitServices implements CarOrderSubmitServices {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarOrderSelfSubmitServices.class);

    @Autowired
    private CarOrderDao carOrderDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = {Exception.class,RuntimeException.class})
    public Integer submit(CarOrderSubmitReqDto reqDto) {
        CarOrderEntity carOrderEntity = new CarOrderEntity();
        carOrderEntity.setOrderNo(reqDto.getOrderNo());
        carOrderEntity.setCarNo(reqDto.getCarNo());

        // 执行事务提交后置操作
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization() {
            @Override
            public void beforeCommit(boolean readOnly) {
                LOGGER.info("事务提交前......");
            }

            @Override
            public void afterCommit() {
                LOGGER.info("事务提交后......");
            }

            /**
             * 不管事务成功还是失败，当事务完成后就会执行
             * @param status
             */
            @Override
            public void afterCompletion(int status) {
                LOGGER.info("事务执行完成......");
            }
        });

        carOrderDao.add(carOrderEntity);

        Integer id = carOrderEntity.getId();

        this.updateStock(carOrderEntity, 1);

        return id;
    }

    @Override
    public void updateStock(CarOrderEntity carOrderEntity, Integer stockCount) {
        carOrderDao.updateStockById(carOrderEntity.getId(),stockCount);
    }

    @Override
    public SubmitTypeEnum getType() {
        return SubmitTypeEnum.SELF;
    }
}
