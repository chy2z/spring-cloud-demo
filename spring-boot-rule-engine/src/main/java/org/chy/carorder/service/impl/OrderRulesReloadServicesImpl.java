package org.chy.carorder.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.chy.carorder.rules.drools.util.KieUtils;
import org.chy.carorder.service.OrderRulesReloadServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.IOException;


/**
 * Created by chy on 2022/5/22.
 */

@Service
public class OrderRulesReloadServicesImpl implements OrderRulesReloadServices {
    private final static Logger LOGGER = LoggerFactory.getLogger(OrderRulesReloadServicesImpl.class);

    /**
     * 清理原来所有规则，重新写入新规则
     * @param drlName
     * @throws Exception
     */
    @Override
    public void reload(String drlName) throws Exception {
        KieFileSystem kfs = KieUtils.getKieServices().newKieFileSystem();
        loadDBRules(drlName, kfs);
        //loadFileRules(drlName, kfs);
        KieBuilder kieBuilder = KieUtils.getKieServices().newKieBuilder(kfs).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            System.out.println(results.getMessages());
            throw new IllegalStateException("### errors ###");
        }
        KieContainer container = KieUtils.getKieServices().newKieContainer(KieUtils.getKieServices().getRepository().getDefaultReleaseId());
        KieUtils.setKieContainer(container);
        LOGGER.info("新规则重载成功");
    }

    /**
     * 模拟从数据库加载规则
     * @param drlName
     * @param kfs
     */
    private void loadDBRules(String drlName, KieFileSystem kfs) {
        if (StringUtils.isBlank(drlName)) {
            drlName = "order";
        }
        // 模拟重写规则
        String path = "src/main/resources/" + KieUtils.RULES_PATH + "/" + drlName + ".drl";
        // 数据库内容
        String dbContent = "package org.chy.rules\n" +
                "import org.chy.carorder.bo.OrderRuleBo\n" +
                "import org.chy.carorder.rules.drools.OrderRuleAction\n" +
                "dialect  \"java\"\n" +
                "\n" +
                "rule \"order-rule-5\"\n" +
                "    no-loop true\n" +
                "    when\n" +
                "        $o : OrderRuleBo(count <= 1000 && $o.getType().equals(\"租车\"))\n" +
                "    then\n" +
                "         OrderRuleAction.doParse($o, drools.getRule());\n" +
                "         System.out.println(\"订单数量<=1000\");\n" +
                "end\n" +
                "\n" +
                "rule \"order-rule-6\"\n" +
                "    no-loop true\n" +
                "    when\n" +
                "        $o : OrderRuleBo(count>1000 &&  count <= 2000 && $o.getType().equals(\"租车\"))\n" +
                "    then\n" +
                "         OrderRuleAction.doParse($o, drools.getRule());\n" +
                "         System.out.println(\"1000<订单数量<=2000\");\n" +
                "end";
        // 模拟从数据库加载的规则
        kfs.write(path, dbContent);
    }

    /**
     * 从文件系统加载文件
     * @param drlName
     * @param kfs
     * @throws IOException
     */
    private void loadFileRules(String drlName, KieFileSystem kfs) throws IOException{
        for (Resource file : KieUtils.getRuleFiles(drlName)) {
            KieUtils.writeFileRules(kfs,file);
        }
    }
}