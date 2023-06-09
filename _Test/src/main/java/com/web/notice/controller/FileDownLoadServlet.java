package com.web.notice.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FileDownLoadServlet
 */
@WebServlet("/fileDownload.do")
public class FileDownLoadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileDownLoadServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		파일에 대한 다운로드기능 구현하기 
//		1. 클라이언트가 보낸 파일이름 받기
		String filename = request.getParameter("name"); 
		
//		2. 파일에 대한 절대경로를 가져옴(스트림을 이용)
		String path = getServletContext().getRealPath("/upload/notice/");
		File f = new File(path + filename);
		
//		3. InputStream을 생성 - > FileInputStream을 생성 
		FileInputStream fis = new FileInputStream(f);
		BufferedInputStream bis = new BufferedInputStream(fis);
		
//		4. 한글파일명이 깨지지 않도록 인코딩처리하기 
		String fileRename = "";
		String header = request.getHeader("user-agent");
//		IE, 그외 브라우저랑 인코딩처리방식이 달라서 인코딩을 분리해서 처리해줘야 함.
		boolean isMSIE=header.indexOf("MSIE")!=-1||header.indexOf("Trident")!=-1;
		if(isMSIE) {
			fileRename = URLEncoder.encode(filename, "UTF-8").replaceAll("\\+","%20");
		}else {
			fileRename=new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
//		5. 응답해더 설정 -> contentType설정(어떠한 파일타입이 오더라도 처리하는 설정기능)
		response.setContentType("application/octet-stream");
//		이건 해더부분에 상세한 설명에 대한 기능(attachment는 파일을 눌렀을때 다운로드 되도록하는것, 만약 바로 새창이 열리려면 index로 설정해야함
		response.setHeader("Content-disposition", "attachment;filename=" + fileRename);
		
//		6. 사용자에게 파일 보내기
//		클라이언트의 스트림을 가져오기 -> getOutputStream();
		ServletOutputStream sos = response.getOutputStream();
		BufferedOutputStream bos = new BufferedOutputStream(sos);
		
		int read = 1;
		while((read = bis.read()) != -1) {
			bos.write(read);
		}
		bis.close();
		bos.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
