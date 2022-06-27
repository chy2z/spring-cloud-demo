package org.chy.carorder.controller;

import org.chy.carorder.bo.CouponsRuleBo;
import org.chy.carorder.bo.OrderRuleBo;
import org.chy.carorder.entity.response.ResponseEntityDTO;
import org.chy.carorder.rules.drools.util.KieUtils;
import org.chy.carorder.service.OrderRulesReloadServices;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.KieSession;
import org.kie.internal.utils.KieHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chy on 2022/5/22.
 */
@RestController
@RequestMapping("/drools/rule/engine/")
public class DroolsRuleEngineController {
    private final static Logger LOGGER = LoggerFactory.getLogger(DroolsRuleEngineController.class);

    @Autowired
    OrderRulesReloadServices rulesReloadServices;

    /**
     * 测试订单规则
     * http://localhost:9090/drools/rule/engine/order/demo?count=100&type=租车
     *
     * @return
     */
    @GetMapping("order/demo")
    public ResponseEntityDTO<Boolean> orderRule(Long count , String type) {
        // 拿到kiesession
        KieSession kieSession = KieUtils.getKieBase().newKieSession();
        // 准备要验证的数据
        OrderRuleBo data = new OrderRuleBo(count,type);
        // 将准备好的数据写入kiesession
        kieSession.insert(data);
        // 验证所有规则
        kieSession.fireAllRules();
        // 清除kiesession
        kieSession.dispose();
        return ResponseEntityDTO.success(true);
    }

    /**
     * 测试优惠券规则
     * http://localhost:9090/drools/rule/engine/coupons/demo?money=300&type=消费券
     *
     * @return
     */
    @GetMapping("coupons/demo")
    public ResponseEntityDTO<Boolean> couponsRule(Long money , String type) {
        // 拿到kiesession
        KieSession kieSession = KieUtils.getKieBase().newKieSession();
        // 准备要验证的数据
        CouponsRuleBo data = new CouponsRuleBo(money,type);
        // 将准备好的数据写入kiesession
        kieSession.insert(data);
        // 验证所有规则
        kieSession.fireAllRules();
        // 清除kiesession
        kieSession.dispose();
        return ResponseEntityDTO.success(true);
    }

    /**
     * 测试动态增加规则
     *
     * 问题：
     * 1. 这种方式增加的规则和自动spring装配的不是同一个规则引擎
     * 2. 这种方式可以用于每次更新全量规则，不适用增量更新规则
     *
     * http://localhost:9090/drools/rule/engine/order/rule/add
     *
     * @return
     */
    @GetMapping("order/rule/add")
    public ResponseEntityDTO<Boolean> orderRuleAdd() {
        String myRule =
                "package org.chy.rules\n" +
                        "import org.chy.carorder.bo.OrderRuleBo\n" +
                        "import org.chy.carorder.rules.drools.OrderRuleAction\n" +
                "\n" +
                "dialect  \"java\"\n" +
                "no-loop true \n" +
                "rule \"order-rule-3\"\n" +
                "\n" +
                "    when\n" +
                "        $o : OrderRuleBo(count>500 &&  count <= 1000 && $o.getType().equals(\"租车\"))\n" +
                "    then\n" +
                "        OrderRuleAction.doParse($o, drools.getRule());\n" +
                "        System.out.println(\"500<订单数量<=1000！\");\n" +
                "end\n";

        KieHelper helper = new KieHelper();
        helper.addContent(myRule, ResourceType.DRL);
        KieSession ksession = helper.build().newKieSession();
        OrderRuleBo data = new OrderRuleBo(600L,"租车");
        ksession.insert(data);
        ksession.fireAllRules();
        ksession.dispose();
        return ResponseEntityDTO.success(true);
    }

    /**
     * http://localhost:9090/drools/rule/engine/order/rule/reload
     * @return
     */
    @GetMapping("order/rule/reload")
    public ResponseEntityDTO<Boolean> orderRuleReload() {
        try {
            rulesReloadServices.reload(null);
        } catch (Exception e) {
            LOGGER.error("orderRuleReload",e);
        }
        return ResponseEntityDTO.success(true);
    }

}