package webContent;

import app.item.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author colsen@sfy.co.za
 * @since 18-Jul-17.
 */

public class DvdDataServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String jsonString = req.getReader().readLine();
            List<Item> dvdList = new ObjectMapper().readValue(jsonString, new TypeReference<List<Item>>(){});

            DataSingleton.getInstance().setDvdList(dvdList);

            String output = new ObjectMapper().writeValueAsString(DataSingleton.getInstance().getDvdList());
            resp.getWriter().print(output);
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
            throw new RuntimeException("Blab bla");
    }
}
