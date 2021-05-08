package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MyConnectDB;

/**
 * Servlet implementation class SuaSP
 */
@WebServlet("/SuaSP")
public class SuaSP extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String id="";
	String ten="";
	String pl="";
       
    public SuaSP() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String stt=request.getParameter("id");
		ResultSet rs;
		try {
			rs = new MyConnectDB().chonDuLieuTuDTB("select * from PRODUCT where stt='"+stt+"'");
			while(rs.next()){
				id=rs.getString(2);
				ten=rs.getString(3);
				pl=rs.getString(4);
			}
			response.setContentType("text/html");
			ServletContext context = getServletContext();
			context.setAttribute("stt", stt);
			context.setAttribute("id", id);
			context.setAttribute("ten", ten);
			context.setAttribute("pl", pl);
			
			
			response.sendRedirect("SuaSanPham.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String ten = request.getParameter("ten");
		String pl = request.getParameter("pl");
		response.setContentType("text/html");
		ServletContext context = getServletContext();
		String stt=(String) context.getAttribute("stt");
		
		
		try {
			new MyConnectDB().thucThiCauLenhSQL("update PRODUCT set stt='"+stt+"',id='"+id+"',Ten='"+ten+"',PhanLoai='"+pl+"' where stt='"+stt+"'");
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
