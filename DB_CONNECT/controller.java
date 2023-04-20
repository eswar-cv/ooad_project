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
        // view = view.show_login();
        view = view.show_dashboard();
    }

    void validate_now(String username, String password) {
        try{
            ArrayList<String> li = model.validate(username, password);
            if (li.size() == 3) {
                view.username = username;
                view.password = password;
                view = view.show_dashboard();
            }
            System.out.println(li);
        }
        catch(Exception e){
			System.out.println(e);
		}
        view = view.refresh_frame();
        
    }

    void logout() {
        view.username = "null";
        view.password = "null";
        view = view.show_login();
        
    }

}