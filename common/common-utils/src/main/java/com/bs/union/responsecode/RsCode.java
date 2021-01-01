package com.bs.union.responsecode;

import lombok.AllArgsConstructor;
import lombok.Getter;


/**
 * 通用返回枚举
 *
 * @author gralves
 * @date 2020/6/5
 */
public class RsCode {

    /**
     * 中台三方服务返回枚举
     * 返回码 2600~2699
     *
     * @author gralves
     * @date 2020/6/5
     */
    @Getter
    @AllArgsConstructor
    public enum MidwareCloudRsCode implements BaseResponse {
        /**
         * 不支持的OCR类型
         */
        UNSUPPORTED_OCR_TYPE(2650, "不支持的OCR类型"),
        /**
         * 短信天级风控
         */
        SMS_DAILY_CONTROL(2652, "当日发送短信次数已达上限,请明天重试~"),
        /**
         * 无效的OCR识别请求
         */
        INVALID_OCR_REQUEST(2650, "无效的OCR识别请求"),
        /**
         * STS签名失败
         */
        STS_SIGN_EXCEPTION(2652, "STS签名失败"),
        /**
         * 无效的手机号码格式
         */
        INVALID_MOBILE_NO(2651, "无效手机号码格式"),
        /**
         * 发送过于频繁，请稍后再试
         */
        FREQUENCY(2652, "发送过于频繁，请稍后再试"),
        /**
         * 无效的手机号码格式
         */
        FAIL_RECOGNITION_BANK(2653, "无法自动识别，请手动选择开户银行"),
        ;

        private Integer code;

        private String msg;

        /**
         * 获取code
         *
         * @return code
         */
        @Override
        public Integer getCode() {
            return code;
        }

        /**
         * 获取返回信息
         *
         * @return msg
         */
        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum MidwareAuthenticationRsCode implements BaseResponse {
        /**
         * 用户不存在
         */
        ACCOUNT_NOT_EXIST(2506, "用户不存在"),
        /**
         * 该手机已绑定其他微信
         */
        BINDED_PHONE(2500, "当前手机号已被绑定"),
        /**
         * 该微信已绑定其他手机
         */
        BINDED_WX(2500, "该微信已绑定其他手机"),
        /**
         * 两次输入密码不一致
         */
        DIFFERENT_PWD(2504, "两次输入密码不一致"),
        /**
         * 用户已绑定手机
         */
        DUPLICATE_BINDING(2501, "用户已绑定手机"),
        /**
         * e省宝小程序登录失败
         */
        ESB_LOGIN_ERROR(2513, "e省宝服务登录失败,请返回app重试"),
        /**
         * 获取用户手机号异常,请稍候重试~
         */
        GET_USER_PHONE_INFO_FAIL(2513, "获取用户手机号异常,请稍候重试~"),
        /**
         * 非法删除部门请求
         */
        ILLEGAL_DELETE_DEPT(2510, "非法删除部门请求,该部门已经绑定了用户"),
        /**
         * 非法删除菜单请求
         */
        ILLEGAL_DELETE_MENU(2509, "非法删除菜单请求,该菜单已经绑定了用户或角色"),
        /**
         * 非法删除权限请求
         */
        ILLEGAL_DELETE_PERMISSION(2507, "非法删除权限请求,该权限已经绑定了用户或角色"),
        /**
         * 非法删除角色请求
         */
        ILLEGAL_DELETE_ROLE(2508, "非法删除权限请求,该角色已经绑定了用户"),
        /**
         * 非法注册用户请求
         */
        ILLEGAL_REGISTRY_REQUEST(2511, "非法注册用户请求"),
        /**
         * 重复绑定手机号
         */
        INVALID_BIND_REQUEST(2515, "重复绑定手机号"),
        /**
         * 无效的对接人登录请求
         */
        INVALID_PROMOTER_LOGIN_REQUEST(2511, "无效的对接人登录请求"),
        /**
         * 无效短信验证码
         */
        INVALID_SMS_CODE(2512, "无效的短信验证码"),
        /**
         * 还未绑定手机
         */
        NOT_BIND_PHONE(2503, "还未绑定手机"),
        /**
         * 还未绑定微信
         */
        NOT_BIND_WX(2502, "还未绑定微信"),
        /**
         * 密码错误
         */
        PWD_INVALID(2505, "密码错误"),
        /**
         * 验证码已失效
         */
        SMS_CODE_EXPIRED(2512, "验证码已失效"),
        /**
         * 验证码校验失败
         */
        SMS_CODE_INVALID(2504, "请输入正确的手机验证码"),
        /**
         * 用户已注销,禁止登录
         */
        USER_DELETED(2514, "用户已注销,禁止登录"),
        /**
         * e省宝商家端授权登录失败,请先关注e省宝商家公众号
         */
        ESB_UNION_LOGIN_ERROR(2515, "e省宝商家端授权登录失败,请先关注e省宝商家公众号"),
        /**
         * 无效的微信授权code
         */
        INVALID_WX_AUTH_CODE(2515, "无效微信授权信息"),
        /**
         * 该手机号尚未注册
         */
        PHONE_NOT_REGISTRY(2516, "该手机号尚未注册"),
        /**
         * 获取账号授权状态失败,请稍后重试
         */
        GET_AUTH_STATUS_FAIL(2517, "获取账号授权状态失败,请稍后重试"),
        /**
         * 获取账号授权地址失败,请稍后重试
         */
        GET_AUTH_DESTINATION_FAIL(2517, "获取账号授权地址失败,请稍后重试"),
        ;

        private Integer code;

        private String msg;

        /**
         * 获取code
         *
         * @return code
         */
        @Override
        public Integer getCode() {
            return code;
        }

        /**
         * 获取返回信息
         *
         * @return msg
         */
        @Override
        public String getMsg() {
            return msg;
        }
    }


