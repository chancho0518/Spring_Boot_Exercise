package com.crudexercise.repository.passenger;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerTemplateDao implements PassengerRepository {

    private JdbcTemplate jdbcTemplate;

    public PassengerTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static RowMapper<PassengerEntity> passengerEntityRowMapper = ((rs, rowNum) ->
            new PassengerEntity(
                    rs.getInt("passenger_id"),
                    rs.getInt("user_id"),
                    rs.getNString("passport_num")
            ));

    @Override
    public PassengerEntity findPassengerByUserId(Integer userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM passenger WHERE user_id=?", passengerEntityRowMapper, userId);
    }
}
