package servlets;


import model.StringRepeaterBean;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
public class XmlDataServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/xml; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        StringRepeaterBean bean = (StringRepeaterBean) request.getSession().getAttribute("stringRepeaterBean");
        PrintWriter out = response.getWriter();
        out.print(bean.getXmlStringRepeater());
    }
}
