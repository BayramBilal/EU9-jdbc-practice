package jdbcTest;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class jdbc_examples {

    String dbUrl ="jdbc:oracle:thin:@54.88.101.116:1521:XE";
    String dbUsername ="hr";
    String dbPassword ="hr";



    @Test
    public void test1() throws SQLException {
        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");

        // move to first row
      //  resultSet.next();
     //   System.out.println(resultSet.getString(2));
    // display departments table in 10-Administration - 200 - 1700 format

    // code for iterating for each row

        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" - "+resultSet.getString(2)
                    +" - "+ resultSet.getString(3)+" - "+ resultSet.getInt(4));
        }


        //-------------------------------------------------------------

        resultSet = statement.executeQuery("SELECT * FROM regions");

        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));

        }



        // close connection

        resultSet.close();
        statement.close();
        connection.close();

    }
    @Test
    public void test2() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM departments");


    // how many rows we have for the query
        // move to last row

        resultSet.last();
        // get the row count

        int rowCount = resultSet.getRow();
        System.out.println(rowCount);

        // move before first row after we use .last method
        resultSet.beforeFirst();

        // print all second column informatiom

        while (resultSet.next()) {
            System.out.println(resultSet.getString(2));
        }



        resultSet.close();
        statement.close();
        connection.close();

    }

    @Test
    public void test3() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT * FROM employees");

// get the database related to inside the dbMetedata object

    DatabaseMetaData dbMetaData = connection.getMetaData();
        System.out.println("dbMetaData.getUserName() = " + dbMetaData.getUserName());
        System.out.println("dbMetaData.getDatabaseProductName() = " + dbMetaData.getDatabaseProductName());
        System.out.println("dbMetaData.getDatabaseMajorVersion() = " + dbMetaData.getDatabaseMajorVersion());
        System.out.println("dbMetaData.getDriverName() = " + dbMetaData.getDriverName());
        System.out.println("dbMetaData.getDriverVersion() = " + dbMetaData.getDriverVersion());

        // get resultSetMetaData
        ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

        // how many columns have;

        int colCount = resultSetMetaData.getColumnCount();
        System.out.println("colCount = " + colCount);

        // get columns name

        System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(1));
        System.out.println("resultSetMetaData.getColumnName() = " + resultSetMetaData.getColumnName(2));
        
        // print all the columns name dynamically
        for (int i = 1; i < resultSetMetaData.getColumnCount(); i++) {
            System.out.println(resultSetMetaData.getColumnName(i));
        }


        resultSet.close();
        statement.close();
        connection.close();

    }
}
