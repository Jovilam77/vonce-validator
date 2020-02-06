package cn.vonce.validator.test;

import cn.vonce.validator.helper.ValidatorHelper;
import cn.vonce.validator.model.BeanResult;
import cn.vonce.validator.model.FieldResult;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 16:00
 */
public class Test {

    public static void main(String[] agrs) {
        testUser();
    }

    public static void testUser() {
        User user = new User();
        user.setId(18);
        user.setName("Jovi123");
        user.setIdCard("12300");
        user.setAge(120);
        user.setVip(false);
        user.setVip2("123");
        user.setEmail("imjovi@qq.com");
        user.setEmail2("imjovi@qq.com");
        user.setPhone("14168000000");

        BeanResult beanResult = ValidatorHelper.validBean(user, "", false);
        if (!beanResult.isPass()) {
            for (FieldResult fieldResult : beanResult.getFieldResultList()) {
                System.out.println("返回提示：" + fieldResult.getTips() + "，错误原因：" + fieldResult.getName() + fieldResult.getError());
            }
        } else {
            System.out.println("恭喜！没有错误！");
        }

    }


}
