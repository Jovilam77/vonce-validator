## vonce-validator(字段验证器)
#### 介绍
###### 一款轻量级的字段验证助手，集成方式极其简单并具拓展性，使用它可以减少大量不必要的if判断，使你的业务代码更加简洁优雅。

###### 环境：JDK7+，(Spring MVC 4.1.2+ 或 Spring Boot 1x 或 Spring Boot 2x)

#### 即刻上手
###### 1：如何引入
    <dependency>
    	<groupId>cn.vonce</groupId>
    	<artifactId>vonce-validator</artifactId>
    	<version>0.9.1</version>
    </dependency>
###### 2：如何配置
```java
//方式一：默认查找所有Controller所在包自动配置，注解：@EnableValidatorAutoConfig
@EnableValidatorAutoConfig
@SpringBootApplication
public class DoerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoerApiApplication.class, args);
    }
}

//方式二：手动指定表达式，注解：@EnableValidatorAutoConfig
@EnableValidatorAutoConfig(expressions = {"execution(* cn.abc.api.controller..*.*(..))"})
@SpringBootApplication
public class DoerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(DoerApiApplication.class, args);
    }
}

//方式三：配置某个Controller，注解：@EnableValidator
@EnableValidator
@RequestMapping("test")
@RestController
public class TestController extends BaseController {

}
```
###### 3：如何使用

```java
//方式一：
@GetMapping("test")
public RS test(@VNotEmpty("姓名不能为空") String name, @VPhone("手机号码不正确") String phone, @VRangeValue(min = 1, max = 120, value = "年龄范围为1-120")Integer age) {
    return super.successHint("成功");
}

//方式二：
public class User {
    //value=错误提示，不写会有默认提示；group=分组验证配合@VBean注解使用，可以不分组，比如此处则为新增、更新时需要验证；
    @VMinValue(val = 1, value = "id最小从1开始",group = {"user.add","user.update"})
    private Integer id;

    @VChinese(value = "名字必须为中文",group = {"user.add"})
    private String name;

    @VNotEmpty(value = "身份证不能为空",group = {"user.add"})
    private String idCard;

    @VRangeValue(min = 1, max = 120, value = "年龄范围为1-120",group = {"user.add"})
    private Integer age;

    @VBoolean(val = VBoolean.BoolValue.FALSE,group = {"user.add"})
    private boolean vip;

    @VEmail(group = {"user.add"})
    private String email;
    
    //省略get set方法
}
@GetMapping("add")
public RS add(@VBean(group = "user.add") User user) {
    return super.successHint("成功");
}

//方式三：
public static void main(String[] agrs) {
    User user = new User();
    //user.set...
    //省略
    BeanResult beanResult = ValidatorHelper.validBean(user, "user.add", false);
    if (!beanResult.isPass()) {
        for (FieldResult fieldResult : beanResult.getFieldResultList()) {
            System.out.println("返回提示：" + fieldResult.getTips() + "，错误原因：" + fieldResult.getName() + fieldResult.getError());
        }
    } else {
        System.out.println("恭喜！没有错误！");
    }
}
```
###### 4：如何自定义验证注解
```java
//自定义一个注解：值必须等于1的验证注解：

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
@Validate(type = ValidateEqual1.class)//必须，且需指定规则实现
public @interface VEqual1 {

    /**
     * 字段名称
     * @return
     */
    String name() default "";//必须

    /**
     * 是否只有不为空的时候才校验
     * @return
     */
    boolean onlyWhenNotEmpty() default false;//看个人需求，如果需要则只支持String类型校验

    /**
     * 消息提示
     * @return
     */
    String value() default "";//必须

    /**
     * 分组校验
     * @return
     */
    String[] group() default "";//必须
    
    //看个人需求增加方法

}

//自定义一个规则实现：值必须等于1的验证注解的规则实现
public class ValidateEqual1 extends AbstractValidate<VEqual1> {
    @Override
    public WhatType[] type() {
        //指定该注解用于验证什么类型
        return new WhatType[]{WhatType.STRING_TYPE};
    }

    @Override
    public String getAnticipate(VEqual1 valid) {
        //返回你期望的结果（使用时如果没指定错误提示，那么这里返回的内容则为默认错误提示）
        return "只能是1";
    }

    @Override
    public boolean check(VEqual1 valid, FieldInfo fieldInfo) {
        //具体的验证规则
        if(fieldInfo.getValue().equals("1")){
            return true;
        }
        return false;
    }
}
```
###### 5：注解说明
```java
@Validate     //统一校验注解规范
```
参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------:  
 type  | 指定规则实现类 |  | 是
  
