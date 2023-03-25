import java.sql.PreparedStatement;
import java.util.ArrayList;

public class user_validate extends model {
    
    public ArrayList<String> validate(String uname,String Password)throws Exception
	{
		try{
			ArrayList <String> x= new ArrayList<>();
			String SELECT_QUERY = "SELECT * FROM account where user_name=? and password=?;";
			PreparedStatement ps = connection.prepareStatement(SELECT_QUERY);
			ps.setString(1, uname);
            ps.setString(2, Password);
			resultSet = ps.executeQuery();
			if(resultSet.next())
			{
				x.add(resultSet.getString("user_name"));
				x.add(resultSet.getString("password"));
				return x;
			}
			else 
			{
				x.add("null");
				x.add("null");
				return x;
			}
		}
		catch(Exception e){
			throw(e);
			
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
