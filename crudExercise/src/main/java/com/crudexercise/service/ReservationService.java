package com.crudexercise.service;

import com.crudexercise.repository.airlineTicket.TicketAndFlightInfo;
import com.crudexercise.repository.airlineTicket.TicketEntity;
import com.crudexercise.repository.airlineTicket.TicketRepository;
import com.crudexercise.repository.passenger.PassengerEntity;
import com.crudexercise.repository.passenger.PassengerRepository;
import com.crudexercise.repository.reservation.Reservation;
import com.crudexercise.repository.reservation.ReservationRepository;
import com.crudexercise.repository.users.UserEntity;
import com.crudexercise.repository.users.UserRepository;
import com.crudexercise.web.dto.ReservationRequest;
import com.crudexercise.web.dto.ReservationResult;
import com.crudexercise.web.dto.Ticket;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private UserRepository userRepository;
    private TicketRepository ticketRepository;
    private PassengerRepository passengerRepository;
    private ReservationRepository reservationRepository;

    public ReservationService(UserRepository userRepository, TicketRepository ticketRepository, PassengerRepository passengerRepository, ReservationRepository reservationRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
        this.passengerRepository = passengerRepository;
        this.reservationRepository = reservationRepository;
    }

    public List<Ticket> searchUserFavoriteLocationTickets(Integer userId, String ticketType) {
        // 1. userId로 유저 정보 조회
        // 2. 선호하는 여행지와 tickerType으로 AirlineTicker table에 질의하여 필요한 AirlineTicket 정보 조회
        // 3. 해당 정보를 바탕으로 ticketDto를 생성

        UserEntity userEntity = userRepository.searchUserById(userId);
        String likePlace = userEntity.getLikeTravelPlace();

        List<TicketEntity> airlineTickets = ticketRepository.findAllTicketsWithPlaceAndType(likePlace, ticketType);
        List<Ticket> tickets = airlineTickets.stream().map(Ticket::new).collect(Collectors.toList());

        return tickets;
    }

    @Transactional
    public ReservationResult makeReservation(ReservationRequest reservationRequest) {
        // 1. Reservation Repository
        // 2. Passenger Repository
        // 3. Join Table(flight - airline_ticket) Repository

        // userId , airline_ticket_id
        Integer userId = reservationRequest.getUserId();
        Integer ticketId = reservationRequest.getAirlineTicketId();

        // Passenger 정보
        PassengerEntity passengerEntity = passengerRepository.findPassengerByUserId(userId);
        Integer passengerId = passengerEntity.getPassengerId();

        // price 등의 정보 불러오기
        List<TicketAndFlightInfo> ticketAndFlightInfo = ticketRepository.findAllInfoTicketAndFlight(ticketId);

        // reservation 생성
        Reservation reservation = new Reservation(passengerId, ticketId);
        Boolean isSuccess = reservationRepository.saveReservation(reservation);

        // ReservationResult DTO
        List<Integer> prices = ticketAndFlightInfo.stream().map(TicketAndFlightInfo::getPrice).collect(Collectors.toList());
        List<Integer> charges = ticketAndFlightInfo.stream().map(TicketAndFlightInfo::getCharge).collect(Collectors.toList());
        Integer tax = ticketAndFlightInfo.stream().map(TicketAndFlightInfo::getTax).findFirst().get();
        Integer totalPrice = ticketAndFlightInfo.stream().map(TicketAndFlightInfo::getTotalPrice).findFirst().get();

        return new ReservationResult(prices, charges, tax, totalPrice, isSuccess);
    }
}
