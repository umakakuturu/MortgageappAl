package com.org.mortgageapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.org.mortgageapp.entity.Property;

@Repository
public interface PropertyRepository extends JpaRepository<Property, Long>{

	Property findByUserPanCard(String panCard);

}
