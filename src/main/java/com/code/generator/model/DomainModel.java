package com.code.generator.model;

import java.util.ArrayList;

public class DomainModel extends ArrayList<DomainObject>{

	private static final long serialVersionUID = 1L;
	
	public String toString() {
		
		String toString = "";
		DomainObject domainObject = null;
		for (int i=0; i < size(); i++) {
			
			domainObject = get(i);
			toString += domainObject.toString();
		}
		return toString;
	}

}
