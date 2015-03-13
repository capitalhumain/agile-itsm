package br.com.centralit.citcorpore.metainfo.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class ExternalClassDTO extends BaseEntity {

    private String nameJar;
    private String nameJarOriginal;
    private String nameClass;

    public String getNameJar() {
        return nameJar;
    }

    public void setNameJar(final String nameJar) {
        this.nameJar = nameJar;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(final String nameClass) {
        this.nameClass = nameClass;
    }

    public String getNameJarOriginal() {
        return nameJarOriginal;
    }

    public void setNameJarOriginal(final String nameJarOriginal) {
        this.nameJarOriginal = nameJarOriginal;
    }

}
