package common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import member.MemberVO;

@Service
public class CommonService {
			
	//첨부파일 삭제 메소드
	public void fileDelete(String filepath, HttpServletRequest request) {
		if( filepath != null ) {

			filepath = filepath.replace(appURL(request), "d://app"+request.getContextPath());
			
			File file = new File( filepath );
			if (file.exists()) file.delete();

		}
	}
	

	// 첨부파일 업로드 처리
	
	
	public String fileUplaod(String category, MultipartFile file, HttpServletRequest request) {
		
		//업로드할 물리적 위치
		
		//String path = request.getSession().getServletContext().getRealPath("resources");
		//프로젝트내에 저장을하다보니 clean하면 다없어지는 문제가 발생해버림.. 고정적인 물리영역에 저장하도록 한다.
		String path = "d://app"+request.getContextPath();
		
		
		
		// /upload/myinfo/2022/12/20
		String upload = "/upload/"+category+"/"+ new SimpleDateFormat("yyyy/MM/dd").format(new Date()) ;
		
		path+=upload;
		
		//해당 폴더가 없으면 폴더를 만든다
		File folder = new File(path);
		if( ! folder.exists() ) folder.mkdirs();
		
		// 해당 폴더에 첨부한 파일을 저장한다.//파일명이 겹칠 수 있으므로 랜덤하게
		String filename =  UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
		try {
			file.transferTo( new File(path,filename) );
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return appURL(request)+upload+"/"+filename;

		//매핑도 추가해줘야함
	}
	
	
	
	
	
	//요청 url의 contextpath
	public String appURL(HttpServletRequest request) {
		return request.getRequestURL().toString().replace(request.getServletPath(),"");
	}
	
	//요청
	public String requestAPI(String apiURL) {
		try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"utf-8"));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      apiURL=res.toString();//apiURL변수 재활용
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return apiURL;
	}
	
	//오버로딩 속성값을 넣어 요청하기 위해서
	public String requestAPI(String apiURL,String property) {
		try {
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      //프로퍼티추가
		      con.setRequestProperty("Authorization", property);
		      //
		      
		      
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream(),"utf-8"));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream(),"utf-8"));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      apiURL=res.toString();//apiURL변수 재활용
		    } catch (Exception e) {
		      System.out.println(e);
		    }
		return apiURL;
	}
	
	
	//솔트 생성 메소드
	public String generateSalt() {
		SecureRandom secure = new SecureRandom(); //암호화 랜덤값 생성 객체
		byte bytes[] = new byte[24];
		secure.nextBytes(bytes);
		
		//byte값을 16진수로 변환 : 
		StringBuffer salt = new StringBuffer();
		for(byte b : bytes) {
			salt.append( String.format("%02x", b) ); //%o			
		}
		return salt.toString();
	}
	
	//솔트를 사용해 비밀번호를 암호화하는 메소드
	public String getEncrypt(String salt, String pw) {
		pw += salt;
		
		//암호화 해시 함수: 암호화방식 지정- SHA-256
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update( pw.getBytes() );
			byte[] bytes = md.digest();
			//16진수로 변환
			
			StringBuffer data = new StringBuffer();
			for(byte b : bytes ) {
				data.append( String.format("%02x", b));
			}
			pw = data.toString();			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return pw;
	}
	
	//임시 비밀번호 이메일 전송처리
	public boolean sendPassword(MemberVO vo, String pw) {
		boolean send =true;
		HtmlEmail mail =  new HtmlEmail();
		mail.setCharset("utf-8");
		
		
		mail.setHostName("smtp.naver.com"); // 이메일 서버 지정
		mail.setAuthentication("itstudydev", "Itstudy10102");  // 관리자 이메일주소 ,비번
		mail.setSSLOnConnect(true);
		
		try {
			mail.setFrom("itstudydev@naver.com","관리자"); // 전송자 이메일 주소와 전송자 이름
			mail.addTo(vo.getEmail(),vo.getName());
			mail.setSubject("임시비밀번호입니다.");
			
			StringBuffer msg = new StringBuffer();
			msg.append("<html>");
			msg.append("<body>");
			msg.append("<h3>[").append(vo.getName()).append("] 님의 임시 비밀번호</h3>");
			msg.append("<div>임시비밀번호가 발급되었습니다.</div>");
			msg.append("<div>아래 임시 비밀번호로 로그인 하신 뒤 비밀번호를 변경하세요</div>");
			msg.append("<div><strong>").append(pw).append("</strong></div>");
			msg.append("</body>");
			msg.append("</html>");
			mail.setHtmlMsg(msg.toString());
			
			
			mail.send();
		
		} catch (EmailException e) {
			System.out.println(e.getMessage());
			send=false;
		}
		
		return send;
	}
	//첨부파일 다운로드
	public boolean fileDownload(String filename, String filepath,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//DB:  http://localhost/smart/upload/myinfo/2022/12/20/afdlj_abc.png
		//실제: d://app/smart/upload/myinfo/2022/12/20/afdlj_abc.png 
		filepath = 
		filepath.replace(appURL(request), "d://app/"+request.getContextPath());
		//filepath는 이제 실제 위치임.
		File file = new File(filepath);
		if(!file.exists()) return false;
		
	  //파일 형태는 마임매핑에서 마임타입을 뽑아내야하는데...? -> 서버가 알아서 판단
		String mime = request.getSession().getServletContext().getMimeType(filename);
		response.setContentType( mime );
		
		//첨부파일을 다운로드하는 것임을 지정
		response.setHeader("content-disposition", "attachment; filename="+
				URLEncoder.encode(filename,"utf-8"));
		// 한글 첨부파일명의 경우 URLEncoder.encode를 이용해 utf-8로 인코딩해주어야한다!@#!@#!@#!@
		
		
		//PrintWriter는 문자만 내보냄...
		//바이너리 데이터를 쓰기작업할 스트림이 필요 !! = > OutputStream
		
		//     IO : byte( inputStream /outputStream) , character(reader/writer)
		//File IO : fileinputStream/ fileoutputStream , Filereader/filewriter
		// 위아래 맞춰서 생각
		
		ServletOutputStream out = response.getOutputStream();
		//Exception 던지기로 한번 해봄.
		//파일정보를 읽어들여 저장해주는 처리
		FileCopyUtils.copy( new FileInputStream(file)  , out );
		out.flush();
		
		
		return true;
	}
	
}
