package br.cefetmg.inf.hosten.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Logout {
    @SuppressWarnings("static-access")
    public static String execute(HttpServletRequest request) {

        String jsp = "";

        try {
            HttpSession session = request.getSession();
            if (session != null)
                session.invalidate();
        } catch (Exception e) {
            e.printStackTrace();
            jsp = "/view/login.jsp";
        }
        return jsp;
    }
    
}
