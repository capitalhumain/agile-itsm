/**
 * FUNCTIONS
 */
/* Criação Viagem */
function habilitaInfoNaoFuncionario() {
	if($("#nao_funcionario:checked").length) {
		$("#info-complementares-nao-funcionario-container").show();
	} else {
		$("#info-complementares-nao-funcionario-container").hide();
	}
}

function addIntegrante() {
	if(StringUtils.trim($("#nome_funcionario").val()) == "") {
		alert("Informe o nome do funcionário!");
		return;
	}
	if($("#nao_funcionario:checked").length) {
		if(StringUtils.trim($("#nome_responsavel_prest_contas").val()) == "") {
			alert("Informe o nome do responsável pela prestação de contas!");
			return;
		} 
		if(StringUtils.trim($("#info_complementares_nao_funcionario").val()) == "") {
			alert("Informe as informações complementares do não funcionário!");
			return;
		}
	}
}

/* Execução Viagem */
function abrirPopup(){
	$("#POPUP_ITEMCONTROLEFINANCEIRO").dialog("open");
} 

function fecharFrameItemControleFinanceiro(){
	limparTabelaDeItensCadastradosDaPopup('tblItemControleFinaceiro');
	$("#POPUP_ITEMCONTROLEFINANCEIRO").dialog("close");
}

/* Autorização Viagem */
function habilitaJustificativaNaoAutorizacao() {
	if($("#nao_autorizado:checked").length) {
		$("#autorizacao-justificativa-container").show();
	} else {
		$("#autorizacao-justificativa-container").hide();
	}
}

/**
 * LOAD
 */
$(window).load(function() {
	/* Criação Viagem */
	habilitaInfoNaoFuncionario();
	
	/* Autorização Viagem */
	habilitaJustificativaNaoAutorizacao();
	
	/* Conferência Viagem */
	habilitaJustificativaNaoAutorizacaoConferencia();
});

/**
 * ACTIONS
 */
/* Criação Viagem */
$(".radio-integrante").on("click", function() {
	habilitaInfoNaoFuncionario();
});

$("#btn-add-integrante").click(function() {
	addIntegrante();
});

/* Execução Viagem */
$(".browser").treeview();

$("#POPUP_ITEMCONTROLEFINANCEIRO").dialog({
	autoOpen : false,
	width : "80%",
	height : $(window).height()-200,
	modal : true
});

/* Autorização Viagem */
$(".radio-autorizacao").on("click", function() {
	habilitaJustificativaNaoAutorizacao();
});

/* Conferência Viagem */
$(".radio-autorizacao-conferencia").on("click", function() {
	habilitaJustificativaNaoAutorizacaoConferencia();
});

/* Conferência Viagem */
function habilitaJustificativaNaoAutorizacaoConferencia() {
	if($("#nao_autorizado_conferencia:checked").length) {
		$("#autorizacao-justificativa-conferencia-container").show();
	} else {
		$("#autorizacao-justificativa-conferencia-container").hide();
	}
}