<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="import.jsp"></c:import>
<link rel='stylesheet' type='text/css' href='CSS/preferencia.css'>
<title>Preferencia</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp"/>

	<div id='login'>
	</div>
	<jsp:include page="menuvertical.jsp"></jsp:include>
	<div id="secao">
		<section>
		<div id="combosPreferencia">
			<div id="combo1">
				<br><label>Bloco</label><br> 
				
				<select name="bloco" size=1" >
					<option name="valor1" >SSC0456</option>
				</select>
			</div>
			<div id="combo2">
				<br><label>Andar</label><br>
				<select name="andar" size=1">
					<option name="valor2">SSC0456</option>
				</select>
				</div>
				<div id="combo3">
					<br><label>Curso</label><br>
					<select name="curso" size=1">
						<option name="valor3">SSC0456</option>
					</select>
				</div>
				<div id="combo4">
					<label>Grau de Prioridade</label><br> 
					<select name="prioridade" size=1">
						<option name="valor4">SSC0456</option>
					</select>
				</div>
			</div>
		</div>
		</section>
	</div>
	<footer> </footer>
</body>
</html>