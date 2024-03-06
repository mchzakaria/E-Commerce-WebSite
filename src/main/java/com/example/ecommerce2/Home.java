package com.example.ecommerce2;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.ecommerce2.Model.Donnees;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/home")
public class Home extends HttpServlet {
    private PreparedStatement instruction = null;
    private ResultSet resultSet = null;
    private String requete = "SELECT * FROM produit";

    public void init() throws ServletException {
        String pilote = getServletContext().getInitParameter("jdbc.Driver");
        String BaseDonnees = getServletContext().getInitParameter("localisation");
        try {
            Class.forName(pilote);
            Connection connexion = DriverManager.getConnection(BaseDonnees, "root", "");
            instruction = connexion.prepareStatement(requete);
            resultSet = instruction.executeQuery();
        } catch (ClassNotFoundException e) {
            System.out.println("DriverManager BD non Trouve");
            throw new ServletException();
        } catch (SQLException e) {
            System.out.println("Base de donnees non Trouvee");
            throw new ServletException();
        }
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        String nom = request.getParameter("nom1");
        String pass = request.getParameter("motdepasse1");
        String username = null, password = null, NomProduit, Prix;
        int Id;
        List<Donnees> list = new ArrayList<>();
        HttpSession session = request.getSession(true);

        // Cookies :
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
                if (cookie.getName().equals("password")) {
                    password = cookie.getValue();
                }
            }
        }

        try {
            ResultSet resultSet = instruction.executeQuery();
            while (resultSet.next()) {
                Id = resultSet.getInt("id");
                NomProduit = resultSet.getString("nom");
                Prix = resultSet.getString("prix");
                Donnees obj = new Donnees(Id, NomProduit, Prix);
                list.add(obj);
            }
            session.setAttribute("list", list);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<style> table, th, td {border:1px solid black;margin-left:300px;} h3{text-align: center;}</style>");
        out.println("<title> Home </title>");
        out.println("</head>");
        out.println("<body bgcolor='white'>");
        out.println("<h3>" + " WELCOME " + username + " TO OUR STORE : " + "</h3>");
        out.println("<form action='./achat' method='GET'>");
        out.println("<table style='width:50%'>");
        out.println("<tr> <th>NomProduit</th> <th>Prix</th> <th>Acheter</th> </tr>");
        for (Donnees donnees : list) {
            out.println("<tr><td>" + donnees.getNomProduit() + " </td>" + "<td>" + donnees.getPrix() + "</td>" +
                    "<td><button type='submit' name='button' value='" + donnees.getId() + "'>buy</button></td></tr>");
        }
        out.println("</table></form></body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        doGet(request, response);
    }
}


