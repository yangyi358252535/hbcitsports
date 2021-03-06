/*
* Copyright(C) 2012, XXXXXXXX.
*
* 模块名称：     AAAAAAAAAAA
* 子模块名称：   BBBBBBBBBBB
*
* 备注：
*
* 修改历史：
* 时间			版本号	姓名		修改内容
* 2004/12/12		0.1		张 三		新建
* 2005/02/05		0.1		李 四		Bug修正
*/
package cn.edu.hbcit.smms.servlet.gamemanageservlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.edu.hbcit.smms.pojo.GameManagePoJo;
import cn.edu.hbcit.smms.services.gamemanageservices.GameManageCheckTableServices;
import cn.edu.hbcit.smms.services.gamemanageservices.GameManageServices;
import cn.edu.hbcit.smms.util.UtilTools;

/**
 * XXXXXXXXXXXXXXXXXXXXXXXX类
 *
 *简要说明
 *
 *详细解释。
 * @author 张三
 * @version 1.00  2011/12/07 新規作成<br>
 */

public class GameManageCheckTablePrintScanServlet extends HttpServlet {
	UtilTools tools = new UtilTools();
	/**
	 * Constructor of the object.
	 */
	public GameManageCheckTablePrintScanServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{

		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;utf-8");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		StringBuffer buffer = new StringBuffer();
		String finalitemname = tools.toUTF8(request.getParameter("finalitemname"));
		String finalitemtype = tools.toUTF8( request.getParameter("finalitemtype"));
		String groupname = new String(request.getParameter("groupname").getBytes("ISO-8859-1"),"UTF-8");
		//System.out.println(groupname);
		GameManageCheckTableServices gs = new GameManageCheckTableServices();
		int a = 0;
		String file = request.getSession().getServletContext().getRealPath("/")+"word/";
		HttpSession  session = request.getSession();
		session.setAttribute("file", file);
		try{
		if(gs.createDocContext(file, finalitemname, finalitemtype, groupname)){
			a = 1;
			
		}else{
		    a = 0;	
		 }
		}catch(Exception e){
			e.printStackTrace();
		}
		buffer.append(a);
		out.println(buffer);
		out.flush();
		out.close();
		
		
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		this.doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
