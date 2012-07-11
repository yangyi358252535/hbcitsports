
package cn.edu.hbcit.smms.servlet.createprogramservlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.createprogramdao.CreateProgramGameGrouping;
import cn.edu.hbcit.smms.pojo.GameLookPojo;
import cn.edu.hbcit.smms.services.createprogramservices.CreateProgramGameGroupingServices;

/**
 * 径赛项目预览
 * @author 韩鑫鹏
 *
 */
public class TrackGameLook extends HttpServlet {

	protected final Logger log = Logger.getLogger(CreateProgramGameGrouping.class.getName());
	/**
	 * Constructor of the object.
	 */
	public TrackGameLook() {
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
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		int finalId = 1;
		String itenName = "";
		CreateProgramGameGroupingServices cpgg = new CreateProgramGameGroupingServices(); //数据库操作类对象cpgg
		itenName = cpgg.selectItnameByFid(finalId);
		int flag1 = itenName.indexOf("1500");
    	int flag2 = itenName.indexOf("5000");
    	int flag3 = itenName.indexOf("马拉松");
    	if ( flag1 >= 0 && flag2 >= 0 && flag3 >= 0){  //判断是否为长跑
    		int count = 0;
    		ArrayList players = new ArrayList();
    		players = cpgg.slectTrack1500Ps(finalId);
    		ArrayList allPlayers = new ArrayList();
    		if (players != null){
    			for (int i = 0; i < (players.size()/8+1); i++){
        			ArrayList temp = new ArrayList();
        			
        			while(true){
        				temp.add(players.get(count));
        				count++;
        				if((i != 0) && (i % 8 ==0)){
        					break;
        				}
        			}
        			allPlayers.add(temp);
        		}
    		}
    		
    		session.setAttribute("longTrack", allPlayers);
    		String fId = finalId+"";
    		session.setAttribute("fid", fId);
    		response.sendRedirect("../longtracllook.jsp");
    		
    	}else{
    		ArrayList nextFlag = new ArrayList();
    		nextFlag = cpgg.slectFidRGnum(finalId); //根据finalitemid得到finalitemid与分组序号finalitemid + ";" + teamnum
    		ArrayList group = cpgg.selectTrackGroup(finalId); //根据finalitemid查询每个项目的每组的人数
    		ArrayList players = cpgg.slectTrackInfo(finalId); //根据finalitemid查询每个径赛项目的各组运动员 编号（ArrayList） 跑道号（ArrayList）
    		ArrayList allPlayers = new ArrayList();
    		int count = 0;
    		if (nextFlag != null && group != null && players != null){
    			for (int i = 0; i < group.size(); i++){
        			GameLookPojo glk = new GameLookPojo();
        			String aa = "第" +(i+1)+ "组";
        			
        			int num = Integer.parseInt(group.get(i).toString());
        			String temp = "";
        			for (int j = 0; j < num; j++){
        				temp += "   "+players.get(count).toString();
        				count++;
        			}
        			String nfg = nextFlag.get(i).toString();
        			glk.setGroupNum(aa);
        			glk.setPlayers(temp);
        			glk.setNextFlag(nfg);
        			allPlayers.add(glk);
        		}
        		
    		}
    		
    		session.setAttribute("trackPlayers", allPlayers);
    		response.sendRedirect("../tracklock.jsp");
    	}
		
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