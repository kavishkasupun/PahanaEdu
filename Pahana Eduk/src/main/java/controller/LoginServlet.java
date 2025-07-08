package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import model.User;
import util.EmailUtility;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		 String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        UserDAO dao = new UserDAO();
	        User user = dao.checkLogin(username, password);

	        if(user != null) {
	            HttpSession session = req.getSession();
	            session.setAttribute("user", user);

	            Cookie cookie = new Cookie("username", username);
	            res.addCookie(cookie);

	            if(user.getRole().equals("admin")) {
	                // Send email notification to Admin
	                try {
	                    EmailUtility.sendEmail(user.getEmail(), "Admin Login Alert", "Admin logged in successfully at "+new java.util.Date());
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }

	            res.sendRedirect(req.getContextPath() + "/Admin/adminDashboard.jsp");
	        } else {
	            req.setAttribute("error", "Invalid username or password");
	            RequestDispatcher rd = req.getRequestDispatcher("/Auth/index.jsp");
	            rd.forward(req, res);
	        }
	}

}
