package sec01.ex01;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import sec01.ex01.MemberVO;

public class MemberDAO {
	
	private Connection con;
	private PreparedStatement pstmt;//sql 읽어주는 메소드
	private DataSource dataFactory;

	public MemberDAO() {
		
		try {
			
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env"); // JNDI 사용을 위한 설정//자바의 환경변수를 가져옴
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");
//			dataFactory = (DataSource) ctx.lookup("java:/comp/env/jdbc/oracle"); // 한번에 써도 됨
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<MemberVO> listMembers() {
		List <MemberVO> list = new ArrayList();
		
		try {
			con = dataFactory.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("select * from t_member");
			
			pstmt = this.con.prepareStatement(sb.toString());
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String id = rs.getString("id");
				System.out.println("id : "+ id);
				
				String pwd = rs.getString("pwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Date joindate = rs.getDate("joindate");
				
				
				MemberVO vo = new MemberVO();
				vo.setId(id);
				vo.setPwd(pwd);
				vo.setName(name);
				vo.setEmail(email);
				vo.setJoinDate(joindate);
				
				list.add(vo);
			}
			
			rs.close();
			this.pstmt.close();
			this.con.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
		return list;
	}
	
	
	public void addMember(MemberVO m) {
		
		try {
			con = dataFactory.getConnection();
			
			String id = m.getId();
			String pwd = m.getPwd();
			String name = m.getName();
			String email = m.getEmail();
			
			StringBuffer sb = new StringBuffer();
			sb.append("insert into t_member(id, pwd, name, email)");
			sb.append(" values(?, ?, ?, ?)");
			System.out.println(sb);
			
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			
			pstmt.executeUpdate();
			pstmt.close();
			con.close();
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
}
