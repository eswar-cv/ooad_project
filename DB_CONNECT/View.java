import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

class Builder {
    int y_point = 50;
    JLabel get_label(String text, Dimension dims, Dimension pos) {
        JLabel label;
        label = new JLabel(text, SwingConstants.CENTER);
        label.setBounds(pos.width, pos.height, dims.width, dims.height);
        return label;
    }

    JTextField get_textfield(Dimension dims, Dimension pos) {
        JTextField label;
        label = new JTextField();
        label.setBounds(pos.width, pos.height, dims.width, dims.height);
        return label;
    }

    JPasswordField get_textfield_p(Dimension dims, Dimension pos) {
        JPasswordField label;
        label = new JPasswordField(SwingConstants.CENTER);
        label.setBounds(pos.width, pos.height, dims.width, dims.height);
        return label;
    }

    JButton get_button(String text, Dimension dims, Dimension pos) {
        JButton button;
        button = new JButton(text);
        button.setBounds(pos.width, pos.height, dims.width, dims.height);
        return button;
    }

    JPanel get_login_page(Dimension dims, Dimension dm){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, dims.width, dims.height);        
        
        JLabel login_heading = get_label("Login", dm, new Dimension(x_point, y_point));
        JTextField login_username = get_textfield(dm, new Dimension(x_point, y_point + 100));
        JPasswordField password = get_textfield_p(dm, new Dimension(x_point, y_point + 200));
        // button.addActionListener(e);
        panel.add(login_heading);
        panel.add(login_username);
        panel.add(password);
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



public class View {
    private JFrame frame;
    Dimension pos;
    Builder builder = new Builder();
    public View() {
        frame = new JFrame("SubmitEase");
        // frame = new JFrame("SubmitEase");
        show_login_page(frame);
    }

    ArrayList<String> show_login_page(JFrame frame) {
        Dimension dm = new Dimension(250, 30);
        int x_point = builder.get_position(0, 250, 30, frame.getSize()).width;
        frame.setSize(800, 600);
        JPanel login_panel = builder.get_login_page(new Dimension(800, 600), dm);
        JButton login_button = builder.get_button("Login", dm, new Dimension(x_point, builder.y_point + 100));
        login_panel.add(login_button);
        login_button.addActionListener((ActionEvent e) -> { 
            // return new ArrayList<String>(
            //     Arrays.asList("Geeks",
            //                   "for",
            //                   "Geeks"));
        });
        frame.setLayout(null);
        // frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // JLabel login_title = new JLabel("Login", SwingConstants.CENTER);
        // login_title.setOpaque(true);
        // login_title.setBackground(new Color(255, 0, 0));
        // // pos = new builder().get_position(1, 100, 50, frame.getSize());
        // login_title.setBounds(pos.width, pos.height, 100, 50);
        System.out.println(login_panel);
        frame.add(login_panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        System.out.println("Started");
        View n = new View();
    }
}