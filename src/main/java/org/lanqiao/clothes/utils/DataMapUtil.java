package org.lanqiao.clothes.utils;

import org.lanqiao.clothes.mapper.BrandMapper;
import org.lanqiao.clothes.mapper.ColorMapper;
import org.lanqiao.clothes.mapper.GoodsClassMapper;
import org.lanqiao.clothes.mapper.SizeMapper;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: WDS
 * @Date: 2019/1/11 14:55
 * @Description:
 */
public class DataMapUtil {
    private static DataMapUtil dataMapUtil;

    //品牌
    private BrandMapper brandMapper;
    //颜色
    private ColorMapper colorMapper;
    //尺码
    private SizeMapper sizeMapper;

    /*public static Map<Integer,String> getBrandMap(){
        Map<Integer,String> brandMap = new HashMap<>();
        DeptMapper deptMapper = dataMapUtil.deptMapper;
        List<Dept> deptList = deptMapper.getAll(null);
        for (Dept dept : deptList){
            deptMap.put(dept.getDeptno(),dept.getDname());
        }
        return deptMap;
    }

    public static   Map<Integer,String> getEmpMap(){
        Map<Integer,String> empMap = new HashMap<>();
        EmpMapper empMapper = dataMapUtil.empMapper;
        List<Emp> empList = empMapper.getAll(null);
        for(Emp emp : empList){
            empMap.put(emp.getEmpno(),emp.getEname());
        }
        return empMap;
    }*/

    //注：@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
    // PostConstruct在构造函数之后执行,init()方法之前执行。
    // PreDestroy（）方法在destroy()方法执行执行之后执行
    /*@PostConstruct
    public void init(){
        dataMapUtil = this;
        dataMapUtil.brandMapper = this.brandMapper;
        dataMapUtil.colorMapper = this.colorMapper;
        dataMapUtil.sizeMapper = this.sizeMapper;
    }*/
}
