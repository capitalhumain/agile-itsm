package br.com.centralit.citcorpore.bean;

import lombok.Getter;
import lombok.Setter;
import br.com.agileitsm.model.support.BaseEntity;
import br.com.citframework.push.DevicePlatformType;

/**
 * DTO para persistência dos dados de alocação de um device a um atendente
 *
 * @author bruno.ribeiro - <a href="mailto:bruno.ribeiro@centrait.com.br">bruno.ribeiro@centrait.com.br</a>
 * @since 15/11/2014
 */
public class AssociacaoDeviceAtendenteDTO extends BaseEntity {

    private static final long serialVersionUID = 3025354135360257061L;

    @Getter
    @Setter
    private Integer idUsuario;

    @Getter
    @Setter
    private String token;

    @Getter
    @Setter
    private Integer active;

    @Getter
    @Setter
    private String connection;

    @Getter
    @Setter
    private Integer devicePlatform;

    @Getter
    @Setter
    private String nomeAtendente;

    public boolean isActive() {
        return getActive() == 1;
    }

    public DevicePlatformType getDevicePlatformType() {
        if (getDevicePlatform() != null) {
            return DevicePlatformType.fromId(getDevicePlatform());
        }
        return null;
    }

    public void setDevicePlatformType(final DevicePlatformType devicePlatform) {
        if (devicePlatform != null) {
            setDevicePlatform(devicePlatform.getId());
        }
    }

}
