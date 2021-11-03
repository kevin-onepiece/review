package com.doctortech.quartz;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.quartz.domain.SysUserMemberRs;
import com.doctortech.quartz.entity.EmployeeEntity;
import com.doctortech.quartz.entity.UserVO;
import com.doctortech.quartz.listener.EmployeeEntityListener;
import com.doctortech.quartz.mapper.SysUserMemberRsMapper;
import com.doctortech.quartz.service.SysUserMemberRsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class QuartzApplicationTests {

    @Autowired
    private SysUserMemberRsService service;
    @Autowired
    private SysUserMemberRsMapper mapper;

    /**
     * 最简单的读
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link }
     * <p>
     * 2. 由于默认一行行的读取excel，所以需要创建excel一行一行的回调监听器，参照{@link }
     * <p>
     * 3. 直接读即可
     */
    @Test
    public void simpleRead() {

        // 写法4：
        String fileName = "/Users/onepiecekevin/Downloads/广州博士信息技术研究院有限公司_员工导出记录-202110251555292.xlsx";
        // 一个文件一个reader
        ExcelReader excelReader = null;
        try {
            excelReader = EasyExcel.read(fileName, EmployeeEntity.class, new EmployeeEntityListener()).build();
            // 构建一个sheet 这里可以指定名字或者no
            ReadSheet readSheet = EasyExcel.readSheet(0).build();
            // 读取一个sheet
            excelReader.read(readSheet);
        } finally {
            if (excelReader != null) {
                // 这里千万别忘记关闭，读的时候会创建临时文件，到时磁盘会崩的
                excelReader.finish();
            }
        }
    }

    @Test
    public void testMemberRs() {
        //List<SysUserMemberRs> list = service.list();
        Page<SysUserMemberRs> page = new Page<>(1, 50);
        IPage<UserVO> iPage = mapper.pageOfMember(page, null, null, null);
        iPage.getRecords().forEach(System.out::println);
        System.out.println("heyhey");
    }




}
