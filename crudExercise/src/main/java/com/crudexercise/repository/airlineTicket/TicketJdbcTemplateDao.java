package com.crudexercise.repository.airlineTicket;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class TicketJdbcTemplateDao implements TicketRepository {

    private JdbcTemplate jdbcTemplate;

    public TicketJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static RowMapper<TicketEntity> ticketEntityRowMapper = ((rs, rowNum) ->
            new TicketEntity(
                    rs.getInt("ticket_id"),
                    rs.getNString("ticket_type"),
                    rs.getNString("departure_loc"),
                    rs.getNString("arrival_loc"),
                    rs.getDate("departure_at"),
                    rs.getDate("return_at"),
                    rs.getDouble("tax"),
                    rs.getDouble("total_price")
            )
    );

    static RowMapper<TicketAndFlightInfo> ticketAndFlightInfoRowMapper = ((rs, rowNum) ->
            new TicketAndFlightInfo(
                    rs.getInt("A.ticket_id"),
                    rs.getDouble("F.flight_price"),
                    rs.getDouble("F.charge"),
                    rs.getDouble("A.tax"),
                    rs.getDouble("A.total_price")
            ));

    @Override
    public List<TicketEntity> findAllTicketsWithPlaceAndType(String likePlace, String ticketType) {
        return jdbcTemplate.query("SELECT * FROM airline_ticket WHERE arrival_loc=? and ticket_type=?",
                ticketEntityRowMapper,
                likePlace,
                ticketType
        );
    }

    @Override
    public List<TicketAndFlightInfo> findAllInfoTicketAndFlight(Integer ticketId) {
        return jdbcTemplate.query("SELECT A.ticket_id, A.tax, A.total_price, F.flight_price, F.charge FROM airline_ticket A "
                    + "INNER JOIN flight F "
                    + "ON A.ticket_id = F.ticket_id "
                    + "WHERE A.ticket_id=?",
                    ticketAndFlightInfoRowMapper,
                    ticketId
                );
    }
}