    /**
     * 中台三方服务返回枚举
     * 返回码 2700~2799
     *
     * @author gralves
     * @date 2020/6/5
     */
    @Getter
    @AllArgsConstructor
    public enum MidwareGatewayRsCode implements BaseResponse {

        ;

        private Integer code;

        private String msg;

        /**
         * 获取code
         *
         * @return code
         */
        @Override
        public Integer getCode() {
            return code;
        }

        /**
         * 获取返回信息
         *
         * @return msg
         */
        @Override
        public String getMsg() {
            return msg;
        }
    }

    /**
     * 通用枚举返回
     * 返回码 600~999
     *
     * @author gralves
     * @date 2020/6/5
     */
    @Getter
    @AllArgsConstructor
    public enum CommonRsCode implements BaseResponse {
        /**
         * 权限校验异常
         */
        AUTHORIZATION_EXCEPTION(603, "权限校验异常,请重新登录获取授权"),
        /**
         * 权限不足
         */
        PERMISSION_INVALID(603, "权限不足,请联系管理员进行授权"),
        /**
         * 超时异常
         */
        CONNECTION_TIMEOUT_EXCEPTION(602, "请求超时,请稍候重试"),
        /**
         * 主键重复异常
         */
        DATABASE_DUPLICATE_KEY(601, "主键重复异常"),
        /**
         * 文件丢失异常
         */
        FILE_NOT_FOUND_EXCEPTION(607, "文件丢失"),
        /**
         * 分页参数异常
         */
        INVALID_PAGE_PARAM(600, "分页参数异常"),
        /**
         * 请求方法不支持
         */
        INVALID_REQUEST_TYPE(609, "请求方式错误"),
        /**
         * 方法参数校验异常
         */
        METHOD_ARGUMENT_VALID_EXCEPTION(604, "方法参数校验异常"),
        /**
         * 参数丢失
         */
        MISSING_PARAM_EXCEPTION(605, "参数丢失"),
        /**
         * 文件上传异常
         */
        MULTIPART_FILE_EXCEPTION(606, "文件上传异常"),
        /**
         * 返回状态异常
         */
        RESPONSE_STATUS_EXCEPTION(608, "返回状态异常"),
        /**
         * 返回状态异常
         */
        SYSTEM_METHOD_NOT_ALLOWED(609, "请求方法不支持"),
        /**
         * 操作频繁，请稍后重试
         */
        SYSTEM_BUSY_REQUEST(610, "操作频繁,请稍后重试"),
        /**
         * 信息异常，找不到店铺
         */
        SYSTEM_NOT_FIND_MERCHANT(611, "信息异常，找不到店铺"),
        /**
         * 系统异常
         */
        SYSTEM_ERROR(500, "系统异常"),
        /**
         * 参数验证失败
         */
        SYSTEM_PARAM_ERROR(400, "参数验证失败");

        private Integer code;

        private String msg;

