package com.sean.tools;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
/** 
* @ClassName: ConnDB 
* @Description: TODO(数据库相关操作) 
* @author sean，wsl 
* @email:364672554@qq.com,616272735@qq.com
* @date 2017年12月6日 下午7:39:28 
*  
*/
public class ConnDB {
	public Connection conn=null;				//声明Connection对象的实例
	public Statement stmt=null;	               //声明Statement对象的实例
	public ResultSet rs=null;               //声明ResultSet对象的实例
	private static String propFileName="connDB.properties"; //指定资源文件保存的位置
	private static Properties prop=new Properties();   			//创建并实例化Properties对象的实例
	private static String dbClassName="oracle.jdbc.driver.OracleDriver";//定义并保存数据库驱动的变量
	private static String dbUrl="jdbc:oracle:thin:@39.106.214.19:1521:orcl";
	private static String jdbcIpv6Url = "jdbc:oracle:thin:@(DESCRIPTION=(ADDRESS=(PROTOCOL=tcp)(HOST=39.106.214.19)(PORT=1521))(CONNECT_DATA=(SERVICE_NAME=orcl)))";  
	private static String dbUsername="wsl";
	private static String dbPassword="123456";
	public ConnDB(){
		try{
			InputStream in=getClass().getResourceAsStream(propFileName);
			prop.load(in);//通过输入流对象加载propeties文件
			dbClassName=prop.getProperty("DB_CLASS_NAME",dbClassName);
			dbUrl=prop.getProperty("DB_URL",jdbcIpv6Url);
			dbUsername=prop.getProperty("DB_USERNAME",dbUsername);
			dbPassword=prop.getProperty("DB_PASSWORD",dbPassword);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/** 
	* @Title: getConnection 
	* @Description: TODO(连接数据库) 
	* @param @return   conn
	* @return Connection    返回类型 
	* @throws 
	*/
	public static Connection getConnection(){
		Connection conn=null;
		try{
			Class.forName(dbClassName).newInstance();//加载数据库驱动
			conn=DriverManager.getConnection(dbUrl,dbUsername,dbPassword);//建立数据库连接
		}catch(Exception e){
			e.printStackTrace();
		}
		if(conn==null){
			System.err.println("警告：获得数据库连接失败.\r\n 连接类型："+dbClassName+"\r\n 连接位置："+dbUrl);
		}
		return conn;
	}
	/** 
	* @Title: executeQuery 
	* @Description: TODO(查询数据库操作) 
	* @param  sql
	* @return ResultSet    返回类型 
	* @throws 
	*/
	public ResultSet executeQuery(String sql){
		try {		
			conn=getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;	
	}
	/** 
	* @Title: executeUpdate 
	* @Description: TODO(更新数据库操作) 
	* @param sql 
	* @return int  返回类型 
	* @throws 
	*/
	public int executeUpdate(String sql){
		int result=0;
		try {	
			conn=getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			result=stmt.executeUpdate(sql);
		} catch (SQLException e) {
			result=0;
		}
		return result;
	}
	public void close(){
		try{
			if(rs!=null)
			{
				rs.close();
			}
			if(stmt!=null)
			{
				stmt.close();
			}
			if(conn!=null)
			{
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace(System.err);
		}
	}
}