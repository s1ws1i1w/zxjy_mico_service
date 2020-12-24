package com.hdyl.schedule.xxljob.entity.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商家信息传输实体
 *
 * @author gralves
 * @date 2020/6/30
 */
@Data
@ApiModel("商家信息")
public class CmsMerchantEntity implements Serializable {
    private Long id;

    /**
     * 商家人员Id merchant_user_id
     **/
    @ApiModelProperty("商家人员Id merchant_user_id")
    private Integer merchantUserId;

    /**
     * 推荐人ID referrer_userid
     **/
    @ApiModelProperty("推荐人ID")
    private String referrerUserid;

    /**
     * 商铺名称 store_name
     **/
    @ApiModelProperty("商铺名称")
    private String storeName;

    /**
     * 店铺简称（20个字以内） store_name_abbreviation
     **/
    @ApiModelProperty("店铺简称")
    private String storeNameAbbreviation;

    /**
     * 电话信息 telephone
     **/
    private String telephone;

    /**
     * 身份证姓名 user_name
     **/
    private String userName;

    /**
     * 身份证号 card
     **/
    private String card;

    /**
     * 身份证正面 front_identity
     **/
    private String frontIdentity;

    /**
     * 身份证反面 reverse_identity
     **/
    private String reverseIdentity;

    /**
     * 身份证手持 inhand_identity
     **/
    private String inhandIdentity;

    /**
     * 协议佣金 commission
     **/
    private BigDecimal commission;

    /**
     * 手机号 phone
     **/
    private String phone;

    /**
     * 省 province
     **/
    private String province;

    /**
     * 市 city
     **/
    private String city;

    /**
     * 区/县 area
     **/
    private String area;

    /**
     * 详细地址 address
     **/
    private String address;

    /**
     * 额外详细地址 extraAddress
     **/
    private String extraAddress;

    /**
     * 银行名称 bank_name
     **/
    private String bankName;

    /**
     * 银行卡号 bank_num
     **/
    private String bankNum;

    /**
     * 行业 tradeid
     **/
    private Integer tradeid;

    /**
     * 添加时间 addtime
     **/
    private Date addtime;

    /**
     * 修改时间 edittime
     **/
    private Date edittime;

    /**
     * 删除状态：0正常；1已删 is_delete
     **/
    private Integer isDelete;

    /**
     * 营业执照名称 license_title
     **/
    private String licenseTitle;

    /**
     * 统一社会信用代码（注册号） registr_num
     **/
    private String registrNum;

    /**
     * 法人代表姓名 law_person
     **/
    private String lawPerson;

    /**
     * 法人身份证号码 law_card
     **/
    private String lawCard;

    /**
     * 法人身份证正面url law_front_identity
     **/
    private String lawFrontIdentity;

    /**
     * 法人身份证背面url law_reverse_identity
     **/
    private String lawReverseIdentity;

    /**
     * 有效期结束 indate_end
     **/
    private String indateEnd;

    /**
     * 有效期开始 indate_start
     **/
    private String indateStart;

    /**
     * 0未审核1通过2拒绝3下架4待易宝审核 status
     **/
    private Integer status;

    /**
     * 身份证性别 card_sex
     **/
    private String cardSex;

    /**
     * 身份证生日 card_birth
     **/
    private String cardBirth;

    /**
     * 身份证民族 card_nationality
     **/
    private String cardNationality;

    /**
     * 身份证地址 card_address
     **/
    private String cardAddress;

    /**
     * 身份证签发机关 card_issue
     **/
    private String cardIssue;

    /**
     * 身份证有效期开始时间 card_start_date
     **/
    private String cardStartDate;

    /**
     * 身份证结束时间 card_end_date
     **/
    private String cardEndDate;

    /**
     * 是否签约合同（0：否，1：是）  is_sign
     **/
    private Integer isSign;

    /**
     * 推荐佣金 referrer_commission
     **/
    private Double referrerCommission;

    /**
     * 是否激活（0：否，1：是，2：拒绝，3：审核中） is_activation
     **/
    private Integer isActivation;

