package com.demo.test.easyexcel;

import com.alibaba.excel.EasyExcel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author scc
 * @date 2020/5/28 15:49
 */
public class TestEasyExcel {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestEasyExcel.class);

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link DemoDataListener}
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {
        // 有个很重要的点 DemoDataListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
        // 写法1：
        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
        // 这里 需要指定读用哪个class去读，然后读取第一个sheet 文件流会自动关闭
        EasyExcel.read(fileName, DemoData.class, new DemoDataListener())
                .sheet()
                // excel有标题则从1开始读，否则从0开始读
                .headRowNumber(1)
                .doRead();

        // 写法2：
//        String fileName = TestFileUtil.getPath() + "demo" + File.separator + "demo.xlsx";
//        ExcelReader excelReader = EasyExcel.read(fileName, DemoData.class, new DemoDataListener()).build();
//        ReadSheet readSheet = EasyExcel.readSheet(0).build();
//        excelReader.read(readSheet);
//        // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
//        excelReader.finish();
    }

    /**
     * 最简单的写
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link DemoData}
     * <p>
     * 2. 直接写即可
     */
    @Test
    public void simpleWrite() {
        String fileName = TestFileUtil.getPath() + "simpleWrite" + System.currentTimeMillis() + ".xlsx";
        EasyExcel.write(fileName, V2.class)
                .sheet("Sheet1")
                .doWrite(v2());
    }

    private List<V2> v2() {
        List<V2> list = new ArrayList<>();
        for (int i = 1; i < 100000; i++) {
            V2 data = new V2();
            /**业务编码*/
            data.setProcessingCode("2019");
            /**机构编码*/
            data.setMechanismId("100012");
            /**机构交易日期*/
            data.setMechanismTransDate("20200916");
            /**机构交易时间*/
            data.setMechanismTransTime("100000");
            /**版本号*/
            data.setVersionNum("2.0");
            /**签名信息*/
            data.setSignCode("");

            /**机构交易流水号*/
            Long num = 2020091600802001l;
            data.setMechanismOrderNum(String.valueOf(num + i));

            /**交易金额*/
            data.setTradeAmount(new BigDecimal("0.01"));
            data.setRouteType("USS0008");
            /**币种*/
            data.setCurType("01");
            /**用户号*/
            data.setUserCode("100012");
            /**用户名*/
            data.setUserName("V2用户名");
            /**用户优先级*/
            data.setUserLevel("0");
            /**事业部*/
            data.setDepartment("0001");
            /**银行账号*/
            data.setBankAccount("6212264100000000002");
            /**银行编号*/
            data.setBankCode("01050000");
            /**户名*/
            data.setBankAccountName("V2结算");
            /**对公对私标识*/
            data.setPublicAndPrivateFlag("01");
            /**省份*/
            data.setProvince("0013");
            /**地区*/
            data.setArea("1303");
            /**支行名*/
            data.setSubbranchBankName("");
            /**联行号*/
            data.setCorrespondentCode("");
            /**手机号*/
            data.setPhoneNumber("");
            /**备注*/
            data.setRemark("");
            /**产品标识*/
            data.setProductId("");
            /**垫资标识*/
            data.setAdvanceFlag("");
            /** 全局流水号，需要支持新老格式，老格式没有*/
            data.setTraceId("");
            /**商户编号*/
            data.setMechantCode("");
            /**商户名称*/
            data.setMechantName("");
            /**业务产品标识*/
            data.setBusinessProductId("");
            /**付款方名称*/
            data.setPayer("");
            /**付款方账户*/
            data.setPayerAccount("");
            /**摘要*/
            data.setSummary("");
            /**设备信息*/
            data.setEquipmentInfo("");
            list.add(data);
        }
        return list;
    }

    private List<V1Common> v1Common() {
        List<V1Common> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            V1Common data = new V1Common();
            /**业务编码*/
            data.setProcessingCode("5010");
            /**机构编码*/
            data.setMechanismId("100012");
            /**机构交易日期*/
            data.setMechanismTransDate("20200724");
            /**机构交易时间*/
            data.setMechanismTransTime("100000");
            /**版本号*/
            data.setVersionNum("1.0");
            /**签名信息*/
            data.setSignCode("");

            /**机构交易流水号*/
            Long num = 2020072400500003l;
            data.setMechanismOrderNum(String.valueOf(num + i));

            /**交易金额*/
            data.setTradeAmount(new BigDecimal("0.01"));
            /**结算方式*/
            data.setPayType("01");
            /**币种*/
            data.setCurType("01");
            /**用户号*/
            data.setUserCode("100012");
            /**用户名*/
            data.setUserName("V1用户名");
            /**用户优先级*/
            data.setUserLevel("0");
            /**事业部*/
            data.setDepartMent("0001");
            /**银行账号*/
            data.setBankAccount("6212264100000000002");
            /**银行编号*/
            data.setBankCode("01050000");
            /**户名*/
            data.setUserAccountName("V1结算");
            /**对公对私标识*/
            data.setPublicAndPrivateFlag("01");
            /**省份*/
            data.setProvince("0013");
            /**地区*/
            data.setArea("1303");
            /**支行名*/
            data.setSubbranchBankName("");
            /**联行号*/
            data.setCorrespondentCode("");
            /**手机号*/
            data.setPhoneNumber("");
            /**备注*/
            data.setRemark("");
            /**银行通道*/
            data.setBankChannel("");
            /**产品标识*/
            data.setProductId("");
            /**垫资标识*/
            data.setAdvanceFlag("");
            /** 全局流水号，需要支持新老格式，老格式没有*/
            data.setTraceId("");
            /**商户编号*/
            data.setMechantCode("");
            /**商户名称*/
            data.setMechantName("");
            /**业务产品标识*/
            data.setBusinessProductId("");
            /**付款方名称*/
            data.setPayer("");
            /**付款方账户*/
            data.setPayerAccount("");
            /**摘要*/
            data.setSummary("");
            /**设备信息*/
            data.setEquipmentInfo("");
            list.add(data);
        }
        return list;
    }

    private List<V1Pay> v1PayData() {
        List<V1Pay> list = new ArrayList<V1Pay>();
        for (int i = 0; i < 10000; i++) {
            V1Pay data = new V1Pay();
            /**业务编码*/
            data.setProcessingCode("6010");
            /**机构编码*/
            data.setMechanismId("100012");
            /**机构交易日期*/
            data.setMechanismTransDate("20200724");
            /**机构交易时间*/
            data.setMechanismTransTime("100000");
            /**版本号*/
            data.setVersionNum("1.0");
            /**签名信息*/
            data.setSignCode("");

            /**机构交易流水号*/
            Long num = 2020072400100003l;
            data.setMechanismOrderNum(String.valueOf(num + i));

            /**交易金额*/
            data.setTradeAmount(new BigDecimal("0.01"));
            /**结算方式*/
            data.setPayType("01");
            /**币种*/
            data.setCurType("01");
            /**用户号*/
            data.setUserCode("100012");
            /**用户名*/
            data.setUserName("V1用户名");
            /**用户优先级*/
            data.setUserLevel("0");
            /**事业部*/
            data.setDepartMent("0001");
            /**银行账号*/
            data.setBankAccount("6212264100000000002");
            /**银行编号*/
            data.setBankCode("01050000");
            /**户名*/
            data.setUserAccountName("V1代付对公");
            /**对公对私标识*/
            data.setPublicAndPrivateFlag("01");
            /**省份*/
            data.setProvince("0013");
            /**地区*/
            data.setArea("1303");
            /**支行名*/
            data.setSubbranchBankName("");
            /**联行号*/
            data.setCorrespondentCode("");
            /**手机号*/
            data.setPhoneNumber("");
            /**备注*/
            data.setRemark("");
            /**银行通道*/
            data.setBankChannel("");
            /**产品标识*/
            data.setProductId("");
            /**垫资标识*/
            data.setAdvanceFlag("");
            /** 全局流水号，需要支持新老格式，老格式没有*/
            data.setTraceId("");
            /**商户编号*/
            data.setMechantCode("");
            /**商户名称*/
            data.setMechantName("");
            /**业务产品标识*/
            data.setBusinessProductId("");
            /**付款方名称*/
            data.setPayer("");
            /**付款方账户*/
            data.setPayerAccount("");
            /**摘要*/
            data.setSummary("");
            /**设备信息*/
            data.setEquipmentInfo("");
            list.add(data);
        }
        return list;
    }
}
