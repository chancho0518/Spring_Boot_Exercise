package com.crudexercise.repository.reservation;

import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository {

    Boolean saveReservation(Reservation reservation);
}
