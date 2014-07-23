<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="import.jsp"></c:import>
<title>Turmas</title>
</head>
<body>
	<jsp:include page="cabecalho.jsp"/>

	<div id='login'>
	</div>
	<jsp:include page="menuvertical.jsp" />
	<div id>
	
	</div>
	<div id='secao'>
		<section>
		<h1>Turmas</h1>
		<br><br>
		<div id="turmas">
			<div id="jupiterweb">
				<form action="turcontroller.do" method="post">
		<input type="submit" value="Importar dados do Jupterweb">
	
	</form>
	<br><br>
				<label heigth=30px>Turmas Cadastradas</label><br> <br> <select
					name="Disciplinasjupiter" size=20 multiple style="min-width: 250px">
					<c:forEach items="${requestScope.turmas}" var="turm">
					<option >${turm.codtur} - ${turm.codcur} - ${turm.coddis} - ${turm.numalu }</option>
					</c:forEach>
				</select>
			</div>
		</section>
	</div>
	<footer> </footer>
</body>
</html>