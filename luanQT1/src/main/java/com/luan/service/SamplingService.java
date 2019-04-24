package com.luan.service;

import com.alibaba.fastjson.JSONObject;

public interface SamplingService {

    String addSampling(String id);

    void loadData();

    String saoMa(String sampleCode);
    JSONObject loadCountData();
}
