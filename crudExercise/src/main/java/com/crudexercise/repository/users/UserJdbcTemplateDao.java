package com.crudexercise.repository.users;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserJdbcTemplateDao implements UserRepository {

    private JdbcTemplate jdbcTemplate;

    public UserJdbcTemplateDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    static RowMapper<UserEntity> userEntityRowMapper = ((rs, rowNum) ->
        new UserEntity(
                rs.getInt("user_id"),
                rs.getNString("user_name"),
                rs.getNString("like_travel_place"),
                rs.getNString("phone_num")
        )
    );

    @Override
    public UserEntity searchUserById(Integer userId) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE user_id=?", userEntityRowMapper, userId);
    }
}
