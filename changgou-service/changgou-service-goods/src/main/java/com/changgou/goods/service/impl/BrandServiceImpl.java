package com.changgou.goods.service.impl;

import com.changgou.goods.dao.BrandMapper;
import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @ClassName BrandServiceImpl
 * @Description
 * @Author 传智播客
 * @Date 11:54 2020/12/8
 * @Version 2.1
 **/
@Service
public class BrandServiceImpl implements BrandService {

    @Autowired(required = false)
    private BrandMapper brandMapper;

    /**
     * @author 栗子
     * @Description 查询所有的品牌列表
     * @Date 11:53 2020/12/8
     * @param
     * @return java.util.List<com.changgou.goods.pojo.Brand>
     **/
    @Override
    public List<Brand> findAll() {
        return brandMapper.selectAll();
    }

    /**
     * @author 栗子
     * @Description 根据id查询
     * @Date 12:03 2020/12/8
     * @param id
     * @return com.changgou.goods.pojo.Brand
     **/
    @Override
    public Brand findById(Integer id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    /**
     * @author 栗子
     * @Description 保存品牌
     * @Date 12:07 2020/12/8
     * @param brand
     * @return void
     **/
    @Override
    public void save(Brand brand) {
        brandMapper.insertSelective(brand);     // 推荐使用   判断pojo中的属性值是否为null   <if name !=null and name != ''>
//        brandMapper.insert(brand);            // insert into tb_brand(id,name..) values(...)
    }

    /**
     * @author 栗子
     * @Description 更新品牌
     * @Date 12:14 2020/12/8
     * @param brand
     * @return void
     **/
    @Override
    public void update(Brand brand) {
        brandMapper.updateByPrimaryKeySelective(brand);
    }

    /**
     * @author 栗子
     * @Description 根据id删除
     * @Date 12:17 2020/12/8
     * @param id
     * @return void
     **/
    @Override
    public void deleteById(Integer id) {
        brandMapper.deleteByPrimaryKey(id);
    }

    /**
     * @author 栗子
     * @Description 条件查询
     * @Date 14:35 2020/12/8
     * @param brand
     * @return java.util.List<com.changgou.goods.pojo.Brand>
     **/
    @Override
    public List<Brand> search(Brand brand) {
        // 1、封装查询条件
        Example example = createExample(brand);

        // 2、根据条件查询
        List<Brand> list = brandMapper.selectByExample(example);
        return list;
    }

    /**
     * @author 栗子
     * @Description 分页查询
     * @Date 14:53 2020/12/8
     * @param pageNum   当前页码
     * @param pageSize  每页显示的条数
     * @return com.github.pagehelper.PageInfo<com.changgou.goods.pojo.Brand>
     **/
    @Override
    public PageInfo<Brand> search(Integer pageNum, Integer pageSize) {
        // 1、设置分页条件
        PageHelper.startPage(pageNum, pageSize);    // 计算起始行 = （pageNum - 1） * pageSize
        // 2、查询
        List<Brand> list = brandMapper.selectAll();
        // 3、将结果封装到分页对象中
        return new PageInfo<Brand>(list);
    }

    /**
     * @author 栗子
     * @Description
     * @Date 14:59 2020/12/8
     * @param brand      查询条件
     * @param pageNum    分页条件：当前页码
     * @param pageSize   分页条件：每页显示的条数
     * @return com.github.pagehelper.PageInfo<com.changgou.goods.pojo.Brand>
     **/
    @Override
    public PageInfo<Brand> search(Brand brand, Integer pageNum, Integer pageSize) {
        // 1、设置分页条件
        PageHelper.startPage(pageNum, pageSize);
        // 2、设置查询条件
        Example example = createExample(brand);
        // 3、根据条件查询
        List<Brand> list = brandMapper.selectByExample(example);
        // 4、将结果封装到分页对象中
        return new PageInfo<Brand>(list);
    }

    // 封装查询条件
    private Example createExample(Brand brand) {
        Example example = new Example(Brand.class);

        // 封装查询条件
        Example.Criteria criteria = example.createCriteria();   // 封装查询条件的对象
        if (brand != null){
            // 封装品牌名称
            if (!StringUtils.isEmpty(brand.getName())){
                criteria.andLike("name", "%" + brand.getName() + "%"); // 封装查询条件：拼接sql语句  select * from  tb_brand where name like
            }
            // 封装品牌首字母
            if (!StringUtils.isEmpty(brand.getLetter())){
                criteria.andEqualTo("letter", brand.getLetter());    // select * from  tb_brand where letter = H
            }
        }

        return example;
    }
}
