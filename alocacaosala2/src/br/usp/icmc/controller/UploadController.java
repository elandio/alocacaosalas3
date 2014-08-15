package br.usp.icmc.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class UploadController
 */
// TODO change tmp filename for the actual temp file directory
@WebServlet("/upload")
@MultipartConfig(location = "/tmp/", fileSizeThreshold = 512 * 512, maxFileSize = 512 * 512 * 5, maxRequestSize = 512 * 512 * 5 * 5)
public class UploadController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Part part;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UploadController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("upload.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Collection<Part> parts = request.getParts();
		for (Part p : parts) {
			this.part = p;
			this.uploadFile();
		}
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("success.jsp");
		dispatcher.forward(request, response);
	}

	public String uploadFile() throws IOException {
		if (this.part.getSize() > 0 && this.part.getSize() < 512 * 512 * 5) {

			InputStream inputStream = null;
			ByteArrayOutputStream out = null;
			File ff = new File("/tmp/" + this.getFileName(this.part));
			FileOutputStream fileOut = new FileOutputStream(ff);
			try {
				inputStream = part.getInputStream();
				out = new ByteArrayOutputStream();

				int read = 0;
				final byte[] bytes = new byte[128];
				while ((read = inputStream.read(bytes)) != -1) {
					fileOut.write(bytes, 0, read);
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				fileOut.flush();
				fileOut.close();
				if (out != null) {
					out.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			}

			return "/img/" + this.getFileName(this.part);
		}
		return null;
	}

	protected String getFileName(Part part) {
		for (String content : part.getHeader("content-disposition").split(";")) {
			if (content.trim().startsWith("filename")) {
				return content.substring(content.indexOf('=') + 1).trim()
						.replace("\"", "");
			}
		}
		return null;
	}
}
