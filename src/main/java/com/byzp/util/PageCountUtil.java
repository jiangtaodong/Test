package com.byzp.util;

public class PageCountUtil{

    private int rowscount;//总条数
    private int pagecount;//总页数
    private int pagenow;//当前页
    private int startrows;//开始行 //起始索引=（当前页数-1）*每页显示的条数
    private int pagerowscount;//每页显示几行

    public int startrows(){

        startrows = (pagenow - 1) * pagerowscount;

        System.out.println("startrows --- " + startrows);

        return startrows;

    }

    public int pagecount(){

        pagecount = (int) Math.ceil(((double)rowscount / pagerowscount));

        System.out.println("pagecount --- " + pagecount);

        return pagecount;

    }

    public int getRowscount() {
        return rowscount;
    }

    public void setRowscount(int rowscount) {
        this.rowscount = rowscount;
    }

    public int getPagecount() {
        return pagecount;
    }

    public void setPagecount(int pagecount) {
        this.pagecount = pagecount;
    }

    public int getStartrows() {
        return startrows;
    }

    public void setStartrows(int startrows) {
        this.startrows = startrows;
    }

    public int getPagerowscount() {
        return pagerowscount;
    }

    public void setPagerowscount(int pagerowscount) {
        this.pagerowscount = pagerowscount;
    }

    public int getPagenow() {
        return pagenow;
    }

    public void setPagenow(int pagenow) {
        this.pagenow = pagenow;
    }
}
