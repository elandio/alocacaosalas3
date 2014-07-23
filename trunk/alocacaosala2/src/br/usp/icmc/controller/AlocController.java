package br.usp.icmc.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Time;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.usp.icmc.dao.HorarioDao;
import br.usp.icmc.dao.HorarioSalaTurmaDao;
import br.usp.icmc.dao.SalaDao;
import br.usp.icmc.entidades.JHorSlaTur;
import br.usp.icmc.entidades.Main;
import br.usp.icmc.entidades.Sala;
import br.usp.icmc.entidades.Turma;

import com.google.gson.Gson;

import java.io.File;
/**
 * Servlet implementation class AlocController
 */
@WebServlet("/aloccontroller.do")
public class AlocController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AlocController() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String saida = new File(".").getCanonicalPath();
				
		
		try {
			Runtime.getRuntime().exec("java -jar MetaHeuristica.jar").waitFor();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

			//Runtime.getRuntime().exec("java -jar MetaHeuristica.jar");
		
	
		response.getWriter().write(new Gson().toJson(saida));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Turma t = new Turma();
		String dia = request.getParameter("dia");
		int k;
		int s;
		int h;
		SalaDao sd = new SalaDao();
		HorarioDao hd = new HorarioDao();
		HorarioSalaTurmaDao dao = new HorarioSalaTurmaDao();
		ArrayList<Time> hors = new ArrayList<Time>();
		ArrayList<Integer> numslas = new ArrayList<Integer>();
		ArrayList<JHorSlaTur> jhst = new ArrayList<JHorSlaTur>();
		HashMap<Integer, JHorSlaTur> saida = new HashMap<Integer, JHorSlaTur>();
		hors = hd.ListarHoras();
		numslas = sd.ListarNumeroSla();
		jhst = dao.listarSaida(dia);
		for (JHorSlaTur join : jhst) {
			k = 0;
			for (s = 0; s < numslas.size(); s++) {
				if (join.getNumsla() == numslas.get(s)) {
					for (h = 0; h < hors.size(); h++) {
						if (join.getHora().equals(hors.get(h))) {
							k = s * hors.size() + h;
							saida.put(k, join);
						}
					}
				}
			}
		}
		write(response, saida);
	}
	private void write(HttpServletResponse response, HashMap<Integer, JHorSlaTur> saida) throws IOException {
		response.setCharacterEncoding("application/json");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(new Gson().toJson(saida));
	}

}
