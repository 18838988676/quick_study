//package com.cn.oskeycheck;
//
//import com.botbrain.timer.core.model.XxlJobInfo;
//import com.botbrain.timer.dao.XxlJobInfoDao;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
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
//public class OskeyCheck {
//
//    private volatile  List<String> osKeyLocal=new CopyOnWriteArrayList<String>();
//    @Autowired
//    ThreadPoolTaskExecutor checkExecutor;
//    @Resource
//    private XxlJobInfoDao xxlJobInfoDao;
//
//
//    public  OskeyCheck(){
//    }
//
//
//
//
//
//    //检测oskey的变化,并将所有带oskey的任务
//    @PostConstruct
//    private void checkCenter(){
//        //获得osKey
//        ArrayList<String> list=new ArrayList<>();
//
//        //判断，osKey是增加 还是减少
//        Map<String,List<String>> results=check(list);
//        if(results.containsKey("add")){
//            //执行新增任务
//            addJod( results.get("add"));
//        }
//        if(results.containsKey("remove")){
//            //执行删除任务
//            removeJod(results.get("remove"));
//        }
//    }
//
//    public void addJod(List<String> oskeys){
//
//        List<XxlJobInfo> dbxxlJobInfoConfigList=xxlJobInfoDao.findJobInfoConfigPublic();
//
//        List<XxlJobInfo> xxlJobInfoList=new ArrayList<>(30);
//        for (XxlJobInfo jobInfo : xxlJobInfoList ) {
//            //查询公共任务的job配置
//            for (String dataos : oskeys) {
//                XxlJobInfo newXxlJobInfo = new XxlJobInfo();
//                BeanUtils.copyProperties(jobInfo, newXxlJobInfo);
//                newXxlJobInfo.setJobInfoGroupParentId(jobInfo.getId());
//                newXxlJobInfo.setExecutorParam(jobInfo.getExecutorParam().replace("{os_key}", dataos));
//                newXxlJobInfo.setJobDesc(jobInfo.getJobDesc() + "任务下发到:" + dataos);
//                xxlJobInfoList.add(newXxlJobInfo);
//            }
//        }
//        int num=xxlJobInfoDao.saveBatch(xxlJobInfoList);
//    }
//
//    public void removeJod(List<String> oskeys){
//        List<Integer> ids=xxlJobInfoDao.findJobInfoConfigPublicId();
//        for (String str:oskeys){
//            xxlJobInfoDao.deleteBatch(ids,str);
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
//}
