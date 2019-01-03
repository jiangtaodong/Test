package com.byzp.controller;

import com.byzp.pojo.ByWeather;
import com.byzp.service.ByWeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class WeatherController {

    @Autowired
    private ByWeatherService byWeatherService;

    @RequestMapping("selectallcity")
    @ResponseBody
    public List<ByWeather> selectallcity(){

        List<ByWeather> selectallcity = byWeatherService.selectallcity();

        return selectallcity;

    }

    @RequestMapping("selectweatherbycityname")
    @ResponseBody
    public List<ByWeather> selectweatherbycityname(String cityname){

        System.out.println("城市为： 【" + cityname + "】");

        List<ByWeather> selectweatherbycityname = byWeatherService.selectweatherbycityname(cityname);

        System.out.println(selectweatherbycityname.size() + " ------ ");

        return selectweatherbycityname;

    }

}
