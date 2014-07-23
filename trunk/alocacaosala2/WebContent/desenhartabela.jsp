<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<%@page import="br.usp.icmc.dao.HorarioDao"%>
	<%@page import="br.usp.icmc.dao.SalaDao"%>
	<%@page import="java.util.ArrayList"%>
	<div id="tabela2">
		<%
			SalaDao s = new SalaDao();
			HorarioDao h = new HorarioDao();
			int k = 1;
			int i = 0;
			ArrayList<String> hrs = new ArrayList<String>();
			ArrayList<Integer> pav = new ArrayList<Integer>();
			ArrayList<Integer> sla = new ArrayList<Integer>();
			ArrayList<Integer> blo = new ArrayList<Integer>();
			hrs = h.ListarHora();
			int max = hrs.size();
			blo = s.listarBloco();
			for (Integer b : blo) {
		%>
		<h3>
			Bloco<%=" " + b%></h3>
		<table>
			<tr>
				<td>/</td>
				<%
					for (String hora : hrs) {
				%>
				<td><%=hora%></td>
				<%
					}
				%>
			</tr>
			<%
				pav = s.listarPavimento(b);
					for (Integer p : pav) {
						sla = s.ListarSala(p);
						for (Integer sala : sla) {
			%>
			<tr>
				<td><%=sala%></td>
				<%
					for (i = 1; i <= max; i++) {
				%>
				<td>
					<div id="div1" class='horario-<%=k%>' ondrop="drop(event)"
						ondragover="allowDrop(event)"></div>
				</td>
				<%
					k++;
								}
				%>
			</tr>
			<%
				}
					}
			%>
		</table>
		<%
			}
		%>
	</div>
</body>
</html>