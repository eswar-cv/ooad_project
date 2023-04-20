import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class View {
    JFrame frame;
    Dimension pos;
    String username = "null";
    String password = "null";
    Controller controller;
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
        frame.getContentPane().removeAll();
        JPanel dashboard = builder.get_dashboard(this);
        System.out.println("u " + username + " p " + password);
        frame.add(dashboard);
        refresh_frame();
        return this;
        
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
}