import java.sql.PreparedStatement;
import java.util.* ;
import java.sql.* ; 


public class assignment extends Model{
    public List<List<String>> get_assignment(String username,int c_id) throws Exception

	{
        List<List<String>> res=new ArrayList<>();
		try{

			String SELECT_QUERY = "SELECT DISTINCT ASSGN_ID,assignment.c_id, deadline, instruc from assignment, acc_course where assignment.c_id = ? and acc_course.u_name = ?;";
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setInt(1, c_id);
            ps.setString(2, username);
			resultSet = ps.executeQuery();
            while(resultSet.next())
			{
				ArrayList<String> x = new ArrayList<>();
				x.add(String.valueOf(resultSet.getString("ASSGN_ID")));
				x.add(String.valueOf(resultSet.getInt("c_id")));
				x.add(resultSet.getString("deadline"));
                x.add(resultSet.getString("instruc"));
				res.add(x);
			}
		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
		return res;
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
