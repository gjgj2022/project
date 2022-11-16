package kr.co.freshkit.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import kr.co.freshkit.vo.FkcustomerVO;

public class FkcustomerDAO {
	String driver = "oracle.jdbc.driver.OracleDriver";
	String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	String user = "scott";
	String password = "tiger";
	Connection conn = null;
	PreparedStatement pstmt = null;
	StringBuffer sb = new StringBuffer();
	ResultSet rs = null;
	
	public FkcustomerDAO() {
		try {
			// 2. 드라이버 로딩
			Class.forName(driver);
			// 3. Connection 객체 생성
			conn = DriverManager.getConnection(url, user, password);
			System.out.println("conn : "+conn);
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결실패");
			e.printStackTrace();
		}		
	}// constructor end
	
	public boolean idCheck(String id) {
		
		sb.setLength(0);
		sb.append("select id from fkcustomer ");
		sb.append("where id = ? ");
		boolean isOk = false;
		
		try {
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
						
			rs = pstmt.executeQuery();
			isOk=rs.next();	
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return isOk; 
		
		
	}//아이디 중복 메서드 끝
	
	
	public boolean phoneCheck(String phone) {
		
		sb.setLength(0);
		sb.append("select phone from fkcustomer ");
		sb.append("where phone = ? ");
		boolean isOk = false;
		
		try {
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, phone);
						
			rs = pstmt.executeQuery();
			isOk=rs.next();	
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return isOk; 
		
		
	}//연락처 중복 메서드 끝
	
	public boolean emailCheck(String email) {
		
		sb.setLength(0);
		sb.append("select email from fkcustomer ");
		sb.append("where email = ? ");
		boolean isOk = false;
		
		try {
			
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, email);
						
			rs = pstmt.executeQuery();
			isOk=rs.next();	
						
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return isOk; 
		
		
	}//이메일 중복 메서드 끝
	
	
	
	public FkcustomerVO findById(String id) {
		sb.setLength(0);
		sb.append("select * from fkcustomer " );
		sb.append("where id = ? " );
		FkcustomerVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("no");
				String grade=rs.getString("grade");
				String pw=rs.getString("pw");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String post=rs.getString("post");
				String phone=rs.getString("phone");
				vo=new FkcustomerVO(no,grade,id,pw,name,gender,email,address,post,phone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
		
	}
	// 회원탈퇴용
	public FkcustomerVO withdrawal(String id, String pw) {
		sb.setLength(0);
		sb.append("select no, grade, id, pw, name, gender, email, address, post, phone from fkcustomer " );
		sb.append("where id = ? " );
		sb.append("and pw = ? " );
		FkcustomerVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int no = rs.getInt("no");
				String grade=rs.getString("grade");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String post=rs.getString("post");
				String phone=rs.getString("phone");
				vo=new FkcustomerVO(no,grade,id,pw,name,gender,email,address,post,phone);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
		return vo;
		
	}
	
	
	
	
		
	//전체조회
	public ArrayList<FkcustomerVO> selectAll(){
        		
		ArrayList<FkcustomerVO> list = new ArrayList<FkcustomerVO>();
		sb.setLength(0);
		sb.append("select * from fkcustomer ");
		
		try {
			pstmt = conn.prepareStatement(sb.toString());
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				int no = rs.getInt("no");
				String grade=rs.getString("grade");
				String id=rs.getString("id");
				String pw=rs.getString("pw");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String post=rs.getString("post");
				String phone=rs.getString("phone");
				FkcustomerVO vo=new FkcustomerVO(no,grade,id,pw,name,gender,email,address,post,phone);
			
				list.add(vo);
			}
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
		
	}
	
	//회원조회
	public FkcustomerVO selectOne(int no) {
		sb.setLength(0);
		sb.append("select * from fkcustomer ");
		sb.append("where no = ? ");
		FkcustomerVO vo = null;
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setInt(1, no);
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String grade=rs.getString("grade");
				String id=rs.getString("id");
				String pw=rs.getString("pw");
				String name=rs.getString("name");
				String gender=rs.getString("gender");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String post=rs.getString("post");
				String phone=rs.getString("phone");
				vo=new FkcustomerVO(no,grade,id,pw,name,gender,email,address,post,phone);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		return vo;
	}
	
	//회원가입
	public void insertOne(FkcustomerVO vo) {
			sb.setLength(0);
			sb.append("insert into fkcustomer ");
			sb.append("values (null,?,?,?,?,?,?,?,?,?) ");
		
			
			try {
				pstmt = conn.prepareStatement(sb.toString());
				
				pstmt.setString(1, vo.getGrade());
				pstmt.setString(2, vo.getId());
				pstmt.setString(3, vo.getPw());
				pstmt.setString(4, vo.getName());
				pstmt.setString(5, vo.getGender());
				pstmt.setString(6, vo.getEmail());
				pstmt.setString(7, vo.getAddress());
				pstmt.setString(8, vo.getPost());
				pstmt.setString(9, vo.getPhone());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				close();
			}
			
			
		}
		
		
	//변경
	public void modifyOne(FkcustomerVO vo) {
		sb.setLength(0);
		sb.append("update fkcustomer ");
		sb.append("set grade=?, id=?, pw=?, name=?, gender=?, email=?, address=?, post=?, phone=? ");
		sb.append("where no=? ");
		
				
		try {
			pstmt = conn.prepareStatement(sb.toString());
			pstmt.setString(1, vo.getGrade());
			pstmt.setString(2, vo.getId());
			pstmt.setString(3, vo.getPw());
			pstmt.setString(4, vo.getName());
			pstmt.setString(5, vo.getGender());
			pstmt.setString(6, vo.getEmail());
			pstmt.setString(7, vo.getAddress());
			pstmt.setString(8, vo.getPost());
			pstmt.setString(9, vo.getPhone());
			pstmt.setInt(10, vo.getNo());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close();
		}
		
	}
	// id 입력값으로 변경 (마이페이지)
	public void modifyOne2(String name, String gender, String email, String addrs, String post, String phone, String id) {
		sb.setLength(0);
		sb.append("update fkcustomer ");
		sb.append("set name=?, gender=?, email=?, address=?, post=?, phone=? ");
		sb.append("where id=? ");
		
				
		try {
			pstmt = conn.prepareStatement(sb.toString());			
			
			pstmt.setString(1, name);
			pstmt.setString(2, gender);
			pstmt.setString(3, email);
			pstmt.setString(4, addrs);
			pstmt.setString(5, post);
			pstmt.setString(6, phone);
			pstmt.setString(7, id);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	//삭제
	public void deleteOne(int no) {
		sb.setLength(0);
		sb.append("delete from fkcustomer ");
		sb.append("where no = ? ");
		
		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setInt(1, no);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("삭제 실패");
			e.printStackTrace();
		} finally {
			close();
		}     	
		
	}
	//아이디,패스워드로 삭제
	public void deleteOne2(String id, String pw) {
		sb.setLength(0);
		sb.append("delete from fkcustomer ");
		sb.append("where id = ? ");
		sb.append("and pw = ? ");
		
		try {
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("삭제 실패");
			e.printStackTrace();
		}	
		
	}
	public void close() {
		try {
			if(rs!=null) rs.close();
			if(conn!=null)	conn.close();
			if(pstmt!=null) pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
}
}

	

