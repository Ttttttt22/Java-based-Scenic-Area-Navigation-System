package com.example.my_v_7;

/**
 * Created with IntelliJ IDEA.
 *
 * @Author: Kuang Wentao
 * @Date: 2022/12/04/16:01
 * @Description:
 */
public class Point {
    private int X;
    private int Y;
    private String name;

    public Point(int x, int y, String n){
        this.X = x;
        this.Y = y;
        this.name = n;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public String getName() {
        return name;
    }
}
