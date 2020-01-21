package cn.vonce.validator.model;

import java.util.List;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/20 9:49
 */
public class BeanResult {

    private boolean pass;
    private String error;

    private List<FieldResult> fieldResultList;

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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
                ", error='" + error + '\'' +
                ", fieldResultList=" + fieldResultList +
                '}';
    }
}
