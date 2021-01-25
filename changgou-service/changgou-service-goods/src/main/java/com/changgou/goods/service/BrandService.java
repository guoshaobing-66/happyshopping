package com.changgou.goods.service;

import com.changgou.goods.pojo.Brand;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @ClassName BrandService
 * @Description
 * @Author 传智播客
 * @Date 11:53 2020/12/8
 * @Version 2.1
 **/
public interface BrandService {

    /**
     * @author 栗子
     * @Description 查询所有的品牌列表
     * @Date 11:53 2020/12/8
     * @param
     * @return java.util.List<com.changgou.goods.pojo.Brand>
     **/
    List<Brand> findAll();

    /**
     * @author 栗子
     * @Description 根据id查询
     * @Date 12:03 2020/12/8
     * @param id
     * @return com.changgou.goods.pojo.Brand
     **/
    Brand findById(Integer id);

    /**
     * @author 栗子
     * @Description 保存品牌
     * @Date 12:07 2020/12/8
     * @param brand
     * @return void
     **/
    void save(Brand brand);

    /**
     * @author 栗子
     * @Description 更新品牌
     * @Date 12:14 2020/12/8
     * @param brand
     * @return void
     **/
    void update(Brand brand);

    /**
     * @author 栗子
     * @Description 根据id删除
     * @Date 12:17 2020/12/8
     * @param id
     * @return void
     **/
    void deleteById(Integer id);

    /**
     * @author 栗子
     * @Description 条件查询
     * @Date 14:35 2020/12/8
     * @param brand
     * @return java.util.List<com.changgou.goods.pojo.Brand>
     **/
    List<Brand> search(Brand brand);

    /**
     * @author 栗子
     * @Description 分页查询
     * @Date 14:53 2020/12/8
     * @param pageNum   当前页码
     * @param pageSize  每页显示的条数
     * @return com.github.pagehelper.PageInfo<com.changgou.goods.pojo.Brand>
     **/
    PageInfo<Brand> search(Integer pageNum, Integer pageSize);

    /**
     * @author 栗子
     * @Description
     * @Date 14:59 2020/12/8
     * @param brand      查询条件
     * @param pageNum    分页条件：当前页码
     * @param pageSize   分页条件：每页显示的条数
     * @return com.github.pagehelper.PageInfo<com.changgou.goods.pojo.Brand>
     **/
    PageInfo<Brand> search(Brand brand, Integer pageNum, Integer pageSize);
}
