package com.dairyfarm.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dairyfarm.dao.DFIncomeCategoryRepository;
import com.dairyfarm.dao.DFIncomeRepository;
import com.dairyfarm.dao.DFRepository;
import com.dairyfarm.dto.DFIncomeCategoryDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFIncomeCategory;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@Service
@RequiredArgsConstructor
public class DFIncomeCategoryServiceImpl implements DFIncomeCategoryService {

	private final DFIncomeCategoryRepository dairyfarmIncomeCategoryRepository;

	private final DFIncomeRepository dairyfarmIncomeRepository;

	private final DFRepository dairyfarmRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addDFIncomeCategory(DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO) {
		DFIncomeCategory dfIncomeCategory = modelMapper.map(dairyfarmIncomeCategoryDTO, DFIncomeCategory.class);
		DFIncomeCategory savedDFIncome = dairyfarmIncomeCategoryRepository.save(dfIncomeCategory);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(savedDFIncome.getId())
						.message("Income record saved successfully.").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFIncomeCategory(DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO) {
		Optional<DFIncomeCategory> optionalDFIncomeCategory = dairyfarmIncomeCategoryRepository
				.findById(dairyfarmIncomeCategoryDTO.getId());
		if (!optionalDFIncomeCategory.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFIncomeCategory dairyfarmIncome = optionalDFIncomeCategory.get();
		modelMapper.map(dairyfarmIncomeCategoryDTO, dairyfarmIncome);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(dairyfarmIncomeCategoryDTO.getId())
						.message("Income record updated successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFIncomeCategoryDTO> fetchDFIncomeCategoryDetails(long categoryId) {
		Optional<DFIncomeCategory> optionalDFIncome = dairyfarmIncomeCategoryRepository.findById(categoryId);
		if (!optionalDFIncome.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFIncomeCategoryDTO dairyfarmIncomeCategoryDTO = modelMapper.map(optionalDFIncome.get(),
				DFIncomeCategoryDTO.class);
		return new ResponseEntity<DFIncomeCategoryDTO>(dairyfarmIncomeCategoryDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteDFIncomeCategory(long categoryId) {
		if (0 == dairyfarmIncomeCategoryRepository.countById(categoryId))
			throw new DairyfarmmanagementException("No record exists with the provided ID", HttpStatus.NOT_FOUND);

		if (dairyfarmIncomeRepository.countByCategoryId(categoryId) > 0) {
			throw new DairyfarmmanagementException(
					"Operation is Denied as Income entries exists for the provided Category.", HttpStatus.FORBIDDEN);
		}

		dairyfarmIncomeCategoryRepository.deleteById(categoryId);
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder()
				.message("Category record deleted successfully.").status(HttpStatus.OK).build(), HttpStatus.OK);
	}

}
