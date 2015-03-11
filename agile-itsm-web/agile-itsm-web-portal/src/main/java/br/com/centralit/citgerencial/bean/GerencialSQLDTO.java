package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialSQLDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4096190794278568225L;
	private String sql;
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
