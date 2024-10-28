package com.dairyfarm.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatusCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Pavankumar - created date : Sep 27, 2021
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
public class SuccessResponse implements Serializable {

	private static final long serialVersionUID = 3260080921833124416L;

	private String message;

	private HttpStatusCode status;

	private long savedItemId;

}
