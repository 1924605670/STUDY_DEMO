package com.study.admin.notify;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import de.codecentric.boot.admin.server.domain.entities.Instance;
import de.codecentric.boot.admin.server.domain.entities.InstanceRepository;
import de.codecentric.boot.admin.server.domain.events.InstanceEvent;
import de.codecentric.boot.admin.server.domain.events.InstanceStatusChangedEvent;
import de.codecentric.boot.admin.server.notify.AbstractStatusChangeNotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.thymeleaf.expression.Lists;
import org.thymeleaf.expression.Maps;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class DiyStatusChangeNotifier extends AbstractStatusChangeNotifier {

    public DiyStatusChangeNotifier(InstanceRepository repository) {
        super(repository);
    }

    @Override
    protected Mono<Void> doNotify(InstanceEvent event, Instance instance) {
        return Mono.fromRunnable(() -> {
            if (event instanceof InstanceStatusChangedEvent) {
                log.info("Instance {} ({}) is {}", instance.getRegistration().getName(), event.getInstance(),
                        ((InstanceStatusChangedEvent) event).getStatusInfo().getStatus());
                //钉钉机器人地址（配置机器人的webhook）
                String dingUrl = "https://oapi.dingtalk.com/robot/send?access_token=75706561ff45b2db6d2d2eba8a38810abc0c7c2fdb559bbc5a08d15e6e03dd1d";

                //是否通知所有人
                boolean isAtAll = true;

                //钉钉机器人消息内容
                String content = "robot 小哥，你好！";
                //组装请求内容
                String reqStr = buildReqStr(content, isAtAll,null);

                //推送消息（http请求）
                String result = HttpUtil.post(dingUrl, reqStr);
                System.out.println("result == " + result);
            } else {
                log.info("Instance {} ({}) {}", instance.getRegistration().getName(), event.getInstance(),
                        event.getType());
            }
        });
    }

    /**
     * 组装请求报文
     * @param content
     * @return
     */
    private static String buildReqStr(String content, boolean isAtAll, List<String> mobileList) {
        //消息内容
        Map<String, String> contentMap = new HashMap<>();
        contentMap.put("content", content);

        //通知人
        Map<String, Object> atMap = new HashMap<>();
        //1.是否通知所有人
        atMap.put("isAtAll", isAtAll);
//        //2.通知具体人的手机号码列表
//        atMap.put("atMobiles", mobileList);

        Map<String, Object> reqMap = new HashMap<>();
        reqMap.put("msgtype", "text");
        reqMap.put("text", contentMap);
        reqMap.put("at", atMap);

        return JSONUtil.toJsonStr(reqMap);
    }


}
