package cn.vonce.validator.model;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 14:39
 */
public class FieldResult {

    public FieldResult() {
    }

    public FieldResult(boolean pass, String name) {
        this.pass = pass;
        this.name = name;
    }

    public FieldResult(String name, String tips, String error) {
        this.name = name;
        this.tips = tips;
        this.error = error;
    }

    private boolean pass;
    private String name;
    private String tips;
    private String error;

    public boolean getPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @Override
    public String toString() {
        return "FieldResult{" +
                "pass=" + pass +
                ", name='" + name + '\'' +
                ", tips='" + tips + '\'' +
                ", error='" + error + '\'' +
                '}';
    }

}
