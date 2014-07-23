package br.usp.icmc.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usp.icmc.dao.TurmaDao;
import br.usp.icmc.entidades.Turma;

/**
 * Servlet implementation class DisciplinaController
 */
@WebServlet("/turcontroller.do")
public class TurmaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TurmaController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TurmaDao td = new TurmaDao();
		
		ArrayList<Turma> turmas = td.listarTurma();
		
		request.setAttribute("turmas", turmas);
		
		RequestDispatcher saida = request.getRequestDispatcher("turmas.jsp");
		saida.forward(request, response);
	}

}
