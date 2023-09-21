package com.crudexercise.web.dto;

import com.crudexercise.repository.airlineTicket.TicketEntity;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.time.format.DateTimeFormatter;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Ticket {

    private String depart;
    private String arrival;
    private String departureTime;
    private String returnTime;
    private Integer ticketId;

    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Ticket() {
    }

    public Ticket(TicketEntity ticketEntity) {
        this.ticketId = ticketEntity.getTicketId();
        this.depart = ticketEntity.getDepartureLocation();
        this.arrival = ticketEntity.getArrivalLocation();
        this.departureTime = ticketEntity.getDepartureAt().format(formatter);
        this.returnTime = ticketEntity.getReturnAt().format(formatter);
    }

    public String getDepart() {
        return depart;
    }

    public String getArrival() {
        return arrival;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public Integer getTicketId() {
        return ticketId;
    }
}
