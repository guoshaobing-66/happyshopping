package com.changgou.goods.controller;

import com.changgou.goods.pojo.Brand;
import com.changgou.goods.service.BrandService;
import com.github.pagehelper.PageInfo;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName BrandController
 * @Description
 * @Author 传智播客
 * @Date 11:55 2020/12/8
 * @Version 2.1
 **/
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    /**
     * @author 栗子
     * @Description 查询所有的品牌列表
     * @Date 11:57 2020/12/8
     * @param
     * @return entity.Result<java.util.List<com.changgou.goods.pojo.Brand>>
     **/
    @GetMapping
    public Result<List<Brand>> findAll(){
        List<Brand> list = brandService.findAll();
        int i = 1/0;
        return new Result<>(true, StatusCode.OK, "品牌列表查询成功", list);
    }

    /**
     * @author 栗子
     * @Description 根据id查询
     * @Date 12:06 2020/12/8
     * @param id
     * @return entity.Result
     **/
    @GetMapping("/{id}")
    public Result findById(@PathVariable(value = "id") Integer id){
        Brand brand = brandService.findById(id);
        return new Result<>(true, StatusCode.OK, "品牌id查询成功", brand);
    }

    /**
     * @author 栗子
     * @Description 保存品牌
     * @Date 12:11 2020/12/8
     * @param brand
     * @return entity.Result
     **/
    @PostMapping
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return new Result(true, StatusCode.OK, "保存品牌成功");
    }

    /**
     * @author 栗子
     * @Description 品牌更新
     * @Date 12:15 2020/12/8
     * @param id
     * @param brand
     * @return entity.Result
     **/
    @PutMapping("/{id}")
    public Result update(@PathVariable(value = "id") Integer id, @RequestBody Brand brand){
        brand.setId(id);
        brandService.update(brand);
        return new Result(true, StatusCode.OK, "更新品牌成功");
    }

    /**
     * @author 栗子
     * @Description 根据id删除
     * @Date 12:18 2020/12/8
     * @param id
     * @return entity.Result
     **/
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable(value = "id") Integer id){
        brandService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除品牌成功");
    }

    /**
     * @author 栗子
     * @Description 条件查询
     * @Date 14:44 2020/12/8
     * @param brand
     * @return entity.Result
     **/
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Brand brand){
        List<Brand> list = brandService.search(brand);
        return new Result(true, StatusCode.OK, "品牌条件成功", list);
    }

    /**
     * @author 栗子
     * @Description 分页查询
     * @Date 14:57 2020/12/8
     * @param pageNum
     * @param pageSize
     * @return entity.Result
     **/
    @GetMapping("/search/{pageNum}/{pageSize}")
    public Result search(@PathVariable(value = "pageNum") Integer pageNum, @PathVariable(value = "pageSize") Integer pageSize){
        PageInfo<Brand> pageInfo = brandService.search(pageNum, pageSize);
        return new Result(true, StatusCode.OK, "分页查询成功", pageInfo);
    }

    /**
     * @author 栗子
     * @Description 分页和条件查询
     * @Date 15:02 2020/12/8
     * @param brand     查询条件
     * @param pageNum   分页条件
     * @param pageSize  分页条件
     * @return entity.Result
     **/
    @PostMapping("/search/{pageNum}/{pageSize}")
    public Result search(@RequestBody(required = false) Brand brand,
                         @PathVariable(value = "pageNum") Integer pageNum,
                         @PathVariable(value = "pageSize") Integer pageSize){
        PageInfo<Brand> pageInfo = brandService.search(brand, pageNum, pageSize);
        return new Result(true, StatusCode.OK, "分页加条件查询成功", pageInfo);
    }
}
