package org.lanqiao.clothes.service.impl;

import org.lanqiao.clothes.mapper.ColorMapper;
import org.lanqiao.clothes.pojo.Brand;
import org.lanqiao.clothes.pojo.Color;
import org.lanqiao.clothes.pojo.Condition;
import org.lanqiao.clothes.service.IColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements IColorService {
    @Autowired
    ColorMapper colorMapper;

    @Override
    public void addColor(Color color) {
        colorMapper.insertColor(color);
    }

    @Override
    public List<Color> getColorAll(Condition condition) {
        List<Color> colorList = colorMapper.selectColorAll(condition);
        for(Color color : colorList){
            int state = color.getState();
            if(state == 0){
                color.setStateStr("启用");
            }else if(state == 1){
                color.setStateStr("停用");
            }
        }
        return colorList;
    }

    @Override
    public int getColorCount(Condition condition) {
        return colorMapper.selectColorCount(condition);
    }

    @Override
    public void removeColorById(int colorId) {
        colorMapper.deleteColorById(colorId);
    }

    @Override
    public void modifyColorById(Color color) {
        colorMapper.updateColorById(color);
    }

    @Override
    public Color getColorById(int id) {
        return colorMapper.selectColorById(id);
    }

    @Override
    public List<Color> getColorSelectedList(int storeId) {
        return colorMapper.selectColorSelectedList(storeId);
    }
}
