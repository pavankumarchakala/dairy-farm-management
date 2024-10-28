package com.dairyfarm.service;

import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dairyfarm.dao.DFExpenseCatItemRepository;
import com.dairyfarm.dao.DFExpenseCategoryRepository;
import com.dairyfarm.dao.DFExpenseRepository;
import com.dairyfarm.dao.DFRepository;
import com.dairyfarm.dto.DFExpenseDTO;
import com.dairyfarm.dto.PaginatedResponse;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFExpense;
import com.dairyfarm.entity.DFExpenseCategory;
import com.dairyfarm.entity.DFExpenseCategoryItem;
import com.dairyfarm.entity.Dairyfarm;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavankumar - created date : Oct 14, 2021
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DFExpenseServiceImpl implements DFExpenseService {

	private final DFExpenseRepository dairyfarmExpenseRepository;

	private final DFExpenseCategoryRepository dfExpenseCategoryRepository;

	private final DFExpenseCatItemRepository dfExpenseCatItemRepository;

	private final DFRepository dairyfarmRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addDFExpense(DFExpenseDTO dairyfarmExpenseDTO)
			throws DairyfarmmanagementException {
		return saveOrUpdateDFExpense(dairyfarmExpenseDTO, true);
	}

	private ResponseEntity<SuccessResponse> saveOrUpdateDFExpense(DFExpenseDTO dairyfarmExpenseDTO, boolean isSave) {
		long dairyfarmId = dairyfarmExpenseDTO.getDairyfarm() != null ? dairyfarmExpenseDTO.getDairyfarm().getId() : 0;

		if (0 == dairyfarmId) {
			log.error("Please provide valid Dairyfarm Id.");
			throw new DairyfarmmanagementException("Please provide valid Dairyfarm Id.", HttpStatus.NOT_FOUND);
		}

		Optional<Dairyfarm> optionaldairyfarm = dairyfarmRepository.findById(dairyfarmId);
		if (!optionaldairyfarm.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		Dairyfarm dairyfarm = optionaldairyfarm.get();
		// Validating Category
		DFExpenseCategory dfCategory = validateExpenseCategory(dairyfarmExpenseDTO, dairyfarm);
		// validating Category Item
		DFExpenseCategoryItem dfCategoryItem = validateCategoryItem(dairyfarmExpenseDTO, dfCategory);
		DFExpense dfExpense = null;

		if (!isSave) {
			dfExpense = dairyfarmExpenseRepository.findById(dairyfarmExpenseDTO.getId()).get();
			modelMapper.addMappings(new PropertyMap<DFExpenseDTO, DFExpense>() {

				@Override
				protected void configure() {
					skip(destination.getDairyfarm());
					skip(destination.getCategory());
					skip(destination.getItem());
				}

			});
			modelMapper.map(dairyfarmExpenseDTO, dfExpense);
		} else
			dfExpense = modelMapper.map(dairyfarmExpenseDTO, DFExpense.class);

		dfExpense.setItem(dfCategoryItem);
		dfExpense.setCategory(dfCategory);
		DFExpense savedDFExpense = dairyfarmExpenseRepository.save(dfExpense);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(savedDFExpense.getId())
						.message("Expense record saved successfully.")
						.status(isSave ? HttpStatus.CREATED : HttpStatus.OK).build(),
				isSave ? HttpStatus.CREATED : HttpStatus.OK);
	}

	private DFExpenseCategoryItem validateCategoryItem(DFExpenseDTO dairyfarmExpenseDTO, DFExpenseCategory dfCategory) {
		DFExpenseCategoryItem dfCategoryItem = null;
		if (dairyfarmExpenseDTO.getItem().getId() != 0)
			dfCategoryItem = dfExpenseCatItemRepository.findById(dairyfarmExpenseDTO.getItem().getId()).get();
		else if (null != dfCategory && dairyfarmExpenseDTO.getItem().getId() == 0
				&& null != dairyfarmExpenseDTO.getItem().getItem()) {
			DFExpenseCategoryItem dfExpenseCategoryItem = modelMapper.map(dairyfarmExpenseDTO.getItem(),
					DFExpenseCategoryItem.class);
			dfExpenseCategoryItem.setCategory(dfCategory);
			dfCategoryItem = dfExpenseCatItemRepository.save(dfExpenseCategoryItem);
		} else
			throw new DairyfarmmanagementException("Data not found", HttpStatus.NOT_FOUND);
		return dfCategoryItem;
	}

	private DFExpenseCategory validateExpenseCategory(DFExpenseDTO dairyfarmExpenseDTO, Dairyfarm dairyfarm) {
		DFExpenseCategory dfCategory = null;
		if (dairyfarmExpenseDTO.getCategory().getId() != 0)
			dfCategory = dfExpenseCategoryRepository.findById(dairyfarmExpenseDTO.getCategory().getId()).get();
		else if (dairyfarmExpenseDTO.getCategory().getId() == 0
				&& null != dairyfarmExpenseDTO.getCategory().getCategory()) {
			DFExpenseCategory dfExpenseCategory = modelMapper.map(dairyfarmExpenseDTO.getCategory(),
					DFExpenseCategory.class);
			dfExpenseCategory.setDairyfarm(dairyfarm);
			dfCategory = dfExpenseCategoryRepository.save(dfExpenseCategory);
		} else
			throw new DairyfarmmanagementException("Data not found", HttpStatus.NOT_FOUND);
		return dfCategory;
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFExpense(DFExpenseDTO dairyfarmExpenseDTO)
			throws DairyfarmmanagementException {
		return saveOrUpdateDFExpense(dairyfarmExpenseDTO, false);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteDFExpense(long dairyfarmExpenseId) {
		Optional<DFExpense> optionalDFExpense = dairyfarmExpenseRepository.findById(dairyfarmExpenseId);
		if (!optionalDFExpense.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		dairyfarmExpenseRepository.deleteById(dairyfarmExpenseId);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Expense record deleted successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFExpenseDTO> fetchDFExpenseDetails(long expenseId) {
		Optional<DFExpense> optionalDFExpense = dairyfarmExpenseRepository.findById(expenseId);
		if (!optionalDFExpense.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFExpenseDTO dairyfarmExpenseDTO = modelMapper.map(optionalDFExpense.get(), DFExpenseDTO.class);
		return new ResponseEntity<DFExpenseDTO>(dairyfarmExpenseDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PaginatedResponse<DFExpenseDTO>> fetchAllDFExpensesBydairyfarm(Pageable pageable,
			long dairyfarmId) {
		Page<DFExpense> ExpenseResponse = dairyfarmExpenseRepository.findAllBydairyfarmId(pageable, dairyfarmId);
		PaginatedResponse<DFExpenseDTO> response = new PaginatedResponse<>();
		modelMapper.addMappings(new PropertyMap<DFExpense, DFExpenseDTO>() {

			@Override
			protected void configure() {
				skip(destination.getCategory().getDairyfarm());
				skip(destination.getItem().getCategory());
				skip(destination.getDairyfarm());
			}

		});
		response.setList(ExpenseResponse.getContent().stream().map(item -> modelMapper.map(item, DFExpenseDTO.class))
				.collect(Collectors.toList()));
		response.setCurrPageNum(ExpenseResponse.getNumber());
		response.setTotalRecords(ExpenseResponse.getNumberOfElements());
		response.setTotalPages(ExpenseResponse.getTotalPages());
		return new ResponseEntity<PaginatedResponse<DFExpenseDTO>>(response, HttpStatus.OK);
	}

}
