package org.chy.account.response;

import java.io.Serializable;
import java.util.Objects;

/**
 * 统一响应体
 * Created by chy on 2021/11/16.
 */
public class ResponseEntityDTO<T> implements Serializable {
    private static final long serialVersionUID = 3035640217960587862L;
    // 返回数据
    private T data;
    private String message;
    private Integer errCode = 0;
    // 耗时时间
    private Long cost;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getErrCode() {
        return this.errCode;
    }

    public void setErrCode(Integer errCode) {
        this.errCode = errCode;
    }

    public ResponseEntityDTO() {
    }

    public static <T> boolean succeed(ResponseEntityDTO<T> entity, boolean checkData) {
        return Objects.isNull(entity) ? false : entity.succeed(checkData);
    }

    public static <T> boolean succeed(ResponseEntityDTO<T> entity) {
        return succeed(entity, false);
    }

    public boolean succeed() {
        return this.succeed(false);
    }

    public boolean succeed(boolean checkData) {
        if (Objects.isNull(this.errCode)) {
            return false;
        } else {
            boolean result = this.errCode == 0;
            if (!result) {
                return false;
            } else if (checkData) {
                return !Objects.isNull(this.data);
            } else {
                return true;
            }
        }
    }

    public static <T> ResponseEntityDTO<T> success() {
        ResponseEntityDTO responseEntity = new ResponseEntityDTO();
        return responseEntity;
    }

    public static <T> ResponseEntityDTO<T> success(T data) {
        ResponseEntityDTO responseEntity = new ResponseEntityDTO();
        responseEntity.setData(data);
        return responseEntity;
    }

    public static <T> ResponseEntityDTO<T> success(Integer code, String message) {
        ResponseEntityDTO responseEntity = new ResponseEntityDTO();
        responseEntity.setErrCode(code);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    public static <T> ResponseEntityDTO<T> failure(Integer errCode, String message) {
        ResponseEntityDTO responseEntity = new ResponseEntityDTO();
        responseEntity.setErrCode(errCode);
        responseEntity.setMessage(message);
        return responseEntity;
    }

    public static <T> ResponseEntityDTO<T> failure(ResponseCode code) {
        ResponseEntityDTO responseEntity = new ResponseEntityDTO();
        responseEntity.setResultCode(code);
        return responseEntity;
    }

    public static <T> ResponseEntityDTO<T> failure(ResponseCode code, T data) {
        ResponseEntityDTO responseEntity = new ResponseEntityDTO();
        responseEntity.setResultCode(code);
        responseEntity.setData(data);
        return responseEntity;
    }

    public void setResultCode(ResponseCode code) {
        this.errCode = code.getCode();
        this.message = code.getMessage();
    }

    public Long getCost() {
        return this.cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }
}
