package jdbcTest;

import org.junit.jupiter.api.Test;

import java.sql.*;
import java.util.*;

public class dynamic_list {

    String dbUrl ="jdbc:oracle:thin:@54.88.101.116:1521:XE";
    String dbUsername ="hr";
    String dbPassword ="hr";

    @Test
    public void test2() throws SQLException {


        Connection connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet resultSet = statement.executeQuery("SELECT FIRST_NAME, LAST_NAME, SALARY, JOB_ID\n" +
                "FROM EMPLOYEES\n" +
                "WHERE ROWNUM <6");


        // in order to get column names we need resulsetmetadata
        ResultSetMetaData rsmd = resultSet.getMetaData();

    // list of maps to keep all information

        List<Map<String, Object>> queryData = new ArrayList<>();

        // number of columns
        int colCount= rsmd.getColumnCount();

        // loop through each row
        while (resultSet.next()){
           // Map<String, Object> row = new HashMap<>();
            Map<String, Object> row = new LinkedHashMap<>();

            //some code to fill the dynamically
            for (int i = 1; i <= colCount ; i++) {
                row.put(rsmd.getColumnName(i), resultSet.getObject(i));
            }
    // add ready map to the list

            queryData.add(row);



        }

        //
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());
        }




        resultSet.close();
        statement.close();
        connection.close();

}
}
