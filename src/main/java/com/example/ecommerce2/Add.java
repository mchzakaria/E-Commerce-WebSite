package com.example.ecommerce2;

import java.io.*;
import java.util.List;

import com.example.ecommerce2.Model.Donnees;
import com.example.ecommerce2.Model.ImpDonnees;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(value = "/add")
public class Add extends HttpServlet {
    public void init(){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession(false);
        List<Donnees> list = (List<Donnees>) session.getAttribute("cart");
        Cookie[] cookies = request.getCookies();
        PrintWriter out = response.getWriter();
        String username=null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        for (Donnees donnees : list ){
            ImpDonnees impDonnees = new ImpDonnees();
            impDonnees.save(donnees,username);
        }
        out.println("<html><head><style>h3{align-items:center;}" +
                "</style></head><body><h3> DATA ADDED SUCCESFULLY </h3></body></html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doGet(request, response);
    }
}