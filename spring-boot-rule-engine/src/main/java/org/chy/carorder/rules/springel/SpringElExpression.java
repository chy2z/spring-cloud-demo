package org.chy.carorder.rules.springel;

import org.chy.carorder.entity.CarOrderEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.*;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2022/6/26 22:14
 */
public class SpringElExpression {
    private final static Logger LOGGER = LoggerFactory.getLogger(SpringElExpression.class);
    public static void main(String[] args) {
        test7();
    }

    /**
     * 字符串的截取
     */
    private static void test1() {
        String exp = "\"123456\".substring(0,2)";
        try {
            ExpressionParser expressionParser = new SpelExpressionParser();// 指定spelExpressionParser解析器实现类
            Expression expression = expressionParser.parseExpression(exp);//解析表达式
            EvaluationContext evaluationContext = new StandardEvaluationContext(expression);//设置对象模型基础。
            Object result = expression.getValue(evaluationContext);
            LOGGER.info(result.toString());
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis", e);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis", e);
        }
    }

    /**
     * 参数动态化传入
     */
    private static void test2() {
        String template = "#text.substring(#start,#end)";
        try {
            ExpressionParser expressionParser = new SpelExpressionParser();// 指定spelExpressionParser解析器实现类
            Expression expression = expressionParser.parseExpression(template);//解析表达式
            EvaluationContext evaluationContext = new StandardEvaluationContext(expression);//设置对象模型基础。
            evaluationContext.setVariable("text","\"654321");
            evaluationContext.setVariable("start",0);
            evaluationContext.setVariable("end",3);
            Object result = expression.getValue(evaluationContext);
            LOGGER.info(result.toString());
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis", e);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis", e);
        }
    }

    /**
     * 参数动态化传入
     */
    private static void test3() {
        String template = "#model.orderNo";
        CarOrderEntity carOrderEntity=new CarOrderEntity();
        carOrderEntity.setOrderNo("100001");
        try {
            ExpressionParser expressionParser = new SpelExpressionParser();// 指定spelExpressionParser解析器实现类
            Expression expression = expressionParser.parseExpression(template);//解析表达式
            EvaluationContext evaluationContext = new StandardEvaluationContext(expression);//设置对象模型基础。
            evaluationContext.setVariable("model",carOrderEntity);
            Object result = expression.getValue(evaluationContext);
            LOGGER.info(result.toString());
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis", e);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis", e);
        }
    }

    /**
     * 套用模板方法解析器,参数动态化传入
     */
    private static void test3_2() {
        String template = "#{#model.orderNo}";
        CarOrderEntity carOrderEntity=new CarOrderEntity();
        carOrderEntity.setOrderNo("100001");
        try {
            ExpressionParser expressionParser = new SpelExpressionParser();// 指定spelExpressionParser解析器实现类
            Expression expression = expressionParser.parseExpression(template,new TemplateParserContext());//解析表达式
            EvaluationContext evaluationContext = new StandardEvaluationContext(expression);//设置对象模型基础。
            evaluationContext.setVariable("model",carOrderEntity);
            Object result = expression.getValue(evaluationContext);
            LOGGER.info(result.toString());
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis", e);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis", e);
        }
    }

    /**
     * 套用模板方法解析器
     */
    private static void test4(){
        //设置文字模板,其中#{}表示表达式的起止，#name是表达式字符串，表示引用一个变量。
        String template = "#{#name},早上好";

        ExpressionParser expressionParser = new SpelExpressionParser();//创建表达式解析器

        //解析表达式，如果表达式是一个模板表达式，需要为解析传入模板解析器上下文。
        Expression expression = expressionParser.parseExpression(template,new TemplateParserContext());

        //通过evaluationContext.setVariable可以在上下文中设定变量。
        EvaluationContext evaluationContext = new StandardEvaluationContext();
        evaluationContext.setVariable("name","chy");

        //使用Expression.getValue()获取表达式的值，这里传入了Evalution上下文，第二个参数是类型参数，表示返回值的类型。
        LOGGER.info(expression.getValue(evaluationContext,String.class));
    }

    /**
     * 动态获取类的属性值
     */
    private static void test5(){
        String propertyName="orderNo";
        CarOrderEntity carOrderEntity=new CarOrderEntity();
        carOrderEntity.setOrderNo("100001");
        try {
            ExpressionParser expressionParser = new SpelExpressionParser();
            EvaluationContext evaluationContext = new StandardEvaluationContext();
            evaluationContext.setVariable("model",carOrderEntity);
            Expression expression =expressionParser.parseExpression(String.format("#{#model.%s}",propertyName),new TemplateParserContext());
            String result=expression.getValue(evaluationContext,String.class);
            LOGGER.info(result);
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis the parameter is {}",propertyName);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis the parameter is {}",propertyName);
        }
    }

    /**
     * 动态执行条件语句
     */
    private static void test6(){
        String propertyId="id";
        CarOrderEntity carOrderEntity=new CarOrderEntity();
        carOrderEntity.setId(100L);
        try {
            ExpressionParser expressionParser = new SpelExpressionParser();
            EvaluationContext evaluationContext = new StandardEvaluationContext();
            evaluationContext.setVariable("model",carOrderEntity);
            evaluationContext.setVariable("conditions",99);
            Expression expression =expressionParser.parseExpression(String.format("#{#model.%s > #conditions }",propertyId),new TemplateParserContext());
            Boolean result=expression.getValue(evaluationContext,Boolean.class);
            LOGGER.info(result?"true":"false");
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis the parameter is {}",propertyId);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis the parameter is {}",propertyId);
        }
    }

    /**
     * 动态执行条件语句
     */
    private static void test7(){
        String propertyId="orderNo";
        CarOrderEntity carOrderEntity=new CarOrderEntity();
        carOrderEntity.setOrderNo("8888");
        try {
            ExpressionParser ApplicationContext  = new SpelExpressionParser();
            EvaluationContext evaluationContext = new StandardEvaluationContext();
            evaluationContext.setVariable("model",carOrderEntity);
            evaluationContext.setVariable("conditions","9999");
            Expression expression =expressionParser.parseExpression(String.format("#{#model.%s eq #conditions }",propertyId),new TemplateParserContext());
            Boolean result=expression.getValue(evaluationContext,Boolean.class);
            LOGGER.info(result?"true":"false");
        } catch (ParseException e) {
            LOGGER.error("ParseException analysis the parameter is {}",propertyId);
        } catch (EvaluationException e) {
            LOGGER.error("EvaluationException analysis the parameter is {}",propertyId);
        }
    }
}