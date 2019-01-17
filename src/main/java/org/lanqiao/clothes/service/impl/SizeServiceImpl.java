package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.SizeMapper;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.pojo.Size;
import org.lanqiao.clothes.service.ISizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("sizeService")
public class SizeServiceImpl implements ISizeService {
    @Autowired
    SizeMapper sizeMapper;


    @Override
    public List<Size> getSizeAll(Condition condition) {
        List<Size> sizeList = sizeMapper.selectSizeAll(condition);
        for (Size size : sizeList){
            int state = size.getState();
            if (state==0){
                size.setStateStr("启用");
            }else if(state==1){
                size.setStateStr("停用");
            }
        }
        return sizeList;
    }

    @Override
    public void addSize(Size size) {
        sizeMapper.insertSize(size);
    }

    @Override
    public void removeSize(int sizeId) {
        sizeMapper.deleteSize(sizeId);
    }

    @Override
    public void modifySize(Size size) {
        sizeMapper.updateSize(size);
    }

    @Override
    public int getSizeCount(Condition condition) {
        return sizeMapper.selectSizeCount(condition);
    }

    @Override
    public List<Size> getSizeSelectedList(int storeId) {
        return sizeMapper.selectSizeSelectedList(storeId);
    }

    @Override
    public Size getSizeById(int id) {
        return sizeMapper.selectSizeById(id);
    }
}
