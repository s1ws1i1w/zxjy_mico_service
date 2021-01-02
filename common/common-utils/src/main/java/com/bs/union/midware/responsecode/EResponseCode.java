package com.bs.union.midware.responsecode;

/**
 * 响应枚举，包括响应码和响应信息
 * 00000 ： 成功
 * 11111 ： 失败
 * 以上两者为默认存在的，其余错误码按以下规则进行定义：
 * 1）位数5位
 * 2）开始1位为业务范畴，中间两位为服务范畴，后两位为具体错误种类
 * 3）不好定义的，可以嵌入相似业务范畴，但服务范畴需区分
 * 已定义好的范畴：
 * 9开头：系统级别错误
 * 8开头：订单服务错误
 * 7开头：钱包服务错误
 * 6开头：用户服务错误
 * 5开头：点餐服务错误
 * 3开头：第三方服务错误
 * <p>
 * 响应枚举命名：业务名称(*)_服务类型(?)_错误信息(*)
 * *为必填，？为可选
 * 尽量采取英语，减少拼音缩写。
 *
 * @author Godzilla
 * @create 2018-11-02 17:39
 */
public enum EResponseCode implements ResponseCode {
    SUCCESS("200","登录成功"),
    FALI("404","登录失败");
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String describe;

    EResponseCode(String value, String describe) {
        this.code = value;
        this.describe = describe;
    }
    public String getCode() {
         return code;
    }
    public String getDescribe() {
        return describe;
    }
}
