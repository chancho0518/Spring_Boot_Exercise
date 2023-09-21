package com.crudexercise.repository.passenger;

import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository {

    PassengerEntity findPassengerByUserId(Integer userId);
}
