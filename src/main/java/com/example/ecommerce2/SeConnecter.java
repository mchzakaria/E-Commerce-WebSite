package com.example.ecommerce2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/traitement")
public class SeConnecter extends HttpServlet {
    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        Cookie nomCookie = new Cookie("username",request.getParameter("nom"));
        Cookie motPasseCookie = new Cookie("password",request.getParameter("motdepasse"));
        response.addCookie(nomCookie);
        response.addCookie(motPasseCookie);

        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<head> <title> Se Connecter  </title> </head>");
        out.println("<body bgcolor='white' >");
        out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
        out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
        out.print("<form action=./home method='GET'>");
        out.println("Nom : ");
        out.println("<input type='text' size='20' name='nom1' >");
        out.println("<br>");
        out.println("Mot de passe : ");
        out.println("<input type='password' size='20' name='motdepasse1'> <br>");
        out.println("<input type='submit' value='inscription'>");
        out.println("</form></body></html>");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }

}
