package com.demo.test.easyexcel;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author scc
 * @date 2020/6/9 18:21
 */
@Data
public class V1Pay {
    /**
     * 业务编码
     **/
    private String processingCode;

    /**
     * 机构编号
     **/
    private String mechanismId;

    /**
     * 机构交易日期
     **/
    private String mechanismTransDate;

    /**
     * 机构交易时间
     **/
    private String mechanismTransTime;

    /**
     * 版本号
     **/
    private String versionNum;

    /**
     * 机构交易流水号
     **/
    private String mechanismOrderNum;

    /**
     * 交易金额
     **/
    private BigDecimal tradeAmount;

    /**
     * 代付方式
     **/
    private String payType;

    /**
     * 币种
     **/
    private String curType;

    /**
     * 用户号
     **/
    private String userCode;

    /**
     * 用户名
     **/
    private String userName;

    /**
     * 用户优先级
     **/
    private String userLevel;

    /**
     * 事业部
     **/
    private String departMent;

    /**
     * 银行账号
     **/
    private String bankAccount;

    /**
     * 银行编号
     **/
    private String bankCode;

    /**
     * 戶名
     **/
    private String userAccountName;

    /**
     * 对公对私标识
     **/
    private String publicAndPrivateFlag;

    /**
     * 省份
     **/
    private String province;

    /**
     * 地区
     **/
    private String area;

    /**
     * 支行名
     **/
    private String subbranchBankName;

    /**
     * 联行号
     **/
    private String correspondentCode;

    /**
     * 手机号
     **/
    private String phoneNumber;

    /**
     * 银行通道
     **/
    private String bankChannel;

    /**
     * 路由编码
     **/
    private String routeId;

    /**
     * 备注
     **/
    private String remark;

    /**
     * 前面方式
     **/
    private String signCode;

    /**
     * 产品标识
     **/
    private String productId;

    /**
     * 垫付标识
     **/
    private String advanceFlag;

    /**
     * 全局唯一流水号
     */
    private String traceId;

    /**
     * 商户编号
     */
    private String mechantCode;

    /**
     * 商户名称
     */
    private String mechantName;

    /*
     业务用途种类
     */
    private String businessProductId;

    /**
     * 付款方信息
     */
    private String payer;

    /**
     * 付款方账户
     */
    private String payerAccount;

    /**
     * 摘要
     */
    private String summary;

    /**
     * 设备信息
     */
    private String equipmentInfo;
}