```java
@VBean        //校验bean
```
参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 group  | 分组检验 | "" |否
 interrupt  | 遇到错误是否继续校验 | true |否

```java
@VBoolean     //校验布尔类型，支持字符、布尔类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 val  | 必须是布尔值(包含true和false)或必须是true或必须是false | BoolValue.NORMAL |否
 group | 分组检验 | "" |否

```java
@VChinese     //校验中文，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 val  | 校验中文的类型 | ChineseType.IS_CHINESE_NOT_HAS_SYMBOL |否
 group | 分组检验 | "" |否

```java
@VConsist     //校验是否由指定值构成，支持字符、数值、布尔类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 val  | 指定值 |  |是
 group | 分组检验 | "" |否

```java
@VEmail       //校验邮箱格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否


```java
@VEqualTo     //校验该字段值是否与指定的值一致，支持字符、数值、布尔类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 val  | 指定的值 | "" | val和field二选一
 field  | 指定的字段名，优先级比val高(仅支持bean模式) | "" | val和field二选一
 group | 分组检验 | "" |否


```java
@VIDCard      //校验身份证号码格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VIPAddress   //校验IP地址格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VMaxLength   //校验设置的最大长度，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 val  | 字段最大长度值 |  |是
 group | 分组检验 | "" |否

```java
@VMaxValue    //校验设置的最大值，支持字符、数值类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 val  | 字段最大值 |  |是
 group | 分组检验 | "" |否

```java
@VMinLength   //校验设置的最小长度，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 val  | 字段最小长度值 |  |是
 group | 分组检验 | "" |否

```java
@VMinValue    //校验设置的最小值，支持字符、数值类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 val  | 字段最小值 |  |是
 group | 分组检验 | "" |否

```java
@VNotEmpty    //校验不能为empty，支持所有类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VNotNull     //校验不能为null，支持所有类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VNumber      //校验数字，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 val  | 数值类型或整数或浮点数 | VNumber.NumType.NUMBER |否
 group | 分组检验 | "" |否

```java
@VPassword    //校验密码格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VPhone       //校验手机或电话格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 phoneType  | 手机或电话 | VPhoneType.MOBILEPHONE |否
 group | 分组检验 | "" |否

```java
@VRangeLength //校验长度范围，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 max  | 字段最大长度值 |  |是
 min  | 字段最小长度值 |  |是
 group | 分组检验 | "" |否

```java
@VRangeValue  //校验值范围，支持字符、数值类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 max  | 字段最大值 |  |是
 min  | 字段最小值 |  |是
 group | 分组检验 | "" |否

```java
@VRegex       //校验是否符合正则表达式的规则，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 value  | 消息提示 | "" |否
 val  | 正则表达式 |  |是
 group | 分组检验 | "" |否

```java
@VSQLInject   //校验是否存在sql注入，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VUrl         //校验URl格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否

```java
@VUserName    //校验用户名格式，支持字符类型校验
```

参数  | 解释  | 默认 | 必须
 :----: | :-----: | :-----: | :------: 
 name  | 字段名 | "" | 否
 onlyWhenNotEmpty  | 不为空的时候才校验 | false | 否
 value  | 消息提示 | "" |否
 group | 分组检验 | "" |否