import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.* ; 
public class ConnectDB {
	
	// DB Connection variables 
	
	static Connection connection = null;
	static String databasename ="";
	static String url= "jdbc:mysql://localhost:3306/"+databasename; 
	static ResultSet resultSet = null;
	static String username = "root";  // ADD your SQL username
	static String password = "Umaam123"; //Your password 
	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception
	{
		ConnectDB obj = new ConnectDB();
		obj.start();
	}
		
	public void start() throws Exception 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			
			connection = DriverManager.getConnection(url,username,password);
			PreparedStatement ps = connection.prepareStatement("Insert into studentdb.student values (10,'Routray');"); 
			int status = ps.executeUpdate();
			if (status!=0)
			{
				System.out.println("Database was recorded");
				
			}
			ps = connection.prepareStatement("Select * from studentdb.student;"); 
			resultSet = ps.executeQuery();
			writeResultSet(resultSet);
		}
		catch(Exception e){
			throw(e);
			
		}finally {
            close();
        }
		
	}
	private void writeResultSet(ResultSet resultSet) throws SQLException {
        // ResultSet is initially before the first data set
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
            System.out.print(resultSet.getMetaData().getColumnName(i)+" ");
        }
        System.out.println();
        while (resultSet.next()) {
            // It is possible to get the columns via name
            // also possible to get the columns via the column number
            // which starts at 1
            // e.g. resultSet.getSTring(2);
        	
            int user = resultSet.getInt("studentID");
            String name = resultSet.getString("name");
            System.out.print(" "+user + " ");
            System.out.println(" " + name+" ");
            
        }
	}
	private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {

        }
    }
}
