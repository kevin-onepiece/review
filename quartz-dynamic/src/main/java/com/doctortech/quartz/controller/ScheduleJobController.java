package com.doctortech.quartz.controller;

import com.doctortech.quartz.service.ScheduleJobService;
import org.quartz.SchedulerConfigException;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ScheduleJobController {
    @Autowired
    private ScheduleJobService jobService;

    @GetMapping("/load/{taskId}")
    public String loadJob(@PathVariable("taskId") String taskId){
        try {
            jobService.loadJob(taskId);
        } catch (SchedulerConfigException e) {
            return "导入定时任务失败";
        }
        return "成功";
    }
    @GetMapping("/resume/{taskId}")
    public String resumeJob(@PathVariable("taskId") String taskId){
        try {
            jobService.resumeJob(taskId);
        }catch (SchedulerException e) {
            return "恢复定时任务失败";
        }
        return "成功";
    }
    @GetMapping("/stop/{taskId}")
    public String stopJob(@PathVariable("taskId") String taskId){
        try {
            jobService.stopJob(taskId);
        }catch (SchedulerException e) {
            return "暂停定时任务失败";
        }
        return "成功";
    }
    @GetMapping("/unload/{taskId}")
    public String unloadJob(@PathVariable("taskId") String taskId){
        try {
            jobService.unloadJob(taskId);
        }catch (SchedulerException e) {
            return "卸载定时任务失败";
        }
        return "成功";
    }
}
