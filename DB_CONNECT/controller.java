import java.sql.Connection;
import java.util.*;

import javax.swing.text.View;

import java.sql.* ; 

public class controller {
    private static controller c = new controller(null, null);
    Model mymodel;
    View myview;
    private controller(Model mymodel, View myview) {
        
    }
    public static controller getInstance(Model mymodel, View myview){
        c.mymodel = mymodel;
        c.myview = myview;
        return c;
     }
  

}