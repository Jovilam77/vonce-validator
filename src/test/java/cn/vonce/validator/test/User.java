package cn.vonce.validator.test;

import cn.vonce.validator.annotation.*;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Jovi
 * @version 1.0
 * @email imjovi@qq.com
 * @date 2020/1/19 15:57
 */
public class User {

    @VMinValue(val = 1, value = "id最小从1开始", group = {"user.add", "user.update"})
    private Integer id;

    @VNotEmpty("名字不能为空")
    @VChinese("名字必须为中文")
    private String name;

    @VNotEmpty("身份证不能为空")
    private String idCard;

    @VConsist(val = {"99", "18", "22"})
    @VRangeValue(min = 1, max = 120, value = "年龄范围为1-120")
    private Integer age;

    //    @VBoolean(val = VBoolean.BoolValue.FALSE)
    @VConsist(val = {"false2"})
    private boolean vip;

    @VBoolean(val = VBoolean.BoolValue.NORMAL, value = "vip2必须为boolean类型")
    private String vip2;

    @VEmail
    private String email;

    //    @VEqualTo(val = "imjovi@qq.com")
    @VConsist(val = {"imjovi@qq.com", "766255988@qq.com"})
    private String email2;

    @VNumber(val = VNumber.NumType.INTEGER)
    @VMaxLength(val = 11)
    @VPhone
    private String phone;

    @VNotNull
    private BigDecimal cashPledge;

    @VNotEmpty
    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public String getVip2() {
        return vip2;
    }

    public void setVip2(String vip2) {
        this.vip2 = vip2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail2() {
        return email2;
    }

    public void setEmail2(String email2) {
        this.email2 = email2;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public BigDecimal getCashPledge() {
        return cashPledge;
    }

    public void setCashPledge(BigDecimal cashPledge) {
        this.cashPledge = cashPledge;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
