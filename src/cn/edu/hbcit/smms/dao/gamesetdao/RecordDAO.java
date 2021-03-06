package cn.edu.hbcit.smms.dao.gamesetdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import cn.edu.hbcit.smms.dao.databasedao.DBConn;
import cn.edu.hbcit.smms.dao.databasedao.DBTest;
import cn.edu.hbcit.smms.pojo.Admin;
import cn.edu.hbcit.smms.pojo.Item;
import cn.edu.hbcit.smms.pojo.Record;
import cn.edu.hbcit.smms.util.UtilTools;

public class RecordDAO {
	protected final Logger log = Logger.getLogger(RecordDAO.class.getName());
	private ResultSet rs = null;
	private Connection conn = null;
	private PreparedStatement pStatement = null;
	DBConn db = new DBConn();
	/**
	 * 查询所有记录
	 * @return air（ArrayList）
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public ArrayList getQuery() {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select * from t_record"); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
    				admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setItemName(getItemName( rs.getInt("itemid")));
    				air.add(admin);
                     
                }
                rs.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }
	/**
	 * 查询所有项目名称
	 * @return ArrayList
	 * @author 于德水
	 * @version 2012/6/24 新建
	 */
	public ArrayList getItemRecord() {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
                Statement statement = conn.createStatement(); 
                ResultSet rs = statement.executeQuery("select id,itemname from t_item"); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setIteId(rs.getInt("id"));
    				admin.setItemName(rs.getString("itemname"));
    				air.add(admin);
                     
                }
                rs.close();
                statement.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }
	
	/**
	 * 记录是否存在的查询
	 * @param itemId 项目Id
	 * @param recTime 创记录时间
	 * @return boolean
	 * @author 于德水
	 * @version 2012/6/20 新建
	 */
	public boolean getQuery( int itemId, String recTime) {
	 	boolean air = false;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	PreparedStatement ps = conn.prepareStatement("select * from t_record where itemid = ? and recordtime = ?");
   			 	ps.setInt(1, itemId);
   			 	ps.setString(2, recTime);
                ResultSet rs = ps.executeQuery(); 
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	 air = true;
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    
}
	/**
	 * 根据项目名称查询
	 * @param itemId 项目ID 
	 * @return air （ArrayList）
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public ArrayList seleteByName( int itemId) {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select * from t_record where itemid = ?");
    			 ps.setInt(1,itemId);
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setIteId(rs.getInt("itemid"));
    				air.add(admin);
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }

	/**
	 * 获取项目Id
	 * @param itemName 项目名称
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public int getItemId( String itemName) {
		int id = 0;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select id from t_item  where itemname like ?");
    			 ps.setString(1,"%"+itemName+"%");
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	//Admin admin = new Admin();
    				 id = rs.getInt("id");
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
        return id;
    }
	/**
	 * 获取项目名称
	 * @param itemId 项目Id
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public String getItemName( int itemId) {
		String name = null;
		DBConn db = new DBConn();
        try {
            Connection conn = db.getConn();
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement("select itemname from t_item  where id = ?");
    			 ps.setInt(1,itemId);
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	//Admin admin = new Admin();
    				name = rs.getString("itemname");
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
        return name;
    }
	/**
	 * 根据学生记录id的查询
	 * @param recordId	学生记录id
	 * @return ArrayList
	 * @author 于德水
	 * @version 2012/6/23
	 */
	 
	public ArrayList seleteByRecordId( int recordId) {
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
        try {
            Connection conn = db.getConn();
            if(conn != null){
            	String sql = "select * from t_record where id = ?";
    			PreparedStatement ps = conn.prepareStatement(sql);
    			 ps.setInt(1,recordId);
    			 
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRecordId(rs.getInt("id"));
                	admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setItemName(getItemName( rs.getInt("itemid")));
    				air.add(admin);
                     
                }
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
    }
	
	/**
	 * 根据项目名称及破记录时间的查询
	 * @param itemId 项目Id
	 * @param recordTime 破记录时间
	 * @return  ArrayList
	 * @author 于德水
	 * @version 2012/6/23
	 */
	public ArrayList seleteByNameTime( int itemId, String recordTime, boolean flag) {
		log.debug("itemId:"+itemId+",recordTime:"+recordTime+",flag:"+flag);
		DBConn db = new DBConn();
		ArrayList air = new ArrayList();
		Connection conn = db.getConn();
		String sql = "";
		if(flag == false){
			sql = "select * from t_record where itemid = ?  ";
		}else{
			sql = "select * from t_record where itemid = ? and recordtime =? ";
		}
        try {
            
            if(conn != null){
    			PreparedStatement ps = conn.prepareStatement(sql);
    			if(recordTime.equals("")){
    				ps.setInt(1,itemId);
    			}else{
    			 ps.setInt(1,itemId);
    			 ps.setString(2,recordTime);
    			}
    			 ResultSet rs = ps.executeQuery();
                //int c = rs.getMetaData().getColumnCount();
    			 int count = 0;
                while(rs.next()){
                	Admin admin = new Admin();
                	admin.setRecordId(rs.getInt("id"));
                	admin.setRecTime(rs.getString("recordtime"));
    				admin.setRecLevel(rs.getString("recordlevel"));
    				admin.setSor(rs.getString("score"));
    				admin.setSportsName1(rs.getString("sportsname"));
    				admin.setPlaName(rs.getString("playername"));
    				admin.setDepName(rs.getString("departname"));
    				admin.setPlaSex(rs.getInt("sex"));
    				admin.setItemName(getItemName( rs.getInt("itemid")));
    				air.add(admin);
    				count++;
                }
                log.debug("seleteByNameTime查询个数："+count);
                rs.close();
                ps.close();
            }
            db.freeConnection(conn);
        } catch (SQLException e) {                 
            e.printStackTrace();       
        }       
      return air;
      
    }
	
	/**
	 * 添加新记录
	 * @param plaName 运动员名
	 * @param sportsName1 运动会名
	 * @param recTime  记录时间
	 * @param recLevel  级别
	 * @param plaSex  运动员性别
	 * @param sor    成绩
	 * @param depName  系别
	 * @param iteId    项目ID
	 * @return retuValue 
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
//	public boolean addRecord(String plaName,String sportsName1,String recTime,String recLevel,int plaSex,String sor, String depName, int itemId) {
//		
//		boolean retuValue = false;
//		DBConn db = new DBConn();
//		
//			
//		try {
//			 
//			Connection conn = db.getConn();
//			String sql = "insert into t_record (itemid ,sex, score, playername, "+
//			"departname, sportsname, recordtime, recordlevel) values(?,?,?,?,?,?,?,?)";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			 
//			 ps.setInt(1, itemId);
//			// System.out.println("hhhhhhhhhhhhhhhhh"+itemId);
//			 ps.setInt(2,plaSex);
//			 ps.setString(3,sor);
//			 ps.setString(4,plaName);
//			 ps.setString(5,depName);
//			 ps.setString(6,sportsName1);
//			 ps.setString(7,recTime);
//			 ps.setString(8,recLevel);
//			int s = ps.executeUpdate();
//			if(s==1)
//			{
//			retuValue = true;
//			}
//			ps.close();
//			db.freeConnection(conn);
//		} catch (SQLException e) {                 
//            e.printStackTrace();       
//        }
//		
//		return retuValue;
//	}
	
	/**
	 * 修改记录
	 * @param plaName 运动员名
	 * @param newIteId 项目ID
	 * @param recordId  学生记录ID
	 * @param sportsName1 运动会名
	 * @param newRecTime 记录时间
	 * @param recLevel  级别
	 * @param plaSex   运动员性别
	 * @param sor    成绩
	 * @param depName 系部
	 * @return boolean 
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
//	public boolean updateRecord(String plaName, String sportsName1,String newRecTime,String recLevel,int plaSex,String sor, String depName, int recordId)
//	{
//		
//		boolean retuValue = false;
//		DBConn db = new DBConn();
//		
//		try {
//			
//			Connection conn = db.getConn();
//			String sql = "update t_record  set sex = ?, score = ?,"+
//			" playername = ?, departname = ?, sportsname = ?, recordtime = ?, recordlevel = ? where id = ?";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			 
//			 ps.setInt(1,plaSex);
//			 ps.setString(2,sor);
//			 ps.setString(3,plaName);
//			 ps.setString(4,depName);
//			 ps.setString(5,sportsName1);
//			 ps.setString(6,newRecTime);
//			 ps.setString(7,recLevel);
//			 ps.setInt(8,recordId);
//
//			int s = ps.executeUpdate();
//			if(s==1)
//			{
//			retuValue = true;
//			}
//			ps.close();
//			db.freeConnection(conn);
//		} catch (SQLException e) {                 
//            e.printStackTrace();       
//        }   
//		return retuValue;
//	}
	
	/**
	 * 删除记录
	 * @param recordtime 记录时间
	 * @param recordId 学生记录Id
	 * @return boolean
	 * @author 于德水
	 * @version 1.00  2012/6/15  新建
	 */
	public boolean deleteRecord( int recordId) {
		boolean retuValue = false;
		DBConn db = new DBConn();
		try {
			
			Connection conn = db.getConn();
			PreparedStatement ps = conn.prepareStatement("delete from t_record  where id = ?");
			 
			 ps.setInt(1,recordId);
			 int s = ps.executeUpdate();
			 if(s == 1){
			retuValue = true;
			 }
			 ps.close();
			db.freeConnection(conn);
			 
		} catch (SQLException e) {                 
            e.printStackTrace();       
        }   
		return retuValue;
	}
	/*
	 * 
	 * 以下为重做的赛事记录
	 * 2012-10-14 BY liwei
	 * 
	 * */
	/**
	 * 查询最新的运动会记录
	 * 李玮 2012-10-15
	 * @param itemIdList
	 * @return ArrayList
	 */
	public ArrayList selectLastRecords(ArrayList itemIdList, int sex){
		ArrayList list = new ArrayList();
		UtilTools ut = new UtilTools();
		conn = db.getConn();
		String sql = "SELECT t_record.id,t_record.itemid,t_item.itemname,t_record.sex,t_record.score,t_record.departname,t_record.sportsname,t_record.playername,t_record.recordtime,t_record.recordlevel,t_item.itemtype FROM t_record,t_item WHERE t_record.itemid=t_item.id AND t_record.itemid=? AND t_record.sex=?  ORDER BY t_record.recordtime DESC LIMIT 1";
		try {
			pStatement = conn.prepareStatement(sql);
			for(int i=0; i<itemIdList.size(); i++){
				pStatement.setInt(1, (Integer)itemIdList.get(i));
				pStatement.setInt(2, sex);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt(1));
					record.setItemid(rs.getInt(2));
					record.setItemname(rs.getString(3));
					record.setSex(rs.getInt(4));
					//record.setScore(rs.getString(5));
					record.setScore(ut.coverToTrackScore(rs.getString(5), rs.getString(11)));
					record.setDepartname(rs.getString(6));
					record.setSportsname(rs.getString(7));
					record.setPlayername(rs.getString(8));
					record.setRecordtime(rs.getString(9));
					record.setRecordlevel(rs.getString(10));
					record.setItemtype(rs.getString(11));
					list.add(record);
				}
			}
			
			db.freeConnection(rs,pStatement,conn);
		} catch (Exception e) {
			log.error("获取最新运动会记录失败！");
			log.error(e.getMessage());
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 获取所有项目的ID
	 * @return
	 */
	public ArrayList selectAllItemId(){
		ArrayList list = new ArrayList();
		conn = db.getConn();
		String sql = "SELECT t_item.id FROM t_item";
		try {
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt(1));
			}
			db.freeConnection(rs,pStatement,conn);
		} catch (Exception e) {
			log.error("获取所有项目ID失败！");
			log.error(e.getMessage());
		}
		return list;
	}
	
	/**
	 * 查询历届的运动会记录（按性别区分）
	 * 李玮 2012-10-23
	 * @param sex
	 * @return ArrayList
	 */
	public ArrayList selectAllRecords(int sex){
		ArrayList list = new ArrayList();
		UtilTools ut = new UtilTools();
		conn = db.getConn();
		String sql = "SELECT t_record.id,t_record.itemid,t_item.itemname,t_record.sex,t_record.score,t_record.departname,t_record.sportsname,t_record.playername,t_record.recordtime,t_record.recordlevel,t_item.itemtype FROM t_record,t_item WHERE t_record.itemid=t_item.id AND t_record.sex=?  ORDER BY t_record.itemid,t_record.recordtime DESC ";
		try {
			pStatement = conn.prepareStatement(sql);
				pStatement.setInt(1, sex);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt(1));
					record.setItemid(rs.getInt(2));
					record.setItemname(rs.getString(3));
					record.setSex(rs.getInt(4));
					//record.setScore(rs.getString(5));
					record.setScore(ut.coverToTrackScore(rs.getString(5), rs.getString(11)));
					record.setDepartname(rs.getString(6));
					record.setSportsname(rs.getString(7));
					record.setPlayername(rs.getString(8));
					record.setRecordtime(rs.getString(9));
					record.setRecordlevel(rs.getString(10));
					record.setItemtype(rs.getString(11));
					list.add(record);
				}

			db.freeConnection(rs,pStatement,conn);
		} catch (Exception e) {
			log.error("获取历届运动会记录失败！");
			log.error(e.getMessage());
			//e.printStackTrace();
		}
		return list;
	}
	/**
	 * 获取所有项目及格式
	 * @return
	 */
	public ArrayList selectItemAndScoreFormat(){
		ArrayList list = new ArrayList();
		UtilTools ut = new UtilTools();
		conn = db.getConn();
		String sql = "SELECT t_item.id,t_item.itemname,t_scoreformat.reg FROM t_scoreformat,t_item WHERE t_item.scoreformatid=t_scoreformat.id ORDER BY t_item.itemname ASC ";
		try {
			pStatement = conn.prepareStatement(sql);
			rs = pStatement.executeQuery();
			while (rs.next()) {
				Item item = new Item();
				item.setItemid(rs.getInt(1));
				item.setItemname(rs.getString(2));
				item.setFormat(rs.getString(3));
				list.add(item);
			}

			db.freeConnection(rs,pStatement,conn);
		} catch (Exception e) {
			log.error("获取所有项目及格式失败！");
			log.error(e.getMessage());
			//e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 手动插入新赛事记录
	 * @param itemid
	 * @param sex
	 * @param score
	 * @param playername
	 * @param departname
	 * @param sportsname
	 * @param recordtime
	 * @param recordlevel
	 * @return
	 */
	public boolean addRecord(int itemid, int sex, String score, String playername, String departname, String sportsname, String recordtime, String recordlevel) {
		int rst = 0;
		boolean flag = false;
		conn = db.getConn();
		String sql = "INSERT INTO t_record (itemid,sex,score,playername,departname,sportsname,recordtime,recordlevel) VALUES(?,?,?,?,?,?,?,?)";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1,itemid);
			pStatement.setInt(2,sex);
			pStatement.setString(3, score);
			pStatement.setString(4, playername);
			pStatement.setString(5, departname);
			pStatement.setString(6, sportsname);
			pStatement.setString(7, recordtime);
			pStatement.setString(8, recordlevel);
			rst = pStatement.executeUpdate();
			if(rst > 0){
				flag = true;
			 }
			db.freeConnection(pStatement,conn);
		} catch (SQLException e) {                 
			log.error("插入新记录失败！");
			log.error(e.getMessage());
        }   
		return flag;
	}
	/**
	 * 获取指定ID的运动会记录
	 * @param id
	 * @return
	 */
	public ArrayList selectRecordsById(int id){
		ArrayList list = new ArrayList();
		UtilTools ut = new UtilTools();
		conn = db.getConn();
		String sql = "SELECT t_record.id,t_record.itemid,t_record.sex,t_record.score,t_record.playername,t_record.departname,t_record.sportsname,t_record.recordtime,t_record.recordlevel FROM t_record WHERE t_record.id=?";
		try {
			pStatement = conn.prepareStatement(sql);
				pStatement.setInt(1, id);
				rs = pStatement.executeQuery();
				while (rs.next()) {
					Record record = new Record();
					record.setId(rs.getInt(1));
					record.setItemid(rs.getInt(2));
					record.setSex(rs.getInt(3));
					record.setScore(rs.getString(4));
					record.setPlayername(rs.getString(5));
					record.setDepartname(rs.getString(6));
					record.setSportsname(rs.getString(7));
					record.setRecordtime(rs.getString(8));
					record.setRecordlevel(rs.getString(9));
					list.add(record);
				}

			db.freeConnection(rs,pStatement,conn);
		} catch (Exception e) {
			log.error("获取指定ID的运动会记录失败！");
			log.error(e.getMessage());
			//e.printStackTrace();
		}
		return list;
	}
	/**
	 * 更新运动会记录
	 * @param id
	 * @param itemid
	 * @param sex
	 * @param score
	 * @param playername
	 * @param departname
	 * @param sportsname
	 * @param recordtime
	 * @param recordlevel
	 * @return
	 */
	public boolean updateRecord(int id, int itemid, int sex, String score, String playername, String departname, String sportsname, String recordtime, String recordlevel) {
		int rst = 0;
		boolean flag = false;
		conn = db.getConn();
		String sql = "UPDATE t_record SET itemid=?,sex=?,score=?,playername=?,departname=?,sportsname=?,recordtime=?,recordlevel=? WHERE id=?";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1,itemid);
			pStatement.setInt(2,sex);
			pStatement.setString(3, score);
			pStatement.setString(4, playername);
			pStatement.setString(5, departname);
			pStatement.setString(6, sportsname);
			pStatement.setString(7, recordtime);
			pStatement.setString(8, recordlevel);
			pStatement.setInt(9,id);
			rst = pStatement.executeUpdate();
			if(rst > 0){
				flag = true;
			 }
			db.freeConnection(pStatement,conn);
		} catch (SQLException e) {                 
			log.error("更新记录失败！");
			log.error(e.getMessage());
        }   
		return flag;
	}
	/**
	 * 删除赛事记录
	 * @param id
	 * @return
	 */
	public boolean removeRecord(int id) {
		int rst = 0;
		boolean flag = false;
		conn = db.getConn();
		String sql = "DELETE FROM t_record WHERE id=?";
		try {
			pStatement = conn.prepareStatement(sql);
			pStatement.setInt(1,id);
			rst = pStatement.executeUpdate();
			if(rst > 0){
				flag = true;
			 }
			db.freeConnection(pStatement,conn);
		} catch (SQLException e) {                 
			log.error("删除记录失败！");
			log.error(e.getMessage());
        }   
		return flag;
	}
}
