import java.awt.event.*;
import java.awt.*;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import javax.swing.event.*;

public class View {
    JFrame frame;
    Dimension pos;
    String username = "null";
    String password = "null";
    String type = "null";
    static Controller controller;
    Builder builder = new Builder(this);

    public View() { 
        frame = new JFrame("SubmitEase");
        frame.setSize(1200, 860);
        frame.setResizable(false);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    View refresh_frame() {
        frame.invalidate();
        frame.validate();
        frame.repaint();
        frame.setVisible(true);
        return this;
    }
    View show_dashboard() {

        if(this.username.equals("null"))
        {
            return this.show_login();
        }
        else 
        {
            frame.getContentPane().removeAll();
            System.out.println("u " + username + " p " + password);
            Dimension dm = new Dimension(250, 40);
            if(this.type.equals("student"))
            {
                JPanel stud_dash = builder.get_stud_dash(frame.getSize(),dm,this);
                frame.add(stud_dash);
                refresh_frame();
            }
            if(this.type.equals("teacher"))
            {
                JPanel stud_dash = builder.get_teach_dash(frame.getSize(),dm,this);
                frame.add(stud_dash);
                refresh_frame();
            }

            return this;
        }
        
        
    }
    public View set_controller(Controller ctrler) {
        this.controller = ctrler;
        return this;
    }

    View show_login() {
        frame.getContentPane().removeAll();
        Dimension dm = new Dimension(250, 40);
        System.out.println(frame.getSize());
        JPanel login_panel = builder.get_login_page(frame.getSize(), dm, this);
        // System.out.println(login_panel);
        frame.add(login_panel);
        refresh_frame(); 
        return this; 
    }
    View add_elem(JPanel x)
    {
        frame.getContentPane().removeAll();
        Dimension dm = new Dimension(250, 40);
        frame.add(x);
        refresh_frame();
        return this;

    }
}