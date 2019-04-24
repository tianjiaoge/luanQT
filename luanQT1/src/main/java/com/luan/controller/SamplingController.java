package com.luan.controller;

import com.alibaba.fastjson.JSONObject;
import com.luan.service.SamplingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sampling")
public class SamplingController {

  @Autowired
    SamplingService samplingService;

    @RequestMapping("/page")
    public String page (){
        return "samping";
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(String id) {
        return samplingService.addSampling(id);
    }

    @RequestMapping("loadData")
    @ResponseBody
    public  void loadData(){
      samplingService.loadData();
    }

    @RequestMapping("/saoMa")
    @ResponseBody
    public String saoMa(String sampleCode){
   return samplingService.saoMa(sampleCode);
  }

    @RequestMapping("/samplingCount")
    public  String samplingCount(){
      return  "samplingCount";
    }

    @RequestMapping("/loadCountData")
    @ResponseBody
    public JSONObject loadCountData(){
      return samplingService.loadCountData();
    }
}
