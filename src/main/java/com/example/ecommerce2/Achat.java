package com.example.ecommerce2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce2.Model.Donnees;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/achat")
public class Achat extends HttpServlet {
    public void init(){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");String username = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) { if (cookie.getName().equals("username")) { username = cookie.getValue();}}}
        HttpSession session = request.getSession(false);
        List<Donnees> list = (List<Donnees>) session.getAttribute("list");
        int id = Integer.parseInt(request.getParameter("button"));
        // Retrieve or create session attribute for cart
        HttpSession cart = request.getSession(true);
        List<Donnees> cartList = (List<Donnees>) cart.getAttribute("cart");
        if (cartList == null) { cartList = new ArrayList<>(); }
        for (Donnees d : list) { if (d.getId() == id) { cartList.add(d);} }
        cart.setAttribute("cart", cartList);
        PrintWriter out = response.getWriter();
        out.println("<html><head><style> table, th, td {border:1px solid black;margin-left:300px;} " +
                "h3,h5{text-align: center;}</style> </head>");
        out.println("<body>");
        out.println("<h3>" + " WELCOME " + username + " TO ACHAT SERVLET : " + "</h3>");
        out.println("<form method='GET'>");
        out.println("<table style='width:50%'>");
        out.println("<tr> <th> Information Produit choisis </th> </tr>");

        if (!cartList.isEmpty()) {
            for (Donnees item : cartList) {
                out.println("<tr><td>" + "<h5>" + item.getNomProduit() + " - " + item.getPrix() + "</h5>" + "</td></tr>");
            }}
        out.println("</form>");
        out.println("<a href='./home'> Vous Pouvez Commandez autre Produit </a><br><br>");
        out.println("<a href='./add'> Vous Pouvez Enregistrer votre Commande </a>");
        out.println("</body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}
