import java.sql.PreparedStatement;
import java.util.* ;
import java.sql.* ; 

    public class get_course extends model
    {
        public  List<List<String>> course(String uname) throws Exception
	    {
			List<List<String>> res = new ArrayList<>();
		try{
			String SELECT_QUERY = "select c_name, c_desc from course, acc_course where acc_course.c_id = course.Course_id and u_name = ?;";
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, uname);
			resultSet = ps.executeQuery();
			while(resultSet.next())
			{
				ArrayList<String> x = new ArrayList<>();
				x.add(resultSet.getString("c_name"));
				x.add(resultSet.getString("c_desc"));
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
