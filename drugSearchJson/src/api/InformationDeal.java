package api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

import com.dataDeal;

/**
 * Servlet implementation class InformationDeal
 */
@WebServlet("/InformationDeal")
public class InformationDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper mapper = new ObjectMapper();  
    private ObjectNode content = mapper.createObjectNode();
    private ArrayNode arrayNode=mapper.createArrayNode();
    /**
     * Default constructor. 
     */
    public InformationDeal() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String sql;
		
		sql=request.getParameter("product_name");
		arrayNode=dataDeal.query(sql);
		if(arrayNode!=null){
			content.put("status", 200);
			content.put("msg", "succeed");
		}
		else{
			content.put("status", 700);
			content.put("msg", "hele server error");
		}
		content.put("arrayNode", arrayNode);
		
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("content-type","text/html;charset=UTF-8");
		response.getWriter().print(content);
	}

}
