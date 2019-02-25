package com.alipay.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePayRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Test;

import java.util.UUID;

/**
 * 描述:
 * 支付测试
 *
 * @author xiechenglin
 * @create 2019-02-24 13:52
 */
public class AlipayTradePay {

    @Test
    public void testPay() throws AlipayApiException {
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求体
        AlipayTradePayRequest alipayTradePayRequest = new AlipayTradePayRequest();
        BizContent bizContent = new BizContent();
        bizContent.setOut_trade_no(UUID.randomUUID().toString().replace("-",""));
        bizContent.setBody("java代码编写测试");
        bizContent.setSubject("小试牛刀");
        bizContent.setProduct_code("FAST_INSTANT_TRADE_PAY");
        bizContent.setTotal_amount("0.02");
        //将请求体转成json
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(bizContent);
        alipayTradePayRequest.setBizContent(content);
        //请求
        String result = alipayClient.pageExecute(alipayTradePayRequest).getBody();
        //输出
        System.out.println(result);
    }




}
