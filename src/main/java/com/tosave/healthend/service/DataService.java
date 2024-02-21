package com.tosave.healthend.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DataService {
    private final List<String> dataList = new ArrayList<>();

    public void addDataItem(String dataItem) {
        dataList.add(dataItem);
    }

    public List<String> getDataList() {
        return dataList;
    }
}
