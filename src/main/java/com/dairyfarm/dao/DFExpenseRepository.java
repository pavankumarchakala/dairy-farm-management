package com.dairyfarm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.DFExpense;

/**
 * @author Pavankumar - created date : Oct 14, 2021
 */
@Repository
public interface DFExpenseRepository extends JpaRepository<DFExpense, Long> {

	Page<DFExpense> findAllBydairyfarmId(Pageable pageable, long dairyfarmId);

}
