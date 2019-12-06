package com.cn.threads;

/**
 * @Copyright：botBrain.ai
 * @Author: WangMingChao
 * @Date: 2019/12/3.
 * @Description:
 */
public class LiftOff implements Runnable {

    protected int countDown=10;
    private static int taskCount=0;
    private final int id=taskCount++;
    public LiftOff(){};
    public String status(){
        return "#"+id+"("+(countDown>0 ? countDown:"off")+"),";
    }
    @Override
    public void run() {
        while (countDown-- >0){
            System.out.println(status());
            Thread.yield();
        }
    }
}
