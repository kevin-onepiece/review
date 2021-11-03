package com.doctortech.quartz.listener;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.fastjson.JSON;

import com.doctortech.quartz.entity.EmployeeEntity;
import com.doctortech.quartz.mapper.EmployeeMapper;
import com.doctortech.quartz.service.EmployeeService;
import com.doctortech.quartz.utils.RequestUtils;
import com.doctortech.quartz.utils.SpringBeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: foo
 * @Date: 2021-10-26 13:52
 * @description:
 */
// 有个很重要的点 EmployeeEntityListener 不能被spring管理，要每次读取excel都要new,然后里面用到spring可以构造方法传进去
public class EmployeeEntityListener extends AnalysisEventListener<EmployeeEntity> {
    private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeEntityListener.class);
    /**
     * 每隔5条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 3000;
    /**
     * 缓存的数据
     */
    private List<EmployeeEntity> list = new ArrayList<>(BATCH_COUNT);
    /**
     * 假设这个是一个DAO，当然有业务逻辑这个也可以是一个service。当然如果不用存储这个对象没用。
     */
    //private DemoDAO demoDAO;

    //private EmployeeMapper employeeMapper;
    private EmployeeService employeeService;
    /*public EmployeeEntityListener() {
        // 这里是demo，所以随便new一个。实际使用如果到了spring,请使用下面的有参构造函数
        employeeService = new EmployeeService();
    }*/

    /**
     * 如果使用了spring,请使用这个构造方法。每次创建Listener的时候需要把spring管理的类传进来
     *
     * @param demoDAO
     */
    /*public EmployeeEntityListener(DemoDAO demoDAO) {
        this.demoDAO = demoDAO;
    }*/

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(EmployeeEntity data, AnalysisContext context) {
        LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
        list.add(data);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            //saveData();
            // 存储完成清理 list
            list = new ArrayList<>(BATCH_COUNT);
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        // saveData();
        // 拿出名字去查正式数据库
        //employeeMapper = SpringBeanUtils.getBean(EmployeeMapper.class);
        //employeeMapper = new e
        //employeeService = new EmployeeService();
        employeeService = SpringBeanUtils.getBean(EmployeeService.class);
        RequestUtils requestUtils = new RequestUtils();
        list.forEach(employee -> {
            int count = employeeService.count(employee.getName());
            if (count < 1) {
                System.out.println("count : " + count);
                System.out.println(employee.getName());

                String id = requestUtils.sendRequest(employee.getName());
                long userId = 0;
                try {
                    userId = employeeService.selectUserId(employee.getName());
                } catch (Exception e) {
                    System.out.println("sys_user null:" + employee.getName());
                    //e.printStackTrace();
                }
                employeeService.insert(id, userId);
            }

        });

        LOGGER.info("所有数据解析完成！");
    }

    /**
     * 加上存储数据库
     */
    /*private void saveData() {
        LOGGER.info("{}条数据，开始存储数据库！", list.size());
        demoDAO.save(list);
        LOGGER.info("存储数据库成功！");
    }*/
}
