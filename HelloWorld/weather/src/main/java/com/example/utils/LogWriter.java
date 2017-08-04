package com.example.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Diak on 2017/2/16.
 */

public class LogWriter {
    private  static LogWriter logWriter;
    private static String path;
    private static Writer writer;
    private static SimpleDateFormat dateFormat;

    private LogWriter(String path){
        this.path = path;
        this.writer = null;
    }

    public static LogWriter getInstance(String path)throws IOException{
        if(logWriter == null){
            logWriter = new LogWriter(path);
        }
        File file = new File(path);
        if(!file.exists()){
            file.mkdir();
        }
        file.createNewFile();
        writer = new BufferedWriter(new FileWriter(file));
        dateFormat = new SimpleDateFormat("[yyyy-MM-dd hh:mm:ss]:");
        return logWriter;
    }

    public void close(){
        try{
            writer.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void print(String log) throws IOException{
        writer.write(dateFormat.format(new Date()));
        writer.write(log);
        writer.write("\n");
        writer.flush();
    }

    public void print(Class cls,String log) throws IOException{
        writer.write(dateFormat.format(new Date()));
        writer.write(cls.getSimpleName() + " ");
        writer.write(log);
        writer.write("\n");
        writer.flush();
    }
}
