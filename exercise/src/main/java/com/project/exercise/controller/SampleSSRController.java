package com.project.exercise.controller;

import com.project.exercise.dto.SampleData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleSSRController {

    @GetMapping("/sample")
    public String samplePage(Model model) {
        List<SampleData> sampleDataList = new ArrayList<>();
        sampleDataList.add(new SampleData(1, "Sample Item 1"));
        sampleDataList.add(new SampleData(2, "Sample Item 2"));
        sampleDataList.add(new SampleData(3, "Sample Item 3"));
        sampleDataList.add(new SampleData(4, "Sample Item 4"));
        sampleDataList.add(new SampleData(5, "Sample Item 5"));
        sampleDataList.add(new SampleData(6, "Sample Item 6"));

        model.addAttribute("dataList", sampleDataList);

        return "SSRExam";
    }
}
