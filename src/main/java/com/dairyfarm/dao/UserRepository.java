package com.dairyfarm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.User;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByMobile(String mobile);

}
