package com.example.diak.asynctasktest;

/**
 * Created by Diak on 2017/1/18.
 */

public class NetOperator {

    public void operator(){
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
