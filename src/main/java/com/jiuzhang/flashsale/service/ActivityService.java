package com.jiuzhang.flashsale.service;

import java.util.List;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jiuzhang.flashsale.entity.Activity;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author jiuzhang
 * @since 2021-01-15
 */
public interface ActivityService extends IService<Activity> {

    List<Activity> getActivitiesByStatus(Integer activityStatus);

    boolean processOverSell(long activityId);

    boolean hasStock(long activityId);

    boolean lockStock(Long activityId);

    boolean revertStock(Long activityId);

    boolean deductStock(Long activityId);
}