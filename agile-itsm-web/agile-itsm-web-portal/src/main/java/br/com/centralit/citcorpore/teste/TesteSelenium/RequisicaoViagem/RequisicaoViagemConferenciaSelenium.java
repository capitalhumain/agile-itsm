package br.com.centralit.citcorpore.teste.TesteSelenium.RequisicaoViagem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RequisicaoViagemConferenciaSelenium {

	private RequisicaoViagemUtilSelenium utilSelenium = new RequisicaoViagemUtilSelenium();

	@Before
	public void setUp() throws Exception {
		utilSelenium.iniciarNavegador();
	}

	@Test
	public void testCaseRequisicao() throws Exception {		
		utilSelenium.login("david", "1");
		utilSelenium.pesquisarSolicitacaoInterno();
		utilSelenium.conferenciaAprovada();
//		utilSelenium.conferenciaNaoAprovada();
	}
	
	@After
	public void tearDown() throws Exception {
		utilSelenium.fechar();
	}
}