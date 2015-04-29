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
		String key=request.getParameter("producer");
		String name=request.getParameter("name");
		String md5_id=com.IndexAccess.query(key);
		arrayNode=com.detailInformationAccess.show(md5_id);
		if(arrayNode!=null){
			content.put("status", 200);
			content.put("msg", "succeed");
		}
		else{
			content.put("status", 700);
			content.put("msg", "hele server error");
		}
		content.put("name", name);
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
