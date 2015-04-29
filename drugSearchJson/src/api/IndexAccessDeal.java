package api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.ArrayNode;
import org.codehaus.jackson.node.ObjectNode;

/**
 * Servlet implementation class IndexAccessDeal
 */
@WebServlet("/IndexAccessDeal")
public class IndexAccessDeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ObjectMapper mapper=new ObjectMapper();
    private ObjectNode content=mapper.createObjectNode();
    private ArrayNode arrayNode=mapper.createArrayNode();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IndexAccessDeal() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//String key=request.getParameter("producer");
		ArrayList<HashMap<String,String>> List=com.UsedInformation.query();
		//String name=request.getParameter("name");
		//String md5_id=com.IndexAccess.query(key);
		//arrayNode=com.UsedInformation.query();
		for(int i=0;i<5;i++){
			ObjectNode info_information=mapper.createObjectNode();
	    	info_information.put("name" , com.IndexAccessDetail.show(List.get(i).get("md5_id")).get(0).get("name"));
	    	info_information.put("product_name", com.IndexAccessDetail.show(List.get(i).get("md5_id")).get(0).get("product_name"));
	    	info_information.put("producer", com.IndexAccessDetail.show(List.get(i).get("md5_id")).get(0).get("producer"));
	    	info_information.put("md5_id", List.get(i).get("md5_id"));
			arrayNode.add(info_information);
		}
		if(arrayNode!=null){
			content.put("status", 200);
			content.put("msg", "succeed");
		}
		else{
			content.put("status", 700);
			content.put("msg", "hele server error");
		}
		//content.put("name", name);
		content.put("arrayNode", arrayNode);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Origin","*");
		response.setHeader("content-type","text/html;charset=UTF-8");
		response.getWriter().print(content);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}

}
