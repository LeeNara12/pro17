package sec02.ex01;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec01.ex01.MemberDAO;
import sec01.ex01.MemberVO;


@WebServlet("/member/*")
public class MemberController_ extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberDAO memberDAO;
	
	public void init(ServletConfig config) throws ServletException {
		memberDAO = new MemberDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doHandle(request, response);
	}
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String nextPage = null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String action = request.getPathInfo();
		System.out.println("action: "+action);
		
		if(action == null || action.equals("/listMember.do")) {
			
			List<MemberVO> memberList = memberDAO.listMembers();
			request.setAttribute("memberList", memberList);
			nextPage = "/test01/listMember.jsp";
			
		}else if(action.equals("/addMember.do")) {
			
		}else if(action.equals("/memberForm.do")) {
			
		}else {
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
