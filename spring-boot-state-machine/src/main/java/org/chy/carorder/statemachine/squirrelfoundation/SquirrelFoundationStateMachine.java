package org.chy.carorder.statemachine.squirrelfoundation;

import com.alibaba.fastjson.JSON;
import org.chy.carorder.statemachine.MyOrderStateEnum;
import org.chy.carorder.statemachine.MyOrderStateEventEnum;
import org.chy.carorder.statemachine.MyStateMachineContext;
import org.squirrelframework.foundation.fsm.impl.AbstractStateMachine;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2023/4/5 22:48
 */
public class SquirrelFoundationStateMachine extends
    AbstractStateMachine<SquirrelFoundationStateMachine, MyOrderStateEnum, MyOrderStateEventEnum, MyStateMachineContext> {

  public void defaultHandle(MyOrderStateEnum from, MyOrderStateEnum to, MyOrderStateEventEnum event,
      MyStateMachineContext context) {
    System.out.println(
        "defaultHandle() 方法执行了。。。。。。。。。。。。。 from：" + from + ", to:" + to + ", event:" + event + ", context:"
            + JSON.toJSONString(context));
  }

  /**
   * 约定大于配置
   * like：
   * transitFrom[fromStateName]To[toStateName]On[eventName]When[conditionName]
   * transitFrom[fromStateName]To[toStateName]On[eventName]
   * transitFromAnyTo[toStateName]On[eventName]
   * transitFrom[fromStateName]ToAnyOn[eventName]
   * transitFrom[fromStateName]To[toStateName]
   * on[eventName]
   */
  protected void transitFromWAIT_CONFIRMINGToWAIT_ENTERING(MyOrderStateEnum from, MyOrderStateEnum to, MyOrderStateEventEnum event,
      MyStateMachineContext context){
    System.out.println("从WAIT_CONFIRMING--->WAIT_ENTERING执行....约定大于配置");
  }

  protected void onFROM_10_TO_20(MyOrderStateEnum from, MyOrderStateEnum to, MyOrderStateEventEnum event,
      MyStateMachineContext context){
    System.out.println("eventFROM_10_TO_20执行....约定大于配置");
  }

  /**
   * exitWAIT_CONFIRMING执行
   * 退出WAIT_CONFIRMING状态时执行
   */
  protected void exitWAIT_CONFIRMING(MyOrderStateEnum from, MyOrderStateEnum to, MyOrderStateEventEnum event,
      MyStateMachineContext context){
    System.out.println("exitWAIT_CONFIRMING()方法执行了。。。。。。。。");
  }
}