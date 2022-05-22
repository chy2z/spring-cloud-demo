package org.chy.carorder.service.impl;

import org.chy.carorder.rules.util.KieUtils;
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

    @Override
    public void reload(String drlName) throws Exception {
        KieFileSystem kfs = KieUtils.getKieServices().newKieFileSystem();
        //loadDBRules(drlName, kfs);
        loadFileRules(drlName, kfs);
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
     * 从数据库加载规则
     * @param drlName
     * @param kfs
     */
    private void loadDBRules(String drlName, KieFileSystem kfs) {
        // "src/main/resources/rules/address.drl";
        String path = "src/main/resources/"+ KieUtils.RULES_PATH + "/"+ drlName + ".drl";
        // 模拟从数据库加载的规则
        kfs.write(path, "package plausibcheck.adress\n\n import com.neo.drools.model.Address;\n import com.neo.drools.model.fact.AddressCheckResult;\n\n rule \"Postcode 6 numbers\"\n\n    when\n  then\n        System.out.println(\"打印日志：更新rules成功!\");\n end");
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