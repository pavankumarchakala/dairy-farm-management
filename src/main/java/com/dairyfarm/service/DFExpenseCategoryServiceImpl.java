package com.dairyfarm.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dairyfarm.dao.DFExpenseCategoryRepository;
import com.dairyfarm.dto.DFExpenseCategoryDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFExpenseCategory;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@Service
@RequiredArgsConstructor
public class DFExpenseCategoryServiceImpl implements DFExpenseCategoryService {

	private final DFExpenseCategoryRepository dairyfarmExpenseCategoryRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addDFExpenseCategory(DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO) {
		DFExpenseCategory savedDFExpense = dairyfarmExpenseCategoryRepository
				.save(modelMapper.map(dairyfarmExpenseCategoryDTO, DFExpenseCategory.class));
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(savedDFExpense.getId())
						.message("Expense record saved successfully.").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFExpenseCategory(DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO) {
		Optional<DFExpenseCategory> optionalDFExpense = dairyfarmExpenseCategoryRepository
				.findById(dairyfarmExpenseCategoryDTO.getId());
		if (!optionalDFExpense.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFExpenseCategory dairyfarmExpense = optionalDFExpense.get();
		modelMapper.map(dairyfarmExpenseCategoryDTO, dairyfarmExpense);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(dairyfarmExpenseCategoryDTO.getId())
						.message("Expense record updated successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFExpenseCategoryDTO> fetchDFExpenseCategoryDetails(long categoryId) {
		Optional<DFExpenseCategory> optionalDFExpense = dairyfarmExpenseCategoryRepository.findById(categoryId);
		if (!optionalDFExpense.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFExpenseCategoryDTO dairyfarmExpenseCategoryDTO = modelMapper.map(optionalDFExpense.get(),
				DFExpenseCategoryDTO.class);
		return new ResponseEntity<DFExpenseCategoryDTO>(dairyfarmExpenseCategoryDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteDFExpenseCategory(long categoryId) {
		Optional<DFExpenseCategory> optionalDFExpenseCategory = dairyfarmExpenseCategoryRepository.findById(categoryId);
		if (!optionalDFExpenseCategory.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		dairyfarmExpenseCategoryRepository.deleteById(categoryId);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Expense record deleted successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

}
