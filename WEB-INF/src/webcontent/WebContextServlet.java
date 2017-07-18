package webContent;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class WebContextServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        BufferedReader reader = req.getReader();

        try {
            String jsonString = req.getReader().readLine();

            WebContext webContext = new ObjectMapper().readValue(jsonString, WebContext.class);

            DataSingleton.getInstance().setWebContext(webContext);

            String output = new ObjectMapper().writeValueAsString(DataSingleton.getInstance().getWebContext());
            resp.getWriter().print(output);
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
