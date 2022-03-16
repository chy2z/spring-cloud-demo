package org.chy.carorder.util;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author admin
 */
@Service
public class TransactionHelper {
    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public <R> R withTransaction(Functions.Func0<R> func) {
        return func.apply();
    }

    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public <T, R> R withTransaction(Functions.Func1<T, R> func, T t) {
        return func.apply(t);
    }

    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public <T1, T2, R> R withTransaction(Functions.Func2<T1, T2, R> func, T1 t1, T2 t2) {
        return func.apply(t1, t2);
    }

    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public <T1, T2, T3, R> R withTransaction(Functions.Func3<T1, T2, T3, R> func, T1 t1, T2 t2, T3 t3) {
        return func.apply(t1, t2, t3);
    }


    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public <T1, T2, T3, T4, R> R withTransaction(Functions.Func4<T1, T2, T3, T4, R> func, T1 t1, T2 t2, T3 t3, T4 t4) {
        return func.apply(t1, t2, t3, t4);
    }

    @Transactional(rollbackFor = Exception.class, timeout = 30)
    public <T1, T2, T3, T4, T5, R> R withTransaction(Functions.Func5<T1, T2, T3, T4, T5, R> func, T1 t1, T2 t2, T3 t3, T4 t4, T5 t5) {
        return func.apply(t1, t2, t3, t4, t5);
    }

    public static void main(String[] args) {
        TransactionHelper transactionHelper = new TransactionHelper();
        // 无参数
        transactionHelper.withTransaction(() -> {
            return 1;
        });
        // 1个参数
        transactionHelper.withTransaction((parm) -> {
            return 1;
        }, "1");
        // 2个参数
        transactionHelper.withTransaction((parm, parm2) -> {
            return Boolean.TRUE;
        }, "1", "2");
    }
}
