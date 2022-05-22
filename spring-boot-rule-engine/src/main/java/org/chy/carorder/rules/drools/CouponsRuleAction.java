package org.chy.carorder.rules.drools;

import org.chy.carorder.bo.CouponsRuleBo;
import org.drools.core.definitions.rule.impl.RuleImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chy on 2022/5/22.
 */
public class CouponsRuleAction {
    private final static Logger LOGGER = LoggerFactory.getLogger(CouponsRuleAction.class);

    public static void doParse(CouponsRuleBo data, RuleImpl rule) {
        LOGGER.info("{} is matched Rule[{}]!", data, rule.getName());
    }
}
