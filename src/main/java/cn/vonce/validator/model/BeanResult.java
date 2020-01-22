package cn.vonce.validator.model;

import java.util.List;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/20 9:49
 */
public class BeanResult {

    public BeanResult() {
    }

    public BeanResult(boolean pass, String message) {
        this.pass = pass;
        this.message = message;
    }

    public BeanResult(String message) {
        this.message = message;
    }

    public BeanResult(String message, List<FieldResult> fieldResultList) {
        this.message = message;
        this.fieldResultList = fieldResultList;
    }

    private boolean pass;
    private String message;
    private List<FieldResult> fieldResultList;

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<FieldResult> getFieldResultList() {
        return fieldResultList;
    }

    public void setFieldResultList(List<FieldResult> fieldResultList) {
        this.fieldResultList = fieldResultList;
    }

    @Override
    public String toString() {
        return "BeanResult{" +
                "pass=" + pass +
                ", message='" + message + '\'' +
                ", fieldResultList=" + fieldResultList +
                '}';
    }
}
