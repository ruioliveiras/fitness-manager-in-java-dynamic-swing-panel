package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import com.jgoodies.looks.LookUtils;
import com.jgoodies.looks.Options;

import javax.swing.JFrame;
import javax.swing.UIManager;

public class Login {

	public static void main(String  JavaDoc[] args) {
		52         try {
		53             UIManager.setLookAndFeel("com.jgoodies.plaf.plastic.PlasticXPLookAndFeel");
		54         } catch (Exception  JavaDoc e) {
		55             // Likely PlasticXP is not in the class path; ignore.
		56 }
		57         JFrame frame = new JFrame();
		58         frame.setTitle("Forms Tutorial :: ButtonBarFactory");
		59         frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		60         JComponent panel = new ButtonBarFactoryExample().buildPanel();
		61         frame.getContentPane().add(panel);
		62         frame.pack();
		63         frame.show();
		64     }
		65 
		66 
		67     public JComponent buildPanel() {
		68         JTabbedPane tabbedPane = new JTabbedPane();
		69         tabbedPane.putClientProperty("jgoodies.noContentBorder", Boolean.TRUE);
		70 
		71         tabbedPane.add(buildButtonBar1Panel(), "Dialog 1");
		72         tabbedPane.add(buildButtonBar2Panel(), "Dialog 2");
		73         tabbedPane.add(buildButtonBar3Panel(), "Dialog 3");
		74         tabbedPane.add(buildAddRemovePropertiesPanel(), "List 1");
		75         tabbedPane.add(buildAddRemovePanel(), "List 2");
		76         return tabbedPane;
		77     }
		78     
		79     private Component  JavaDoc buildButtonBar1Panel() {
		80         FormLayout layout = new FormLayout(
		81                         "default:grow",
		82                         "0:grow, p, 4dlu, p, 4dlu, p, 4dlu, p");
		83                         
		84         PanelBuilder builder = new PanelBuilder(layout);
		85         builder.setDefaultDialogBorder();
		86         
		87         builder.nextRow();
		88         builder.add(ButtonBarFactory.buildCloseBar(
		89             new JButton("Close")
		90         )); 
		91         builder.nextRow(2);
		92         builder.add(ButtonBarFactory.buildOKBar(
		93             new JButton("OK")
		94         )); 
		95         builder.nextRow(2);
		96         builder.add(ButtonBarFactory.buildOKCancelBar(
		97             new JButton("OK"), new JButton("Cancel")
		98         )); 
		99         builder.nextRow(2);
		100         builder.add(ButtonBarFactory.buildOKCancelApplyBar(
		101             new JButton("OK"), new JButton("Cancel"), new JButton("Apply")
		102         )); 
		103                    
		104         return builder.getContainer();
		105     }
		106     
		107 
		108     private Component  JavaDoc buildButtonBar2Panel() {
		109         FormLayout layout = new FormLayout(
		110                         "default:grow",
		111                         "0:grow, p, 4dlu, p, 4dlu, p, 4dlu, p");
		112                         
		113         PanelBuilder builder = new PanelBuilder(layout);
		114         builder.setDefaultDialogBorder();
		115         
		116         builder.nextRow();
		117         builder.add(ButtonBarFactory.buildCloseHelpBar(
		118             new JButton("Close"), new JButton("Help")
		119         )); 
		120         builder.nextRow(2);
		121         builder.add(ButtonBarFactory.buildOKHelpBar(
		122             new JButton("OK"), new JButton("Help")
		123         )); 
		124         builder.nextRow(2);
		125         builder.add(ButtonBarFactory.buildOKCancelHelpBar(
		126             new JButton("OK"), new JButton("Cancel"), new JButton("Help")
		127         )); 
		128         builder.nextRow(2);
		129         builder.add(ButtonBarFactory.buildOKCancelApplyHelpBar(
		130             new JButton("OK"), new JButton("Cancel"), new JButton("Apply"), new JButton("Help")
		131         )); 
		132                    
		133         return builder.getContainer();
		134     }
		135     
		136 
		137     private Component  JavaDoc buildButtonBar3Panel() {
		138         FormLayout layout = new FormLayout(
		139                         "default:grow",
		140                         "0:grow, p, 4dlu, p, 4dlu, p, 4dlu, p");
		141                         
		142         PanelBuilder builder = new PanelBuilder(layout);
		143         builder.setDefaultDialogBorder();
		144         
		145         builder.nextRow();
		146         builder.add(ButtonBarFactory.buildHelpCloseBar(
		147             new JButton("Help"), new JButton("Close")
		148         )); 
		149         builder.nextRow(2);
		150         builder.add(ButtonBarFactory.buildHelpOKBar(
		151             new JButton("Help"), new JButton("OK")
		152         )); 
		153         builder.nextRow(2);
		154         builder.add(ButtonBarFactory.buildHelpOKCancelBar(
		155             new JButton("Help"), new JButton("OK"), new JButton("Cancel")
		156         )); 
		157         builder.nextRow(2);
		158         builder.add(ButtonBarFactory.buildHelpOKCancelApplyBar(
		159             new JButton("Help"), new JButton("OK"), new JButton("Cancel"), new JButton("Apply")
		160         )); 
		161              
		162         return builder.getContainer();
		163     }
		164     
		165 
		166     private Component  JavaDoc buildAddRemovePropertiesPanel() {
		167         FormLayout layout = new FormLayout(
		168                         "fill:default:grow",
		169                         "fill:p:grow, 4dlu, p, 14dlu, " +
		170                         "fill:p:grow, 4dlu, p");
		171                         
		172         PanelBuilder builder = new PanelBuilder(layout);
		173         builder.setDefaultDialogBorder();
		174 
		175         builder.add(new JScrollPane(new JTextArea()));
		176         builder.nextRow(2);
		177         builder.add(ButtonBarFactory.buildAddRemovePropertiesLeftBar(
		178             new JButton("Add..."), new JButton("Remove"), new JButton("Properties...")
		179         ));
		180         builder.nextRow(2);
		181 
		182         builder.add(new JScrollPane(new JTextArea()));
		183         builder.nextRow(2);
		184         builder.add(ButtonBarFactory.buildAddRemovePropertiesRightBar(
		185             new JButton("Add..."), new JButton("Remove"), new JButton("Properties...")
		186         )); 
		187         return builder.getContainer();
		188     }
		189     
		190     private Component  buildAddRemovePanel() {
		191         FormLayout layout = new FormLayout(
		192                         "fill:default:grow, 9dlu, fill:default:grow",
		193                         "fill:p:grow, 4dlu, p");
		194                         
		195         PanelBuilder builder = new PanelBuilder(layout);
		196         builder.setDefaultDialogBorder();
		197         CellConstraints cc = new CellConstraints();
		198 
		199         builder.add(new JScrollPane(new JTextArea()), cc.xy(1, 1));
		200         builder.add(ButtonBarFactory.buildAddRemoveLeftBar(
		201             new JButton("Add..."), new JButton("Remove")), cc.xy(1, 3));
		202                 
		203         builder.add(new JScrollPane(new JTextArea()), cc.xy(3, 1));
		204         builder.add(ButtonBarFactory.buildAddRemoveRightBar(
		205             new JButton("Add..."), new JButton("Remove")), cc.xy(3, 3));
		206         return builder.getContainer();
		207     }
		208     

		Read more: http://kickjava.com/src/com/jgoodies/forms/tutorial/factories/ButtonBarFactoryExample.java.htm#ixzz2y8AmvxoR

}
