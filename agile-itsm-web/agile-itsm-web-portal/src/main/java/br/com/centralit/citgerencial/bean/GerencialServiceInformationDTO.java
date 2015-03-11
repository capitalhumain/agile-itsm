package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialServiceInformationDTO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8696987035765743336L;
	private String className;
	private String methodName;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
}
