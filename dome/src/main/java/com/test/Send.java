package com.test;

import com.util.CreateExeclFile;
import com.util.LoadProp;

import java.io.IOException;
import java.util.*;

public class Send{
    public static void main(String[] args) throws Exception {
        List list = new ArrayList();
        Map<String,String> map = new HashMap<String,String>();
        map.put("name","nihao");
        map.put("address","zhongguo");
        list.add(map);

        LoadProp prop = new LoadProp();
        Map map1 = prop.loadProp();

        CreateExeclFile createExeclFile = new CreateExeclFile();
        createExeclFile.exportFile(list,map1);
    }
}
