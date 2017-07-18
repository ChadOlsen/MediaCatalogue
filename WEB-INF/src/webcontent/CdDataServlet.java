package webContent;

import app.item.Item;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class CdDataServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;

        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            List<Item> cdList = new ObjectMapper().readValue(stringBuilder.toString(), new TypeReference<List<Item>>(){});

            DataSingleton.getInstance().setCdList(cdList);

            String output = new ObjectMapper().writeValueAsString(DataSingleton.getInstance().getCdList());
            resp.getWriter().print(output);
            resp.getWriter().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}