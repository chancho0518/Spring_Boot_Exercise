package com.crudexercise.web.dto;

import java.util.List;

public class TicketResponse {

    private List<Ticket> tickets;

    public TicketResponse() {
    }

    public TicketResponse(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }
}