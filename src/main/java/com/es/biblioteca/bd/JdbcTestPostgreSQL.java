package com.es.biblioteca.bd;
//JdbcTestPostgreSQL.java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

//teste de conexao com o bd + driver postgres
class JdbcTestPostgreSQL {
   public static void main (String args[]) {
      try {
         Class.forName("org.postgresql.Driver");
      }
      catch (ClassNotFoundException e) {
         System.err.println(e);
         System.exit(-1);
      }
      try {
         // open connection to database
         Connection connection = DriverManager.getConnection(
         //"jdbc:postgresql://dbhost:port/dbname", "user", "dbpass");
         "jdbc:postgresql://127.0.0.1:5432/biblioteca", "postgres", "admin");

         // build query, here we get info about all databases"
         String query = "SELECT datname FROM pg_database WHERE datistemplate = false";

         // execute query
         Statement statement = connection.createStatement();
         ResultSet rs = statement.executeQuery(query);

         // return query result
         while ( rs.next() )
            // display table name
            System.out.println("PostgreSQL Query result: " + rs.getString ("datname"));
         connection.close();
      }
      catch (java.sql.SQLException e) {
         System.err.println(e);
         System.exit(-1);
      }
   }
}