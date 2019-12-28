//package checkos;
//
//import com.botbrain.timer.core.model.OsCheckEntity;
//import com.botbrain.timer.core.model.XxlJobInfo;
//import com.botbrain.timer.dao.XxlJobInfoDao;
//import com.google.gson.JsonObject;
//import com.google.gson.JsonParser;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.BeanUtils;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.StringUtils;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///** 删除 {"osk":"666888","isAdd":false,"osType":3}
// * add {"osk":"666888","isAdd":true,"osType":3}
// * @Copyright：botBrain.ai
// * @Author: WangMingChao
// * @Date: 2019/12/10.
// * @Description: oskey新增或删除后的任务同步
// */
//@Configuration
//@Slf4j
//public class CheckOs {
//    @Resource
//    private XxlJobInfoDao xxlJobInfoDao;
//
//    public void initJudge(OsCheckEntity osCheckEntity) {
//        log.info("success to check oskey judge:{}", osCheckEntity);
//        if (osCheckEntity.getIsAdd()) {
//            //add
//            add(osCheckEntity.getOsk(), osCheckEntity.getOsType());
//        } else {
//            //delete
//            delete(osCheckEntity.getOsk());
//        }
//    }
//
//    /**
//     * 添加任务
//     *
//     * @param oskey  公司标识
//     * @param osType ostype
//     */
//    private void add(String oskey, Integer osType) {
//        //获取type对应的任务类型的配置
//        List<XxlJobInfo> xxlJobInfoLIst = xxlJobInfoDao.findJobConfigByOsType(osType);
//        log.info("get all config job  by  type :{}", xxlJobInfoLIst);
//        try {
//            for (XxlJobInfo xxlJobInfo : xxlJobInfoLIst) {
//                if (xxlJobInfo.getJobInfoGroupParentId() == -1) {
//                    XxlJobInfo x = new XxlJobInfo();
//                    BeanUtils.copyProperties(xxlJobInfo, x);
//                    x.setJobInfoGroupParentId(xxlJobInfo.getId());
//                    x.setJobDesc(xxlJobInfo.getJobDesc() + ":" + oskey);
//                    JsonObject jsonObject = new JsonParser().parse(xxlJobInfo.getExecutorParam()).getAsJsonObject();
//                    x.setExecutorParam(StringUtils.replace(jsonObject.get("url").getAsString(), "{os_key}", oskey));
//                    //这已经是具体任务了   不是配置
//                    x.setOsType(null);
//                    //默认启动该任务；
//                    x.setTriggerStatus(0);
//                    xxlJobInfoDao.save(x);
//                }
//            }
//        } catch (Exception e) {
//            log.error("add new oskey meet error..", e);
//        }
//    }
//
//    /**
//     * 删除
//     *
//     * @param oskey
//     */
//    private void delete(String oskey) {
//        //通过os 遍历删除
//        xxlJobInfoDao.removeJobs(oskey);
//        log.info("delete osk:{},by listening ", oskey);
//    }
//}
