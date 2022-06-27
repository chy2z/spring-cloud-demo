package org.chy.carorder.rules.drools.util;

import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 *
 * @author chy
 * @date 2022/5/22
 */
public class KieUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(KieUtils.class);

    public static final String BASE_RULES_PATH = "classpath*:";
    public static final String RULES_PATH = "rules/";

    static {
        try {
            initKieRepository();
            initKieContainer();
        } catch (IOException e) {
            LOGGER.error("kie init error", e);
        }
    }

    private static KieContainer kieContainer;

    public static void setKieContainer(KieContainer kieContainer) {
        KieUtils.kieContainer = kieContainer;
    }

    public static KieBase getKieBase() {
        return kieContainer.getKieBase();
    }

    public static KieServices getKieServices() {
        return KieServices.Factory.get();
    }

    public static KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = getKieServices().newKieFileSystem();
        for (Resource file : getRuleFiles(null)) {
            KieUtils.writeFileRules(kieFileSystem, file);
        }
        return kieFileSystem;
    }

    public static Resource[] getRuleFiles(String drlName) throws IOException {
        if (StringUtils.isEmpty(drlName)) {
            // 加载全部文件
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            return resourcePatternResolver.getResources(BASE_RULES_PATH + RULES_PATH + "**/*.*");
        } else {
            // 加载指定文件
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            return resourcePatternResolver.getResources(BASE_RULES_PATH + RULES_PATH + "**/" + drlName + ".*");
        }
    }

    public static void writeFileRules(KieFileSystem kieFileSystem,Resource file) throws IOException {
        LOGGER.info("kile file system load file:{}", file.getFile().getPath());
        kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
    }

    public static KieRepository initKieRepository()  {
        final KieRepository kieRepository = getKieServices().getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });
        LOGGER.info("kie repository default releaseId:{}", kieRepository.getDefaultReleaseId());
        return kieRepository;
    }

    public static KieContainer initKieContainer() throws IOException {
        KieRepository kieRepository = getKieServices().getRepository();
        KieBuilder kieBuilder = getKieServices().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        KieContainer container = getKieServices().newKieContainer(kieRepository.getDefaultReleaseId());
        KieUtils.setKieContainer(container);
        return container;
    }
}