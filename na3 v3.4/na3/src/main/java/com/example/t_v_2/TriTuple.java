package com.example.t_v_2;


/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Kuang Wentao
 * @Date: 2022/11/29/23:38
 * @Description:
 */


public class TriTuple {
    public int from;
    public int to;
    public int length;
    TriTuple(int f, int t, int l){
        this.from = f;
        this.to = t;
        this.length = l;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getLength() {
        return length;
    }
}
