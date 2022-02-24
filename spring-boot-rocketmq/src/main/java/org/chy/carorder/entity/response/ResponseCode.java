package org.chy.carorder.entity.response;

/**
 * Created by chy on 2021/11/16.
 */
public abstract class ResponseCode {
    public static final ResponseCode SUCCEED;
    public static final ResponseCode FAILED;
    private Integer code;
    private String template;
    private String message;
    private static final Integer MIN_PREFIX_CODE = 1000;
    private static final Integer MAX_PREFIX_CODE = 10000;
    private static final Integer MIN_CODE = 100;
    private static final Integer MAX_CODE = 1000;

    public ResponseCode() {
    }

    public abstract Integer prefixCode();

    public Boolean isSucceed() {
        return SUCCEED.code.equals(this.code);
    }

    public Integer getCode() {
        if (this.code == null) {
            throw new IllegalArgumentException("illegal Code");
        } else {
            boolean check = this.code != 0 && this.code != 1;
            if (check) {
                boolean invalidPrefixCode = this.prefixCode() == null || this.prefixCode() > MAX_PREFIX_CODE || this.prefixCode() < MIN_PREFIX_CODE;
                if (invalidPrefixCode) {
                    throw new IllegalArgumentException("illegal prefixCode");
                }

                boolean invalidCode = this.code > MAX_CODE || this.code < MIN_CODE;
                if (invalidCode) {
                    throw new IllegalArgumentException("illegal Code");
                }
            }

            return this.prefixCode() * 1000 + this.code;
        }
    }

    public String getMessage() {
        return this.message;
    }

    public ResponseCode(Integer code, String template) {
        this.code = code;
        this.template = template;
        this.message = template;
    }

    public synchronized ResponseCode setArgs(String... args) {
        this.message = String.format(this.template, args);
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    static {
        SUCCEED = ResponseCode.$.SUCCEED;
        FAILED = ResponseCode.$.FAILED;
    }

    public static final class $ extends ResponseCode {
        public static ResponseCode SUCCEED = new ResponseCode.$(0, "成功");
        public static ResponseCode FAILED = new ResponseCode.$(1, "失败");

        public Integer prefixCode() {
            return 0;
        }

        $(Integer code, String template) {
            super(code, template);
        }
    }
}