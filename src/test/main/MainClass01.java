package test.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 *  JDBC(Java DataBase Connectivity)
 *  
 *  - Java 와 DataBase 연동하기 
 *  - Java 에서 DB 에 접근하여 데이터 조회, 삽입, 수정, 삭제
 *  를 가능하게 하는것
 */
public class MainClass01 {
	public static void main(String[] args) {
		// oracle 에 scott/tiger 계정으로 접속해서 member 테이블에
		// 회원 한명의 정보를 저장하려고 한다.
		
		//DataBase 연결객체를 담을 지역변수 선언
		Connection conn=null;
		try{
			//오라클 드라이버 로딩하기
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//접속할 oracle DB url 정보
			String url="jdbc:oracle:thin:@localhost:1521:xe";
			//Connection 객체의 참조값 얻어오기
			conn=DriverManager.getConnection(url, "scott", "tiger");
			System.out.println("Oracle DB 접속 성공");
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//아래 변수 안에 있는 정보를 member 테이블에 넣고자 한다. 
		int num=3;
		String name="원숭이";
		String addr="상도동";
		
		//필요한 객체의 참조값을 담을 변수 만들기
		PreparedStatement pstmt=null;
		try{
			//실행할 sql 문 구성하기 
			String sql="INSERT INTO member(num,name,addr) VALUES(?, ?, ?)";
			//PreparedStatement 객체의 참조값 얻어오기
			pstmt=conn.prepareStatement(sql);
			// ? 에 값 바인딩하기
			pstmt.setInt(1, num);
			pstmt.setString(2, name);
			pstmt.setString(3, addr);
			//sql 문 실행하기
			pstmt.executeUpdate();
			System.out.println("회원정보를 저장했습니다.");
		}catch(SQLException se){
			se.printStackTrace();
		}finally{
			try{
				if(pstmt!=null)pstmt.close();
				if(conn!=null)conn.close();
			}catch(Exception e){}
		}
		
		System.out.println("main 메소드가 리턴됩니다.");
	}
}



