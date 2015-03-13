package br.com.centralit.citcorpore.bean;

import java.util.Collection;

import br.com.agileitsm.model.support.BaseEntity;

/**
 * @author valdoilo.damasceno
 *
 */
public class IndexDTO extends BaseEntity {

    private static final long serialVersionUID = -945638009975594699L;

    private Collection<ReleaseDTO> listRelease;

    public Collection<ReleaseDTO> getListRelease() {
        return listRelease;
    }

    public void setListRelease(final Collection<ReleaseDTO> listRelease) {
        this.listRelease = listRelease;
    }

}
