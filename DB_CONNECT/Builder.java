import java.awt.event.*;
//import java.awt.*;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.Dimension;
import java.awt.Color;

import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import javax.swing.event.*;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

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
                JPanel dash1 = get_stud_class_panel(dims,dm,view,res);
                view.add_elem(dash1);
                //System.out.println("refereshed");
            }  
        }); 

        return dash;
    }
    JPanel get_stud_class_panel(Dimension dims,Dimension dm,View view,List<List<String>> res)
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
                    List<List<String>>  res=View.controller.get_assgn(view.username,Integer.parseInt(c.getText()));
                        try {
                            JPanel dash1 = get_stud_assgn_panel(dims,dm,view,res);
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
    JPanel get_teach_class_panel(Dimension dims,Dimension dm,View view,List<List<String>> res)
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
                    List<List<String>>  res=View.controller.get_teach_assgn(Integer.parseInt(c.getText()));
                        try {
                            JButton x = new JButton("Add assignment");
                            x.setBounds(150, 100, 200, 50);
                            x.setBackground(Color.white);
                            x.addActionListener(new ActionListener(){  
                                public void actionPerformed(ActionEvent e){  
                                    JTextArea instruc = new JTextArea(100,100);
                                    int x_point = get_position(0, 500, 300, dims).width;
                                    String str="<html>Enter Instructions</html>";
                                    JLabel in = get_label(str, dims, dm);
                                    in.setBounds(x_point, 100, 100,300);
                                    instruc.setBounds(x_point+150, 100, 500,300);
                                    str="<html>Enter Deadline</html>";
                                    JLabel time = get_label(str,dims, dm);
                                    time.setBounds(x_point,450 , 100,50);
                                    JTextField t = get_textfield(dims, dm);
                                    t.setBounds(x_point+150,450 , 500,50);
                                    JButton submit = get_button("Submit", dims, dm);
                                    submit.setBounds(x_point+150,510 , 100,50);
                                    submit.addActionListener(new ActionListener() {
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            String text=instruc.getText();
                                            //String time = t.getText();
                                            view.controller.add_assgn(text,"",Integer.parseInt(c.getText()));
                                            JPanel dash1 =get_teach_dash(dims, dm, view);
                                            view.add_elem(dash1);
                                        }
                                    });
                                    JPanel dash1 = get_dashboard(view);
                                    dash1.add(in);
                                    dash1.add(instruc);
                                    //dash1.add(time);
                                    //dash1.add(t);
                                    dash1.add(submit);
                                    view.add_elem(dash1);
                                    //view.controller.add_assgn(c.getText(),desc,deadline);
                                }  
                            });
                            JPanel dash1 = get_teach_assgn_panel(dims,dm,view,res);
                            dash1.add(x);
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

    JPanel get_teach_assgn_panel(Dimension dims,Dimension dm,View view, List<List<String>> res) throws Exception
    {
        JPanel dash = get_dashboard(view);
        System.out.println("Came to get teacher assignment panel");
        int z=100;
        int y=150;
        int x= 200;
        try{
            //assgn_id,deadline, instruc
            for (List<String> list : res) {
                int i=0;
                String instruc="", deadline="",a_id="",c_id="";
                for (String item : list) {
                    if(i==0)
                    {
                        a_id= item;  
                    }
                    else if(i==1)
                    {
                        deadline = item;
                    }
                    else if(i==2)
                    {
                        instruc= item;
                    }
                    else 
                    {
                        c_id = item;
                    }

                    i++;
                }
            int x_point = get_position(0, 250, 30, dims).width;
            JLabel a = get_label(a_id,dm,new Dimension(x_point, y_point + y));
            JTextArea ind=new JTextArea(100,100);
            JLabel c=get_label(c_id,dm,new Dimension(x_point, y_point + y));
            ind.setText(instruc);
            JButton button = get_button("Open", dims, dm);
            button.setBounds(x_point,y_point+x , 100,30);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e){  
                    List<List<String>> students = view.controller.get_stud_assgn(Integer.parseInt(c.getText()),Integer.parseInt(a.getText()));
                    int x=100;
                    System.out.println("Clicked");
                    JPanel dash2 = get_dashboard(view);
                    for (List<String> list : students) {
                        int i=0;
                        String comments="", stud_name="";
                        for (String item : list) {
                            if(i==0)
                            {
                               comments = list.get(0); 
                            }
                            else if(i==1)
                            {
                                stud_name = list.get(1);
                            }
        
                            i++;
                        }
                        
                        JLabel stud_name1 = get_label(stud_name, dims, dm);
                        JButton button = get_button(stud_name, dims, dm);
                        button.setBounds(x_point,y_point+x , 100,30);
                        JLabel comm = get_label(comments, dims, dm);
                        x+=100;
                        dash2.add(button);
                        view.add_elem(dash2);
                        button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e)
                            {
                                int x_point = get_position(0, 250, 30, dims).width;
                                JPanel dash3 = get_dashboard(view);
                                String x="<html>"+comm.getText()+"</html>";
                                JLabel subbb = get_label("Submission: ",dims,dm);
                                subbb.setBounds(x_point, 100, 100, 300);
                                JLabel zz= get_label(x, dims, dm);
                                zz.setBounds(x_point+50, 100, 500,300);
                                JTextField marks = get_textfield(dims, dm);
                                int m=0;
                                try {
                                     m = view.controller.get_marks(stud_name1.getText(),Integer.parseInt(c.getText()),Integer.parseInt(a.getText()));
                                } catch (NumberFormatException e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                } catch (Exception e1) {
                                    // TODO Auto-generated catch block
                                    e1.printStackTrace();
                                }
                                JLabel mar = get_label("Enter marks", dims, dm);
                                mar.setBounds(x_point, 450, 100,50);
                                marks.setBounds(x_point+100, 450, 500,50);
                                marks.setText(String.valueOf(m));
                                JButton b = get_button("Submit", dims, dm);
                                b.setBounds(x_point+100, 600, 500,50);
                                //JLabel x = get_label(, dims, dm)
                                dash3.add(zz);
                                dash3.add(marks);
                                dash3.add(mar);
                                dash3.add(subbb);
                                dash3.add(b);
                                b.addActionListener(new ActionListener() {
                                    public void actionPerformed(ActionEvent e)
                                    {
                                        try {
                                            view.controller.enter_marks(stud_name1.getText(),Integer.parseInt(marks.getText()),Integer.parseInt(c.getText()),Integer.parseInt(a.getText()));
                                            view.add_elem(get_dashboard(view));
                                        } catch (NumberFormatException e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                        } catch (Exception e1) {
                                            // TODO Auto-generated catch block
                                            e1.printStackTrace();
                                        }
                                    }
                                });
                                view.add_elem(dash3);

                            }
                        });
                    }

                }
            });
            dash.add(a);
            dash.add(button);
            z+=120;
            y+=120;
            x+=120;
           /*  button.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    // System.out.println(password.getPassword());
                    System.out.println("Clicked on the assignmnet: "+a.getText());
                    JPanel dash1 =get_dashboard(view);
                    JLabel course_name = get_label("Course ID",dm,new Dimension(x_point, y_point ));
                    //JLabel course = get_label(c.getText(),dm,new Dimension(x_point+50, y_point ));
                    JLabel a_name = get_label("Assignment ID",dm,new Dimension(x_point, y_point +50));
                    JLabel a_ = get_label(a.getText(),dm,new Dimension(x_point+70, y_point +50));
                    JLabel in_name = get_label("Instructions",dm,new Dimension(x_point, y_point +100));
                    JTextArea in= new JTextArea(100,100);
                    in.setText(ind.getText());
                    in.setBounds(x_point+175,y_point+100,500,200);
                    JLabel sub_name = get_label("Submission",dm,new Dimension(x_point, y_point +400));
                    List<String> sub_xx=new ArrayList<>();
                    try {
                        sub_xx = View.controller.get_sub_stud(view.username,Integer.parseInt(course.getText()),Integer.parseInt(a_.getText()));
                    }
                    catch (Exception e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    JTextArea sub = new JTextArea(100,100);
                    sub.setBounds(x_point+175,y_point+400,500,300);
                    sub.setText(sub_xx.get(0));
                    JLabel marks = get_label("Marks "+sub_xx.get(1),dm,new Dimension(50, 500));
                    JButton button = get_button("Submit", dims, dm);
                    button.setBounds(x_point,y_point+450 , 100,30);
                    dash1.add(course);
                    dash1.add(course_name);
                    dash1.add(a_name);
                    dash1.add(a_);
                    dash1.add(button);
                    dash1.add(sub_name);
                    dash1.add(sub);
                    dash1.add(in_name);
                    dash1.add(in);
                    dash1.add(marks);
                    view.add_elem(dash1);
                    button.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e){  
                            System.out.println("Clicked on submit assignment: "+a.getText());
                            view.controller.submit_assgn(view.username,Integer.parseInt(course.getText()),Integer.parseInt(a_.getText()),sub.getText());
                            view.show_dashboard();
                            
                        }
                    });

                }  
            }); */
            
        }
        return dash;
        }
        catch(Exception e)
        {
            throw(e);
        }
    }
    JPanel get_teach_dash(Dimension dims,Dimension dm,View view)
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
                JPanel dash1 = get_teach_class_panel(dims,dm,view,res);
                view.add_elem(dash1);
                //System.out.println("refereshed");
            }  
        }); 

        return dash;
    }
    

    JPanel get_stud_assgn_panel(Dimension dims,Dimension dm,View view, List<List<String>> res) throws Exception
    {

        JPanel dash =get_dashboard(view);
        System.out.println("Came to get assignment panel");
        int z=100;
        int y=150;
        int x= 200;
        try{
            
                for (List<String> list : res) {
                    int i=0;
                    String instruc="", deadline="",a_id="";
                    String c_id="";
                    for (String item : list) {
                        if(i==0)
                        {
                            a_id= item;  
                        }
                        else if(i==1)
                        {
                            c_id = item;
                        }
                        else if(i==2)
                        {
                            deadline = item;
                        }
                        else 
                        {
                            instruc=item;
                        }
    
                        i++;
                    }
                int x_point = get_position(0, 250, 30, dims).width;
                JLabel c = get_label(c_id,dm,new Dimension(x_point, y_point + z));
                JLabel a = get_label(a_id,dm,new Dimension(x_point, y_point + y));
                JTextArea ind=new JTextArea(100,100);
                ind.setText(instruc);
                JButton button = get_button("Open", dims, dm);
                button.setBounds(x_point,y_point+x , 100,30);
                dash.add(c);
                dash.add(a);
                dash.add(button);
                z+=120;
                y+=120;
                x+=120;
                button.addActionListener(new ActionListener(){  
                    public void actionPerformed(ActionEvent e){  
                        // System.out.println(password.getPassword());
                        System.out.println("Clicked on the assignmnet: "+a.getText());
                        JPanel dash1 =get_dashboard(view);
                        JLabel course_name = get_label("Course ID",dm,new Dimension(x_point, y_point ));
                        JLabel course = get_label(c.getText(),dm,new Dimension(x_point+50, y_point ));
                        JLabel a_name = get_label("Assignment ID",dm,new Dimension(x_point, y_point +50));
                        JLabel a_ = get_label(a.getText(),dm,new Dimension(x_point+70, y_point +50));
                        JLabel in_name = get_label("Instructions",dm,new Dimension(x_point, y_point +100));
                        JTextArea in= new JTextArea(100,100);
                        in.setText(ind.getText());
                        in.setBounds(x_point+175,y_point+100,500,200);
                        JLabel sub_name = get_label("Submission",dm,new Dimension(x_point, y_point +400));
                        List<String> sub_xx=new ArrayList<>();
                        try {
                            sub_xx = View.controller.get_sub_stud(view.username,Integer.parseInt(course.getText()),Integer.parseInt(a_.getText()));
                        }
                        catch (Exception e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        JTextArea sub = new JTextArea(100,100);
                        sub.setBounds(x_point+175,y_point+400,500,300);
                        sub.setText(sub_xx.get(0));
                        JLabel marks = get_label("Marks "+sub_xx.get(1),dm,new Dimension(50, 500));
                        JButton button = get_button("Submit", dims, dm);
                        button.setBounds(x_point,y_point+450 , 100,30);
                        dash1.add(course);
                        dash1.add(course_name);
                        dash1.add(a_name);
                        dash1.add(a_);
                        dash1.add(button);
                        dash1.add(sub_name);
                        dash1.add(sub);
                        dash1.add(in_name);
                        dash1.add(in);
                        dash1.add(marks);
                        view.add_elem(dash1);
                        button.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e){  
                                System.out.println("Clicked on submit assignment: "+a.getText());
                                view.controller.submit_assgn(view.username,Integer.parseInt(course.getText()),Integer.parseInt(a_.getText()),sub.getText());
                                view.show_dashboard();
                                
                            }
                        });
    
                    }  
                }); 
                
            }
        return dash;
        }
        catch(Exception e)
        {
            throw(e);
        }
        }

        
    JPanel get_panel(View view) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        // System.out.println(this.view.frame.getWidth() + " " + this.view.frame.getHeight());
        panel.setBounds(0, 0, this.view.frame.getWidth(), this.view.frame.getHeight());
        //panel.setBackground(Color.green);
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