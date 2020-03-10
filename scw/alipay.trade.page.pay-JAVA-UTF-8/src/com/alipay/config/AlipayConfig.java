package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016092900621804";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDuHcyDtwgbLQfClgL5UZiXyJkQgWXsXc+E08u8MxG/PLJ+uRm6ZsWAJk/jr0eyuifEIdYxyIB57cyOnehZBuYFZGcIMqdawBUsjjs6RysX+5iwjOdhrj+LkOlYJ27iKN4jTj4GF/aSXmhYiSQZAAx4daHCoD6CgukBv14bZCdHXVG84BYZzaDoOSLlluJwxv3FfqlhJSdSu3d4G9/8L2FZAMMqmmESNph4eIhyjRLBBE/Du46/UKiHMmY3IMoQRr0FCJwZBlrwUq3iZ+qngE/CWOgQm8WFITuuNr9TTnnUoZu8f05+L5ZiKo6IcuQ1ypy5uCOXV+Th4h5SJk4eVPXVAgMBAAECggEADoiDEwb416/H6Xh+dfkk9x6nOPqs3eBb6h+DL9luG934hwOFLntXUpsEBUGNfrsd8jqeeCtkrAx3j7zTkZt6LpoSL83nqQJQSIfl1YjCeJRCnYP8G1onRr1xgI/nlKYp6L29y2FrC0P32m+EZII8QJ3tYZOmPREAZ7rmY4WZYCGT1hMRFVfr6Jrmzd1Fz4wIVgbxITfdodOWrVrfAUTtFhXc7XE1QMRFKV/vzOvWHAF6KD4cbg1XF/2EVzkFwN0Jl+00/A5+qrtKescH6kzX/1yBfGX7b5C2tjx2Zb64BgGD3FpFUFIwDjnRrDswvvllUy4oraIXv4PiPkEGY+wLgQKBgQD8kaT/1por7Q+w4yF0bzfhPrzfMfsrDXJfcB35K3XLDoHr+HQdydlbOwhhyM9Nns9H5qRyyRveu4/iOIV+WE9XdE3hCW0yIoJ5TDo96nT1yM8NHoJnlkuz1kA6TnvKDqxW2EHiFnVBfUUkV6LHW+KvTh1nYEg+BVKTSFT3WVUcxwKBgQDxWeSc5GqsPk9fBD7sKWCG4/y5RyoPGzkwi/0cT3MJTFrOythELiecn2pxnbuHMRTkbPHyOSo4C9oL4hp3HjASTbvzz+M5nxiTbiVPeCG7f2Tv4Ty0ni89q7pRrXSfFj5dmqGrNx6o1e2dAOK89/oVk1FFs0y/VawaQvPbkqrkgwKBgFUP2Dox1IqmtHomhMXqDy/VKVciRi0hwjtST0Xz1SAmKquGadT+fi2znAcS0n6Z1cWpcAkYokJJeRP/ZTZAF7ikevYnbpdL+BVSDut7YxjEILeSZujsPWK77bW1aQuMFY7KFqZGWgdMYVT0DuaFk7rm3BBM5Qe+Uyk3WyZS989RAoGAFOgRipmqt+Lp4zQt8y0eZHDpWm9UsFxOVus5cLNdJqgLkbA2jall2wZgJRlhvjM9ORBK4Q2cn6A/MhpS6pCd32gGaPdQGpSyf0riRoEEgwZlCvZ62LZ/JxtyF1MsL/+wRVF4wakTqdJmM9x4x5+/dRD+0EfY1RYpw1ibYSDSVtsCgYA9qDSY4S7AgNTQk8GjNIvzOaqDt1LmyDT3sewo571w8rXZxxr7BpxFUoN4Cpe7cai8gYlxKrqhJI6Ga4+9W6ulzudetYd7AQS11aP/lX9GK4M8Sxx6RybXv4iRmWgZ6ojtCwTKKcPRCCBWt2FlcF5ozcVhE7J5sZdmXwLuV262Pg==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAibeLGhTLL3yfHCCtlVz9H538HI3xkL9UEriXsiX6rTaE+Tvx1uALV1IAkIDYTiLQEz1YhCfdsNL4VypcxaHCjF6Z7kRVIM/bYhdBJXESKpGUKP1ASFOiytdhdeYGxXEQk5NYfLAdRN/COFOAcWianJC2vao2woNbLyIK4wX8G+x4OostJi3kA00pUh/rYdiJbLdcpF7KMKhtTbEUHKKHeXRlZ7ItFko7VSwVOAkKe3a/70uoTKVwn7MAvYa7gP8/NLPboKEaQC+Zu02QZtq7r2w8+Jha+tn4vHPHlSYTLVl+bmZupiDzx02/qIgioatqj7st8rjVmDvwYj3Ph8OLBwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://2x46f41192.qicp.vip:22423/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://2x46f41192.qicp.vip:22423/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关   https://openapi.alipaydev.com/gateway.do
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝生成的日志持久化路径
	public static String log_path = "F:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

