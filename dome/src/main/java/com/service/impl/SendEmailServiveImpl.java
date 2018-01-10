package com.service.impl;

import com.entity.AccountEntity;
import com.entity.DigitalEntity;
import com.entity.ManagerEntity;
import com.service.SendEmailService;
import com.util.CreateExeclFile;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class SendEmailServiveImpl implements SendEmailService{

    @Autowired
    private CreateExeclFile execlFile;

    @Override
    public String selAll(Map map) throws Exception {
        List list = this.selManager();
        String file = execlFile.exportFile(list,map);
        return file;
    }

    public List<AccountEntity> selAccount() {
        List<AccountEntity> list = new ArrayList<AccountEntity>();
        for (int i = 0;i<5;i++){
            AccountEntity accEntity = new AccountEntity();
            accEntity.setAccout("62225"+i);
            accEntity.setAmount("40000");
            list.add(accEntity);
        }
        return list;
    }

    public List<DigitalEntity> selDigital() {
        List<DigitalEntity> list = new ArrayList<DigitalEntity>();
        for (int i = 0;i<5;i++){
            DigitalEntity digEntity = new DigitalEntity();
            digEntity.setDaytime(String.valueOf(10+i));
            digEntity.setMonthtime(String.valueOf(1+i));
            list.add(digEntity);
        }
        return list;
    }

    public List<ManagerEntity> selManager() {
        List<ManagerEntity> list = new ArrayList<ManagerEntity>();
        for (int i = 0;i<5;i++){
            ManagerEntity manEntity = new ManagerEntity();
            manEntity.setName("张三");
            manEntity.setAddress("上海");
            list.add(manEntity);
        }
        return list;
    }


}
