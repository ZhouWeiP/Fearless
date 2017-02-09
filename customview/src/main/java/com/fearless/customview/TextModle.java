package com.fearless.customview;

import java.util.LinkedList;

/**
 * Created by zhouwei on 17/2/6.
 */

public class TextModle {



    public static TextModle newInstance(String text,LinkedList<TextModle> textModles){
         TextModle newTextModle=new TextModle();
         newTextModle.setText(text);
         newTextModle.setChildModles(textModles);
        return newTextModle;
    }


    public static TextModle newInstance(String text){
        TextModle newTextModle=new TextModle();
        newTextModle.setText(text);
        newTextModle.setChildModles(null);
        return newTextModle;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public LinkedList<TextModle> getChildModles() {
        return childModles;
    }

    public void setChildModles(LinkedList<TextModle> childModles) {
        this.childModles = childModles;
    }

    private String text;

    private LinkedList<TextModle> childModles;

}
