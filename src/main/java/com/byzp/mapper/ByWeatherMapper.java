package com.byzp.mapper;

import com.byzp.pojo.ByWeather;

import java.util.List;

public interface ByWeatherMapper {

    int insertweather(ByWeather byWeather);

    List<ByWeather> selectweatherbyinserttime(String time);

    List<ByWeather> selectallcity();

    List<ByWeather> selectweatherbycityname(String cityname);

    int countweatherdata();

    int clearweatherdata();

}