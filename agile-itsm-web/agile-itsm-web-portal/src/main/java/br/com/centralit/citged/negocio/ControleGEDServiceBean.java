package br.com.centralit.citged.negocio;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import br.com.agileitsm.model.support.BaseEntity;
import br.com.centralit.citcorpore.bean.AnexoBaseConhecimentoDTO;
import br.com.centralit.citcorpore.bean.UploadDTO;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.CriptoUtils;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citcorpore.util.Util;
import br.com.centralit.citged.bean.AssinaturaControleGEDDTO;
import br.com.centralit.citged.bean.ControleGEDDTO;
import br.com.centralit.citged.integracao.AssinaturaControleGEDDao;
import br.com.centralit.citged.integracao.ControleGEDDao;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.integracao.CrudDAO;
import br.com.citframework.integracao.TransactionControler;
import br.com.citframework.integracao.TransactionControlerImpl;
import br.com.citframework.service.CrudServiceImpl;
import br.com.citframework.util.Constantes;
import br.com.citframework.util.UtilDatas;
import br.com.citframework.util.UtilStrings;

@SuppressWarnings({"rawtypes", "unchecked"})
public class ControleGEDServiceBean extends CrudServiceImpl implements ControleGEDService {

    private ControleGEDDao dao;

    @Override
    protected ControleGEDDao getDao() {
        if (dao == null) {
            dao = new ControleGEDDao();
        }
        return dao;
    }

    @Override
    public Collection listByIdTabelaAndID(final Integer idTabela, final Integer id) throws Exception {
        return getDao().listByIdTabelaAndID(idTabela, id);
    }

    @Override
    public String getProximaPastaArmazenar() throws Exception {
        return getDao().getProximaPastaArmazenar();
    }

    @Override
    public Collection convertListControleGEDToUploadDTO(final Collection colAnexosControleGED) throws Exception {
        if (colAnexosControleGED == null) {
            return null;
        }
        final Collection colFinal = new ArrayList();
        for (final Iterator it = colAnexosControleGED.iterator(); it.hasNext();) {
            final ControleGEDDTO controleGedDto = (ControleGEDDTO) it.next();
            final UploadDTO uploadDto = new UploadDTO();
            uploadDto.setIdControleGED(controleGedDto.getIdControleGED());
            uploadDto.setId(controleGedDto.getId());
            if (controleGedDto.getDescricaoArquivo() != null) {
                uploadDto.setDescricao(controleGedDto.getDescricaoArquivo());
            } else {
                uploadDto.setDescricao("");
            }
            uploadDto.setNameFile(controleGedDto.getNomeArquivo());
            uploadDto.setPath(controleGedDto.getPathArquivo());
            uploadDto.setTemporario("N");
            uploadDto.setSituacao("");
            uploadDto.setPath("ID=" + controleGedDto.getIdControleGED());
            if (controleGedDto.getVersao() != null) {
                if (!controleGedDto.getVersao().trim().equalsIgnoreCase("")) {
                    uploadDto.setVersao(controleGedDto.getVersao());
                }
            }
            uploadDto.setCaminhoRelativo(getRelativePathFromGed(controleGedDto));
            colFinal.add(uploadDto);
        }
        return colFinal;
    }

    @Override
    public BaseEntity create(final BaseEntity model) throws ServiceException, LogicException {
        final CrudDAO crudDao = getDao();
        final AssinaturaControleGEDDao assDao = new AssinaturaControleGEDDao();
        final TransactionControler tc = new TransactionControlerImpl(crudDao.getAliasDB());
        try {
            validaCreate(model);
            assDao.setTransactionControler(tc);
            crudDao.setTransactionControler(tc);
            tc.start();
            final ControleGEDDTO ged = (ControleGEDDTO) crudDao.create(model);
            if (ged != null && ged.getPathsAssinaturas() != null && !ged.getPathsAssinaturas().isEmpty()) {
                AssinaturaControleGEDDTO ass = null;
                for (int i = 0; i < ged.getPathsAssinaturas().size(); i++) {
                    ass = new AssinaturaControleGEDDTO();
                    ass.setIdControleGED(ged.getIdControleGED());
                    ass.setPastaControleGed(ged.getPasta());
                    ass.setPathAssinatura(ged.getPathsAssinaturas().get(i).toString());
                    assDao.create(ass);
                }
            }
            tc.commit();

            return model;
        } catch (final Exception e) {
            rollbackTransaction(tc, e);
        } finally {
            tc.closeQuietly();
        }
        return model;
    }

