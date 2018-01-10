package com.util;

import net.sf.excelutils.ExcelUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class CreateExeclFile {
    private String date;
    private BufferedInputStream fis;
    private  FileOutputStream fos;
    public CreateExeclFile(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMdd");
        date = dateFormat.format(new Date());
    }

    public String exportFile(List list, Map map) throws Exception {
        String file = null;
        String fileName = null;
        try {

            ExcelUtils.addValue("list",list);
            InputStream template = this.getClass().getResourceAsStream("/今日模板.xls");
            String t = "E:\\conf\\今日模板.xls";
            File datefile = new File(t);
            try {
                fis = new BufferedInputStream(template);
                fos = new FileOutputStream(datefile);
                byte[] buf = new byte[1024];
                int c = 0;
                while ((c = fis.read(buf)) != -1) {
                    fos.write(buf, 0, c);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                fos.close();
                fis.close();
            }

            String name = map.get("fileName").toString();
            fileName = name+date+".xls";

            file = map.get("fileAddr").toString()+fileName;
            ExcelUtils.export(t,new FileOutputStream(file));
        } catch (Exception e) {
            e.printStackTrace();
           throw new Exception("文件生成错误！");
        }
        return file;
    }
}
