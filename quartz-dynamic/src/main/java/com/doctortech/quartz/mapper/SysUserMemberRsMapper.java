package com.doctortech.quartz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.doctortech.quartz.domain.SysUserMemberRs;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.doctortech.quartz.entity.UserVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Entity com.doctortech.quartz.domain.SysUserMemberRs
 */
@Mapper
public interface SysUserMemberRsMapper extends BaseMapper<SysUserMemberRs> {

    IPage<UserVO> pageOfMember(@Param("page") Page page, @Param("keyword") String keyword, @Param("openMode") Integer openMode, @Param("ids") List<String> ids);

}




