package br.com.centralit.citgerencial.bean;

import br.com.agileitsm.model.support.BaseEntity;

public class GerencialItemPainelDTO extends BaseEntity {

    private static final long serialVersionUID = -7110507162974361689L;

    private String name;
    private String file;
    private String top;
    private String heigth;
    private String left;
    private String width;
    private String total;

    public String getFile() {
        return file;
    }

    public void setFile(final String file) {
        this.file = file;
    }

    public String getHeigth() {
        return heigth;
    }

    public void setHeigth(final String heigth) {
        this.heigth = heigth;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(final String left) {
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTop() {
        return top;
    }

    public void setTop(final String top) {
        this.top = top;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(final String width) {
        this.width = width;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(final String total) {
        this.total = total;
    }

}
