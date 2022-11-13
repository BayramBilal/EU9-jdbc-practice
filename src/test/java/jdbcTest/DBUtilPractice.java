package jdbcTest;

import org.junit.jupiter.api.Test;
import utilities.DBUtils;

import java.util.List;
import java.util.Map;

public class DBUtilPractice {


    @Test
    public void test1(){

   // create connection
        DBUtils.createConnection();
        String query = "SELECT FIRST_NAME, LAST_NAME, SALARY, JOB_ID\n" +
                "FROM EMPLOYEES\n" +
                "WHERE ROWNUM <6";

        List<Map<String, Object>> queryData = DBUtils.getQueryResultMap(query);

        // printing the result
        for (Map<String, Object> row : queryData) {
            System.out.println(row.toString());

        }
        // close the connection
        DBUtils.destroy();
    }

    @Test
    public void test2(){

        //create connection
        DBUtils.createConnection();
        String query = "SELECT first_name,last_name,salary,job_id\n" +
                "from employees\n" +
                "where rownum <2";

        Map<String, Object> rowMap = DBUtils.getRowMap(query);

        System.out.println(rowMap.toString());

        //close the connection
        DBUtils.destroy();
    }




}