package com.baizhi.bqs.controller;

import com.baizhi.bqs.entity.Category;
import com.baizhi.bqs.entity.User;
import com.baizhi.bqs.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.HashMap;

@Controller
@RequestMapping("category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    //查询
    @ResponseBody
    @RequestMapping("queryByOnePage")
    public HashMap<String,Object> queryByOnePage(Integer page, Integer rows){
        HashMap<String, Object> map = categoryService.queryByOnePage(page,rows);
        return map;
    }

    //查询
    @ResponseBody
    @RequestMapping("queryByTwoPage")
    public HashMap<String,Object> queryByTwoPage(Integer page, Integer rows,String parentId){
        return categoryService.queryByTwoPage(page, rows, parentId);

    }

    //修改
    @ResponseBody
    @RequestMapping("edit")//-------------------oper操作
    public Object edit(Category category,String oper){

        if (oper.equals("add")){
            categoryService.add(category);
        }
        if (oper.equals("edit")){
            categoryService.update(category);
            System.out.println("categoryccccc = " + category);
        }
        if (oper.equals("del")){
            HashMap<String, Object> map = categoryService.delete(category);
            System.out.println(map.get("message"));
            return map;
        }
        return null;
    }
}
