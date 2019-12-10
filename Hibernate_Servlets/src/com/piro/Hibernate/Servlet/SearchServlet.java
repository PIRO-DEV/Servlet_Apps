package com.piro.Hibernate.Servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class SearchServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException
	{
		String uname = req.getParameter("uname");
		String uemail = req.getParameter("uemail");
		String umno = req.getParameter("umno");
		PrintWriter out = resp.getWriter();
		String urole;
		try 
		{
			urole = checkRole(uname,uemail,umno);
			resp.setContentType("text/html");
			out.println("<!Doctype HTML>"
					+ "<html lang='en-IN'>"
					+ "<head>"
					+ "<link href='dist/css/bootstrap.css' rel='stylesheet'>" + 
					"<meta charset='ISO-8859-1'>" + 
					"<meta name='viewport' content='width=device-width, initial-scale=1'>" + 
					"</head>"
					+ "<body>"
					+ "<div class='container row'>"
					+ "<table class='table col-lg-4 col-lg-offset-4 col-md-5 col-md-offset-4 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2'>"
					+ "<tr>"
					+ "<th>Username</th>"
					+ "<th>User Role</th>"
					+ "</tr>"
					+ "<tr>"
					+ "<td>"+uname+"</td>"
					+ "<td>"+urole+"</td>"
					+ "</tr>"
					+ "</table>"
					+" </div>"
					+ "</body>"
					+ "</html>");
			out.flush();
		}
		catch (NullPointerException e) 
		{
			out.println("<!Doctype HTML>"
					+ "<html lang='en-IN'>"
					+ "<head>"
					+ "<link href='dist/css/bootstrap.css' rel='stylesheet'>" + 
					"<meta charset='ISO-8859-1'>" + 
					"<meta name='viewport' content='width=device-width, initial-scale=1'>" + 
					"</head>"
					+ "<body>"
					+ "<div class='container'>"
					+ "<div class='alert alert-danger'>"
					+ "<p class='text-center text-capitalize'>User Not Found!!</p>"
					+ "</div>"
					+ "</div>"
					+ "</body>"
					+ "</html>");
			out.flush();
		}
		finally
		{
			
			out.close();

		}
	}

	private String checkRole(String uname, String uemail, String umno) {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session session  = sf.openSession();
		
		
		
		Query<UserTableDto> query = session.createQuery("SELECT ur.urole FROM UserTableDto ur WHERE ur.uname =:un AND (ur.email =:ue OR ur.umno =:um)");
		query.setParameter("un", uname);
		query.setParameter("ue", uemail);
		query.setParameter("um", umno);
		
		Object ures = query.uniqueResult();
		
		String urole = ures.toString();
		
		session.clear();
		session.close();
		sf.close();
		
		return urole;
	}
}
