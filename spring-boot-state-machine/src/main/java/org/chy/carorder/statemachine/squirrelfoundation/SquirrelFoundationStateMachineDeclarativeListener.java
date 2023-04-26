package org.chy.carorder.statemachine.squirrelfoundation;

import org.chy.carorder.statemachine.MyOrderStateEnum;
import org.chy.carorder.statemachine.MyOrderStateEventEnum;
import org.chy.carorder.statemachine.MyStateMachineContext;
import org.squirrelframework.foundation.exception.TransitionException;
import org.squirrelframework.foundation.fsm.Action;
import org.squirrelframework.foundation.fsm.annotation.ListenerOrder;
import org.squirrelframework.foundation.fsm.annotation.OnActionExecException;
import org.squirrelframework.foundation.fsm.annotation.OnAfterActionExecuted;
import org.squirrelframework.foundation.fsm.annotation.OnBeforeActionExecuted;
import org.squirrelframework.foundation.fsm.annotation.OnTransitionBegin;
import org.squirrelframework.foundation.fsm.annotation.OnTransitionComplete;
import org.squirrelframework.foundation.fsm.annotation.OnTransitionDecline;
import org.squirrelframework.foundation.fsm.annotation.OnTransitionEnd;

/**
 * @author chy
 * @Title:
 * @Description:
 * @date 2023/4/26 9:31
 */
public class SquirrelFoundationStateMachineDeclarativeListener {

  @OnTransitionBegin
  public void transitionBegin(MyOrderStateEventEnum event) {
    // method annotated with TransitionBegin will be invoked when transition begin...
    System.out.println("转换开始执行.." + event);
  }

  /**
   * 条件：context.del == 0 || event.name().equals("待确认到待进场")
   */
  @OnTransitionBegin(when = "context.del == 0 || event.name().equals(\"待确认到待进场\")")
  public void begins(MyOrderStateEnum from, MyOrderStateEnum to, MyOrderStateEventEnum event,
      MyStateMachineContext context) {
    System.out.println("begins 执行了， from：" + from + ", to:" + to + ", event:" + event + ", context:"
        + context.del);
  }

  @OnTransitionEnd
  @ListenerOrder(10)
  public void transitionEnd() {
    System.out.println("转换结束执行..");
  }

  @OnTransitionComplete
  public void transitionComplete(String from, String to, MyOrderStateEventEnum event, Integer context) {
    // method annotated with TransitionComplete will be invoked when transition complete...
    System.out.println("转换成功执行..");
  }

  @OnTransitionDecline
  public void transitionDeclined(String from, MyOrderStateEventEnum event, Integer context) {
    // method annotated with TransitionDecline will be invoked when transition declined...
    System.out.println("转换拒绝执行..");
  }

  @OnBeforeActionExecuted
  public void onBeforeActionExecuted(Object sourceState, Object targetState,
      Object event, Object context, int[] mOfN, Action<?, ?, ?, ?> action) {
    // method annotated with OnAfterActionExecuted will be invoked before action invoked
    System.out.println("状态机内方法动作执行之前......");
  }

  @OnAfterActionExecuted
  public void onAfterActionExecuted(Object sourceState, Object targetState,
      Object event, Object context, int[] mOfN, Action<?, ?, ?, ?> action) {
    // method annotated with OnAfterActionExecuted will be invoked after action invoked
    System.out.println("状态机内方法动作执行之后......");
  }

  @OnActionExecException
  public void onActionExecException(Action<?, ?, ?, ?> action, TransitionException e) {
    // method annotated with OnActionExecException will be invoked when action thrown exception
    System.out.println("转换异常执行。。");
  }
}
