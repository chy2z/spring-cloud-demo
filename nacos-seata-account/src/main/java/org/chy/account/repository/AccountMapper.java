package org.chy.account.repository;

import org.apache.ibatis.annotations.Param;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.chy.account.model.AccountEntity;

/**
 * @author chy
 * @Title: 账户
 * @Description:
 * @date 2022/12/2 22:36
 */
public interface AccountMapper extends BaseMapper<AccountEntity> {
    AccountEntity selectByUserId(@Param("userId") String userId);

    int reduce(AccountEntity record);
}