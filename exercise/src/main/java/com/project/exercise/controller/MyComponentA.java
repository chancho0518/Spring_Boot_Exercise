package com.project.exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MyComponentA {

    // 의존성 해결
    // 1. @Autowired: 필드 주입
    private MyComponentB myComponentB;

    public void sayHello() {
        String message = myComponentB.sayHello() + ", 그리고 난 MyComponentA!!";
        System.out.println(message);
    }

    // 2. @Autowired: setter 주입
    // public void setMyComponentB(MyComponentB myComponentB) {
    //     this.myComponentB = myComponentB;
    // }

    // 3. @Autowired: 생성자 주입(생략 가능)
    public MyComponentA(MyComponentB myComponentB) {
        this.myComponentB = myComponentB;
    }
}
