package controller;

import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DAO;
import model.javaBeans;

// TODO: Auto-generated Javadoc
/**
 * Servlet implementation class ControllerServlet.
 */

@WebServlet(urlPatterns = { "/ControllerServlet", "/entradasForm", "/edita", "/atualizaContato", "/delete",
		"/geraRelatorio" })
public class ControllerServlet extends HttpServlet {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The dao. */
	DAO dao = new DAO();
	
	/** The beans. */
	javaBeans beans = new javaBeans();

	/**
	 * Instantiates a new controller servlet.
	 */
	public ControllerServlet() {
		super();
	}

	/**
	 * Do get.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String acaoRequest = request.getServletPath();
		if (acaoRequest.equals("/main")) {
			entrarAgenda(request, response);
		} else if (acaoRequest.equals("/entradasForm")) {
			novoContato(request, response);
		} else if (acaoRequest.equals("/edita")) {
			editarContato(request, response);
		} else if (acaoRequest.equals("/atualizaContato")) {
			atualizarContato(request, response);
		} else if (acaoRequest.equals("/delete")) {
			removerContato(request, response);
		} else if (acaoRequest.equals("/geraRelatorio")) {
			gerarRelatorio(request, response);
		} else {
			response.sendRedirect("index.html");
		}

	}

	/**
	 * Entrar agenda.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void entrarAgenda(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<javaBeans> lista = dao.listarContatos();
		// response.sendRedirect("agenda.jsp");
		request.setAttribute("contatos", lista);
		RequestDispatcher rd = request.getRequestDispatcher("agenda.jsp");
		rd.forward(request, response);
	}

	/**
	 * Novo contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void novoContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Pegando dados do Formulário
		beans.setNome(request.getParameter("nome"));
		beans.setFone(request.getParameter("fone"));
		beans.setEmail(request.getParameter("email"));
		// Inserindo dados do contato
		dao.inserirContato(beans);
		// Retornar a paginda de agenda
		response.sendRedirect("main");
	}

	/**
	 * Editar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void editarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// setando o id recebido nas variaveis javaBeans
		beans.setId(request.getParameter("id"));
		dao.selecionarContato(beans);
		request.setAttribute("id", beans.getId());
		request.setAttribute("nome", beans.getNome());
		request.setAttribute("fone", beans.getFone());
		request.setAttribute("email", beans.getEmail());
		// Encaminhar ao documento editar.jsp
		RequestDispatcher rd = request.getRequestDispatcher("editar.jsp");
		rd.forward(request, response);
	}

	/**
	 * Atualizar contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void atualizarContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		beans.setId(request.getParameter("id"));
		beans.setNome(request.getParameter("nome"));
		beans.setFone(request.getParameter("fone"));
		beans.setEmail(request.getParameter("email"));
		dao.alterarContato(beans);
		// response.sendRedirect("agenda.jsp");
		entrarAgenda(request, response);
	}

	/**
	 * Remover contato.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void removerContato(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		beans.setId(request.getParameter("id"));
		dao.excluirContato(beans);
		entrarAgenda(request, response);
	}

	/**
	 * Gerar relatorio.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void gerarRelatorio(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Document documento = new Document();
		try {
			// tipo de documento
			response.setContentType("apllication/pdf");
			// nome do documento
			response.addHeader("Content-Disposition", "inline;filename=" + "Relatório Agenda de Contatos.pdf");
			// Criar o documento
			PdfWriter.getInstance(documento, response.getOutputStream());
			documento.open();
			documento.add(new Paragraph("Lista de Contatos:"));
			documento.add(new Paragraph(" "));

			PdfPTable tabela = new PdfPTable(3);
			PdfPCell col1 = new PdfPCell(new Paragraph("Nome"));
			PdfPCell col2 = new PdfPCell(new Paragraph("Fone"));
			PdfPCell col3 = new PdfPCell(new Paragraph("E-mail"));

			tabela.addCell(col1);
			tabela.addCell(col2);
			tabela.addCell(col3);

			ArrayList<javaBeans> lista = dao.listarContatos();
			for (int i = 0; i < lista.size(); i++) {
				tabela.addCell(lista.get(i).getNome());
				tabela.addCell(lista.get(i).getFone());
				tabela.addCell(lista.get(i).getEmail());
			}

			documento.add(tabela);

			documento.close();
		} catch (Exception e) {
			System.out.println(e);
			documento.close();
		}
	}

	/**
	 * Do post.
	 *
	 * @param request the request
	 * @param response the response
	 * @throws ServletException the servlet exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
