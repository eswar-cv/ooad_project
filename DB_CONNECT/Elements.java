import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

public class Elements {
    View view;
    Elements(View view) {
        this.view = view;
    }

    JPanel get_header(View view) {
        JPanel panel = new JPanel();
        panel.setSize(100, 50);
        String btext;
        if (view.username != "null") btext = "Logout";
        else btext = "Login";
        JButton lilo = new JButton(btext);
        lilo.setBounds(5, 0, 100, 50);
        panel.add(lilo);
        return panel;
    }

    


}


