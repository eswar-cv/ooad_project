import java.util.*; 
import java.sql.* ; 

public class Model {
	static ResultSet resultSet = null;
	static Connection connection = Connect_DB.createConnection();
	/***********VALIDATING ACCOUNT********************/
	public ArrayList<String> validate(String uname,String Password) throws Exception {
		try{
			user_validate x = new user_validate();
			return x.validate(uname, Password);
		}
		catch(Exception e){
			throw(e);
		}
		finally {
            close();
        } 
	}

	/************** GEt COURSE DETAILS *********************/
 
	public List<List<String>> course(String uname) throws Exception
	{
		try{

			get_course x = new get_course();
			List<List<String>> res = x.course(uname);
			return res; 

		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
	}


	/******************** Get student for each teacher ***********************/

	public List<List<String>> stud_teacher(String t_name) throws Exception
	{ 
		try{

			get_stud_teacher x = new get_stud_teacher();
			List<List<String>> res = x.stud_teacher(t_name);
			return res; 

		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
	}
	

	/*************** Get submsissions for evaluation  *****************/


	public ResultSet sub_eval(int c_id) throws Exception{
		try{

	         get_sub_eval x=new get_sub_eval();
			 return x.sub_eval(c_id);

		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
		
	}

	/******************************** Creating accounts (Admin function) ******************************/
	public Boolean create_account(String name, String username, String password, String type) throws Exception
	{
		try{

			acc_creation x = new acc_creation();
			Boolean y = x.create_account(name, username, password, type);
			return y;

		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
	}

	/******************************** User assignments******************************/

	public List<List<String>> get_user_assignments(String username, int c_id) throws Exception
	{
		try{

			assignment x = new assignment();
			List<List<String>> y = x.get_assignment(username,c_id);
			return y;
		}
		catch(Exception e){
			throw(e);
			
		}
		finally { 
            close();
        } 
	}

	public List<List<String>> get_teach_assignments(int c_id) throws Exception
	{
		List<List<String>> y = new ArrayList<>();
		try{
			String Query = "select assgn_id, deadline, instruc from assignment where c_id=?;";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setInt(1,c_id);
			resultSet = ps.executeQuery();
			while(resultSet.next())
			{
				String x=String.valueOf(resultSet.getInt("assgn_id"));
				String deadlinee = resultSet.getString("deadline");
				String instruc = resultSet.getString("instruc");
				ArrayList<String> listt = new ArrayList<>();
				listt.add(x);
				listt.add(deadlinee);
				listt.add(instruc);
				listt.add(String.valueOf(c_id));
				y.add(listt);
				
			}
		}
		catch(Exception e){
			throw(e);
			
		}
		finally { 
            close();
        } 
		return y;
	}

	public List<List<String>> get_stud_assgn(int c_id,int a_id) throws Exception
	{
		List<List<String>> y = new ArrayList<>();
		try{
			String Query = "Select stud_name,comments from submission where c_id=? and a_id=?;";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setInt(1,c_id);
			ps.setInt(2,a_id);
			resultSet = ps.executeQuery();
			while(resultSet.next())
			{
				String deadlinee = resultSet.getString("comments");
				String instruc = resultSet.getString("stud_name");
				ArrayList<String> listt = new ArrayList<>();
				listt.add(deadlinee);
				listt.add(instruc);
				y.add(listt);
				
			}
		}
		catch(Exception e){
			throw(e);
			
		}
		finally { 
            close();
        } 
		return y;
	}
	void add_assignments(String instruc, String Deadline, int c_id) throws SQLException
	{
		try{
			String Query = "Insert into assignment(c_id,deadline,instruc) values(?,?, ?);";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setInt(1,c_id);
			ps.setString(2, Deadline);
			ps.setString(3, instruc);
			int x=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw(e);
		}
		finally{
			close();
		}
		
	}
	void enter_marks(String username,int marks, int c_id, int a_id) throws SQLException
	{
		try{
			String Query = "Update submission set results= ?  where stud_name=? and c_id =? and a_id=?;";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setInt(1,marks);
			ps.setString(2,username);
			ps.setInt(3,c_id);
			ps.setInt(4,a_id);
			int x=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw(e);
		}
		finally{
			close();
		}
	}
	void add_sub(String user_name, int c_id, int a_id, String comments) throws SQLException
	{
		try{
			String delete ="Delete from submission where stud_name=? and c_id=? and a_id=?;";
			PreparedStatement ps1 = connection.prepareStatement(delete);
			ps1.setInt(2,c_id);
			ps1.setString(1, user_name);
			ps1.setInt(3,a_id);
			int x=ps1.executeUpdate();
			String Query = "Insert into submission(c_id,stud_name,a_id,comments) values(?,?, ?,?);";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setInt(1,c_id);
			ps.setString(2, user_name);
			ps.setInt(3,a_id);
			ps.setString(4, comments);
			int y=ps.executeUpdate();
		}
		catch(SQLException e)
		{
			throw(e);
		}
		finally{
			close();
		}
	}
	
	List<String> get_sub_stud(String username,int c_id,int a_id) throws SQLException
	{
		List<String> y =new ArrayList<String>();

		try{
			String Query = "Select comments,results from submission where stud_name=? and c_id=? and a_id=?;";
			PreparedStatement ps = connection.prepareStatement(Query);
			ps.setInt(2,c_id);
			ps.setString(1, username);
			ps.setInt(3,a_id);
			resultSet = ps.executeQuery();
			while(resultSet.next())
			{
				String x=resultSet.getString("comments");
				String z= String.valueOf(resultSet.getInt("results"));
				y.add(x);
				y.add(z);
				
			}
		}
		catch(SQLException e)
		{
			throw(e);
		}
		finally{
			close();
		}
		y.add("");
		y.add("");
		return y;
	}
	int get_marks(String username, int c_id,int a_id) throws SQLException
	{
		int x=0;
		try{
			String delete ="Select results from submission where stud_name=? and c_id = ? and a_id=?;";
			PreparedStatement ps1 = connection.prepareStatement(delete);
			ps1.setInt(2,c_id);
			ps1.setString(1, username);
			ps1.setInt(3,a_id);
			resultSet=ps1.executeQuery();
			while(resultSet.next())
			{
				x=resultSet.getInt("results");
			}
			
		}
		catch(SQLException e)
		{
			throw(e);
		}
		finally{
			close();
		}
		return x;
	}
	/*private void writeResultSet(ResultSet resultSet) throws SQLException {
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
	}*/
private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {

        }

    }

	public static void main(String [] args) throws Exception
    {
        try {
			Model x = new Model();
        	ArrayList<String> y = x.validate("lavu", "lavu");
        	System.out.println(y);
        } catch (Exception e) {
           throw(e);
        }
        
    }

}
