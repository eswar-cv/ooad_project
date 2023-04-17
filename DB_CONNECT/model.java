import java.sql.Connection;
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

	public ResultSet get_user_assignments(String username, int c_id) throws Exception
	{
		try{

			assignment x = new assignment();
			ResultSet y = x.get_assignment(username,c_id);
			return y;
		}
		catch(Exception e){
			throw(e);
			
		}
		finally {
            close();
        } 
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
        	List<List<String>> y = x.course("lavu");
        	System.out.println(y);
        } catch (Exception e) {
           throw(e);
        }
        
    }

}
