package org.chy.carorder.expressionengine;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mvel2.MVEL;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.mvel2.templates.TemplateRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2023/4/26 10:39
 */
public class MvelApp {

  private final static Logger LOGGER = LoggerFactory.getLogger(MvelApp.class);

  public static void main(String[] args) {
    test4();
  }

  private static void test1() {
    String template = "Hello, my name is @{name.toUpperCase()}";
    Map vars = new HashMap();
    vars.put("name", "大雁");
    String output = (String) TemplateRuntime.eval(template, vars);
    LOGGER.info("mvel result:{}", output);
  }

  private static void test2() {
    String template = "1 + 1 = @{1+1}";

    // 编译模板
    CompiledTemplate compiled = TemplateCompiler.compileTemplate(template);

    // 执行模板
    String output = (String) TemplateRuntime.execute(compiled);

    LOGGER.info("mvel result:{}", output);
  }

  private static void test3() {
    String template = "@if{foo != bar}\n"
        + "   Foo not a bar!\n"
        + "@else{bar != cat}\n"
        + "   Bar is not a cat!\n"
        + "@else{}\n"
        + "   Foo may be a Bar or a Cat!\n"
        + "@end{}";
    Map vars = new HashMap();
    vars.put("foo", "10");
    vars.put("bar", "10");
    vars.put("cat", "10");
    String output = (String) TemplateRuntime.eval(template, vars);
    LOGGER.info("mvel result:{}", output);
  }

  private static void test4() {
    String expression = "['Jim','Bob','Tom']";
    List<String> l = (List<String>) MVEL.eval(expression);
    for (String str : l) {
      System.out.println(str);
    }

    System.out.println("-----------------");

    expression = "{'Jim','Bob','Tom'}";
    Object str = MVEL.eval(expression);
    String[] newarray = null;
    if (str.getClass().isArray()) {
      System.out.println(String.valueOf(Array.get(str, 0)));
    }

    System.out.println("-----------------");

    expression = "['Bob' : 'TF10001', 'Michael' : 'TF10002']";
    Map o = (Map) MVEL.eval(expression);
    String t = (String) o.get("Bob");
    System.out.println(t);

    System.out.println("-----------------");

    expression = "['Bob' : new org.chy.carorder.expressionengine.MvelPO('bob')  , 'Michael' : new org.chy.carorder.expressionengine.MvelPO('michael')]";
    Map oo = (Map) MVEL.eval(expression);
    MvelPO tt = (MvelPO) oo.get("Bob");
    System.out.println(tt.getName());

    System.out.println("-----------------");
  }

}