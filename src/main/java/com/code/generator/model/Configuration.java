package com.code.generator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Configuration {

	private String sectionPurpose;
	private Programming programming;
	private Database database;
	private DomainModel domainModel;
}
