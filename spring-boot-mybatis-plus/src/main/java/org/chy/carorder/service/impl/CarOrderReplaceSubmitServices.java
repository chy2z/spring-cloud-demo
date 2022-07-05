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
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/7/2 22:50
 */
@Service
public class CarOrderReplaceSubmitServices implements CarOrderSubmitServices {
    private final static Logger LOGGER = LoggerFactory.getLogger(CarOrderReplaceSubmitServices.class);

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private CarOrderDao carOrderDao;

    @Override
    public Integer submit(CarOrderSubmitReqDto reqDto) {
        CarOrderEntity carOrderEntity = new CarOrderEntity();
        carOrderEntity.setOrderNo(reqDto.getOrderNo());
        carOrderEntity.setCarNo(reqDto.getCarNo());

        /**
         * 编程式事务不带返回值
         * 异常会自动回滚
        transactionTemplate.executeWithoutResult(t -> {
            // 执行数据库事务操作
            carOrderDao.add(carOrderEntity);
            this.updateStock(carOrderEntity, 1);
        });
        */

        /**
         * 编程式事务带返回值
         * 异常会自动回滚
         */
        Integer id = transactionTemplate.execute(transactionStatus -> {
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
            // 执行数据库事务操作
            carOrderDao.add(carOrderEntity);
            Integer tempId = carOrderEntity.getId();
            // 执行数据库更新操作
            this.updateStock(carOrderEntity, 1);
            return tempId;
        });

        return id;
    }

    @Override
    public void updateStock(CarOrderEntity carOrderEntity, Integer stockCount) {
        // 声明式事务注解事务依旧有效
        carOrderDao.updateStockByCarNo(carOrderEntity.getCarNo(), stockCount);
    }

    @Override
    public SubmitTypeEnum getType() {
        return SubmitTypeEnum.REPACE;
    }
}