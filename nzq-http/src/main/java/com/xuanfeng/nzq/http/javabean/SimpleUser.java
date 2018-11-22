package com.xuanfeng.nzq.http.javabean;

public class SimpleUser {
    private int xf;
    private long deadline;

    public SimpleUser() {
    }

    public int getXf() {
        return xf;
    }

    public void setXf(int xf) {
        this.xf = xf;
    }

    public long getDeadline() {
        return deadline;
    }

    public void setDeadline(long deadline) {
        this.deadline = deadline;
    }

    public SimpleUser(int xf, long deadline) {
        this.xf = xf;
        this.deadline = deadline;
    }
}
