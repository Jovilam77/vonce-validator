package cn.vonce.validator.test;

import cn.vonce.validator.helper.ValidFieldHelper;
import cn.vonce.validator.model.FieldResult;
import java.util.List;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 16:00
 */
public class Test {

    public static void main(String[] agrs) {
        testUser();
//        testAnnotaion();
    }

    public static void testUser() {
        User user = new User();
        user.setId(18);
        user.setName("Jovi123");
        user.setIdCard("12300");
        user.setAge(120);
        user.setVip(false);
        user.setVip2("123");
        user.setEmail("766255988@qq.com");
        user.setEmail2("766255988@qq.com");
        user.setPhone("123456789111");

        List<FieldResult> fieldResultList = ValidFieldHelper.validBean(user, "", false);
        for (FieldResult fieldResult : fieldResultList) {
            System.out.println("返回提示：" + fieldResult.getTips() + "，错误原因：" + fieldResult.getName()  + fieldResult.getError());
        }
    }


}
