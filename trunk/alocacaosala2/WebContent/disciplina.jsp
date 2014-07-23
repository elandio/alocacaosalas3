<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<c:import url="import.jsp"></c:import>
<title>Disciplina</title>
</head>
<body>
<jsp:include page="cabecalho.jsp"/>
       
<div id='login'>
			<a href="autent.do"></a>
	</form>
		</div>
				<%@ include file="menuvertical.jsp" %>
		
		<div id='secao'>
		<section>
           <h1>Disciplinas</h1>
          
     
	<form name="diciplina" width="700px">
	<div id="disciplina">
	 <div id="jupiterweb">
	 <label heigth=30px>Disciplinas do Jupterweb</label><br><br>
           <select name="Disciplinasjupiter" size=20 multiple  style="min-width:250px">
          		<option name="valor1">SSC0456</option>
          		<option name="valor2">SSC0123</option>
          		<option name="valor3">SSC0863</option>
          		<option name="valor4">SSC0543</option>
           </select>
            </div>
            <div id="botaoescolha">
           <input type="button" style="background:url(images/direita.png)no-repeat;width:35px; 
height:35px;" /><br>
<input type="button" style="background:url(images/esquerda.png)no-repeat;width:35px; 
height:35px;" /><br><br><br>
           <input type="button" style="background:url(images/tudodireita.png) no-repeat;width:35px; 
height:35px;" /><br>
<input type="button" style="background:url(images/tudoesquerda.png)no-repeat;width:35px; 
height:35px;" />
		</div>
		<div id="bancolocal">
		 <label heigth=30px>Disciplinas do ICMC</label><br><br>
           <select name="Disciplinasicmc" size=20 multiple style="min-width:250px">
          		<option name="valor5">SSC0456</option>
          		<option name="valor7">SSC0123</option>
          		<option name="valor6">SSC0863</option>
          		<option name="valor9">SSC0543</option>
           </select>
           </div>
           </div><br>
           <input type="submit" name="salvar">
           </form>
            </section>
		</div>
        <footer>
        </footer>
</body>
</html>