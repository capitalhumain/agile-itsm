package br.com.centralit.citcorpore.bean;

public class DataManagerFKRelatedDTO {

    private String nomeTabelaRelacionada;
    private String join;
    private String partChild;
    private String partParent;

    public String getNomeTabelaRelacionada() {
        return nomeTabelaRelacionada;
    }

    public void setNomeTabelaRelacionada(final String nomeTabelaRelacionada) {
        this.nomeTabelaRelacionada = nomeTabelaRelacionada;
    }

    public String getJoin() {
        return join;
    }

    public void setJoin(final String join) {
        this.join = join;
    }

    public String getPartChild() {
        return partChild;
    }

    public void setPartChild(final String partChild) {
        this.partChild = partChild;
    }

    public String getPartParent() {
        return partParent;
    }

    public void setPartParent(final String partParent) {
        this.partParent = partParent;
    }

}
