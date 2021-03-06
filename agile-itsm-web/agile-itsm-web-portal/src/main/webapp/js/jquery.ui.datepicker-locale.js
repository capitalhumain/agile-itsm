/* Brazilian initialisation for the jQuery UI date picker plugin. */
/* Written by Leonildo Costa Silva (leocsilva@gmail.com). */
jQuery(function($){
	$.datepicker.regional['locale'] = {
		closeText: i18n_message('citcorpore.comum.fechar'),
		prevText: '&#x3c;' + i18n_message('citcorpore.comum.anterior'),
		nextText: i18n_message('citcorpore.comum.proximo') + '&#x3e;',
		currentText: i18n_message('citcorpore.comum.hoje'),
		monthNames: [i18n_message('citcorpore.texto.mes.janeiro'),
		             i18n_message('citcorpore.texto.mes.fevereiro'),
		             i18n_message('citcorpore.texto.mes.marco'),
		             i18n_message('citcorpore.texto.mes.abril'),
		             i18n_message('citcorpore.texto.mes.maio'),
		             i18n_message('citcorpore.texto.mes.junho'),
		             i18n_message('citcorpore.texto.mes.julho'),
		             i18n_message('citcorpore.texto.mes.agosto'),
		             i18n_message('citcorpore.texto.mes.setembro'),
		             i18n_message('citcorpore.texto.mes.outubro'),
		             i18n_message('citcorpore.texto.mes.novembro'),
		             i18n_message('citcorpore.texto.mes.dezembro')],
		monthNamesShort: [i18n_message('citcorpore.texto.abreviado.mes.janeiro'),
		                  i18n_message('citcorpore.texto.abreviado.mes.fevereiro'),
		                  i18n_message('citcorpore.texto.abreviado.mes.marco'),
		                  i18n_message('citcorpore.texto.abreviado.mes.abril'),
		                  i18n_message('citcorpore.texto.abreviado.mes.maio'),
		                  i18n_message('citcorpore.texto.abreviado.mes.junho'),
		                  i18n_message('citcorpore.texto.abreviado.mes.julho'),
		                  i18n_message('citcorpore.texto.abreviado.mes.agosto'),
		                  i18n_message('citcorpore.texto.abreviado.mes.setembro'),
		                  i18n_message('citcorpore.texto.abreviado.mes.outubro'),
		                  i18n_message('citcorpore.texto.abreviado.mes.novembro'),
		                  i18n_message('citcorpore.texto.abreviado.mes.dezembro')],
		dayNames: [i18n_message('citcorpore.texto.diaSemana.domingo'),
                   i18n_message('citcorpore.texto.diaSemana.segundaFeira'),
                   i18n_message('citcorpore.texto.diaSemana.tercaFeira'),
                   i18n_message('citcorpore.texto.diaSemana.quartaFeira'),
                   i18n_message('citcorpore.texto.diaSemana.quintaFeira'),
                   i18n_message('citcorpore.texto.diaSemana.sextaFeira'),
                   i18n_message('citcorpore.texto.diaSemana.sabado')],
		dayNamesShort: [i18n_message('citcorpore.texto.abreviado.diaSemana.domingo'),
		                i18n_message('citcorpore.texto.abreviado.diaSemana.segundaFeira'),
		                i18n_message('citcorpore.texto.abreviado.diaSemana.tercaFeira'),
		                i18n_message('citcorpore.texto.abreviado.diaSemana.quartaFeira'),
		                i18n_message('citcorpore.texto.abreviado.diaSemana.quintaFeira'),
		                i18n_message('citcorpore.texto.abreviado.diaSemana.sextaFeira'),
		                i18n_message('citcorpore.texto.abreviado.diaSemana.sabado')],
		dayNamesMin: [i18n_message('citcorpore.texto.abreviado.diaSemana.domingo'),
	                  i18n_message('citcorpore.texto.abreviado.diaSemana.segundaFeira'),
	                  i18n_message('citcorpore.texto.abreviado.diaSemana.tercaFeira'),
	                  i18n_message('citcorpore.texto.abreviado.diaSemana.quartaFeira'),
	                  i18n_message('citcorpore.texto.abreviado.diaSemana.quintaFeira'),
	                  i18n_message('citcorpore.texto.abreviado.diaSemana.sextaFeira'),
	                  i18n_message('citcorpore.texto.abreviado.diaSemana.sabado')],
		weekHeader: 'Sm',
		dateFormat: ( locale == '' || locale == 'pt' ? 'dd/mm/yy' : (locale == 'en' ? 'mm/dd/yy' : (locale == 'es' ? 'dd/mm/yy' : '') ) ),
		firstDay: 0,
		isRTL: false,
		showMonthAfterYear: false,
		yearSuffix: ''};
	
	$.datepicker.setDefaults($.datepicker.regional['locale']);
});