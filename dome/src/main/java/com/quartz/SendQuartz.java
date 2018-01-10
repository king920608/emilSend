package com.quartz;

import com.service.SendEmailService;

import com.util.LoadProp;
import com.util.SendEmail;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Map;


public class SendQuartz {
    @Autowired
    private SendEmailService emailService;
    @Autowired
    private LoadProp loadProp;
    @Autowired
    private SendEmail sendEmail;

    public void sendMassger() throws Exception {

        //加载配置文件获取配置数据
        Map map = loadProp.loadProp();


        String file= emailService.selAll(map);

        sendEmail.sendEmail(map,file);

        System.out.println("触发器启动");

    }
}
