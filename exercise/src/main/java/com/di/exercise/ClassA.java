package com.di.exercise;

public class ClassA {
    private ClassB classB;

    public ClassA() {
    }

    public ClassA(ClassB classB) {
        this.classB = classB;
    }

    public void checkClassB() {
        System.out.println(classB);
    }

    public void setClassB(ClassB classB) {
        this.classB = classB;
    }

    public void sayHello() {
        String message = classB.sayHello() + " 그리고, 난 A!!!";
        System.out.println(message);
    }
}
