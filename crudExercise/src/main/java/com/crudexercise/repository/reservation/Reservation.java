package com.crudexercise.repository.reservation;

import java.time.LocalDateTime;
import java.util.Objects;

public class Reservation  {

    private Integer reservationId;
    private Integer passengerId;
    private Integer airlineTicketId;
    private String reservatoinStatus;
    private LocalDateTime reserveAt;

    public Reservation() {
    }

    public Reservation(Integer passengerId, Integer airlineTicketId) {
        this.passengerId = passengerId;
        this.airlineTicketId = airlineTicketId;
        this.reservatoinStatus = "대기";
        this.reserveAt = LocalDateTime.now();
    }

    public Integer getReservationId() {
        return reservationId;
    }

    public void setReservationId(Integer reservationId) {
        this.reservationId = reservationId;
    }

    public Integer getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(Integer passengerId) {
        this.passengerId = passengerId;
    }

    public Integer getAirlineTicketId() {
        return airlineTicketId;
    }

    public void setAirlineTicketId(Integer airlineTicketId) {
        this.airlineTicketId = airlineTicketId;
    }

    public String getReservatoinStatus() {
        return reservatoinStatus;
    }

    public void setReservatoinStatus(String reservatoinStatus) {
        this.reservatoinStatus = reservatoinStatus;
    }

    public LocalDateTime getReserveAt() {
        return reserveAt;
    }

    public void setReserveAt(LocalDateTime reserveAt) {
        this.reserveAt = reserveAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return Objects.equals(reservationId, that.reservationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationId);
    }
}
