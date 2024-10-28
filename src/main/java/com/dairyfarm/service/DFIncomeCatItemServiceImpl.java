package com.dairyfarm.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dairyfarm.dao.DFIncomeCatItemRepository;
import com.dairyfarm.dao.DFIncomeRepository;
import com.dairyfarm.dto.DFIncomeCategoryItemDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFIncomeCategoryItem;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;

/**
 * @author Pavankumar - created date : Oct 15, 2021
 */
@Service
@RequiredArgsConstructor
public class DFIncomeCatItemServiceImpl implements DFIncomeCatItemService {

	private final DFIncomeCatItemRepository dairyfarmIncomeCatItemRepository;

	private final DFIncomeRepository dairyfarmIncomeRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addDFIncomeCatItem(DFIncomeCategoryItemDTO dairyfarmIncomeCatItemDTO) {
		DFIncomeCategoryItem savedDFIncome = dairyfarmIncomeCatItemRepository
				.save(modelMapper.map(dairyfarmIncomeCatItemDTO, DFIncomeCategoryItem.class));
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(savedDFIncome.getId())
						.message("Income record saved successfully.").status(HttpStatus.CREATED).build(),
				HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFIncomeCatItem(DFIncomeCategoryItemDTO dairyfarmIncomeCatItemDTO) {
		Optional<DFIncomeCategoryItem> optionalDFIncome = dairyfarmIncomeCatItemRepository
				.findById(dairyfarmIncomeCatItemDTO.getId());
		if (!optionalDFIncome.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFIncomeCategoryItem dairyfarmIncome = optionalDFIncome.get();
		modelMapper.map(dairyfarmIncomeCatItemDTO, dairyfarmIncome);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().savedItemId(dairyfarmIncomeCatItemDTO.getId())
						.message("Income record updated successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFIncomeCategoryItemDTO> fetchDFIncomeCatItemDetails(long itemId) {
		Optional<DFIncomeCategoryItem> optionalDFIncome = dairyfarmIncomeCatItemRepository.findById(itemId);
		if (!optionalDFIncome.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided ID");
		DFIncomeCategoryItemDTO dairyfarmIncomeCatItemDTO = modelMapper.map(optionalDFIncome.get(),
				DFIncomeCategoryItemDTO.class);
		return new ResponseEntity<DFIncomeCategoryItemDTO>(dairyfarmIncomeCatItemDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteDFIncomeCatItem(long itemId) {
		if (0 == dairyfarmIncomeCatItemRepository.countById(itemId))
			throw new DairyfarmmanagementException("No record exists with the provided ID");

		if (dairyfarmIncomeRepository.countByItemId(itemId) > 0) {
			throw new DairyfarmmanagementException(
					"Operation is Denied as Income entries exists for the provided Category Item.",
					HttpStatus.FORBIDDEN);
		}

		dairyfarmIncomeCatItemRepository.deleteById(itemId);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("Income record deleted successfully.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

}
