package com.example.food.model.controller.exceptions;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.example.food.model.entities.ResponseBodyFields;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonInclude(Include.NON_NULL)
public class ResponseBody implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer status;
	private String type;
	private String title;
	private String detail;

	private LocalDateTime timestamp;

	private List<ResponseBodyFields> responseBodyFields;

}
