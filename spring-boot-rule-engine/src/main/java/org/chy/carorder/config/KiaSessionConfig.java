package org.chy.carorder.config;

import org.springframework.context.annotation.Configuration;

/**
 * Created by chy on 2022/5/22.
 */
@Configuration
public class KiaSessionConfig {

    //public static final String RULES_PATH = "rules/";
    //public static final String BASE_RULES_PATH = "classpath*:";

    /*
    @Bean
    KieServices kieService() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieFileSystem kieFileSystem() throws IOException {
        KieFileSystem kieFileSystem = kieService().newKieFileSystem();
        for (Resource file : getRuleFiles(null)) {
            kieFileSystem.write(ResourceFactory.newClassPathResource(RULES_PATH + file.getFilename(), "UTF-8"));
        }
        return kieFileSystem;
    }
    */

//    public static Resource[] getRuleFiles(String drlName) throws IOException {
//        if (StringUtils.isEmpty(drlName)) {
//            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//            return resourcePatternResolver.getResources(BASE_RULES_PATH + RULES_PATH + "**/*.*");
//        } else {
//            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
//            return resourcePatternResolver.getResources(BASE_RULES_PATH + RULES_PATH + "**/" + drlName + ".*");
//        }
//    }

    /*
    @Bean
    public KieContainer kieContainer() throws IOException {
        final KieRepository kieRepository = kieService().getRepository();
        kieRepository.addKieModule(new KieModule() {
            @Override
            public ReleaseId getReleaseId() {
                return kieRepository.getDefaultReleaseId();
            }
        });

        KieBuilder kieBuilder = kieService().newKieBuilder(kieFileSystem());
        kieBuilder.buildAll();
        return kieService().newKieContainer(kieRepository.getDefaultReleaseId());
    }

    @Bean
    public KieBase kieBase() throws IOException {
        return kieContainer().getKieBase();
    }
    */
}