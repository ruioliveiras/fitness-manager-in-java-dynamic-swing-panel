package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import com.jgoodies.forms.builder.PanelBuilder;
import com.jgoodies.forms.layout.FormLayout;
//binding.list.SelectionInList



public class ViewLogin implements ActionListener{


	public ViewLogin(){

	}

	

	public void show(){
		JFrame frame = new JFrame();
		frame.setTitle("Login------");
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
		frame.getContentPane().add(buildPanel());
		
		frame.pack();
		frame.setVisible(true);

	

	/*	 JComponent panel = new ButtonBarFactoryExample().buildPanel();*/
	}

	public JPanel buildPanel() { 
		JPanel jPanel = new JPanel();
		FormLayout layout = new FormLayout("");
		
		
		
		PanelBuilder builder = new PanelBuilder(layout);
        builder.appendColumn("right:pref");
        builder.appendColumn("fill:max(pref; 100px)");

        builder.appendRow("top:pref");
        builder.addLabel("Login:");
        builder.nextColumn();
        builder.add(new JTextField(15));

        builder.appendRow("top:pref");
        builder.nextLine();
        builder.addLabel("Password:");
        builder.nextColumn();
        builder.add(new JPasswordField(15));
        
        builder.appendRow("top:pref");
        builder.nextLine();
        JButton button = new JButton("Login");
        button.addActionListener(this);
        builder.add(button);
        
        builder.appendRow("top:pref");
        builder.nextLine();
  
        //    g.
//       builder.add(buildTab(),);
        
        jPanel.add( builder.getPanel());
      return jPanel;
	}
	
	public JPanel buildTab() { 
		JPanel jPanel = new JPanel();
		FormLayout layout = new FormLayout("","fill:pref:grow");
		
		PanelBuilder builder = new PanelBuilder(layout);
        builder.appendColumn("right:pref");

        builder.appendRow("top:pref");
        JButton button = new JButton("Login______________");
   
        button.addActionListener(this);
        builder.add(button);
       

        jPanel.add( builder.getPanel());
      return jPanel;
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}

	public static void main(String[] args){
		ViewLogin f = new ViewLogin();
		f.show();
	}


	


}
