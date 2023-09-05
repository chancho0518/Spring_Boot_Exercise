package com.project.exercise.controller;

import com.project.exercise.dto.SampleData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class SampleCSRController {

    @Autowired
    private MyComponentA myComponentA;

    @GetMapping("/sample")
    public List<SampleData> getSampleList() {

        myComponentA.sayHello();

        List<SampleData> sampleDataList = new ArrayList<>();
        sampleDataList.add(new SampleData(1, "Sample Item 1"));
        sampleDataList.add(new SampleData(2, "Sample Item 2"));
        sampleDataList.add(new SampleData(3, "Sample Item 3"));
        sampleDataList.add(new SampleData(4, "Sample Item 4"));
        sampleDataList.add(new SampleData(5, "Sample Item 5"));
        sampleDataList.add(new SampleData(6, "Sample Item 6"));

        return sampleDataList;
    }
}
