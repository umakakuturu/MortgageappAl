package com.org.mortgageapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.mortgageapp.entity.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByPanCard(String panCard);

}
