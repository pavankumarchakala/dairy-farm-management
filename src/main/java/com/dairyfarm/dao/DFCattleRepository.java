package com.dairyfarm.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.DFCattle;

/**
 * @author Pavankumar - created date : Sep 18, 2021
 */
@Repository
public interface DFCattleRepository extends JpaRepository<DFCattle, Long> {

	List<DFCattle> findAllBydairyfarmId(long dairyfarmId);

	long countBydairyfarmId(long dairyfarmId);

}
