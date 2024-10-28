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

import com.dairyfarm.dao.DFIncomeCatItemRepository;
import com.dairyfarm.dao.DFIncomeCategoryRepository;
import com.dairyfarm.dao.DFIncomeRepository;
import com.dairyfarm.dao.DFRepository;
import com.dairyfarm.dto.DFExpenseDTO;
import com.dairyfarm.dto.DFIncomeDTO;
import com.dairyfarm.dto.PaginatedResponse;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFExpense;
import com.dairyfarm.entity.DFIncome;
import com.dairyfarm.entity.DFIncomeCategory;
import com.dairyfarm.entity.DFIncomeCategoryItem;
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
public class DFIncomeServiceImpl implements DFIncomeService {

	private final DFIncomeRepository dairyfarmIncomeRepository;

	private final DFIncomeCategoryRepository dfIncomeCategoryRepository;

	private final DFIncomeCatItemRepository dfIncomeCatItemRepository;

	private final DFRepository dairyfarmRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addDFIncome(DFIncomeDTO dairyfarmIncomeDTO) {
		return saveOrUpdateDFIncome(dairyfarmIncomeDTO, true);
	}

	private ResponseEntity<SuccessResponse> saveOrUpdateDFIncome(DFIncomeDTO dairyfarmIncomeDTO, boolean isSave) {
		long dairyfarmId = dairyfarmIncomeDTO.getDairyfarm() != null ? dairyfarmIncomeDTO.getDairyfarm().getId() : 0;

		if (0 == dairyfarmId) {
			log.error("Please provide valid Dairyfarm Id.");
			throw new DairyfarmmanagementException("Please provide valid Dairyfarm Id.", HttpStatus.NOT_FOUND);
		}

		if (0 == dairyfarmRepository.countById(dairyfarmId))
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		Dairyfarm dairyfarm = new Dairyfarm();
		dairyfarm.setId(dairyfarmId);
		// Validating Category
		DFIncomeCategory dfCategory = validateIncomeCategory(dairyfarmIncomeDTO, dairyfarm);
		// validating Category Item
		DFIncomeCategoryItem dfCategoryItem = validateCategoryItem(dairyfarmIncomeDTO, dfCategory);
		DFIncome dfIncome = null;

		if (!isSave) {
			dfIncome = dairyfarmIncomeRepository.findById(dairyfarmIncomeDTO.getId()).get();
			modelMapper.addMappings(new PropertyMap<DFIncomeDTO, DFIncome>() {

				@Override
				protected void configure() {
					skip(destination.getDairyfarm());
					skip(destination.getCategory());
					skip(destination.getItem());
				}

			});
			modelMapper.map(dairyfarmIncomeDTO, dfIncome);
		} else
			dfIncome = modelMapper.map(dairyfarmIncomeDTO, DFIncome.class);

		dfIncome.setItem(dfCategoryItem);
		dfIncome.setCategory(dfCategory);
		DFIncome savedDFIncome = dairyfarmIncomeRepository.save(dfIncome);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder()
						.savedItemId(savedDFIncome != null ? savedDFIncome.getId() : dairyfarmIncomeDTO.getId())
						.message("Income record saved successfully.")
						.status(isSave ? HttpStatus.CREATED : HttpStatus.OK).build(),
				isSave ? HttpStatus.CREATED : HttpStatus.OK);
	}

	private DFIncomeCategoryItem validateCategoryItem(DFIncomeDTO dairyfarmIncomeDTO, DFIncomeCategory dfCategory) {
		DFIncomeCategoryItem dfCategoryItem = null;
		if (dairyfarmIncomeDTO.getItem().getId() != 0)
			dfCategoryItem = dfIncomeCatItemRepository.findById(dairyfarmIncomeDTO.getItem().getId()).get();
		else if (null != dfCategory && dairyfarmIncomeDTO.getItem().getId() == 0
				&& null != dairyfarmIncomeDTO.getItem().getItem()) {
			DFIncomeCategoryItem dfIncomeCategoryItem = modelMapper.map(dairyfarmIncomeDTO.getItem(),
					DFIncomeCategoryItem.class);
			dfIncomeCategoryItem.setCategory(dfCategory);
			dfCategoryItem = dfIncomeCatItemRepository.save(dfIncomeCategoryItem);
		} else
			throw new DairyfarmmanagementException("Data not found", HttpStatus.NOT_FOUND);
		return dfCategoryItem;
	}

	private DFIncomeCategory validateIncomeCategory(DFIncomeDTO dairyfarmIncomeDTO, Dairyfarm dairyfarm) {
		DFIncomeCategory dfCategory = null;
		if (dairyfarmIncomeDTO.getCategory().getId() != 0)
			dfCategory = dfIncomeCategoryRepository.findById(dairyfarmIncomeDTO.getCategory().getId()).get();
		else if (dairyfarmIncomeDTO.getCategory().getId() == 0
				&& null != dairyfarmIncomeDTO.getCategory().getCategory()) {
			DFIncomeCategory dfIncomeCategory = modelMapper.map(dairyfarmIncomeDTO.getCategory(),
					DFIncomeCategory.class);
			dfIncomeCategory.setDairyfarm(dairyfarm);
			dfCategory = dfIncomeCategoryRepository.save(dfIncomeCategory);
		} else
			throw new DairyfarmmanagementException("Data not found", HttpStatus.NOT_FOUND);
		return dfCategory;
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFIncome(DFIncomeDTO dairyfarmIncomeDTO) {
		return saveOrUpdateDFIncome(dairyfarmIncomeDTO, false);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteDFIncome(long dairyfarmIncomeId) {
		long count = dairyfarmIncomeRepository.countById(dairyfarmIncomeId);
		if (count == 0)
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		dairyfarmIncomeRepository.deleteById(dairyfarmIncomeId);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Income record deleted successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFIncomeDTO> fetchDFIncomeDetails(long incomeId) {
		Optional<DFIncome> optionalDFIncome = dairyfarmIncomeRepository.findById(incomeId);
		if (!optionalDFIncome.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFIncomeDTO dairyfarmIncomeDTO = modelMapper.map(optionalDFIncome.get(), DFIncomeDTO.class);
		return new ResponseEntity<DFIncomeDTO>(dairyfarmIncomeDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<PaginatedResponse<DFIncomeDTO>> fetchAllDFIncomesBydairyfarm(Pageable pageable,
			long dairyfarmId) {
		Page<DFIncome> incomeResponse = dairyfarmIncomeRepository.findAllBydairyfarmId(pageable, dairyfarmId);
		PaginatedResponse<DFIncomeDTO> response = new PaginatedResponse<>();
		modelMapper.addMappings(new PropertyMap<DFExpense, DFExpenseDTO>() {

			@Override
			protected void configure() {
				skip(destination.getCategory().getDairyfarm());
				skip(destination.getItem().getCategory());
				skip(destination.getDairyfarm());
			}

		});
		response.setList(incomeResponse.getContent().stream().map(item -> modelMapper.map(item, DFIncomeDTO.class))
				.collect(Collectors.toList()));
		response.setCurrPageNum(incomeResponse.getNumber());
		response.setTotalRecords(incomeResponse.getNumberOfElements());
		response.setTotalPages(incomeResponse.getTotalPages());
		return new ResponseEntity<PaginatedResponse<DFIncomeDTO>>(response, HttpStatus.OK);
	}

}