    /**
     * 是否签银联合同（0：否，1：是） is_sign_unionpay
     **/
    private Integer isSignUnionpay;

    /**
     * 云合同平台上的合同id contract_id
     **/
    private String contractId;

    /**
     * 签约完成的合同url contract_url
     **/
    private String contractUrl;

    /**
     * 收款二维码id qrcode_id
     **/
    private Integer qrcodeId;

    /**
     * 银联商户号 unionpay_merid
     **/
    private String unionpayMerid;

    /**
     * 对接人审核状态（0:未审核，1：同意佣金，2：不同意佣金） referrer_status
     **/
    private Integer referrerStatus;

    /**
     * 视频缩略图url video_img
     **/
    private String videoImg;

    /**
     * 视频url video_url
     **/
    private String videoUrl;

    /**
     * 营业时间段 time_tab
     **/
    private String timeTab;

    /**
     * 人均消费 per_capita
     **/
    private Double perCapita;

    /**
     * 合同图片url contract_img
     **/
    private String contractImg;

    /**
     * 商户类型 merchant_type
     **/
    private String merchantType;

    /**
     * 银行类型 bank_type
     **/
    private String bankType;

    /**
     * 开户省 bank_province
     **/
    private String bankProvince;

    /**
     * 开户市 bank_city
     **/
    private String bankCity;

    /**
     * 手写签名图片url autograph_img
     **/
    private String autographImg;

    /**
     * 经度 longitude
     **/
    private BigDecimal longitude;

    /**
     * 纬度 latitude
     **/
    private BigDecimal latitude;

    /**
     * 合同签约图片url contract_sign_img
     **/
    private String contractSignImg;

    /**
     * 银行卡图片 bank_card_img
     **/
    private String bankCardImg;

    /**
     * 账户表id account_id
     **/
    private Long accountId;

    /**
     * 店铺编号 no
     **/
    private String no;

    /**
     * 备注  remarks
     **/
    private String remarks;

    /**
     * 银行开户许可证 bank_licence
     **/
    private String bankLicence;

    /**
     * 易宝分账方资质审核：0-未激活、1-正常、2-冻结、3-审核中、4-审核失败、5-注销 yop_status
     **/
    private Integer yopStatus;

    /**
     * 银行预留手机号 bank_phone
     **/
    private String bankPhone;

    /**
     * 开户人名 bank_prople_name
     **/
    private String bankPropleName;

    /**
     * 开户人名 bank_user_name
     **/
    private String bankUserName;

    /**
     * 维度取整(用于建立索引) lat_floor
     **/
    private Integer latFloor;

    /**
     * 经度取整(用于建立索引) lon_floor
     **/
    private Integer lonFloor;

    /**
     * 支付渠道 2：汇聚 pay_channel_no
     **/
    private String payChannelNo;

    /**
     * 商家总的状态：
     * 0-基本信息输入；1-已认证（营业执照等信息输入）；2-对接人同意佣金；3-对接人不同意佣金；4-享多赚平台审核通过；
     * 5-享多赚平台审核不通过；6-第三方平台审核通过（汇聚等）；7-第三方平台审核不通过；8-签约；9-授权；10-激活；11-下架
     * ***用来代替以前的status，yop_status，is_sign，referrer_status，is_activation字段*** merchant_status
     **/
    private Integer merchantStatus;

    /**
     * 门店描述 merchant_desc
     **/
    private String merchantDesc;

    /**
     * 门店公告 merchant_notice
     **/
    private String merchantNotice;

    /**
     * 行业证件 documentation
     **/
    private String documentation;

    /**
     * 店铺门头照片 storefront_thumb
     **/
    private String storefrontThumb;

    /**
     * 店铺招牌照片 storeinside_thumb
     **/
    private String storeinsideThumb;

    /**
     * 备注：审核意见评论。 comment
     **/
    private String comment;

    /**
     * 经营场所 abode
     **/
    private String abode;

    /**
     * 营业执照照片地址 business_license
     **/
    private String businessLicense;

    /**
     * 拒绝邀活的理由 activation_comment
     **/
    private String activationComment;

    private static final long serialVersionUID = 1L;
}