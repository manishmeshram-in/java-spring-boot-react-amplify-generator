package com.code.generator.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Database {

    String port;
    String name;
    String user;
    String password;
}
