package com.dairyfarm.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dairyfarm.dao.DFExpenseCatItemRepository;
import com.dairyfarm.dto.DFExpenseCategoryItemDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFExpenseCategoryItem;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@Service
@RequiredArgsConstructor
public class DFExpenseCatItemServiceImpl implements DFExpenseCatItemService {

	private final DFExpenseCatItemRepository dairyfarmExpenseCatItemRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addDFExpenseCatItem(DFExpenseCategoryItemDTO dairyfarmExpenseCatItemDTO) {
		DFExpenseCategoryItem savedDFExpense = dairyfarmExpenseCatItemRepository
				.save(modelMapper.map(dairyfarmExpenseCatItemDTO, DFExpenseCategoryItem.class));
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(savedDFExpense.getId())
						.message("Expense record saved successfully.").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFExpenseCatItem(DFExpenseCategoryItemDTO dairyfarmExpenseCatItemDTO) {
		Optional<DFExpenseCategoryItem> optionalDFExpense = dairyfarmExpenseCatItemRepository
				.findById(dairyfarmExpenseCatItemDTO.getId());
		if (!optionalDFExpense.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFExpenseCategoryItem dairyfarmExpense = optionalDFExpense.get();
		modelMapper.map(dairyfarmExpenseCatItemDTO, dairyfarmExpense);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(dairyfarmExpenseCatItemDTO.getId())
						.message("Expense record updated successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFExpenseCategoryItemDTO> fetchDFExpenseCatItemDetails(long itemId) {
		Optional<DFExpenseCategoryItem> optionalDFExpense = dairyfarmExpenseCatItemRepository.findById(itemId);
		if (!optionalDFExpense.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFExpenseCategoryItemDTO dairyfarmExpenseCatItemDTO = modelMapper.map(optionalDFExpense.get(),
				DFExpenseCategoryItemDTO.class);
		return new ResponseEntity<DFExpenseCategoryItemDTO>(dairyfarmExpenseCatItemDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteDFExpenseCatItem(long itemId) {
		Optional<DFExpenseCategoryItem> optionalDFExpenseCategory = dairyfarmExpenseCatItemRepository.findById(itemId);
		if (!optionalDFExpenseCategory.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		dairyfarmExpenseCatItemRepository.deleteById(itemId);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Expense record deleted successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

}
