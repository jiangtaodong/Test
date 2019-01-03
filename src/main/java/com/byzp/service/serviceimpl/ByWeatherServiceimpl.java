package com.byzp.service.serviceimpl;

import com.byzp.mapper.ByWeatherMapper;
import com.byzp.pojo.ByWeather;
import com.byzp.service.ByWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ByWeatherServiceimpl implements ByWeatherService {

    @Autowired
    ByWeatherMapper byWeatherMapper;



    @Override
    public int insertweather(ByWeather byWeather) {

        int insertweather = byWeatherMapper.insertweather(byWeather);

        return insertweather;
    }

    @Override
    public List<ByWeather> selectweatherbyinserttime(String time) {

        List<ByWeather> selectweatherbyinserttime = byWeatherMapper.selectweatherbyinserttime(time);

        return selectweatherbyinserttime;
    }

    @Override
    public List<ByWeather> selectallcity() {

        List<ByWeather> selectallcity = byWeatherMapper.selectallcity();

        return selectallcity;
    }

    @Override
    public List<ByWeather> selectweatherbycityname(String cityname) {

        List<ByWeather> selectweatherbycityname = byWeatherMapper.selectweatherbycityname(cityname);

        return selectweatherbycityname;
    }

    @Override
    public int countweatherdata() {

        int countweatherdata = byWeatherMapper.countweatherdata();

        return countweatherdata;
    }

    @Override
    public int clearweatherdata() {

        int clearweatherdata = byWeatherMapper.clearweatherdata();

        return clearweatherdata;

    }

}
