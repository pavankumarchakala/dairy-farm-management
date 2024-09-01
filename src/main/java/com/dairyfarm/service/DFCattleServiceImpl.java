package com.dairyfarm.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dairyfarm.dao.DFCattleRepository;
import com.dairyfarm.dto.DFCattleDTO;
import com.dairyfarm.dto.SuccessResponse;
import com.dairyfarm.entity.DFCattle;
import com.dairyfarm.enums.CattleStatus;
import com.dairyfarm.exceptions.DairyfarmmanagementException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Pavankumar - created date : Sep 28, 2021
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DFCattleServiceImpl implements DFCattleService {

	private final DFCattleRepository dairyfarmCattleRepository;

	private final ModelMapper mapper;

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> addCattleTodairyfarm(DFCattleDTO dairyfarmCattleDTO) {
		DFCattle dfCattle = mapper.map(dairyfarmCattleDTO, DFCattle.class);
		DFCattle savedCattle = dairyfarmCattleRepository.save(dfCattle);
		return new ResponseEntity<SuccessResponse>(SuccessResponse.builder().savedItemId(savedCattle.getId())
				.message("dairyfarm Successfully added.").status(HttpStatus.CREATED).build(), HttpStatus.CREATED);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> updateDFCattle(DFCattleDTO dairyfarmCattleDTO) {
		Optional<DFCattle> optionaldairyfarmCattle = dairyfarmCattleRepository.findById(dairyfarmCattleDTO.getId());

		if (!optionaldairyfarmCattle.isPresent()) {
			log.error("No record exists with the provided Dairyfarm Cattle ID.");
			throw new DairyfarmmanagementException("No record exists with the provided Dairyfarm Cattle ID.",
					HttpStatus.NOT_FOUND);
		}

		mapper.map(dairyfarmCattleDTO, optionaldairyfarmCattle.get());
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("dairyfarm Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<DFCattleDTO> fetchDFCattleDetails(long dairyfarmCattleId) {
		Optional<DFCattle> optionaldairyfarmCattle = dairyfarmCattleRepository.findById(dairyfarmCattleId);

		if (!optionaldairyfarmCattle.isPresent()) {
			log.error("No record exists with the provided Dairyfarm Cattle ID.");
			throw new DairyfarmmanagementException("No record exists with the provided Dairyfarm Cattle ID.",
					HttpStatus.NOT_FOUND);
		}

		DFCattleDTO dairyfarmCattleDTO = mapper.map(optionaldairyfarmCattle.get(), DFCattleDTO.class);
		return new ResponseEntity<DFCattleDTO>(dairyfarmCattleDTO, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<List<DFCattleDTO>> fetchDFCattleBydairyfarm(long dairyfarmId) {
		long count = dairyfarmCattleRepository.countBydairyfarmId(dairyfarmId);

		if (count == 0) {
			log.error("No records exist for the provided Dairyfarm ID.");
			throw new DairyfarmmanagementException("No records exist for the provided Dairyfarm ID.",
					HttpStatus.NOT_FOUND);
		}

		List<DFCattle> dairyfarmCattle = dairyfarmCattleRepository.findAllBydairyfarmId(dairyfarmId);
		List<DFCattleDTO> dairyfarmCattleDTOs = mapper.map(dairyfarmCattle, new TypeToken<List<DFCattleDTO>>() {
		}.getType());
		return new ResponseEntity<List<DFCattleDTO>>(dairyfarmCattleDTOs, HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> deleteCattle(long dairyfarmCattleId) {
		Optional<DFCattle> optionaldairyfarmCattle = dairyfarmCattleRepository.findById(dairyfarmCattleId);

		if (!optionaldairyfarmCattle.isPresent()) {
			log.error("No record exists with the provided Dairyfarm Cattle ID.");
			throw new DairyfarmmanagementException("No record exists with the provided Dairyfarm Cattle ID.",
					HttpStatus.NOT_FOUND);
		}

		optionaldairyfarmCattle.get().setStatus(CattleStatus.INACTIVE);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("dairyfarm Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<SuccessResponse> changeCattleStatus(long dairyfarmCattleId, CattleStatus cattleStatus) {
		Optional<DFCattle> optionaldairyfarmCattle = dairyfarmCattleRepository.findById(dairyfarmCattleId);

		if (!optionaldairyfarmCattle.isPresent()) {
			log.error("No record exists with the provided Dairyfarm Cattle ID.");
			throw new DairyfarmmanagementException("No record exists with the provided Dairyfarm Cattle ID.",
					HttpStatus.NOT_FOUND);
		}

		if (null == cattleStatus) {
			log.error("Entered status not found.");
			throw new DairyfarmmanagementException("Entered status not found.", HttpStatus.NOT_FOUND);
		}

		optionaldairyfarmCattle.get().setStatus(cattleStatus);
		return new ResponseEntity<SuccessResponse>(
				SuccessResponse.builder().message("dairyfarm Successfully updated.").status(HttpStatus.OK).build(),
				HttpStatus.OK);
	}

}
