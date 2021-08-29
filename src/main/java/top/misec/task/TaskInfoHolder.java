package top.misec.task;

import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;
import top.misec.apiquery.ApiList;
import top.misec.pojo.userinfobean.Data;
import top.misec.utils.HttpUtil;

/**
 * 任务信息持有类
 *
 * @author cmcc
 * @since 2020-11-22 5:02
 */
@Log4j2
public class TaskInfoHolder {

    public static final String STATUS_CODE_STR = "code";
    public static Data userInfo = null;
    public static GetVideoId getVideoId = new GetVideoId();

    public static void calculateUpgradeDays() {
        if (userInfo == null) {
            log.info("未请求到用户信息，暂无法计算等级相关数据");
            return;
        }

        int todayExp = 15;
        todayExp += expConfirm() * 10;
        log.info("今日获得的总经验值为: " + todayExp);

        int needExp = userInfo.getLevel_info().getNext_exp_asInt()
                - userInfo.getLevel_info().getCurrent_exp();

        if (userInfo.getLevel_info().getCurrent_level() < 6) {
            log.info("按照当前进度，升级到升级到Lv" + (userInfo.getLevel_info().getCurrent_level() + 1) + "还需要: " +
                    (needExp / todayExp) + "天");
        } else {
            log.info("当前等级Lv6，经验值为：" + userInfo.getLevel_info().getCurrent_exp());
        }
    }

    /**
     * 获取当前投币获得的经验值
     *
     * @return 本日已经投了几个币
     */
    public static int expConfirm() {
        JsonObject resultJson = HttpUtil.doGet(ApiList.needCoinNew);
        int getCoinExp = resultJson.get("data").getAsInt();
        return getCoinExp / 10;
    }


    /**
     * 此功能依赖UserCheck
     *
     * @return 返回会员类型
     * 0:无会员（会员过期，当前不是会员）
     * 1:月会员
     * 2:年会员
     */
    public static int queryVipStatusType() {
        if (userInfo == null) {
            log.info("暂时无法查询会员状态，默认非大会员");
        }
        if (userInfo != null && userInfo.getVipStatus() == 1) {
            //只有VipStatus为1的时候获取到VipType才是有效的。
            return userInfo.getVipType();
        } else {
            return 0;
        }
    }

}
