package org.lanqiao.clothes.service;

import org.lanqiao.clothes.pojo.Color;
import org.lanqiao.clothes.pojo.Condition;

import java.util.List;

public interface IColorService {
    public void addColor(Color color);
    public List<Color> getColorAll(Condition condition);
    public int getColorCount(Condition condition);
    public void removeColorById(int colorId);
    public void modifyColorById(Color color);
    public Color getColorById(int id);
    public List<Color> getColorSelectedList(int storeId);
    public List<Color> getColorListByIds(List<Integer> ids);
}
