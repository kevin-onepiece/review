package com.doctortech.quartz.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.doctortech.quartz.dao.TaskConfigDao;
import com.doctortech.quartz.entity.TaskConfigEntity;
import lombok.extern.log4j.Log4j2;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
public class ScheduleJobService {

    @Autowired
    private TaskConfigDao taskConfigDao;

    @Autowired
    private Scheduler scheduler;

    /**
     * 程序启动开始加载定时任务
     */
    public void startJob(){
        List<TaskConfigEntity> taskConfigEntities = taskConfigDao.selectList(
                Wrappers.<TaskConfigEntity>lambdaQuery()
                        .eq(TaskConfigEntity::getStatus, 1));
        if (taskConfigEntities == null || taskConfigEntities.size() == 0){
            log.error("定时任务加载数据为空");
            return;
        }
        for (TaskConfigEntity configEntity : taskConfigEntities) {
            CronTrigger cronTrigger = null;
            JobDetail jobDetail = null;
            try {
                cronTrigger = getCronTrigger(configEntity);
                jobDetail = getJobDetail(configEntity);
                scheduler.scheduleJob(jobDetail,cronTrigger);
                log.info("编号：{}定时任务加载成功",configEntity.getTaskId());
            }catch (Exception e){
                log.error("编号：{}定时任务加载失败",configEntity.getTaskId());
            }

        }
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            log.error("定时任务启动失败",e);
        }
    }

    /**
     * 停止任务
     * @param taskId
     */
    public void stopJob(String taskId) throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey(taskId));
    }

    /**
     * 恢复任务
     * @param taskId
     * @throws SchedulerException
     */
    public void resumeJob(String taskId) throws SchedulerException {
        scheduler.resumeJob(JobKey.jobKey(taskId));
    }

    /**
     * 添加新的job
     * @param taskId
     * @throws SchedulerConfigException
     */
    public void loadJob(String taskId) throws SchedulerConfigException {
        TaskConfigEntity taskConfigEntity = taskConfigDao.selectOne(
                Wrappers.<TaskConfigEntity>lambdaQuery()
                        .eq(TaskConfigEntity::getTaskId, taskId)
                        .eq(TaskConfigEntity::getStatus, 1));
        if (taskConfigEntity == null){
            throw new SchedulerConfigException("未找到相关Job配置");
        }
        try {
            JobDetail jobDetail = getJobDetail(taskConfigEntity);
            CronTrigger cronTrigger = getCronTrigger(taskConfigEntity);
            scheduler.scheduleJob(jobDetail, cronTrigger);
        } catch (Exception e) {
            log.error("加载定时任务异常",e);
            throw new SchedulerConfigException("加载定时任务异常", e);
        }
    }
    public void unloadJob(String taskId) throws SchedulerException {
        // 停止触发器
        scheduler.pauseTrigger(TriggerKey.triggerKey(taskId));
        // 卸载定时任务
        scheduler.unscheduleJob(TriggerKey.triggerKey(taskId));
        // 删除原来的job
        scheduler.deleteJob(JobKey.jobKey(taskId));
    }

    /**
     * 重新加载执行计划
     * @throws Exception
     */
    public void reload(String taskId) throws Exception {
        TaskConfigEntity taskConfigEntity = taskConfigDao.selectOne(
                Wrappers.<TaskConfigEntity>lambdaQuery()
                        .eq(TaskConfigEntity::getTaskId, taskId)
                        .eq(TaskConfigEntity::getStatus, 1));

        String jobCode = taskConfigEntity.getTaskId();
        // 获取以前的触发器
        TriggerKey triggerKey = TriggerKey.triggerKey(jobCode);
        // 停止触发器
        scheduler.pauseTrigger(triggerKey);
        // 删除触发器
        scheduler.unscheduleJob(triggerKey);
        // 删除原来的job
        scheduler.deleteJob(JobKey.jobKey(jobCode));

        JobDetail jobDetail = getJobDetail(taskConfigEntity);
        CronTrigger cronTrigger = getCronTrigger(taskConfigEntity);
        // 重新加载job
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }
    //组装JobDetail
    private JobDetail getJobDetail(TaskConfigEntity configEntity) throws ClassNotFoundException {

        Class<? extends Job> aClass = Class.forName(configEntity.getClassName()).asSubclass(Job.class);

        return JobBuilder.newJob()
                .withIdentity(JobKey.jobKey(configEntity.getTaskId()))
                .withDescription(configEntity.getDescription())
                .ofType(aClass).build();
    }
    //组装CronTrigger
    private CronTrigger getCronTrigger(TaskConfigEntity configEntity){
        CronTrigger cronTrigger = null;
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(configEntity.getCron());
        cronTrigger = TriggerBuilder.newTrigger()
                .withIdentity(TriggerKey.triggerKey(configEntity.getTaskId()))
                .withSchedule(cronScheduleBuilder)
                .build();
        return cronTrigger;
    }
}
