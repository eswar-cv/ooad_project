import java.sql.PreparedStatement;
import java.util.* ;
import java.sql.* ; 

    public class get_stud_teacher extends Model
    {
        public  List<List<String>> stud_teacher(String t_name) throws Exception
	    {
			List<List<String>> res = new ArrayList<>();
            try{
                String SELECT_QUERY = "select name from account, stud_teacher where teach_name=? and stud_name = USER_NAME;";
                PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
                ps.setString(1, t_name);
                resultSet = ps.executeQuery();
                while(resultSet.next())
			    {
				ArrayList<String> x = new ArrayList<>();
				x.add(resultSet.getString("name"));
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
