import java.sql.PreparedStatement;
import java.util.* ;
import java.sql.* ; 


public class assignment extends Model{
    public ResultSet get_assignment(String username,int c_id) throws Exception

	{
		try{
			String SELECT_QUERY = "SELECT ASSGN_ID,c_id, deadline, assgn_file, instruc from assignment, acc_course where assignment.c_id = ? and acc_course.u_name = ?;";
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setInt(1, c_id);
            ps.setString(2, username);
			resultSet = ps.executeQuery();
		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
		return resultSet;
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
