package com.app.manager_assignment.exceptionmapping;

import java.io.Serializable;

public class PermissionDeniedException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -767121509987628799L;

	public PermissionDeniedException(String message) {
		super(message);
	}
}
