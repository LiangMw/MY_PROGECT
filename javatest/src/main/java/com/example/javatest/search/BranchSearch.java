package com.example.javatest.search;

import java.math.BigDecimal;

/**
 * Created by 梁明伟 on 2018/10/25.
 * Copyright © 2018年 CETC. All rights reserved.
 */
public class BranchSearch {

    public String caculat(int num){
        if(num<=0) {
            return "-1";
        }

        BigDecimal org = new BigDecimal(num);
        StringBuilder bud = new StringBuilder("");
        for (int i =0;i<7;i++){
            int low = 0;int high = 9;
            int mid = 0;
            BigDecimal v = new BigDecimal("0");
            while ((high-low)>1) {
                mid = getNum(low,high);
                BigDecimal t;
                if(i == 1) {
                    t = new BigDecimal(bud.toString()+"."+mid);
                }else{
                    t = new BigDecimal(bud.toString()+mid);
                }
                if(t.pow(2).compareTo(org)==-1){
                    low = mid;
                }else {
                    high = mid;
                }
            }

            if(i ==1) {
                bud.append("."+mid);
            }else {
                bud.append(mid);
            }
        }
        return bud.toString();
    }

    private int getNum(int low,int high){
        return (low+high)/2;
    }

}
