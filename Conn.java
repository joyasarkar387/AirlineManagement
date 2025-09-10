package airlinemanagement;


import java.sql.*;

public class Conn {
   
   
    public Connection c;
    public Statement s;
    public Conn(){
    try{
       
        c = DriverManager.getConnection("jdbc:mysql:///airline_db","root",""); //databaser nam, username, password
       
        s = c.createStatement();
    } catch (Exception e){
        System.out.println(e);
    }
    }
}