package com.di.exercise;

public class Main {

    public static void main(String[] args) {

        ClassA classA1 = new ClassA();



        // NullPointException 발생!!
        // - classB 가 Null로 classA를 실행할 수 없음
        // - ClassA는 스스로 실행할 수 없으며, ClassA는 ClassB에 의존성을 가짐
        // classA1.checkClassB(); // Null
        // classA1.sayHello();

        // 의존성 해결하기
        // 1. 생성자로 넣기
        ClassB classB = new ClassB();
        ClassA classA2 = new ClassA(classB);
        classA2.checkClassB();
        classA2.sayHello();

        // 2. setter
        classA1.setClassB(classB);
        classA1.sayHello();
    }
}
