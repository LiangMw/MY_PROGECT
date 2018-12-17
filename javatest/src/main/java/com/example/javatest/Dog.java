package com.example.javatest;

/**
 * Created by 梁明伟 on 2018/12/4.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class Dog {

    private String voice;
    private String color;

    public Dog(String voice, String color){
        this.voice = voice;
        this.color = color;
    }

    public String getVoice() {
        return voice == null ? "" : voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getColor() {
        return color == null ? "" : color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void voice(){
        System.out.println(color+voice+"wang~");
    }

}
