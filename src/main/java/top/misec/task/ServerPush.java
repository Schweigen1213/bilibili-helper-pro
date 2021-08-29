package top.misec.task;

import com.google.gson.JsonObject;
import lombok.extern.log4j.Log4j2;
import top.misec.apiquery.ApiList;
import top.misec.login.ServerVerify;
import top.misec.utils.HttpUtil;
import top.misec.utils.LoadFileResource;

/**
 * @author cmcc
 * @create 2020/10/21 17:39
 */

@Log4j2
public class ServerPush {

    private String pushToken = null;

    public void pushMsg(String text, String desp) {
        if (pushToken == null) {
            pushToken = ServerVerify.getFtkey();
        }

        String url = ApiList.ServerPush + pushToken + ".send";

        String pushBody = "text=" + text + "&desp=" + desp;
        int retryTimes = 3;
        while (retryTimes > 0) {
            retryTimes--;
            try {
                JsonObject jsonObject = HttpUtil.doPost(url, pushBody);
                if (jsonObject != null && "success".equals(jsonObject.get("errmsg").getAsString())) {
                    log.info("任务状态推送成功");
                    break;
                } else {
                    log.info("任务状态推送失败，开始第{}次重试", 3 - retryTimes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    public static void doServerPush() {
        if (ServerVerify.getFtkey() != null && ServerVerify.getChatId() == null) {
            ServerPush serverPush = new ServerPush();
            serverPush.pushMsg("BILIBILI-HELPER任务简报", LoadFileResource.loadFile("logs/daily.log"));
            log.info("本次执行推送日志到微信");
        } else if(ServerVerify.getFtkey() != null && ServerVerify.getChatId() != null){
            ServerPush serverPush = new ServerPush();
            serverPush.pushTelegramMsg("BILIBILI-HELPER任务简报\n" + LoadFileResource.loadFile("logs/daily.log"));
            log.info("本次执行推送日志到Telegram");
        }else{
            log.info("未配置server酱,本次执行不推送日志到微信");
            log.info("未配置Telegram,本次执行不推送日志到Telegram");
        }

    }

    private void pushTelegramMsg(String text) {
        String url = ApiList.ServerPushTelegram + ServerVerify.getFtkey() + "/sendMessage";

        String pushBody = "chat_id="+ServerVerify.getChatId() + "&text=" + text;
        int retryTimes = 3;
        while (retryTimes > 0) {
            retryTimes--;
            try {
                JsonObject jsonObject = HttpUtil.doPost(url, pushBody);
                if (jsonObject != null && "true".equals(jsonObject.get("ok").getAsString())) {
                    log.info("任务状态推送Telegram成功");
                    break;
                } else {
                    log.info("任务状态推送Telegram失败，开始第{}次重试", 3 - retryTimes);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void addOtherMsg(String msg) {
        log.info(msg);
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

}
