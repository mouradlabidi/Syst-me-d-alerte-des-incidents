package dz.appdist.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dz.appdist.beans.User;
import dz.appdist.dao.UserDAO;

/**
 * Servlet implementation class newpassword
 */
public class newpassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	RequestDispatcher dispatcher =null;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public newpassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/newpassword.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		
		User user = new User();
		user.setUserpass(request.getParameter("password"));
		user.setRe_userpass(request.getParameter("repassword"));
		user.setUseremail(request.getParameter("usermail"));
		String inputid=request.getParameter("userid");
		if(inputid!=null) {
		id= Integer.parseInt(inputid);
		user.setId(id);}
		
		if (user.getUserpass() == null || user.getUserpass().equals("")) {
			 request.setAttribute("status", "invalidePass");
			 request.setAttribute("email", user.getUseremail());
			 dispatcher = request.getRequestDispatcher("/WEB-INF/newpassword.jsp");
			 dispatcher.forward(request, response);
			 return;
		 }else if (user.getRe_userpass() == null || user.getRe_userpass() .equals("")) {
			 request.setAttribute("status", "invalideRePass");
			 request.setAttribute("email", user.getUseremail());
			 dispatcher = request.getRequestDispatcher("/WEB-INF/newpassword.jsp");
			 dispatcher.forward(request, response);
			 return;
		 }else if(!user.getUserpass().equals(user.getRe_userpass())) {
			request.setAttribute("email", user.getUseremail());
			request.setAttribute("status", "invalideconfirmepassword");
	        dispatcher = request.getRequestDispatcher("/WEB-INF/newpassword.jsp");
	        dispatcher.forward(request, response);
	        return;
		 }else if(user.getUserpass().length()<8 || user.getUserpass().length()<8) {
			 	request.setAttribute("status", "invalidepassLength");
			 	request.setAttribute("email", user.getUseremail());
	         	dispatcher = request.getRequestDispatcher("/WEB-INF/newpassword.jsp");
	         	dispatcher.forward(request, response);
	         	return;
		 }else if (!isValidPassword(user.getUserpass())) {
		        request.setAttribute("status", "invalidPasswordFormat");
		        request.setAttribute("email", user.getUseremail());
		        dispatcher = request.getRequestDispatcher("/WEB-INF/newpassword.jsp");
		        dispatcher.forward(request, response);
		        return;
		    }
		
		UserDAO us = new UserDAO();
		System.out.println("wait");
        // invoque la méthode du UserDAO
		int rowsAffected = us.userpassBymail(user);
		
		if(rowsAffected>0) {
        	request.setAttribute("status", "succes");
        	if(id==0) {
        	dispatcher = request.getRequestDispatcher("/WEB-INF/Login.jsp");
	       	dispatcher.forward(request, response);
	       	}else {
	       		response.sendRedirect("updatepass?us="+id);
	       	}
        }else {
        	request.setAttribute("status", "failed");
        	request.setAttribute("email", user.getUseremail());
        	dispatcher = request.getRequestDispatcher("/WEB-INF/Trouvercompte.jsp");
	       	dispatcher.forward(request, response);
        }
		
	}
	
	private boolean isValidPassword(String password) {
	    // Valider que le mot de passe a au moins 6 caractères et contient une combinaison de chiffres, de lettres et de caractères spéciaux
	    String regex = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!$@%]).{6,}$";
	    return password.matches(regex);
	}

}
