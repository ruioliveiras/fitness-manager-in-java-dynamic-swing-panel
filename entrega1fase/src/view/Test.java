package view;

import java.awt.BorderLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.FormLayout;

public class Test extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Test() {
      super(new BorderLayout());

        PanelBuilder builder = new PanelBuilder(new FormLayout(""));
        builder.appendColumn("right:pref");
        builder.appendColumn("fill:max(pref; 100px)");

        builder.appendRow("top:pref");
        builder.addLabel("First:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Last:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Married:");
        builder.nextColumn();
        builder.add(new JCheckBox());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Phone:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Fax:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Email:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addSeparator("Work");

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Company:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Phone:");
        builder.nextColumn();
        builder.add(new JTextField());

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Fax:");
        builder.nextColumn();
        builder.add(new JTextField());

        add(builder.getPanel());
    }

    public static void main(String[] a){
      JFrame f = new JFrame("FormLayout: Panel Builder Example 2");
      f.setDefaultCloseOperation(2);
      f.add(new Test());
      f.pack();
      f.setVisible(true);
    }
}