        /**
         * 获取code
         *
         * @return code
         */
        @Override
        public Integer getCode() {
            return code;
        }

        /**
         * 获取返回信息
         *
         * @return msg
         */
        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum MerchantRsCode implements BaseResponse {
        /**
         * 店主
         */
        NO_INDUSTRY_FIND(1001, "没有找到指定行业，请重新选择"),

        /**
         * 行业未开放，请重新选择
         */
        INDUSTRY_NO_OPEN(1002, "行业未开放，请重新选择"),

        /**
         * 所选择的地区未开放，请重新选择
         */
        AREA_NO_OPEN(1003, "所选择的地区未开放，请重新选择"),

        /**
         * 所选择的地区未开放，请重新选择
         */
        AREA_ERROR(1003, "定位信息格式错误，请手动选择店铺地址定位"),

        /**
         * 没找到对应的邀请人
         */
        NO_ABUTMENT_FIND(1004, "邀请码错误，请输入正确邀请码"),

        /**
         * 该邀请人已被禁用
         */
        ABUTMENT_FORBID(1005, "该邀请人已被禁用"),

        /**
         * 对接人处理佣金状态失败
         */
        INVALID_AUDIT_COMMISSION(1007, "参数异常,佣金对接处理失败"),

        /**
         * 还未绑定二维码
         */
        NOT_BIND_QRCODE(1006, "还未绑定二维码"),

        /**
         * 获取店铺信息失败
         */
        MERCHANT_INFO_ERROR(1008, "获取店铺信息失败"),

        /**
         * 店主不能成为自己的员工
         */
        IS_MERCHANT_BOSS(1009, "店老板不能成为自己的员工"),

        /**
         * 您已经加入该店铺了哦
         */
        IS_MERCHANT_OPERATER(1010, "您已经加入该店铺了哦"),

        /**
         * 您暂时无法对接店铺
         */
        CAN_NOT_JOIN(1011, "您暂时无法对接店铺"),

        /**
         * 该店铺已认证
         */
        EQB_HAVE_AUTH(1012, "该店铺已认证"),

        /**
         * 店铺认证中
         */
        EQB_AUTH_ING(1013, "店铺认证中"),

        /**
         * 该店铺已签约
         */
        EQB_HAVE_SIGN(1014, "该店铺已签约"),

        /**
         * 该店铺店铺签约
         */
        EQB_SIGN_ING(1015, "店铺签约中"),

        /**
         * 该对接人身份已过期
         */
        ABUTMENT_OVERDUE(1016, "该对接人身份已过期，暂时无法对接店铺"),

        /**
         * 已经签约太多次了
         */
        TOO_MANY_SIGN_COUNT(1017, "您已提交3次，请明天再试"),
        ;

        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum AccountRsCode implements BaseResponse {
        /**
         * 店主
         */
        NO_VALID_BANK_CARD_INFO(2001, "暂无有效银行卡信息");

        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum OssRsCode implements BaseResponse {
        /**
         * 分类不存在
         */
        INDUSTRY_NOT_FOUND(2000, "分类不存在"),

        /**
         * 二级分类无法推荐到首页
         */
        SECOND_INDUSTRY_RECOMMEND_FORBIDDEN(2001, "二级分类无法推荐到首页"),

        /**
         * 推荐到首页的分类超过了数量限制
         */
        MAX_RECOMMEND_COUNT(2002, "推荐到首页的分类超过了数量限制"),

        /**
         * 该分类已推荐到首页
         */
        DUPLICATE_RECOMMEND(2003, "该分类已推荐到首页"),

        /**
         * 该分类下还存在二级分类，请先删除二级分类
         */
        DELETE_FAIL_REMAIN_SECOND_INDUSTRY(2004, "该分类下还存在二级分类，请先删除二级分类"),

        /**
         * 删除失败，该分类下还存在店铺
         */
        DELETE_FAIL_REMAIN_MERCHANT(2005, "删除分类失败，该分类下还存在店铺"),

        /**
         * 新增分类失败，只有一级类目可以新增子分类
         */
        SECOND_INDUSTRY_ADD_FORBIDDEN(2006, "新增分类失败，只有一级类目可以新增子分类"),

        /**
         * 修改排序失败，不同级别之间不能排序
         */
        SORT_FAIL_DIFFERENT_LEVEL(2007, "修改排序失败，不同级别之间不能排序"),

        /**
         * 报表导出失败，数据为空
         */
        NULL_DATA_FOR_EXPORT(2008, "报表导出失败，数据为空"),

        /**
         * 当前状态不允许同意入驻
         */
        MERCHANT_STATUS_UNABLE_APPLY(2100, "当前状态不允许同意入驻"),

        /**
         * 当前状态不允许拒绝入驻
         */
        MERCHANT_STATUS_UNABLE_REFUSE_APPLY(2101, "当前状态不允许拒绝入驻"),

        /**
         * 当前状态不允许取消拒绝
         */
        MERCHANT_STATUS_UNABLE_CANCEL_REFUSE(2102, "当前状态不允许取消拒绝"),

        /**
         * 注册第三方支付平台失败
         */
        REGISTER_THIRD_PLATFORM_FAIL(2103, "注册第三方支付平台失败"),

        /**
         * 商家已经是黑名单用户了
         */
        MERCHANT_IS_BLACK_LIST(2104, "商家已经是黑名单用户了"),

        /**
         * 添加黑名单失败，请联系管理员
         */
        ADD_BLACK_LIST_FAILED(2105, "添加黑名单失败，请联系管理员"),

        /**
         * 该商家尚未在黑名单中
         */
        MERCHANT_NOT_IN_BLACK_LIST(2106, "该商家尚未在黑名单中"),

        /**
         * 商家已经是白名单用户了
         */
        MERCHANT_IS_WHITE_LIST(2107, "商家已经是白名单用户了"),

        /**
         * 添加白名单失败，请联系管理员
         */
        ADD_WHITE_LIST_FAILED(2108, "添加白名单失败，请联系管理员"),

        /**
         * 商家不在白名单中
         */
        MERCHANT_NOT_IN_WHITE_LIST(2109, "商家不在白名单中"),

        /**
         * 添加冻结名单失败，请联系管理员
         */
        ADD_FROZE_LIST_FAILED(2110, "添加冻结名单失败，请联系管理员"),

        /**
         * 商家已经是冻结名单用户了
         */
        MERCHANT_IS_FROZE_LIST(2111, "商家已经是冻结名单用户了"),

        /**
         * 不在冻结名单中
         */
        MERCHANT_NOT_IN_FROZE_LIST(2112, "不在冻结名单中"),

        /**
         * 审核商品请求无效
         */
        INVALID_GOODS_AUDIT_REQUEST(2113, "审核商品请求无效"),

        /**
         * 商品编辑异常,操作结果已回退
         */
        MERCHANT_GOODS_MODIFY_ERROR(2114, "商品编辑异常,操作结果已回退"),

        /**
         * 处理异常,该类目下存在商品
         */
        CATEGORY_HAS_GOODS_EXCEPTION(2115, "处理异常,该类目下存在商品"),

        /**
         * 非法处理退款订单状态
         */
        INVALID_REFUND_ORDER_AUDIT_RECORD(2116, "非法处理退款订单状态"),
        ;


        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum WalletSaaS implements BaseResponse {
        /**
         * 获取合同失败
         */
        GET_SIGN_PROTOCOL_ERROR(70001, "获取合同失败"),
        /**
         * 不支持的银行卡，请更换银行卡
         */
        NOT_SUPPORT_BANK(70002, "不支持的银行卡，请更换银行卡"),
        /**
         * 签约失败
         */
        SIGN_ERROR(70003, "签约失败"),
        /**
         * 获余额失败
         */
        GET_BALANCE_ERROR(70004, "获余额失败"),
        /**
         * 无效订单编号,订单信息不存在
         */
        INVALID_ORDER_CODE(70005, "无效订单编号,订单信息不存在"),
        ;

        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }


    @Getter
    @AllArgsConstructor
    public enum XshLoginRsCode implements BaseResponse {
        /**
         * 缺少登录参数
         */
        LACK_LOGIN_PARAM(4000, "缺少登录参数"),
        /**
         * 请求微信超时
         */
        REQUEST_WECHAT_TIME_OUT(4001, "请求微信超时"),
        /**
         * 获取微信用户信息失败
         */
        GET_WECHAT_USER_INFO_ERROR(4002, "获取微信用户信息失败"),
        /**
         * 登获取微信用户信息失败
         */
        GET_USER_PHONE_NUM_ERROR(4003, "获取微信用户信息失败"),
        /**
         * 登陆失败，系统异常
         */
        LOGIN_ERROE(4004, "登陆失败，系统异常"),
        /**
         * 用户数据异常
         */
        USER_DATA_ERROR(4004, "用户数据异常"),
        /**
         * 此手机号以被绑定过
         */
        DUPLICATE_PHONE_BINDING(4005, "该手机号已被绑定，请使用未绑定e省宝的手机号码登录绑定");


        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum PromoterRsCode implements BaseResponse {
        /**
         * 缺少登录参数
         */
        NO_MERCHANT_PROMOTER(5000, "不是该店的对接人，不能修改店铺佣金"),
        /**
         * 账号有异常，请联系客服
         */
        ACCOUNT_INFO_ERROR(5001, "账号有异常，请联系客服"),
        /**
         * 该店已经成功修改过一次，如需再次修改，请联系客服
         */
        HAS_CHANGE_ONCE(5002, "该店已经成功修改过一次，如需再次修改，请联系客服"),
        /**
         * 请耐心等待商家确认
         */
        WAIT_FOR_CONFIRM(5003, "请耐心等待商家确认"),
        /**
         * 修改次数大于3次，如需再次修改，请联系客服
         */
        OUT_OF_CHANGE_TIME(5004, "修改次数大于3次，如需再次修改，请联系客服"),
        /**
         * 不是对接人
         */
        NO_PROMOTER(5005, "您不是对接人，没有查看或修改权限"),

        /**************************************商品相关*****************************************************/
        /**
         * 不合法的商品数量
         */
        ILLEGAL_COUNT(70100, "不合法的商品数量"),
        /**
         * 无效的商品
         */
        ILLEGAL_GOODS(70101, "无效的商品"),
        /**
         * 商品库存不足
         */
        UN_ENOUGH_STOCK(70102, "商品库存不足"),
        /**
         * 超出该商品的购买数量限制
         */
        OUT_LIMIT_COUNT(70103, "超出该商品的购买数量限制");


        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum PaymentRsCode implements BaseResponse {
        /**
         * 商户支付渠道已注册
         */
        ALREADY_REGISTERED(3301, "商户支付渠道已注册"),
        /**
         * 商户支付渠道未注册
         */
        NOT_REGISTERED(3301, "商户支付渠道未注册"),
        /**
         * 支付渠道信息缺失
         */
        LOSE_PAY_CHANNEL(3300, "支付渠道信息缺失"),
        /**
         * 聚合下单失败,订单回调地址为空
         */
        LOSE_PAY_NOTIFY_URL(3303, "聚合下单失败,订单回调地址为空"),
        /**
         * 汇聚注册失败,请检查信息重新注册
         */
        REGISTRY_FAIL(3302, "汇聚注册失败,请检查信息重新注册"),
        /**
         * 聚合下单失败,参数签名失败
         */
        UNITE_PAY_SIGNATURE_ERROR(3303, "聚合下单失败,参数签名失败"),
        /**
         * 聚合下单失败,商户信息异常
         */
        UNITE_PAY_PARAM_ERROR(3303, "聚合下单失败,参数验证失败"),
        /**
         * 聚合下单失败,商户信息异常
         */
        UNITE_PAY_FAIL(3303, "聚合下单失败,商户信息异常"),
        /**
         * 尝试渠道获取商家信息失败
         */
        CHANNEL_MERCHANT_INFO_GET_FAILED(3301, "尝试从渠道获取商家信息失败"),
        /**
         * 更新商家信息出错
         */
        CHANNEL_MERCHANT_INFO_UPDATE_FAILED(3301, "更新商家信息出错"),
        /**
         * 无效的微信支付渠道
         */
        INVALID_WX_PLACE_ORDER_CHANNEL(3305, "无效的微信支付渠道"),
        /**
         * 微信H5直连下单失败
         */
        WX_H5_PLACE_ORDER_FAIL(3305, "微信H5直连下单失败"),
        /**
         * 微信H5直连下单失败
         */
        ALI_PAY_H5_PLACE_ORDER_FAIL(3305, "支付宝H5直连下单失败"),
        /**
         * 无效的商品编号
         */
        INVALID_GOODS_ID(3305, "无效的商品编号"),
        /**
         * 处理物流更新信息异常
         */
        EXPRESS_NOTIFY_HANDLE_EXCEPTION(3306, "处理物流更新信息异常");

        private Integer code;

        private String msg;

        @Override
        public Integer getCode() {
            return code;
        }

        @Override
        public String getMsg() {
            return msg;
        }
    }
}
