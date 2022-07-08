package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;
import services.AccountService;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //create a http session
        HttpSession session = request.getSession();

        // get the logout parameter 
        String logout = request.getParameter("logout");

        // check if the user clicked the log out button
        if (logout != null) {
            session.invalidate();
            request.setAttribute("logOut", "You are now logged out");
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }

        // storing the username as a session object and to check if the username is empty and then load the home page
        String username = request.getParameter("uname");

        if (username != null && !username.equals("")) {

            // if action is register, save the username as a session attribute
            session.setAttribute("username", username);
            response.sendRedirect("home");
            return;
        }

        //load the login jsp
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
        return;
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String username = request.getParameter("uname");
        String password = request.getParameter("pword");

        if (username == null || username.equals("") || password == null || password.equals("")) {

            request.setAttribute("forEmpty", "Invalid username and password");
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;

        } else {
            AccountService service = new AccountService();
            User newUser = service.login(username, password);

            if (newUser != null) {
                session.setAttribute("username", username);
                response.sendRedirect("home");
                return;
            } else {
                request.setAttribute("forInvalid", "Invalid");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
                return;
            }

        }

    }
}
