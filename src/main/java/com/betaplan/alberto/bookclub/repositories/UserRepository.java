package com.betaplan.alberto.bookclub.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.betaplan.alberto.bookclub.models.User;

public interface UserRepository extends CrudRepository<User, Long>{

    Optional<User> findByEmail(String email);

    Optional<User> findByUserName(String userName);
}
