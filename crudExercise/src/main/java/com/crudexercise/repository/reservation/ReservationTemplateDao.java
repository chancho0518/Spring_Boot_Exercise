package com.crudexercise.repository.reservation;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;

@Repository
public class ReservationTemplateDao implements ReservationRepository {

    private JdbcTemplate jdbcTemplate;

    public ReservationTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Boolean saveReservation(Reservation reservation) {
        Integer rowNums = jdbcTemplate.update("INSERT INTO reservation(passenger_id, airline_ticket_id, reservation_status, reserve_at) "
            + "VALUES(?, ?, ?, ?)", reservation.getPassengerId(), reservation.getAirlineTicketId(), reservation.getReservatoinStatus(), new Date(Timestamp.valueOf(reservation.getReserveAt()).getTime()));

        return rowNums > 0;
    }
}
