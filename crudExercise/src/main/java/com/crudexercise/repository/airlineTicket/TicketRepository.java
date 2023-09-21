package com.crudexercise.repository.airlineTicket;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository {

    List<TicketEntity> findAllTicketsWithPlaceAndType(String likePlace, String ticketType);

    List<TicketAndFlightInfo> findAllInfoTicketAndFlight(Integer ticketId);
}
