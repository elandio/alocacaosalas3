/**
 * 
 */

$(document).ready(function() {
	$('#realoc').click(function() {
		
		$.get("aloccontroller.do", {
			botao2 : "botao"
		}, function(saida) {
			alert("rodou "+saida);
		}, "json");
	});
		
	$('td div').dblclick(function() {
		// libera a div para o usuario fazero drag
		$(this).contents().css("background", "#33ff33");
		$(this).contents().attr('draggable', true);
		$(this).contents().attr('ondragstart', 'drag(event)');
	});
	// consulta o banco e selelciona de acordo com o dia da semana toda a vez que um item for selecionado
	$('select').change(function() {
		$('.disc3').remove();
		$('.disc2').remove();
		var dia_semana = $('option:selected').text();
		$.post("aloccontroller.do", {
			dia : dia_semana
		}, function(mapa) { 
			var i = 1;
			var k = 2;
			var acres;
			var soma;
			$.each(mapa, function(horario, hst) {//realiza um foreach no hashmap retornado
				var contem = false;
				k++;
				var element = document.createElement('div'); // cria-se o o elemento
				element.id = 'div' + k; // defini diferentes id's
				if(hst.numcre == 3) element.className = 'disc3';
				// classe com tamanho padrão dependendo o numero de creditos da disciplina
				else element.className = 'disc2';
				element.value = 'gerado por js';
				element.textContent = hst.codtur + "\n" + hst.coddis;// imprime na tela e salva dentro da div
				element.setAttribute('draggable', false);
				// element.setAttribute('ondragstart', 'drag(event)');
				$('#div7').css("background", "#FF9900");
				$(".horario-" + horario).append(element);// insere a div criada em uma outra div denominada horario
				$(".horario-" + horario).attr('ondrop', '');
				$(".horario-" + horario).attr('ondragover', '');
				acres = parseInt(horario);
				soma = acres + 1;
				$(".horario-" + soma).attr('ondrop', '');
				$(".horario-" + soma).attr('ondragover', '');
				soma = acres - 1;
				$(".horario-" + soma).attr('ondrop', '');
				$(".horario-" + soma).attr('ondragover', '');
			}); }, "json"); 
	});
});