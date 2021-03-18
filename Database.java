package com.company;

import javax.xml.crypto.Data;
import java.sql.Connection;

public class Database {
  private static Connection connection;
  private static final Database INSTANCE = new Database();

  private Database(){
      connection = JavaMysqlSelectExample.openConnection();
  }
  public static Database getInstance(){
      return INSTANCE;
  }
  public static Connection getConnection(){
      return connection;
  }

}
