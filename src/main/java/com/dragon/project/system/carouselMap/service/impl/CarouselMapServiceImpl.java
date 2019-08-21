package com.dragon.project.system.carouselMap.service.impl;

import com.dragon.project.chart.business.domain.Business;
import com.dragon.project.system.carouselMap.mapper.CarouselMapper;
import com.dragon.project.system.carouselMap.entity.CarouselMap;
import com.dragon.project.system.carouselMap.service.CarouselMapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author：Dragon Wen
 * @email：18475536452@163.com
 * @date：Created in 2019/7/03 11:15
 * @description： 轮播图设置(CarouselMap)表服务实现类
 * @modified By：
 * @version: 1.0.0
 */
@Service
public class CarouselMapServiceImpl implements CarouselMapService {
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public CarouselMap selectCarouselMapById(Integer carouselId) {
        return carouselMapper.selectCarouselMap(carouselId);
    }


    @Override
    public List<CarouselMap> selectCarouselMapList(CarouselMap carouselMap) {
        return carouselMapper.selectCarouselMapList(carouselMap);
    }


    @Override
    public int insertCarouselMap(CarouselMap carouselMap) {
        return carouselMapper.insertCarouselMap(carouselMap);
    }


    @Override
    public int updateCarouselMap(CarouselMap carouselMap) {
        return carouselMapper.updateCarouselMap(carouselMap);
    }


    @Override
    public int deleteCarouselMapByIds(Integer[] carouselId) {
        return carouselMapper.deleteCarouselMapByIds(carouselId);
    }

    @Override
    public int changeCarouselDisplay(String carouselId, String display) {
        return carouselMapper.changeCarouselDisplay(carouselId,display);
    }

    @Override
    public List<CarouselMap> selectCarouselMapListFront() {
        return carouselMapper.selectCarouselMapListFront();
    }

    @Override
    public int incrementCarouselClickById(Integer carouselId) {
        return carouselMapper.incrementCarouselClickById(carouselId);
    }

    @Override
    public List<Business> selectCarouselMapData() {
        return carouselMapper.selectCarouselMapData();
    }
}