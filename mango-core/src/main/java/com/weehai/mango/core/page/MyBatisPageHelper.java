package com.weehai.mango.core.page;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.weehai.mango.core.service.CurdService;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 卢水柏
 * @company WeeHai
 * @date 2019/12/25 4:10 下午
 **/public class MyBatisPageHelper {


    /**
     * 调用分页插件进行分页查询
     * @param pageRequest 分页请求
     * @param T mapper 泛型
     * @param mapper Dao对象，MyBatis的 Mapper
     * @param mapper 方法参数
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static PageResult findPage(PageRequest pageRequest, Class T, BaseMapper mapper ) {
        // 设置分页参数
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();

        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.orderByDesc("id");

        IPage<T> page = new Page<>(pageNum, pageSize);
        IPage<Object> mapIPage = mapper.selectMapsPage(page,wrapper);

        return getPageResult(pageRequest, mapIPage);
    }

    /**
     * 将分页信息封装到统一的接口
     * @param pageRequest
     * @param pageInfo
     * @return
     */
    private static PageResult getPageResult(PageRequest pageRequest, IPage<?> pageInfo) {
        PageResult pageResult = new PageResult();
        pageResult.setPageNum((int) pageInfo.getPages());
        pageResult.setPageSize((int) pageInfo.getSize());
        pageResult.setTotalSize(pageInfo.getTotal());
        pageResult.setTotalPages((int) pageInfo.getPages());
        pageResult.setContent(pageInfo.getRecords());
        return pageResult;
    }


}
