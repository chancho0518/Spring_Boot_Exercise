package com.crudexercise.web.controller;

import com.crudexercise.service.ReservationService;
import com.crudexercise.web.dto.ReservationRequest;
import com.crudexercise.web.dto.ReservationResult;
import com.crudexercise.web.dto.Ticket;
import com.crudexercise.web.dto.TicketResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/air-reservation")
public class ReservationController {

    private ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/tickets")
    public TicketResponse findAirlineTickets(@RequestParam("user-Id") Integer userId,
                                             @RequestParam("airline-ticket-type") String ticketType) {

        List<Ticket> tickets = reservationService.searchUserFavoriteLocationTickets(userId, ticketType);
        return new TicketResponse(tickets);
    }

    @PostMapping("/reservations")
    public ReservationResult makeReservation(@RequestBody ReservationRequest reservationRequest) {
        return reservationService.makeReservation(reservationRequest);
    }
}
