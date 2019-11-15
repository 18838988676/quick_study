package com.cn.oskeycheck;//package com.botbrain.timer.schedule;
//
//import org.junit.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// * Copyright：botBrain.ai
// * Author: WangMingChao
// * Date: 2019/11/14.
// * Description:  检测osk
// */
//@Component
//public class OskeyCheck2 {
//
//    private volatile  List<String> osKeyLocal=new CopyOnWriteArrayList<String>();
//    @Autowired
//    ThreadPoolTaskExecutor checkExecutor;
////	@Autowired
////	private ConfigFeignClient configFeignClient;
//
//
//    public OskeyCheck2(){
//    }
//
//    //检测oskey的变化,并将所有带oskey的任务
//    @PostConstruct
//    private void checkCenter(){
//        //	List<Map<String, Object>> osList1 = configFeignClient.findAll(3, "").getData();
//        //获得osKey
//        ArrayList<String> list=new ArrayList<>();
//
//        //判断，osKey是增加 还是减少
//        Map<String,List<String>> results=check(list);
//        if(results.containsKey("add")){
//            List<String> a=   results.get("add");
//        }
//        if(results.containsKey("remove")){
//            List<String> a=   results.get("remove");
//        }
//    }
//
//    //判断
//    private Map<String,List<String>> check(ArrayList<String> newOskeylists){
//        Map<String,List<String>> results=new HashMap<>();
//
//        //留个副本
//        List newOskeyListCopy=new ArrayList<>();
//        newOskeyListCopy.addAll(newOskeylists);
//        List osKeyLocalCopy=new CopyOnWriteArrayList<>();
//        osKeyLocalCopy.addAll(osKeyLocal);
//
//        //差集
//        newOskeyListCopy.removeAll(osKeyLocalCopy);
//        if(newOskeyListCopy.size()!=0){
//            //新增的
//            results.put("add",newOskeyListCopy);
//            osKeyLocal.addAll(newOskeyListCopy);
//        }
//        //差集
//        osKeyLocalCopy.removeAll(newOskeylists);
//        if(osKeyLocalCopy.size()!=0){
//            //减少的
//            results.put("remove",osKeyLocalCopy);
//            osKeyLocal.removeAll(osKeyLocalCopy);
//        }
//     return results;
//    }
//
//    @Test
//    public void test(){
//        osKeyLocal.add("a");
//        osKeyLocal.add("b");
//        osKeyLocal.add("c");
//        osKeyLocal.add("d");
//        osKeyLocal.add("e");
//        for (int i=0;i<10000;i++)
//        {
//            osKeyLocal.add("EWEJIUGKDJKDLSl"+i);
//        }
//
//        print("开始",osKeyLocal);
//
//        ArrayList<String> list=new ArrayList<>();
//        for (int i=0;i<10000;i++)
//        {
//            list.add("EWEJIUGKDJKDLSl"+i);
//        }
//        long st=System.currentTimeMillis();
//        Map<String,List<String>> results =check(list);
//        long s=System.currentTimeMillis();
//        System.out.println("time:"+(s-st)/1000);
//        if(results.containsKey("add")){
//            List<String> a=   results.get("add");
//            print("add",a);
//        }
//        if(results.containsKey("remove")){
//            List<String> a=   results.get("remove");
//            print("remove",a);
//        }
//        print("结束",osKeyLocal);
//
//
//    }
//    public void print(String msg,List<String> osKeyLocal){
//        System.out.print(msg+"  ");
//        for (String str:osKeyLocal){
//            System.out.print(str+",");
//        }
//        System.out.println("");
//    }
//}
