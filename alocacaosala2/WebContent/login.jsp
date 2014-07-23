<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="import.jsp"></c:import>
<title>Login</title>
</head>
<body>
	
	<jsp:include page="cabecalho.jsp"/>
       
<div id='login'>
	<form action="autent.do" method="post">
			<label>Login</label>
			<input type="text" name="txtlogin" />
			<label>Senha</label>
			<input type="password" name="txtsenha" />
			<input type="submit">
	</form>
		</div>
			<jsp:include page="menureduzid.jsp" />
			
		<div id='secao'>
		<section>
           <h1>SISTEMA DE ALOCAÇÃO DE SALAS</h1>
          
        </section>
		</div>
        <footer>
        </footer>
</body>
</html>