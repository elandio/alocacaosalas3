<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:import url="import.jsp"></c:import>

<title>Insert title here</title>
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
           <h1>SISTEMA DE ALOCAÇÃO DE SALAS</h1>
          
	
	
		
        </section>
		</div>
        <footer>
        </footer>
</body>
</html>