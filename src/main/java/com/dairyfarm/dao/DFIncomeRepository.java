package com.dairyfarm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.DFIncome;

/**
 * @author Pavankumar - created date : Oct 14, 2021
 */
@Repository
public interface DFIncomeRepository extends JpaRepository<DFIncome, Long> {

	Page<DFIncome> findAllBydairyfarmId(Pageable pageable, long dairyfarmId);

	long countById(long dairyfarmIncomeId);

	long countByCategoryId(long categoryId);

	long countByItemId(long itemId);

}
