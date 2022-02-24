package org.chy.carorder.mq.transaction;

import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.Message;

import java.util.HashMap;
import java.util.Map;


/**
 * 事物消息接收状态
 *
 * @author chy
 * @date 2022/2/20
 */
@RocketMQTransactionListener(txProducerGroup = "${my.rocketmq.txProducerGroup}")
public class MQTransactionListenerImpl implements RocketMQLocalTransactionListener {

    private final static Logger LOGGER = LoggerFactory.getLogger(MQTransactionListenerImpl.class);

    private static Map<String, RocketMQLocalTransactionState> STATE_MAP = new HashMap<>();


    /**
     * 执行业务逻辑
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        LOGGER.info("事物消息执行 -> transId ={}", transId);
        try {
            // 执行业务逻辑
            exeRun();
            // 设置事务状态
            STATE_MAP.put(transId, RocketMQLocalTransactionState.COMMIT);

            LOGGER.info("事物消息提交 -> transId ={}", transId);
            // 返回事务状态给生产者
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            LOGGER.error("executeLocalTransaction", e);
        }
        // 事物执行失败回查
        LOGGER.info("事物消息回查-> transId ={}", transId);
        STATE_MAP.put(transId, RocketMQLocalTransactionState.UNKNOWN);
        return RocketMQLocalTransactionState.UNKNOWN;
    }

    /**
     * 回查
     *
     * 执行不成功会周期一直回查
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        LOGGER.info("事物消息执行 -> transId ={}", transId);
        try {
            // 执行业务逻辑
            exeRun();
            // 设置事务状态
            STATE_MAP.put(transId, RocketMQLocalTransactionState.COMMIT);

            LOGGER.info("事物消息提交 -> transId ={}", transId);
            // 返回事务状态给生产者
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            LOGGER.error("executeLocalTransaction", e);
        }
        // 回查执行异常回滚
        LOGGER.info("事物消息回滚 -> transId ={}", transId);
        STATE_MAP.put(transId, RocketMQLocalTransactionState.ROLLBACK);
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    /**
     * 执行业务逻辑
     */
    private void exeRun() {
        return;
    }
}