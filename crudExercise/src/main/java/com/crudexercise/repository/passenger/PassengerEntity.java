package com.crudexercise.repository.passenger;

import java.util.Objects;

public class PassengerEntity {

    private Integer passengerId;
    private Integer userId;
    private String PassportNum;

    public PassengerEntity() {
    }

    public PassengerEntity(Integer passengerId, Integer userId, String passportNum) {
        this.passengerId = passengerId;
        this.userId = userId;
        PassportNum = passportNum;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getPassportNum() {
        return PassportNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PassengerEntity)) return false;
        PassengerEntity that = (PassengerEntity) o;
        return Objects.equals(passengerId, that.passengerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passengerId);
    }
}
