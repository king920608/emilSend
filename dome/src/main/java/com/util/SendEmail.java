package com.util;

import microsoft.exchange.webservices.data.core.ExchangeService;
import microsoft.exchange.webservices.data.core.enumeration.misc.ExchangeVersion;
import microsoft.exchange.webservices.data.core.service.item.EmailMessage;
import microsoft.exchange.webservices.data.credential.ExchangeCredentials;
import microsoft.exchange.webservices.data.credential.WebCredentials;
import microsoft.exchange.webservices.data.property.complex.EmailAddress;
import microsoft.exchange.webservices.data.property.complex.MessageBody;
import java.net.URI;
import java.util.List;
import java.util.Map;


public class SendEmail {

    private ExchangeService service;

    private String regex = "(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)";

    /**
     * 发送邮件
     * **/
    public void sendEmail(Map map,String attachments) throws Exception{
        this.CreateService(map);

        EmailMessage msg= new EmailMessage(service);
        //设置邮件主题
        msg.setSubject(map.get("subject").toString());
        //设置邮件内容
        msg.setBody(MessageBody.getMessageBodyFromText(map.get("body").toString()));
        //设置发送人地址
        msg.setFrom(new EmailAddress(map.get("username").toString()));

        //获取相应的收件人
        String[] recs = map.get("recipients").toString().split(",");
        if (recs.length > 0){
            for (int i = 0;i<recs.length;i++){
                if (recs[i].matches(regex)){
                    msg.getToRecipients().add(recs[i]);
                }
            }
        }else {
            throw new Exception("没有设置收件人!");
        }

        //获取相应的抄送人
        String[] ccRecs = map.get("ccRecipients").toString().split(",");
        if (ccRecs.length>0){
            for (int i = 0;i<ccRecs.length;i++){
                msg.getCcRecipients().add(ccRecs[i]);
            }
        }

        //添加附件

            msg.getAttachments().addFileAttachment(attachments);


        msg.sendAndSaveCopy();

    }

    /**
     *建立会话
     * **/
    public void CreateService(Map map) throws Exception {
        //实例化ExchageService
        service = new ExchangeService(ExchangeVersion.Exchange2010_SP2);
        ExchangeCredentials credentials = new WebCredentials(map.get("username").toString(),map.get("password").toString());
        service.setCredentials(credentials);
        service.setTraceEnabled(true);
        service.setUrl(new URI(map.get("url").toString()));
    }
}
