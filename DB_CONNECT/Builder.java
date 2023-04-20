import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class Builder {
    int y_point = 50;
    View view;
    Font font_default_heading = new Font("verdana", 0, 20);
    Font font_default_basic = new Font("verdana", 0, 16);
    Font font_default_normal = new Font("verdana", 0, 14);


    Builder(View view) {
        this.view = view;
    }
    JLabel get_label(String text, Dimension dims, Dimension pos) {
        JLabel label;
        label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(pos.width, pos.height, dims.width, dims.height);
        label.setFont(font_default_normal);
        return label;
    }

    JTextField get_textfield(Dimension dims, Dimension pos) {
        JTextField label;
        label = new JTextField();
        label.setBounds(pos.width, pos.height, dims.width, dims.height);
        label.setFont(font_default_normal);
        return label;
    }

    JPasswordField get_textfield_p(Dimension dims, Dimension pos) {
        JPasswordField label;
        label = new JPasswordField(SwingConstants.CENTER);
        label.setBounds(pos.width, pos.height, dims.width, dims.height);
        label.setFont(font_default_normal);
        return label;
    }

    JButton get_button(String text, Dimension dims, Dimension pos) {
        JButton button;
        button = new JButton(text);
        button.setBounds(pos.width, pos.height, dims.width, dims.height);
        button.setFont(font_default_normal);
        button.setBackground(Color.white);
        return button;
    }

    JPanel get_login_page(Dimension dims, Dimension dm, View view){
        JPanel panel = get_panel(view);
        panel.setLayout(null);
        panel.setBounds(0, 0, dims.width, dims.height);        
        int x_point = get_position(0, 250, 30, dims).width;
        JLabel login_text = get_label("username", dm, new Dimension(x_point, y_point + 70));
        JTextField login_username = get_textfield(dm, new Dimension(x_point, y_point + 100));
        JLabel passsword_text = get_label("password", dm, new Dimension(x_point, y_point + 170));
        JPasswordField password = get_textfield_p(dm, new Dimension(x_point, y_point + 200));
        JButton login_button = get_button("Login", dm, new Dimension(x_point, y_point + 300));
        panel.add(login_button);
        login_button.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                // System.out.println(password.getPassword());
                view.controller.validate_now(login_username.getText(), new String(password.getPassword()));
            }  
        });
        panel.add(login_text);
        panel.add(login_username);
        panel.add(passsword_text);
        panel.add(password);
        return panel;
    }

    JPanel get_dashboard(View view) {
        JPanel panel = get_panel(view);
        return panel;
    }

    JPanel get_panel(View view) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        // System.out.println(this.view.frame.getWidth() + " " + this.view.frame.getHeight());
        panel.setBounds(0, 0, this.view.frame.getWidth(), this.view.frame.getHeight());
        panel.setBackground(Color.green);
        JPanel header = get_header(view);
        
        // System.out.println("header " + header);
        panel.add(header);
        return panel;
    }

    JPanel get_header(View view) {
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setLayout(null);
        // System.out.println(this.view.frame.getWidth() + " " + this.view.frame.getHeight());
        panel.setBounds(0, 0, view.frame.getWidth(), 50);
        String btext;
        // System.out.println(view.frame.getWidth());
        if (view.username != "null") btext = "Logout";
        else btext = "Login";
        JButton lilo = new JButton(btext);
        lilo.setBounds(0, 0, 100, 50);
        lilo.setBackground(Color.white);
        lilo.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                if (view.username != "null") view.controller.logout();
                else view.show_login();
            }  
        });
        panel.add(lilo);
        Dimension cdim = get_position(10, 300, 30, this.view.frame.getSize());
        String uname;
        if (view.username != "null" && view.username != null) uname = view.username;
        else uname = "Login to access the portal";
        JLabel username = get_label(uname, new Dimension(300, 30), cdim);
        username.setFont(new Font("verdana", 0, 20));
        panel.add(username);
        return panel;
    }

    Dimension get_position(float posy, float elem_width, float elem_height, Dimension dims) {
        float x = (dims.width - elem_width) / 2;
        float y;
        if (posy > 0) {
            y = posy;
        }
        else {
            y = (dims.height - elem_height) / 2;
        }
        Dimension dim = new Dimension();
        dim.setSize(x, y);
        return dim;
    }
}