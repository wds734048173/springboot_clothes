package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Size;

import java.util.List;

public interface ISizeService {
    //店铺查询出自己所有的尺码

    public List<Size> getSizeAll(Condition condition);
    //增
    public void addSize(Size size);
    //删
    public void removeSize(int sizeId);
    //改
    public void modifySize(Size size);
    //获取尺码总数
    public int getSizeCount(Condition condition);

    public List<Size> getSizeSelectedList(int storeId);

    public Size getSizeById(int id);

    public List<Size> getSizeListByIds(List<Integer> ids);
}
