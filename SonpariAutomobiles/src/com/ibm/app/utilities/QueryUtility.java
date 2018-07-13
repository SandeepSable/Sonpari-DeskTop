package com.ibm.app.utilities;

import java.util.ArrayList;
import java.util.List;

public class QueryUtility
{
	
	private static List<String>	querySet				= new ArrayList<String>();
	// ONLY TABLE(S)
	public static final String	MASTER_SP_PRODUCT_TABLE	= "CREATE  TABLE SP_PRODUCT_TABLE(product_id varchar(20) primary key,product_name varchar(100),manufacturer varchar(100),product_desc varchar(500),price float(12),rack_num varchar(10),GST float (10),discount float (10),is_disabled varchar(1))";
	public static final String	MASTER_SP_USER_TABLE	= "CREATE  TABLE SP_USER_TABLE(user_id  varchar(20) primary key,user_name varchar(100),psword varchar(50),is_active varchar(1),last_login_time date)";
	public static final String	MASTER_SP_STOCK_TABLE	= "CREATE  TABLE SP_STOCK_TABLE(stock_id varchar(20) primary key,product_name varchar(100), quantity number(100),added_Date date(500),added_By varchar(10))";
	public static final String	MASTER_SP_RACK_TABLE	= "CREATE  TABLE SP_RACK_TABLE(rack_id varchar(20) primary key,rack_name varchar(30))";
	public static final String	MASTER_SP_AUDIT_TABLE	= "CREATE  TABLE SP_AUDIT_TABLE(audit_id varchar(20) primary key,activity varchar(50),comment varchar(500), time_by date,user_by varchar(30))";
	
	// NOW ONLY SEQUENCES
	public static final String	MASTER_SP_PRODUCT_ID_GENERATOR	= "CREATE SEQUENCE SP_PRODUCT_ID_GENERATOR";
	
	// Product queries
	public static final String	SP_PRODUCT_INSERT_QUERY	= "INSERT INTO SP_PRODUCT_TABLE VALUES(?,?,?,?,?,?,?,?,?)";
	public static final String	SP_PRODUCT_SELECT_QUERY	= "SELECT * FROM SP_PRODUCT_TABLE";
	public static final String	SP_PRODUCT_UPDATE_QUERY	= "UPDATE SP_PRODUCT_TABLE SET product_name=?,product_desc=?,manufacturer=?,price=?,rack_num=?,GST=?, discount=?,is_disabled=? where product_id=?";
	
	// Stock queries
	public static final String		SP_STOCK_INSERT_QUERY	= "INSERT INTO	SP_STOCK_TABLE VALUES(?,?,?,?,?)";
	public static final String SP_PRODUCT_DELETE_A_RECORD_QUERY = "DELETE FROM SP_PRODUCT_TABLE where product_id=?";
	public static final String SP_PRODUCT_TABLE_FIND_PRODUCT_WITH_ID = "SELECT * FROM SP_PRODUCT_TABLE WHERE product_id=?";
	
	public static List<String> getMasterTableAndSeqNames()
	{
		// table added to list
		querySet.add(MASTER_SP_AUDIT_TABLE);
		querySet.add(MASTER_SP_PRODUCT_TABLE);
		querySet.add(MASTER_SP_RACK_TABLE);
		querySet.add(MASTER_SP_USER_TABLE);
		querySet.add(MASTER_SP_STOCK_TABLE);
		
		// sequence added..
		querySet.add(MASTER_SP_PRODUCT_ID_GENERATOR);
		return querySet;
		
	}
	
}
