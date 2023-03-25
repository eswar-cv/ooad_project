import java.sql.PreparedStatement;
import java.util.* ;
import java.sql.* ; 


public class get_sub_eval extends model{
    public ResultSet sub_eval(int c_id) throws Exception

	{
		try{
			String SELECT_QUERY = "SELECT s_id, A_ID, account.name, comments, sub_file from submsissions,account,acc_course where stud_name = account.user_name and c_id = ? ;";
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setInt(1, c_id);
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
