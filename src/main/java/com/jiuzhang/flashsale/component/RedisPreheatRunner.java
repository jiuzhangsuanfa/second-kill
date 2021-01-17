package com.jiuzhang.flashsale.component;

import java.util.List;

import javax.annotation.Resource;

import com.jiuzhang.flashsale.entity.ActivityEntity;
import com.jiuzhang.flashsale.enums.ActivityStatus;
import com.jiuzhang.flashsale.service.ActivityService;
import com.jiuzhang.flashsale.service.impl.RedisServiceImpl;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** 商品预热，从数据库到 Redis 缓存 */
@Component
public class RedisPreheatRunner implements ApplicationRunner {

    @Resource
    RedisServiceImpl redisService;

    @Resource
    ActivityService activityService;

    /**
     * 将商品库存同步到 redis 中
     *
     * @param args
     */
    @Override
    public void run(ApplicationArguments args) {
        List<ActivityEntity> activities = activityService.getActivitiesByStatus(ActivityStatus.NORMAL);
        for (ActivityEntity activity : activities) {
            redisService.setValue("stock:" + activity.getId(), activity.getAvailableStock());
        }
    }

}
