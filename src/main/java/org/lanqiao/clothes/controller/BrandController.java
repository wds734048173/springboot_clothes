package org.lanqiao.clothes.controller;

import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.service.IBrandService;
import org.lanqiao.clothes.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 15:56
 * @Description:
 */
@Controller
@EnableAutoConfiguration
public class BrandController {
    @Autowired
    IBrandService brandService;
    @RequestMapping("manager/addBrand")
    public String addBrand(HttpServletRequest req, HttpServletResponse resp, Model model){
        Brand brand = Brand.builder().build();
        String name = req.getParameter("brandName");
        int state = Integer.valueOf(req.getParameter("brandState"));
        String pic = req.getParameter("pic");
        /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        int storeId = 1;
        brand.setName(name);
        brand.setPic(pic);
        brand.setState(state);
        brand.setStoreId(storeId);
        brandService.addBrand(brand);
        return brandList(req,resp,model);
    }

    @RequestMapping("/manager/brandList")
    public String brandList(HttpServletRequest req, HttpServletResponse resp, Model model){
        int pageNum = 1;
        if(req.getParameter("currentPage") != null){
            pageNum = Integer.valueOf(req.getParameter("currentPage"));
        }
        int pageSize = 5;
        if(req.getParameter("pageSize") != null){
            pageSize = Integer.valueOf(req.getParameter("pageSize"));
        }

        //查询条件
        String searchBrandName = "";
        if(req.getParameter("searchBrandName") != null){
            searchBrandName = req.getParameter("searchBrandName");
        }
        String searchBrandState = "";
        if(req.getParameter("searchBrandState") != null){
            searchBrandState = req.getParameter("searchBrandState");
        }
        Condition condition = new Condition();
        condition.setName(searchBrandName);
        condition.setState(searchBrandState);
        int totalRecords = brandService.getBrandCount(condition);
        //不同操作，不同的当前页设置
        PageModel pm = new PageModel(pageNum,totalRecords,pageSize);
        String method = req.getParameter("method");
        if("add".equals(method)){
            pageNum = pm.getEndPage();
        }else{
            //如果当前页大于总页数，但是排除查询不到数据的情况。当前页等于最大页
            if(pageNum > pm.getTotalPageNum() && pm.getTotalPageNum() != 0){
                pageNum = pm.getTotalPageNum();
            }
        }
        PageModel pageModel = new PageModel(pageNum,totalRecords,pageSize);
        //分页条件封装
        condition.setCurrentPage(pageModel.getStartIndex());
        condition.setPageSize(pageModel.getPageSize());
        List<Brand> brandList = brandService.getBrandAll(condition);
        model.addAttribute("brandList",brandList);
        model.addAttribute("pm",pageModel);
        model.addAttribute("condition",condition);
        model.addAttribute("currentPage",pageNum);
        return "/manager/brandList";
    }

    @RequestMapping("/manager/deleteBrand")
    public String deleteBrand(HttpServletRequest req, HttpServletResponse resp, Model model){
        String brandId = req.getParameter("brandId");
        brandService.removeBrandById(Integer.valueOf(brandId));
        return brandList(req,resp,model);
    }

    @RequestMapping("/manager/updateBrandById")
    public String updateBrandById(HttpServletRequest req, HttpServletResponse resp, Model model){
        Brand brand = Brand.builder().build();
        String id = req.getParameter("brandId");
        String name = req.getParameter("brandName");
        int state = Integer.valueOf(req.getParameter("brandState"));
        String pic = req.getParameter("pic");
         /*HttpSession session = req.getSession();
        int storeId = Integer.valueOf(session.getAttribute("storeId").toString());*/
        int storeId = 1;
        brand.setId(Integer.valueOf(id));
        brand.setName(name);
        brand.setPic(pic);
        brand.setState(state);
        brand.setStoreId(storeId);
        brandService.modifyBrandById(brand);
        return brandList(req,resp,model);
    }

    @RequestMapping("/manager/selectBrandById")
    @ResponseBody
    public Brand selectBrandById(HttpServletRequest req, HttpServletResponse resp){
        String brandId = req.getParameter("brandId");
        Brand brand = brandService.getBrandById(Integer.valueOf(brandId));
        return brand;
    }
}
