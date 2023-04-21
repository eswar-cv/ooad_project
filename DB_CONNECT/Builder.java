import java.awt.event.*;
//import java.awt.*;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

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
                View.controller.validate_now(login_username.getText(), new String(password.getPassword()));
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
    JPanel get_stud_dash(Dimension dims,Dimension dm,View view)
    {

        JPanel dash=get_dashboard(view);
        int x_point = get_position(0, 250, 30, dims).width;
        JButton classes = get_button("View Classes",dm,new Dimension(x_point, y_point+300));
        dash.add(classes);
        classes.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                // System.out.println(password.getPassword());
                List<List<String>> res;
                res=View.controller.get_classes(view.username);
                System.out.println("returned");
                JPanel dash1 = get_class_panel(dims,dm,view,res);
                view.add_elem(dash1);
                //System.out.println("refereshed");
            }  
        }); 

        return dash;
    }
    JPanel get_class_panel(Dimension dims,Dimension dm,View view,List<List<String>> res)
    {
        int z=100;
        int y=150;
        JPanel dash =get_dashboard(view);
        System.out.println("Came to get class panel");
        for (List<String> list : res) {
            int i=0;
            String button="", desc="",c_id="";
            for (String item : list) {
                if(i==0)
                {
                    c_id= item;  
                }
                else if(i==1)
                {
                    button = item;
                }
                else 
                {
                    desc = item;
                }

                i++;
            }
            int x_point = get_position(0, 250, 30, dims).width;
            JLabel c = get_label(c_id,dm,new Dimension(x_point, y_point + y));
            JButton classes = get_button(button,dm,new Dimension(x_point, y_point+z));
            String desc1="<html>"+desc+"</html>";
            JLabel descr = get_label(desc1,dm, new Dimension(x_point, y_point + y+20));
            
            z+=120;
            y+=120;
            classes.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    // System.out.println(password.getPassword());
                    System.out.println("Clicked on the course: "+c.getText());
                    ResultSet res=View.controller.get_assgn(view.username,Integer.parseInt(c.getText()));
                        try {
                            JPanel dash1 = get_assgn_panel(dims,dm,view,res);
                            view.add_elem(dash1);
                        } catch (Exception e1) {
                            
                            e1.printStackTrace();
                        }
                }  
            }); 
            dash.add(c);
            dash.add(classes);
            dash.add(descr);
            
            
        }
        return dash;
    }
    //NEEDS CHANGES
    JPanel get_assgn_panel(Dimension dims,Dimension dm,View view,ResultSet res) throws Exception
    {
        int z=100;
        int y=150;
        JPanel dash =get_dashboard(view);
        System.out.println("Came to get assignment panel");
        File file = new File("output.pdf");
        try{
            try (FileOutputStream output = new FileOutputStream(file)) {
                try{
                    while(res.next())
                    {
                        //ASSGN_ID,c_id, deadline, assgn_file, instruc
                        InputStream input = res.getBinaryStream("assgn_file");
                        byte[] buffer = new byte[1024];
                        while (input.read(buffer) > 0) {
                            output.write(buffer);
                        }
                        int x_point = get_position(0, 250, 30, dims).width;
                        int a_id = res.getInt("ASSGN_ID");
                        int c_id = res.getInt("c_id");
                        String instruc = res.getString("instruc");
                        Timestamp d = res.getTimestamp("deadline");
                        JLabel c = get_label(Integer.toString(c_id),dm,new Dimension(x_point, y_point + y));
                        
                        //String desc1="<html>"++"</html>";
                        //JLabel descr = get_label(desc1,dm, new Dimension(x_point, y_point + y+20));
                        //dash.add()
   
                    }
                }
                catch(SQLException e)
                {
                    int x_point = get_position(0, 250, 30, dims).width;
                    JLabel c = get_label("No Assignments!",dm,new Dimension(x_point, y_point + y));
                    dash.add(c);
                    return dash;
                }
            }
        }
        catch(Exception e)
        {
            int x_point = get_position(0, 250, 30, dims).width;
            JLabel c = get_label("No Assignments!",dm,new Dimension(x_point, y_point + y));
            dash.add(c);
            return dash;
        }
            int x_point = get_position(0, 250, 30, dims).width;
            JLabel c = get_label("Should add files",dm,new Dimension(x_point, y_point + y));
            dash.add(c);
            return dash;
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

        JButton home = new JButton("Home");
        home.setBounds(101, 0, 100, 50);
        home.setBackground(Color.white);
        home.addActionListener(new ActionListener(){  
            public void actionPerformed(ActionEvent e){  
                view.show_dashboard();
            }  
        });
        panel.add(lilo);
        panel.add(home);
        Dimension cdim = get_position(10, 300, 30, this.view.frame.getSize());
        String uname;
        if (view.username != "null" && view.username != null) uname = view.username + " - " + view.type;
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