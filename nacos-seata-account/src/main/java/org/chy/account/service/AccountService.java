/*
 *  Copyright 1999-2021 Seata.io Group.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.chy.account.service;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import org.chy.account.model.AccountEntity;
import org.chy.account.repository.AccountMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class AccountService {
    private final static Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
    private static final String ERROR_USER_ID = "1002";

    @Autowired
    private AccountMapper accountMapper;


    /**
     * 减少金额
     *
     * @param userId 用户id
     * @param money 用户金额
     * @return 结果
     */
    @GlobalTransactional(rollbackFor = Exception.class)
    @Transactional(rollbackFor = Exception.class)
    public Boolean reduce(String userId, BigDecimal money) {
        LOGGER.info("account-service------->中扣减金额开始,全局事务id:{}", RootContext.getXID());
        AccountEntity account = accountMapper.selectByUserId(userId);
        account.setMoney(account.getMoney().subtract(money));
        accountMapper.reduce(account);
        if (ERROR_USER_ID.equals(userId)) {
            throw new RuntimeException("account branch exception");
        }
        LOGGER.info("account-service------->中扣减金额结束,全局事务id:{}", RootContext.getXID());
        return true;
    }
}