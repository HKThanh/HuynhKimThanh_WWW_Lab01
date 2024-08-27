import Beans.JavaBeans;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "MyServlet", value = "/lab01")
public class MyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object unObj = req.getParameter("un");
        Object pwObj = req.getParameter("pw");

        JavaBeans jb = new JavaBeans();
        int checkEmpty = jb.checkEmpty(unObj, pwObj);

        PrintWriter out = resp.getWriter();

        if (checkEmpty == 0) {
            String un = unObj.toString();
            String pw = pwObj.toString();

//            int checkLogin = jb.checkLogin(un, pw);
            int checkLogin = jb.checkLoginWithConnectDB(un, pw);
            if (checkLogin == 1) {
                out.println("<h1>Welcome, " + un + "!</h1>");
            } else if (checkLogin == -1) {
                out.println("<h1>Wrong password!</h1>");
            } else {
                out.println("<h1>Wrong username!</h1>");
            }
        } else if (checkEmpty == -1) {
            out.println("<h1>Please enter your password!</h1>");
        } else if (checkEmpty == 1) {
            out.println("<h1>Please enter your username!</h1>");
        }
    }
}
