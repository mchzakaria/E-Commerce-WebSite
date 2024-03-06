package com.example.ecommerce2;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class Inscription extends HttpServlet {
    public void init() {

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nomRecu=null , motPasseRecu= null ;
        String nomCookie=null , motPasseCookie=null ;
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (nomCookie == null && nomRecu == null){
            out.println("<html><body>");
            out.println("<head> <title> inscription d'un client </title> </head>");
            out.println("<body bgcolor='white' >");
            out.println( nomRecu +" | "+ motPasseRecu +" | "+ nomCookie +" | "+ motPasseCookie );
            out.println("<h3>" + "Bonjour, vous devez vous inscrire " + "</h3>");
            out.println("<h3>" + "Attention mettre nom et le mot de passe avec plus de 3 caracteres" + "</h3>");
            out.print("<form action='./traitement' method='GET'>");
            out.println("Nom : ");
            out.println("<input type='text' size='20' name='nom' >");
            out.println("<br>");
            out.println("Mot de passe : ");
            out.println("<input type='password' size='20' name='motdepasse'> <br>");
            out.println("<input type='submit' value='inscription'>");
            out.println("</form>");
            out.println("</body></html>");
        }
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}