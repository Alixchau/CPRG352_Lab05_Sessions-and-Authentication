package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

/**
 *
 * @author Alix
 */
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get the current session
        HttpSession session = request.getSession();
        //check if user have already login, if yes redirect to home or logout(if user click logout. 
        //otherwise, display login page
        if (session.getAttribute("userObject") == null) {
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        } else {
            
            String logout = request.getParameter("logout");
            // if logout != null, it means user clicked log out. send log out message to display on login page.
            if (logout != null) {
                request.setAttribute("logout_message", "You have been successfully logged out.");
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            } else {
                // if do not click on log out, redirect to home page
                response.sendRedirect("home");
                return;
            }

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //get the current session
        HttpSession session = request.getSession();

        //retrieve user input
        String userName = request.getParameter("u_name");
        String userPassword = request.getParameter("u_password");

        User u1 = new User(userName, userPassword);
        AccountService validateMethod = new AccountService();
        
        //check if user input is empty
        //then use login method to validate user input, return an user object if input is valid, and return null otherwise
        //if u1 is not null store the username in session varialbe and redirect the user to home url
        if (userName != "" && userPassword != "") {
            if (validateMethod.login(userName, userPassword) != null) {
                //create the userObject session attribute, home page will access this attribute
                session.setAttribute("userObject", u1);
                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("error_message", "Incorrect input, please try again");
                request.setAttribute("username", userName);
                request.setAttribute("userpassword", userPassword);

                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }

        } else {
            request.setAttribute("error_message", "Invalid input, please try again");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
    }

}
