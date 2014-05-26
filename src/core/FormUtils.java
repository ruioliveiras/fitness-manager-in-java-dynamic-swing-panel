package core;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.ListSelectionListener;


public class FormUtils {
	
	public interface FormButtons{
		int getIndex();
		public String text1();
		public String text2();
	}
	
	public interface FormAttr{
		int getIndex();
		public String getName(); 
		public Class<? extends JComponent> getComponetClass();
		public Constructor<? extends JComponent> getComponetConstructor();

	}
	
	public static interface FormHandle{
		public JComponent getComponent(FormAttr a);
		public JLabel getLabel(FormAttr a);
		public String getValue(FormAttr a);
		public void setValue(FormAttr a,Object value);
		public JButton getButton(FormButtons a);
		public void addButtonListener(FormButtons a,ActionListener lis);
		public void setText1(FormButtons a);
		public void setText2(FormButtons a);
		public int getTextIndex(FormButtons a);
	}
	
	public static interface FormListHandle extends FormHandle {
		public void addListListener(ListSelectionListener lis);	
		public void addStringAll(Collection<?> str);
		public void addString(Object str);
		public void editString(int index,String str);
	}
	

	
	public static String[] stringFactory(String ...array){
		String res[] = new String[array.length];
		for (int i =0;i<array.length;i++) res[i] = array[i];
		return res;
	}
	
	
	public static void addLine(JPanel panel,String label,JComponent inst,int colums){
		JLabel jLabel = new JLabel(label);
		jLabel.setHorizontalAlignment(JLabel.CENTER);
		panel.add(jLabel);
		inst.setPreferredSize(new Dimension(colums,24));
		panel.add(inst);
	}
	
	
	
	public static JComponent createView_withEmptyConstructor(Class<? extends JComponent> component){
		JComponent obj = null;
		try {
			 obj = component.getConstructor().newInstance();
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			
			System.err.println("Erro class "+ component.getName() + " doesn't have empty constructor--");
			e.printStackTrace();
		}
		return obj;
	}
	public static JComponent createView_withClass(Class<? extends JComponent> component,Object...class_objects){
		JComponent componet2 = null;
		
		int ic,io,size = class_objects.length;
		Class<?> arrayClass[] = new Class<?>[size/2];
		Object arrayObj[] = new Class<?>[size/2];
		ic = 0;	io = 0;
		for (int i=0;i<size;i++){
			Object obj = class_objects[i];
			if (obj instanceof Class<?> && ic < size/2){
				arrayClass[ic] = (Class<?>) obj;
				ic++;
			}else{
				arrayObj[io] = obj;
				io++;
			}	
		}
		
		try {
			componet2 = component.getConstructor(arrayClass).newInstance(arrayObj);
		} catch (InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			System.err.println("Erro class "+ component.getName() + " doesn't have with constructor <" +arrayClass + "> obj: <"+arrayObj+">" );
			e.printStackTrace();
		}


		return componet2;
	}
	
}
