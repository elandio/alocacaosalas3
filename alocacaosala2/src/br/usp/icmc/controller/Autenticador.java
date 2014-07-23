package br.usp.icmc.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.usp.icmc.dao.DadoUsuario;
import br.usp.icmc.entidades.Usuario;

/**
 * Servlet implementation class Autenticador
 */
@WebServlet("/autent.do")
public class Autenticador extends HttpServlet {
	private static final long serialVersionUID = 1L;
/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Autenticador() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession(false);
		if (sessao != null) {
			sessao.invalidate();
		}
		response.sendRedirect("login.jsp");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("txtlogin");
		String senha = request.getParameter("txtsenha");
		Usuario logado = new Usuario();
		DadoUsuario usu = new DadoUsuario();
		logado = usu.consultar(login, senha);
		if (logado.getNome() != null) {
			HttpSession sessao = request.getSession();
			sessao.setMaxInactiveInterval(3000);
			System.out.println(logado.getEmail());
			sessao.setAttribute("logado", logado);
			request.setAttribute("logado", logado);
			request.getRequestDispatcher("index.jsp")
					.forward(request, response);
		} else {
			response.sendRedirect("login.jsp");
		}
	}
}
