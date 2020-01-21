package cn.vonce.validator.model;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/21 17:28
 */
public class FieldInfo {

    private String name;
    private String tips;
    private Object value;
    private Object bean;
    private boolean onlyWhenNotEmpty;

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

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public Object getBean() {
        return bean;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public boolean getOnlyWhenNotEmpty() {
        return onlyWhenNotEmpty;
    }

    public void setOnlyWhenNotEmpty(boolean onlyWhenNotEmpty) {
        this.onlyWhenNotEmpty = onlyWhenNotEmpty;
    }

    @Override
    public String toString() {
        return "FieldInfo{" +
                "name='" + name + '\'' +
                ", tips='" + tips + '\'' +
                ", value=" + value +
                ", bean=" + bean +
                ", onlyWhenNotEmpty=" + onlyWhenNotEmpty +
                '}';
    }
}