    @Override
    public Collection listByIdTabelaAndIdBaseConhecimentoPaiEFilho(final Integer idTabela, final Integer idBasePai, final Integer idBaseFilho) throws Exception {
        return getDao().listByIdTabelaAndIdBaseConhecimentoPaiEFilho(idTabela, idBasePai, idBaseFilho);
    }

    @Override
    public Collection listByIdTabelaAndIdBaseConhecimento(final Integer idTabela, final Integer idBaseConhecimento) throws Exception {
        return getDao().listByIdTabelaAndIdBaseConhecimento(idTabela, idBaseConhecimento);
    }

    @Override
    public Collection listByIdTabelaAndIdLiberacaoAndLigacao(final Integer idTabela, final Integer idRequisicaoLiberacao) throws Exception {
        return getDao().listByIdTabelaAndIdLiberacaoAndLigacao(idTabela, idRequisicaoLiberacao);
    }

    public String getRelativePathFromGed(final ControleGEDDTO controleGEDDTO) throws Exception {
        if (controleGEDDTO == null) {
            return null;
        }
        try {
            final Integer idEmpresa = 1;
            final String pasta = controleGEDDTO.getPasta();

            String PRONTUARIO_GED_DIRETORIO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedDiretorio, "");
            if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.trim().equalsIgnoreCase("")) {
                PRONTUARIO_GED_DIRETORIO = "";
            }

            if (PRONTUARIO_GED_DIRETORIO.equalsIgnoreCase("")) {
                PRONTUARIO_GED_DIRETORIO = Constantes.getValue("DIRETORIO_GED");
            }

