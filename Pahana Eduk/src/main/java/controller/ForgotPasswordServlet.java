package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PasswordResetDAO;
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
        UserDAO userDao = new UserDAO();
        PasswordResetDAO resetDao = new PasswordResetDAO();
        
        // Check if email exists
        if(userDao.emailExists(email)) {
            // Generate token
            String token = UUID.randomUUID().toString();
            
            // Store token in database with expiration (1 hour)
            resetDao.createPasswordResetToken(email, token);
            
            // Send email with reset link
            try {
                String resetLink = req.getRequestURL().toString()
                    .replace("ForgotPasswordServlet", "/Auth/resetPassword.jsp") 
                    + "?token=" + token;
                
                String message = "Click the following link to reset your password:\n\n" 
                    + resetLink + "\n\nThis link will expire in 1 hour.";
                
                EmailUtility.sendEmail(email, "Password Reset Request", message);
                
                req.setAttribute("message", "Password reset link has been sent to your email.");
            } catch(Exception e) {
                e.printStackTrace();
                req.setAttribute("message", "Error sending email. Please try again.");
            }
        } else {
            req.setAttribute("message", "If this email exists in our system, a reset link will be sent.");
        }

        RequestDispatcher rd = req.getRequestDispatcher("/Auth/forgot_password.jsp");
        rd.forward(req, res);
    }
}