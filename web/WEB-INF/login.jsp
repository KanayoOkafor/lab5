

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Login page </title>
    </head>
    <body>
        <h1> Login </h1>
        
        <main>
            <form method="post" action="login">
                <label> Username: </label>
                <input type="text" name="uname" value="${username}"> 
                <br>
                <label> password: </label>
                <input type="text" name="pword" value="${password}">
                <br>
                <input type="submit" value="Log in">                
            </form>
                <p> ${forEmpty}</p>
                <p> ${forInvalid} </p>
                <p> ${logOut}</p>
        </main> 
       
         
    </body>
</html>
