<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="import.jsp"></c:import>
<script src='http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js'></script>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
        <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
        <script src="jquery-2.0.0.js"></script>
        <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
        <script src='javascript/preencher.js' type="text/Javascript"></script>
<title>Alocacao Automatica</title>

<script>
            function allowDrop(ev)
            {
                ev.preventDefault();
            }
            /*Carrega o conteudo selecionado*/
            function drag(ev)
            {
                ev.dataTransfer.setData("Text", ev.target.id);

            }
            /*Função que pega a div selecionada e insere*/
            /*dentro da dis com a classe horario*/
            function drop(ev)
            {
                ev.preventDefault();
                var data = ev.dataTransfer.getData("Text");//carrega os dados do tipo texto
                ev.target.appendChild(document.getElementById(data));
                for (i = 1; i < 290; i++) {
                    j = i - 1;
                    k = i + 1;
                    if ($('.horario-' + i).text() !== "") {
                        $('.horario-' + i).attr('ondrop', '');
                        $('.horario-' + i).attr('ondragover', '');
                        $('.horario-' + j).attr('ondrop', '');
                        $('.horario-' + j).attr('ondragover', '');
                        $('.horario-' + k).attr('ondrop', '');
                        $('.horario-' + k).attr('ondragover', '');
                    } else {
                        $('.horario-' + i).attr('ondrop', 'drop(event)');
                        $('.horario-' + i).attr('ondragover', 'allowDrop(event)');
                    }

                }
            }
        </script>

</head>
<body>
	<jsp:include page="cabecalho.jsp"/>

	<div id='login'>
	<div id="bemvindo">Bem Vindo ${requestScope.logado.nome }</div>
	
	 <div id="logout"><a href="autent.do">Logout</a></div> 
</div>
	
	<jsp:include page="menuvertical.jsp" />

	<div id='secao'>
		<section>
		<div id="combo">

			<form >
				<select name="credito" size=1>
					<option selected value="vazio"></option>
					<option value="segundafeira">segunda-feira</option>
					<option value="tercafeira">terca-feira</option>
					<option value="quartafeira">quarta-feira</option>
					<option value="quintafeira">quinta-feira</option>
					<option value="sextafeira">sexta-feira</option>
					<option value="sabado">sabado</option>
					<option value="domingo">domingo</option>
				</select> 
				<button type="button" id="realoc">Realocar Turmas</button>
				<button type="button" id="Salvar">Salvar</button>
			</form>
		</div>
		<jsp:include page="desenhartabela.jsp" />
		</section>
	</div>
	<footer> 
	</footer>

</body>
</html>