            if (PRONTUARIO_GED_DIRETORIO == null || PRONTUARIO_GED_DIRETORIO.equalsIgnoreCase("")) {
                PRONTUARIO_GED_DIRETORIO = "/ged";
            }
            String PRONTUARIO_GED_INTERNO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInterno, "S");
            if (PRONTUARIO_GED_INTERNO == null) {
                PRONTUARIO_GED_INTERNO = "S";
            }
            String prontuarioGedInternoBancoDados = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInternoBD, "N");
            if (!UtilStrings.isNotVazio(prontuarioGedInternoBancoDados)) {
                prontuarioGedInternoBancoDados = "N";
            }
            if (PRONTUARIO_GED_INTERNO.equalsIgnoreCase("S")) {
                if (PRONTUARIO_GED_INTERNO.equalsIgnoreCase("S") && "S".equalsIgnoreCase(prontuarioGedInternoBancoDados)) { // Se utiliza GED
                    // interno e eh BD
                    // FALTA IMPLEMENTAR!
                } else {
                    final String fileRec = CITCorporeUtil.CAMINHO_REAL_APP + "tempUpload/REC_FROM_GED_" + controleGEDDTO.getIdControleGED() + "."
                            + controleGEDDTO.getExtensaoArquivo();
                    CriptoUtils.decryptFile(PRONTUARIO_GED_DIRETORIO + "/" + idEmpresa + "/" + pasta + "/" + controleGEDDTO.getIdControleGED() + ".ged",
                            fileRec, System.getProperties().get("user.dir") + Constantes.getValue("CAMINHO_CHAVE_PRIVADA"));

                    return Constantes.getValue("CONTEXTO_APLICACAO") + "/tempUpload/REC_FROM_GED_" + controleGEDDTO.getIdControleGED() + "."
                            + controleGEDDTO.getExtensaoArquivo();
                }
            }

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void atualizaAnexos(final Collection<UploadDTO> anexos, final int idTabela, final Integer id, final TransactionControler tc) throws Exception {
        final ControleGEDDao controleGEDDao = getDao();
        controleGEDDao.setTransactionControler(tc);
        String gedInternoBancoDados = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInternoBD, "N");

        if (!UtilStrings.isNotVazio(gedInternoBancoDados)) {
            gedInternoBancoDados = "N";
        }

        final String GED_DIRETORIO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedDiretorio, "/usr/local/gedCitsmart/");

        final String GED_INTERNO = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedInterno, "S");

        String pasta = getProximaPastaArmazenar();
        if (GED_INTERNO.equalsIgnoreCase("S")) {
            pasta = getProximaPastaArmazenar();
            File fileDir = new File(GED_DIRETORIO);

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            fileDir = new File(GED_DIRETORIO + "/1");

            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }

            fileDir = new File(GED_DIRETORIO + "/1/" + pasta);
            if (!fileDir.exists()) {
                fileDir.mkdirs();
            }
        }

        final HashMap<String, UploadDTO> mapUpload = new HashMap();
        if (anexos != null) {
            for (final UploadDTO uploadDto : anexos) {
                if (uploadDto.getIdControleGED() != null) {
                    mapUpload.put("" + uploadDto.getIdControleGED(), uploadDto);
                    continue;
                }
                ControleGEDDTO controleGEDDTO = new ControleGEDDTO();
                controleGEDDTO.setIdTabela(idTabela);
                controleGEDDTO.setId(id);
                controleGEDDTO.setDataHora(UtilDatas.getDataAtual());
                controleGEDDTO.setDescricaoArquivo(uploadDto.getDescricao());
                controleGEDDTO.setExtensaoArquivo(Util.getFileExtension(uploadDto.getNameFile()));
                controleGEDDTO.setPasta(pasta);
                controleGEDDTO.setNomeArquivo(uploadDto.getNameFile());
                controleGEDDTO = (ControleGEDDTO) controleGEDDao.create(controleGEDDTO);
                uploadDto.setControleGEDDto(controleGEDDTO);

                if (controleGEDDTO != null) {
                    mapUpload.put("" + controleGEDDTO.getIdControleGED(), uploadDto);
                }
                if (GED_INTERNO.equalsIgnoreCase("S") && !"S".equalsIgnoreCase(gedInternoBancoDados)) {

                    if (controleGEDDTO != null) {

                        final File arquivo = new File(GED_DIRETORIO + "/1/" + pasta + "/" + controleGEDDTO.getIdControleGED() + "."
                                + Util.getFileExtension(uploadDto.getNameFile()));

                        CriptoUtils.encryptFile(uploadDto.getPath(), GED_DIRETORIO + "/1/" + pasta + "/" + controleGEDDTO.getIdControleGED() + ".ged", System
                                .getProperties().get("user.dir") + Constantes.getValue("CAMINHO_CHAVE_PUBLICA"));

                        arquivo.delete();
                    }

                }
            }
        }

        final Collection<ControleGEDDTO> colGed = controleGEDDao.listByIdTabelaAndID(idTabela, id);
        if (colGed == null) {
            return;
        }

        for (final ControleGEDDTO controleGEDDto : colGed) {
            if (mapUpload.get("" + controleGEDDto.getIdControleGED()) != null) {
                continue;
            }
            controleGEDDao.delete(controleGEDDto);
        }

    }

    @Override
    public ControleGEDDTO getControleGED(final AnexoBaseConhecimentoDTO anexoBaseConhecimento) throws Exception {
        return getDao().getControleGED(anexoBaseConhecimento);
    }

}
