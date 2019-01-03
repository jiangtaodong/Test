package com.byzp.util;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class TestFanSheList {

    public static void main(String[] args){

        ArrayList<String> arrayList = new ArrayList();

        arrayList.add("000");
        arrayList.add("111");
        arrayList.add("222");

        Class listclass = arrayList.getClass();

        Method method = null;

        try {
            method = listclass.getMethod("add",Object.class);
            method.invoke(arrayList,"333");
            for(Object o : arrayList){

                System.out.println(o + " +++++++++++++++ ");

            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

}
