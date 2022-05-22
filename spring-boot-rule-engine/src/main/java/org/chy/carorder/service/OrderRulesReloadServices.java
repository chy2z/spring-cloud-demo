package org.chy.carorder.service;

/**
 * Created by chy on 2022/5/22.
 */
public interface OrderRulesReloadServices {
    /**
     * 重新加载文件
     * @param drlName
     * @throws Exception
     */
    void reload(String drlName) throws Exception;
}
