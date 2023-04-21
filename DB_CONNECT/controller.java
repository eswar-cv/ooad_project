import java.awt.event.*;
import java.sql.Connection;
import java.util.*; 
import java.sql.* ; 
/**
 * This is the Controller in the MVC model. This will act
 * as the communication between the Model(data) and View.
 * This can manipulate, update the values of the Model when
 * an action is performed from the View. Also, this is where
 * you add your listeners to your view in order that it will
 * fire an event, which can update values in your model, or
 * update your view.
 */
 
public class Controller {

    private View view;
    private Model model;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view; 
        view = view.set_controller(this);
        view = view.show_login();
        //view = view.show_dashboard();
    }

    void validate_now(String username, String password) {
        try{
            ArrayList<String> li = model.validate(username, password);
            if (li.size() == 3) {
                view.username = username;
                view.password = password;
                view.type = li.get(2);
                view = view.show_dashboard();
            }
            if(li.size()==2)
            {
                view=view.show_login();
            }
            System.out.println(li);
        }
        catch(Exception e){
			System.out.println(e);
		}
        view = view.refresh_frame();
        
    }

    List<List<String>> get_classes(String username)
    {
        List<List<String>>res=new ArrayList<>();
        List<String> x= new ArrayList<>();
        x.add("");
        x.add("");
        
        try{
            res = model.course(username);
            System.out.println(res);
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return res;
    }
    ResultSet get_assgn(String username,int c_id)
    {
        ResultSet res=null;
        
        try{
            res = model.get_user_assignments(username,c_id);
            return res;
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return res;
    }

    void logout() {
        view.username = "null";
        view.password = "null";
        view = view.show_login();
    }
}