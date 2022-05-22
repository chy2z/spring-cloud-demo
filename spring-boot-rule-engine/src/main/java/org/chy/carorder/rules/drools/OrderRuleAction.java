package org.chy.carorder.rules.drools;

import org.chy.carorder.bo.OrderRuleBo;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chy on 2022/5/22.
 */
public class OrderRuleAction {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRuleAction.class);

    public static void doParse(OrderRuleBo data, RuleImpl rule) {
        LOGGER.info("{} is matched Rule[{}]!", data, rule.getName());
    }
}
