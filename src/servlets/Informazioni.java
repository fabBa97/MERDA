package servlets;

import dao.Model;
import dao.User;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.ServerException;
import java.util.List;

@WebServlet(name = "Informazioni", urlPatterns = {"/Informazioni"})
public class Informazioni extends HttpServlet {

    Model model = null;

    public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
        ServletContext ctx = conf.getServletContext();
        String url = ctx.getInitParameter("http://localhost/phpmyadmin/sql.php?db=project_tweb&table=utenti&pos=0");
        String user = ctx.getInitParameter("user");
        String pwd = ctx.getInitParameter("pwd");
        model = new Model(url, user, pwd);
    }


    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServerException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String tipoInfo = request.getParameter("tipoInfo");
        String loginName = request.getParameter("login");
        Operazione op = new Operazione();
        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Informazioni</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Ciao " + loginName + "</h2>");
            out.println("Hai chiesto la seguente operazione: "
                    + op.getOperazione(tipoInfo) + "<br>");
            out.println("Codice interno: " + tipoInfo + "<br>");
            if (tipoInfo!=null && tipoInfo.equals("persone")) {
                List<User> personas = model.queryDB();
                for (int i = 0; i < personas.size(); i++) {
                    out.println("<p>" + personas.get(i) + "</p>");
                }
            }
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
