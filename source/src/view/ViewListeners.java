package view;
import view.main.panel.PanelEvents;

public class ViewListeners {
	
	public interface ListenerPanelEventos{
		public void save(PanelEvents.Attr a);
		public void load(int index,PanelEvents.Attr a);
		
		public void join();
		public void startEvent();
	}
	
	public interface ListenerPanelActivity{
		public void save(PanelEvents.Attr a);
		public void load(int index,PanelEvents.Attr a);
	}

	public interface ListenerPanelProfile{
		public void save(PanelEvents.Attr a);
	}
	
	
	
	
	
}









































