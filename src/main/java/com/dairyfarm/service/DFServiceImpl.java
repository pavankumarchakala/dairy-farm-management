package com.dairyfarm.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dairyfarm.dao.DFExpenseCategoryRepository;
import com.dairyfarm.dao.DFIncomeCatItemRepository;
import com.dairyfarm.dao.DFIncomeCategoryRepository;
import com.dairyfarm.dao.DFRepository;
import com.dairyfarm.dao.DFUserRepository;
import com.dairyfarm.dao.UserRepository;
import com.dairyfarm.dto.DairyfarmDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFExpenseCategory;
import com.dairyfarm.entity.DFIncomeCategory;
import com.dairyfarm.entity.DFIncomeCategoryItem;
import com.dairyfarm.entity.DFUser;
import com.dairyfarm.entity.Dairyfarm;
import com.dairyfarm.entity.User;
import com.dairyfarm.enums.Status;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavankumar - created date : Sep 18, 2021
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DFServiceImpl implements DFService {

	private final DFRepository dairyfarmRepository;

	private final DFUserRepository dairyfarmUserRepository;

	private final UserRepository userRepository;

	private final DFIncomeCategoryRepository incomeCategoryRepository;

	private final DFIncomeCatItemRepository incomeCatItemRepository;

	private final DFExpenseCategoryRepository expenseCategoryRepository;

	private final ModelMapper modelMapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> adddairyfarm(DairyfarmDTO dairyfarmDTO) throws DairyfarmmanagementException {
		User user = new User();
		user.setId(dairyfarmDTO.getUser().getId());
		Dairyfarm dairyfarm = modelMapper.map(dairyfarmDTO, Dairyfarm.class);
		dairyfarm.setDairyfarmUsers(
				Arrays.asList(DFUser.builder().dairyfarm(dairyfarm).user(user).status(Status.ACTIVE).build()));
		Dairyfarm saveddairyfarm = dairyfarmRepository.saveAndFlush(dairyfarm);
		savedairyfarmDefaultData(saveddairyfarm);
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder().savedItemId(saveddairyfarm.getId())
				.message("dairyfarm Successfully added.").status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updatedairyfarm(DairyfarmDTO dairyfarmDTO)
			throws DairyfarmmanagementException {
		Optional<Dairyfarm> optionaldairyfarm = dairyfarmRepository.findById(dairyfarmDTO.getId());

		if (!optionaldairyfarm.isPresent()) {
			log.error("dairyfarm doesn't exist with the provided ID.");
			throw new DairyfarmmanagementException("dairyfarm doesn't exist with the provided ID.",
					HttpStatus.NOT_FOUND);
		}

		modelMapper.map(dairyfarmDTO, optionaldairyfarm.get());
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("dairyfarm Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DairyfarmDTO> fetchdairyfarmDetails(long dairyfarmId) throws DairyfarmmanagementException {
		Optional<Dairyfarm> optionaldairyfarm = dairyfarmRepository.findById(dairyfarmId);

		if (!optionaldairyfarm.isPresent()) {
			log.error("dairyfarm doesn't exist with the provided ID.");
			throw new DairyfarmmanagementException("dairyfarm doesn't exist with the provided ID.",
					HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<DairyfarmDTO>(modelMapper.map(optionaldairyfarm.get(), DairyfarmDTO.class),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> adddairyfarmToUser(DairyfarmDTO dairyfarmDTO, long userId) {
		Optional<User> optionalUser = userRepository.findById(userId);
		if (!optionalUser.isPresent())
			throw new DairyfarmmanagementException("No record exists with the provided User ID");
		Dairyfarm dairyfarm = modelMapper.map(dairyfarmDTO, Dairyfarm.class);
		Dairyfarm saveddairyfarm = dairyfarmRepository.saveAndFlush(dairyfarm);
		dairyfarmUserRepository.saveAndFlush(
				DFUser.builder().dairyfarm(saveddairyfarm).user(optionalUser.get()).status(Status.ACTIVE).build());
		savedairyfarmDefaultData(saveddairyfarm);
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder().savedItemId(saveddairyfarm.getId())
				.message("dairyfarm Successfully added.").status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
	}

	private void savedairyfarmDefaultData(final Dairyfarm saveddairyfarm) {
		saveDFIncomeDefaultData(saveddairyfarm);
		saveDFExpenseDefaultData(saveddairyfarm);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deletedairyfarm(long dairyfarmId) {
		Optional<Dairyfarm> optionaldairyfarm = dairyfarmRepository.findById(dairyfarmId);

		if (!optionaldairyfarm.isPresent()) {
			log.error("dairyfarm doesn't exist with the provided ID.");
			throw new DairyfarmmanagementException("dairyfarm doesn't exist with the provided ID.",
					HttpStatus.NOT_FOUND);
		}

		optionaldairyfarm.get().setStatus(Status.INACTIVE);
		List<DFUser> dairyfarmUsers = dairyfarmUserRepository.findAllBydairyfarmId(dairyfarmId);
		dairyfarmUsers.stream().forEach(dairyfarmUser -> dairyfarmUser.setStatus(Status.INACTIVE));
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder()
				.message("dairyfarm is deleted for the  Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	// EXPENSE
	// Uploading the default ExpenseCategory data for the Dairyfarm
	private void saveDFExpenseDefaultData(Dairyfarm saveddairyfarm) {
		List<DFExpenseCategory> list = Arrays
				.asList("Infrastructure", "Maintenance", "Salary", "Medical", "Feed", "Cattle").stream()
				.map(item -> DFExpenseCategory.builder().category(item).dairyfarm(saveddairyfarm).build())
				.collect(Collectors.toList());
		expenseCategoryRepository.saveAllAndFlush(list);
	}

	// INCOME
	// Uploading the default IncomeCategory data for the Dairyfarm
	private void saveDFIncomeDefaultData(Dairyfarm saveddairyfarm) {
		/**************************** SAVE CATEGORY ******************************/
		DFIncomeCategory prodCat = incomeCategoryRepository
				.saveAndFlush(DFIncomeCategory.builder().category("Product").dairyfarm(saveddairyfarm).build());
		DFIncomeCategory itemCat = incomeCategoryRepository
				.saveAndFlush(DFIncomeCategory.builder().category("Item").dairyfarm(saveddairyfarm).build());
		List<DFIncomeCategory> catList = Arrays.asList("Feed", "Manure").stream()
				.map(item -> DFIncomeCategory.builder().category(item).dairyfarm(saveddairyfarm).build())
				.collect(Collectors.toList());
		incomeCategoryRepository.saveAllAndFlush(catList);
		/****************************
		 * SAVE CATEGORY ITEMS
		 ******************************/
		List<DFIncomeCategoryItem> itemList = Arrays.asList("Milk", "Ghee", "Butter", "Paneer", "Cattle", "Poultry")
				.stream()
				.map(item -> DFIncomeCategoryItem.builder()
						.category("Cattle".equals(item) || "Poultry".equals(item) ? itemCat : prodCat).item(item)
						.build())
				.collect(Collectors.toList());
		incomeCatItemRepository.saveAllAndFlush(itemList);
	}

}
