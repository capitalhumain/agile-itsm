package br.com.centralit.citgerencial.bean;

import java.util.Collection;
import java.util.HashMap;

public abstract class GerencialGenerateSQL {

    public abstract String generateSQL(final HashMap parametersValues, final Collection paramtersDefinition);

}
