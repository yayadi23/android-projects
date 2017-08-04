package com.example.entity.reversegeo;

/**
 * Created by Diak on 2017/8/1.
 */

public class AddressEntity {
    private int status;

    private Result result;

    public void setStatus(int status){
        this.status = status;
    }
    public int getStatus(){
        return this.status;
    }
    public void setResult(Result result){
        this.result = result;
    }
    public Result getResult(){
        return this.result;
    }
}
