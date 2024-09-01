package com.dairyfarm.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dairyfarm.entity.DFExpenseCategoryItem;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@Repository
public interface DFExpenseCatItemRepository extends JpaRepository<DFExpenseCategoryItem, Long> {}
