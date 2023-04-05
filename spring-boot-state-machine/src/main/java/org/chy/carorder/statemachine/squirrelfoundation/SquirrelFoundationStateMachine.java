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
}