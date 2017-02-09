package com.fearless.entity;

/**
 * Created by zhouwei on 16/12/22.
 */

public class PieData {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getValue() {
        return value;
    }


    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }



    private String  name;  //名字


    public void setValue(int value) {
        this.value = value;
    }

    private int value;   //数值
    private String percentage;   //百分比


    // 非用户关心数据
    private int color = 0;      // 颜色

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    private int angle = 0;    // 角度


}
