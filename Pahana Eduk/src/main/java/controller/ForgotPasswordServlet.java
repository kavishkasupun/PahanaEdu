package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import util.EmailUtility;

/**
 * Servlet implementation class ForgotPasswordServlet
 */
@WebServlet("/ForgotPasswordServlet")
public class ForgotPasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotPasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 String email = req.getParameter("email");
	        String newPass = UUID.randomUUID().toString().substring(0,8);

	        UserDAO dao = new UserDAO();
	        boolean status = dao.updatePassword(email, newPass);

	        if(status) {
	            try {
	                EmailUtility.sendEmail(email, "Password Reset", "Your new password is: "+newPass);
	            } catch(Exception e) {
	                e.printStackTrace();
	            }
	            req.setAttribute("message", "New password sent to your email.");
	        } else {
	            req.setAttribute("message", "Email not found.");
	        }

	        RequestDispatcher rd = req.getRequestDispatcher("/Auth/forgot_password.jsp");
	        rd.forward(req, res);
	}

}
