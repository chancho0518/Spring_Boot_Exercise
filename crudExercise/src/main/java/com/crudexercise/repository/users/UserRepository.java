package com.crudexercise.repository.users;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    UserEntity searchUserById(Integer userId);
}
