package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MyConnectDB;

/**
 * Servlet implementation class ThemTK
 */
@WebServlet("/ThemSP")
public class ThemSP extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ThemSP() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id =request.getParameter("id");
		String ten =request.getParameter("ten");
		String pl =request.getParameter("pl");
		
		String stt="";
		try {
			ResultSet rs = new MyConnectDB().chonDuLieuTuDTB("select * from PRODUCT");
			while(rs.next()){
				String s = rs.getString(1);
				stt=Integer.parseInt(s)+1+"";
			}
			new MyConnectDB().thucThiCauLenhSQL("insert into PRODUCT values('"+stt+"','"+id+"','"+ten+"','"+pl+"')");
			response.sendRedirect("index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
