package br.com.centralit.citcorpore.mail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.ModeloEmailDTO;
import br.com.centralit.citcorpore.negocio.ModeloEmailService;
import br.com.centralit.citcorpore.util.Enumerados.TipoDate;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Reflexao;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilStrings;

/**
 * @author rogerio
 *
 *
 */
public class MensagemEmail {

    public String to;
    public String cc;
    public String cco;
    public String from;
    public String subject;
    public String text;
    public boolean confirmarLeituraMail;

    public MensagemEmail(final String to, final String cc, final String cco, final String from, final String subject, final String text) {
        this.setTo(to);
        this.setCc(cc);
        this.setCco(cco);
        this.setFrom(from);
        this.setSubject(subject);
        this.setText(text);
        this.setConfirmarLeituraMail(false);
    }

    public MensagemEmail(final Integer idModelo, final Map<String, String> map) throws Exception {
        this.criar(idModelo, map);
    }

    public MensagemEmail(final Integer idModelo, final BaseEntity[] bean) throws Exception {
        final Map<String, String> map = new HashMap<>();
        if (bean != null && bean.length > 0) {
            for (final BaseEntity element : bean) {
                try {
                    final List<?> lstGets = Reflexao.findGets(element);
                    for (int i = 0; i < lstGets.size(); i++) {
                        final String propriedade = UtilStrings.convertePrimeiraLetra((String) lstGets.get(i), "L");
                        final Object value = Reflexao.getPropertyValue(element, propriedade);
                        if (value == null) {
                            continue;
                        }
                        final String id = UtilStrings.convertePrimeiraLetra(propriedade, "L");
                        if (String.class.isInstance(value)) {
                            map.put(id, (String) value);
                        } else if (Integer.class.isInstance(value)) {
                            map.put(id, ((Integer) value).toString());
                        } else if (java.sql.Date.class.isInstance(value)) {
                            map.put(id, UtilDatas.dateToSTR((java.sql.Date) value));
                        } else if (java.sql.Timestamp.class.isInstance(value)) {
                            map.put(id, UtilDatas.convertDateToString(TipoDate.TIMESTAMP_DEFAULT, (java.sql.Timestamp) value, null));
                        }

                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                }
            }
        }
        this.criar(idModelo, map);
    }

    public void criar(final Integer idModelo, final Map<String, String> map) throws Exception {

        if (idModelo == null || idModelo.intValue() < 0) {
            return;
        }

        final ModeloEmailService modeloEmailService = (ModeloEmailService) ServiceLocator.getInstance().getService(ModeloEmailService.class, null);

        ModeloEmailDTO modeloEmailDto = new ModeloEmailDTO();

        modeloEmailDto.setIdModeloEmail(idModelo);
        modeloEmailDto = (ModeloEmailDTO) modeloEmailService.restore(modeloEmailDto);

        if (modeloEmailDto == null) {
            throw new LogicException("Modelo de E-mail não parametrizado.");
        }

        String texto = modeloEmailDto.getTexto();
        String titulo = modeloEmailDto.getTitulo();

        try {
            for (final String key : map.keySet()) {
                if (texto != null) {
                    texto = texto.replace(String.format("${%s}", key.toUpperCase()), map.get(key));
                }
                if (titulo != null) {
                    titulo = titulo.replace(String.format("${%s}", key.toUpperCase()), map.get(key));
                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }

        // remove possíveis chaves que não encontraram valores no map
        if (texto != null) {
            texto = texto.replaceAll("\\$\\{[^}]*\\}", "");
        }

        this.setText(texto);
        this.setSubject(titulo);
        this.setConfirmarLeituraMail(false);
    }

    public void envia(final String to, final String cc, final String from) throws Exception {
        this.setTo(to);
        this.setCc(cc);
        this.setFrom(from);
        new Thread(new ControleEmail(this)).start();
    }

    public String getTo() {
        return to;
    }

    public void setTo(final String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(final String cc) {
        this.cc = cc;
    }

    public String getCco() {
        return cco;
    }

    public void setCco(final String cco) {
        this.cco = cco;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(final String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public boolean isConfirmarLeituraMail() {
        return confirmarLeituraMail;
    }

    public void setConfirmarLeituraMail(final boolean confirmarLeituraMail) {
        this.confirmarLeituraMail = confirmarLeituraMail;
    }

}
