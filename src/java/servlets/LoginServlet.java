
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
            throws ServletException, IOException 
    {
       HttpSession session = request.getSession();
       
       String logout = request.getParameter("logout");
       
       if(logout != null){
           session.invalidate();
           request.setAttribute("logOut", "You are now logged out");
           getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
           return;
       }
       
       if(session.getAttribute("username") != null){
           response.sendRedirect("home");
           return;
       }
        
       getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
       return;
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        
        String username = request.getParameter("uname");
        String password = request.getParameter("pword");
        
             
        if(username == null || username.equals("") || password == null || password.equals("")){
          
            request.setAttribute("forEmpty", "Invalid username and password");
            request.setAttribute("username", username);
            request.setAttribute("password", password);           
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
        return;
        
      }
        else{
            AccountService service = new AccountService();
            User newUser = service.login(username, password);
            
            if(newUser != null){
                session.setAttribute("username", username);
                response.sendRedirect("home");
                return;
            }
            else{
                request.setAttribute("forInvalid", "Invalid");
                request.setAttribute("username", username);
                request.setAttribute("password", password);
                getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request,response);
                return;
            }
            
        }

  

     }
}