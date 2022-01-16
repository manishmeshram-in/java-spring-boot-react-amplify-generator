package com.code.generator.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Field {

	String name;
	List<String> type;
	List<String> constraints;
}
