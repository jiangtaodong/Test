package com.byzp.service;

import com.byzp.pojo.ByWeather;

import java.util.List;

public interface ByWeatherService {

    int insertweather(ByWeather byWeather);

    List<ByWeather> selectweatherbyinserttime(String time);

    List<ByWeather> selectallcity();

    List<ByWeather> selectweatherbycityname(String cityname);

    int countweatherdata();

    int clearweatherdata();

}
