package com.code.generator.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Programming {

	String language;
    String platform;
    String tool;
    String cliCommand;
    List<String> dependencies;
	String applicationName;
}
