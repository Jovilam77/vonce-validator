package cn.vonce.validator.test;


import cn.vonce.validator.annotation.VNotEmpty;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 16:26
 */
public class Service {

    @VNotEmpty
    private String id;

    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
