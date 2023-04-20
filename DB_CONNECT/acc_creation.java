import java.sql.PreparedStatement;
import java.util.* ;
import java.sql.* ; 


    public class acc_creation extends Model
    {
        public  Boolean create_account(String name, String username, String password, String type) throws Exception
	    {
			try{
                String SELECT_QUERY = "INSERT INTO ACCOUNT VALUES (?,?,?,?);";
                PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
                ps.setString(1, name);
                ps.setString(2, username);
                ps.setString(3, password);
                ps.setString(4, type);
                ps.executeUpdate();
                return true; 
    
            }
            catch(Exception e){
                return false;
                
            }
            finally {
                close();
            } 
	}
    private void close() 
    {
        try 
        {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
           e.printStackTrace();
        }

    }
}
