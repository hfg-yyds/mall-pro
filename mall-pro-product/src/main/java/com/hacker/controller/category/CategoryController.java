package com.hacker.controller.category;

import java.util.Arrays;
import java.util.List;

import cn.hutool.core.collection.ListUtil;
import com.hacker.cache.CategoryCacheDao;
import com.hacker.cache.CategoryCacheObject;
import com.hacker.common.annotation.SystemLog;
import com.hacker.common.result.R;
import com.hacker.entity.CategoryEntity;
import com.hacker.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zero
 * @Date: 2022/5/19 14:30
 * @Description: 商品分类
 */
@RestController
@RequestMapping("product/category")
@Api(tags = "商品三级分类")
public class CategoryController {
    private String ID = "CATEGORY_KEY";
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryCacheDao categoryCacheDao;
    /**
     * 查出所有分类以及子分类，以树形结构组装起来
     */
    @GetMapping("/list/tree")
    @ApiOperation(value = "查出所有分类以及子分类，以树形结构组装起来")
    public R<List<CategoryEntity>> list() {
//        CategoryEntity categoryEntity = this.categoryCacheDao.get(ID);
        List<CategoryEntity> categoryEntities = categoryService.listWithTree();
        return R.ok(categoryEntities);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{catId}")
    @SystemLog
    public R<?> info(@PathVariable("catId") Long catId) {
        return R.run(() -> categoryService.getById(catId));
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation(value = "添加商品分类菜单")
    public R<?> save(@RequestBody CategoryEntity category) {
        return R.run(() -> categoryService.save(category));
    }

    @PostMapping("/update/sort")
    public R<?> updateSort(@RequestBody CategoryEntity[] category) {
        return R.run(() -> categoryService.updateBatchById(Arrays.asList(category)));
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public R<?> update(@RequestBody CategoryEntity category) {
        return R.run(() -> categoryService.updateCascade(category));
    }

    /**
     * 删除
     * SpringMVC自动将请求体的数据（json）,转为对应的对象
     *
     * @param catIds 获取请求体，必须发送POST请求
     * @return R
     */
    @PostMapping("/delete")
    public R<?> delete(@RequestBody Long[] catIds) {
        return R.run(() -> categoryService.removeMenuByIds(ListUtil.toList(catIds)));
    }

}
