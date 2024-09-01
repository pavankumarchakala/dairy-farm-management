package com.dairyfarm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.DFUser;

/**
 * @author Pavankumar - created date : Sep 28, 2021
 */
@Repository
public interface DFUserRepository extends JpaRepository<DFUser, Long> {

	DFUser findBydairyfarmIdAndUserId(long dairyfarmId, long userId);

	List<DFUser> findAllByUserId(long userId);

	List<DFUser> findAllBydairyfarmId(long dairyfarmId);

}
