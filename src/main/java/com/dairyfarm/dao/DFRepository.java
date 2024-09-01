package com.dairyfarm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.Dairyfarm;

/**
 * @author Pavankumar - created date : Sep 18, 2021
 */
@Repository
public interface DFRepository extends JpaRepository<Dairyfarm, Long> {

	long countById(long dairyfarmId);

}
