package br.com.centralit.citcorpore.batch;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;

import br.com.centralit.citcorpore.bean.CategoriaGaleriaImagemDTO;
import br.com.centralit.citcorpore.bean.GaleriaImagensDTO;
import br.com.centralit.citcorpore.negocio.CategoriaGaleriaImagemService;
import br.com.centralit.citcorpore.negocio.GaleriaImagensService;
import br.com.centralit.citcorpore.util.CITCorporeUtil;
import br.com.centralit.citcorpore.util.CriptoUtils;
import br.com.centralit.citcorpore.util.Enumerados;
import br.com.centralit.citcorpore.util.ParametroUtil;
import br.com.centralit.citged.bean.ControleGEDDTO;
import br.com.centralit.citged.negocio.ControleGEDService;
import br.com.citframework.excecao.LogicException;
import br.com.citframework.excecao.ServiceException;
import br.com.citframework.service.ServiceLocator;
import br.com.citframework.util.Constantes;

public class ThreadIniciaGaleriaImagens extends Thread {

    @Override
    public void run() {
        CategoriaGaleriaImagemService categoriaGaleriaImagemService = null;;
        try {
            categoriaGaleriaImagemService = (CategoriaGaleriaImagemService) ServiceLocator.getInstance().getService(CategoriaGaleriaImagemService.class, null);
        } catch (final ServiceException e) {
            e.printStackTrace();
        } catch (final Exception e) {
            e.printStackTrace();
        }
        Collection colG = null;
        try {
            if (categoriaGaleriaImagemService != null) {
                colG = categoriaGaleriaImagemService.list();
            }
        } catch (final LogicException e) {
            e.printStackTrace();
        } catch (final ServiceException e) {
            e.printStackTrace();
        }
        GaleriaImagensService galeriaImagensService = null;
        try {
            galeriaImagensService = (GaleriaImagensService) ServiceLocator.getInstance().getService(GaleriaImagensService.class, null);
        } catch (final ServiceException e1) {
            e1.printStackTrace();
        } catch (final Exception e1) {
            e1.printStackTrace();
        }
        ControleGEDService controleGEDService = null;
        try {
            controleGEDService = (ControleGEDService) ServiceLocator.getInstance().getService(ControleGEDService.class, null);
        } catch (final ServiceException e1) {
            e1.printStackTrace();
        } catch (final Exception e1) {
            e1.printStackTrace();
        }

        String DIRETORIO_GED = null;
        try {
            DIRETORIO_GED = ParametroUtil.getValorParametroCitSmartHashMap(Enumerados.ParametroSistema.GedDiretorio, "");
        } catch (final Exception e2) {
            e2.printStackTrace();
        }
        if (DIRETORIO_GED == null || DIRETORIO_GED.trim().equalsIgnoreCase("")) {
            DIRETORIO_GED = "";
        }

        if (DIRETORIO_GED.equalsIgnoreCase("")) {
            DIRETORIO_GED = Constantes.getValue("DIRETORIO_GED");
        }

        if (DIRETORIO_GED == null || DIRETORIO_GED.equalsIgnoreCase("")) {
            DIRETORIO_GED = "/ged";
        }

        final Integer idEmpresa = 1;
        if (colG != null && colG.size() > 0) {
            for (final Iterator it1 = colG.iterator(); it1.hasNext();) {
                CategoriaGaleriaImagemDTO categDto = (CategoriaGaleriaImagemDTO) it1.next();
                Collection col = null;
                try {
                    col = galeriaImagensService.findByCategoria(categDto.getIdCategoriaGaleriaImagem());
                } catch (final Exception e1) {
                    e1.printStackTrace();
                }
                if (col != null) {
                    for (final Iterator it = col.iterator(); it.hasNext();) {
                        final GaleriaImagensDTO galeriaImagensAux = (GaleriaImagensDTO) it.next();

                        ControleGEDDTO controleGEDDTO = new ControleGEDDTO();
                        controleGEDDTO.setIdControleGED(new Integer(galeriaImagensAux.getNomeImagem()));
                        try {
                            controleGEDDTO = (ControleGEDDTO) controleGEDService.restore(controleGEDDTO);
                        } catch (final LogicException e1) {
                            e1.printStackTrace();
                        } catch (final ServiceException e1) {
                            e1.printStackTrace();
                        }
                        if (controleGEDDTO != null) {

                            File fileDir2 = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/galeriaImagens");
                            if (!fileDir2.exists()) {
                                fileDir2.mkdirs();
                            }
                            fileDir2 = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/galeriaImagens/" + idEmpresa);
                            if (!fileDir2.exists()) {
                                fileDir2.mkdirs();
                            }
                            fileDir2 = new File(CITCorporeUtil.CAMINHO_REAL_APP + "/galeriaImagens/" + idEmpresa + "/"
                                    + galeriaImagensAux.getIdCategoriaGaleriaImagem());
                            if (!fileDir2.exists()) {
                                fileDir2.mkdirs();
                            }

                            try {
                                final File arquivo = new File(DIRETORIO_GED + "/" + idEmpresa + "/" + controleGEDDTO.getPasta() + "/"
                                        + controleGEDDTO.getIdControleGED() + ".ged");
                                if (arquivo.exists()) {
                                    CriptoUtils.decryptFile(
                                            DIRETORIO_GED + "/" + idEmpresa + "/" + controleGEDDTO.getPasta() + "/" + controleGEDDTO.getIdControleGED()
                                            + ".ged",
                                            CITCorporeUtil.CAMINHO_REAL_APP + "/galeriaImagens/" + idEmpresa + "/"
                                                    + galeriaImagensAux.getIdCategoriaGaleriaImagem() + "/" + controleGEDDTO.getIdControleGED() + "."
                                                    + galeriaImagensAux.getExtensao(),
                                                    System.getProperties().get("user.dir") + Constantes.getValue("CAMINHO_CHAVE_PRIVADA"));
                                } else {
                                    System.out.println("Arquivo : " + DIRETORIO_GED + "/" + idEmpresa + "/" + controleGEDDTO.getPasta() + "/"
                                            + controleGEDDTO.getIdControleGED() + ".ged" + " Não Encontrado!");
                                }

                            } catch (final Exception e) {
                                e.printStackTrace();
                            }

                        }
                    }
                }
                categDto = null;

            }
        }
    }

}
