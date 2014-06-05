package controller.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.activity.Activity;
import model.activity.Contest;
import model.activity.Distance;
import model.activity.Natacao;
import model.activity.Weather;
import model.events.AddEventException;
import model.events.ContestPair;
import model.events.Event;
import model.events.EventContest;
import model.events.EventDistance;
import model.user.Permissoes;
import model.user.User;
import view.main.panel.PanelEvents;
import view.main.panel.PanelEvents.FormAttEnum;
import view.main.panel.PanelEvents.FormButtonEnum;
import controller.ComboRecordModel;
import controller.EventNotPermited;
import controller.Main;
import controller.NameDontExistException;
import core.DistancePair;
import core.EventSimulation;
import core.FormUtils;
import core.FormUtils.FormHandle;
import core.FormUtils.FormListHandle;
import core.FormUtils.OnPanelLoadLisneter;
import core.FormUtils.SimpleListener;
import core.util.Manager.ObjectDontExistException;

public class ControllerEvents{
	private FormListHandle mHandler;
	private User mUser;
	private Event mSelected;
	private List<Event> mEvents;
	private Permissoes mRight;

	public ControllerEvents(FormHandle handler,User user) {
		mHandler = (FormListHandle) handler;
		mUser = user;
		mEvents = Main.getDataSet().eventManager().collection();
		mHandler.addStringAll(mEvents); 
		initListeners();
		mRight = user.getPermissoes();
		checkRight();
	}
	public void setUser(User user,Permissoes p){
		mRight = p;
		mUser = user;
		checkRight();
	}

	private void initListeners(){
		if (mUser.getPermissoes() != Permissoes.Admin){
			mHandler.getButton(FormButtonEnum.EDITAR).setEnabled(false);
			mHandler.getButton(FormButtonEnum.CRIAR_SALVAR).setEnabled(false);
			mHandler.getButton(FormButtonEnum.INICIAR).setEnabled(false);
		}
		mHandler.addButtonListener(FormButtonEnum.ADERIR, new ActionListener() {    
			@Override
			public void actionPerformed(ActionEvent arg0) {
				aderir();
			}
		});
		mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {    
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.getComponent(FormAttEnum.NOME).setEnabled(false);
					mHandler.setText2(FormButtonEnum.EDITAR);
				}else{
					mHandler.setText1(FormButtonEnum.EDITAR);
					saveEventChanges();
					refresh();
				}
			}
		});
		mHandler.addButtonListener(FormButtonEnum.APAGAR, new ActionListener() {    
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean confirmacao = mHandler.getTextIndex(FormButtonEnum.APAGAR) == 1; 
				if (confirmacao){
					mHandler.setText2(FormButtonEnum.APAGAR);
				}else{
					mHandler.setText1(FormButtonEnum.APAGAR);
					apagarEvent();
					refresh();
				}
			}
		});
		mHandler.addButtonListener(FormButtonEnum.CRIAR_SALVAR, new ActionListener() {  
			@Override
			public void actionPerformed(ActionEvent arg0) {
				boolean isEdit = mHandler.getTextIndex(FormButtonEnum.CRIAR_SALVAR) == 1; 
				setComponentsEnable(isEdit); // enable if isEdit
				if (isEdit){
					mHandler.setText2(FormButtonEnum.CRIAR_SALVAR);
				}else{
					mHandler.setText1(FormButtonEnum.CRIAR_SALVAR);
					saveEventChanges();
					refresh();
				}
			}
		});
		mHandler.addButtonListener(FormButtonEnum.INICIAR, new ActionListener() {   
			@Override
			public void actionPerformed(ActionEvent arg0) {
				iniciar();
			}   
		});
		mHandler.addLoadLisnener(new OnPanelLoadLisneter() {

			@Override
			public void load() {
				mEvents.clear();
				mEvents.addAll(Main.getDataSet().eventManager().collection());
				setEvent(new EventDistance(new Natacao()));
			}
		});

		mHandler.addListListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = mHandler.getSelectIndex();
				mSelected = mEvents.get(index);
				setEvent(mSelected);
			}

		});

		@SuppressWarnings("unchecked")
		final JComboBox<Object> activityCombo = (JComboBox<Object>) mHandler.getComponent(FormAttEnum.ACTIVITY);
		activityCombo.setModel(new DefaultComboBoxModel<Object>(Main.getActivitiesNames()));
		activityCombo.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {  

				try {
					Activity a = Main.getActivity(activityCombo.getSelectedIndex());
					if (a.getName().equals(mSelected.getActivity().getName())){
						return;
					}
					if (a instanceof Distance){
						setEvent(new EventDistance(a));
					}else if (a instanceof Contest){
						setEvent(new EventContest(a));
					}else{
						throw new  EventNotPermited();
					}


				} catch (EventNotPermited e) {
					JOptionPane.showMessageDialog(null, "Evento não Permitido");
				}
			}
		});

	}

	private void setEvent(Event event){
		mSelected = event;

		JTextField nome = (JTextField) mHandler.getComponent(FormAttEnum.NOME);
		JTextField dateEvent = (JTextField) mHandler.getComponent(FormAttEnum.DATA_EVENT);
		JTextField dateFim = (JTextField) mHandler.getComponent(FormAttEnum.DATA_FIM);
		JTextField requisito = (JTextField) mHandler.getComponent(FormAttEnum.REQUISITO);
		JTextField nUser = (JTextField) mHandler.getComponent(FormAttEnum.NUM_USER);
        JComboBox<?> activitys = (JComboBox<?>) mHandler.getComponent(FormAttEnum.ACTIVITY);
        @SuppressWarnings("unchecked")
        JComboBox<Object> records = (JComboBox<Object>) mHandler.getComponent(FormAttEnum.RECORD_TYPE);

        JTextField distance = (JTextField) mHandler.getComponent(FormAttEnum.DISTANCE);
        JTextField numJogos = (JTextField) mHandler.getComponent(FormAttEnum.NUM_JOGOS);
        JTextField pVit = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_VIT);
        JTextField pEmp = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_EMP);
        JTextField pDer = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_DER);   

        nome.setText(event.getName());
        dateEvent.setText(new SimpleDateFormat(FormUtils.DATA_PATTERM).format(event.getEventDate().getTime()));
        dateFim.setText(new SimpleDateFormat(FormUtils.DATA_PATTERM).format(event.getEndDate().getTime()));
        requisito.setText(String.valueOf(event.getPreRequisite()));
        nUser.setText(String.valueOf(event.getMaxNumUsers()));
        mHandler.setValue(FormAttEnum.NUM_USER_ACT,String.valueOf(event.getUserManager().size()));
        records.setModel(new ComboRecordModel(event.getActivity()));
        
        try {
            activitys.setSelectedIndex(Main.getActivityIndex(event.getActivity().getName()));
        } catch (NameDontExistException e1) {
        }
        if (event.getRecordType() > 0){
        	records.setSelectedIndex(event.getRecordType());	
        }
        
        if (event instanceof EventDistance){
            EventDistance e = (EventDistance) event;
            distance.setText(String.valueOf(e.getEventDistance()));

        }else if (event instanceof EventContest){
            EventContest e = (EventContest) event;
            numJogos.setText(String.valueOf(e.getNumGames()));
            pVit.setText(String.valueOf(e.getVicPts()));
            pDer.setText(String.valueOf(e.getLossPts()));
            pEmp.setText(String.valueOf(e.getDrawPts()));
        }


        distance.setVisible(event instanceof EventDistance);
        numJogos.setVisible(event instanceof EventContest);
        pVit.setVisible(event instanceof EventContest);
        pEmp.setVisible(event instanceof EventContest);
        pDer.setVisible(event instanceof EventContest);

        mHandler.getLabel(FormAttEnum.DISTANCE).setVisible(event instanceof EventDistance);
        mHandler.getLabel(FormAttEnum.NUM_JOGOS).setVisible(event instanceof EventContest);
        mHandler.getLabel(FormAttEnum.PONTOS_VIT).setVisible(event instanceof EventContest);
        mHandler.getLabel(FormAttEnum.PONTOS_EMP).setVisible(event instanceof EventContest);
        mHandler.getLabel(FormAttEnum.PONTOS_DER).setVisible(event instanceof EventContest);




    }
    
    
    
    
    private void saveEventChanges() {
        try {
            Event event;
            //int index = Main.getEventIndex(event.getName());
            JTextField nome = (JTextField) mHandler.getComponent(FormAttEnum.NOME);
            JTextField dateEvent = (JTextField) mHandler.getComponent(FormAttEnum.DATA_EVENT);
            JTextField dateFim = (JTextField) mHandler.getComponent(FormAttEnum.DATA_FIM);
            JTextField requisito = (JTextField) mHandler.getComponent(FormAttEnum.REQUISITO);
            JTextField nUser = (JTextField) mHandler.getComponent(FormAttEnum.NUM_USER);
            JComboBox<?> activitys = (JComboBox<?>) mHandler.getComponent(FormAttEnum.ACTIVITY);
            JComboBox<?> records = (JComboBox<?>) mHandler.getComponent(FormAttEnum.RECORD_TYPE);
            
            JTextField dist = (JTextField) mHandler.getComponent(FormAttEnum.DISTANCE);
            JTextField njogos = (JTextField) mHandler.getComponent(FormAttEnum.NUM_JOGOS);
            JTextField pVit = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_VIT);
            JTextField pEmp = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_EMP);
            JTextField pDer = (JTextField) mHandler.getComponent(FormAttEnum.PONTOS_DER);   
            
            String name = nome.getText();
            GregorianCalendar eventDate = new GregorianCalendar();  
            GregorianCalendar endDate = new GregorianCalendar();   
            int preRequisite = Integer.parseInt(requisito.getText());
            int maxNumUsers = Integer.parseInt(nUser.getText());
            int recordType = records.getSelectedIndex();
          
            
            eventDate.setTime(new SimpleDateFormat(FormUtils.DATA_PATTERM).parse(dateEvent.getText()));
            endDate.setTime(new SimpleDateFormat(FormUtils.DATA_PATTERM).parse(dateFim.getText()));

            Activity activity = Main.getActivity(activitys  .getSelectedIndex());
            if (activity instanceof Distance){
            	int distancia = Integer.parseInt(dist.getText());
            	event = new EventDistance(name, activity, eventDate, endDate, preRequisite, maxNumUsers, recordType, distancia);
            }else if (activity instanceof Contest){
            	int games = Integer.parseInt(njogos.getText());
            	int victoryPts = Integer.parseInt(pVit.getText());
            	int drawPts = Integer.parseInt(pEmp.getText());
            	int lossPts = Integer.parseInt(pDer.getText());
            	event = new EventContest(name, activity, eventDate, endDate, preRequisite, maxNumUsers, recordType, games, victoryPts, drawPts, lossPts);
            }else{
            	throw new  EventNotPermited();
            }

            Main.getDataSet().eventManager().add(event);
            Main.save();    

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Data com formato invalido");
            return; 
        }catch (StringIndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(null, "Erro String out of bounds");
            return;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Números incorrectos");
            return;
        }catch (NullPointerException e){
            JOptionPane.showMessageDialog(null, "Erro, faltou preencher alguma coisa");
            return; 
        } catch (EventNotPermited e) {
            JOptionPane.showMessageDialog(null, "Evento não Permitido");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERRO FATAL");
            e.printStackTrace();
        }
    }

    private void checkRight(){
	    boolean isAdmin = mRight == Permissoes.Admin;
	    boolean isUser = mRight == Permissoes.User; 
	
	    
	    mHandler.getButton(FormButtonEnum.ADERIR).setEnabled(isUser || isAdmin); 
	    mHandler.getButton(FormButtonEnum.CRIAR_SALVAR).setEnabled(isAdmin);
	    mHandler.getButton(FormButtonEnum.EDITAR).setEnabled(isAdmin);
	    mHandler.getButton(FormButtonEnum.APAGAR).setEnabled(isAdmin);
	    mHandler.getButton(FormButtonEnum.INICIAR).setEnabled(isAdmin);
	    
	}
	protected void setComponentsEnable(boolean b){
        for(FormAttEnum e: FormAttEnum .values()){
            mHandler.getComponent(e).setEnabled(b);
        }
        mHandler.getComponent(FormAttEnum.NUM_USER_ACT).setEnabled(false);         
    }


	private void apagarEvent() {
		Main.getDataSet().eventManager().remove(mSelected);
		Main.save();
	}

	private void refresh(){
		mEvents.clear();
		mEvents.addAll(Main.getDataSet().eventManager().collection());
		mHandler.addStringAll(mEvents); 
	}
	
	private void aderir() {
		try {
        	mSelected.getUserManager().size();
			mSelected.addUser(mUser);
			Main.getDataSet().eventManager().edit(mSelected);
			Main.save();
		} catch (AddEventException e) {
			JOptionPane.showMessageDialog(null, "Não pode Aderir");
		}
    }
    
    
    private void iniciar() {
    	((PanelEvents) mHandler).showPopupEvent(new SimpleListener() {
			
			@Override
			public Object action(Object o) {
				Weather weather = (Weather) o;
				
				if(mSelected.getActivity() instanceof Distance)
		            iniciarDistanceEvent(weather);
		        else
		            iniciarContestEvent(weather);
				
				
				return null;
			}
		});

    }


    
    
    private void printDistanceEvent(TreeSet<DistancePair> results, int stage){
        clearScreen();
        System.out.println("--- Classificação após a ronda " + stage + " ---");
        for(DistancePair p : results)
            System.out.println(p.toString());
        
        nextOutput();
    }
    
    private void iniciarDistanceEvent(Weather w){
        List<String> usersKeys = mSelected.getUserManager().collection();/*ler keys do evento seleccionado*/
        List<User> users = new ArrayList<>();
        Distance act = (Distance) mSelected.getActivity();
        int recordType = mSelected.getRecordType();
        long eventDistance = act.getValue(act.getRecord(recordType));
        int stages = (eventDistance > 1000) ? (int) eventDistance/1000 : 1;
        
        /*adicionar Users atraves das keys*/
        for(String uKey : usersKeys){
            try{
                User aux = Main.getDataSet().userManager().get(new User(uKey));
                users.add(aux);
            }
            catch (ObjectDontExistException e) {
                 JOptionPane.showMessageDialog(null, "Email não existe");
            }
        }
        
        /*criar map de resultados*/
        Map<String,ArrayList<Long>> allResults = EventSimulation.getAllResults(users, act, recordType, stages);
        
        /*apresentacao dos resultados por etapa*/
        for(int i = 0; i < stages; i++){
            TreeSet<DistancePair> aux = EventSimulation.getStageClassification(allResults, i);
            printDistanceEvent(aux, i);
        }
    }
    
    private void iniciarContestEvent(Weather w){
        List<ContestPair> games = gamesResults();
        Map<String,Integer> table = contestTable(games);
        TreeSet<DistancePair> classif = new TreeSet<>();
        
        Iterator<String> it = table.keySet().iterator();
        for(Integer pts : table.values())
            classif.add(new DistancePair(it.next(),pts));/*atencao mudar o nome de DistancePair*/
        
            
        /*output*/
        clearScreen();
        System.out.println(mSelected.toString());
        
        /*imprimir jogos*/
        for(ContestPair g : games)
            System.out.println(g.toString());

        nextOutput();    
        /*imprimir tabela ordenada*/
        System.out.println("--- Tabela Classificativa ---");
        for(DistancePair p : classif)
            System.out.println(p.getName() + " --- " + p.getResult());  
    }
    
    /**contest simulation*/
    public Map<String,Integer> contestTable(List<ContestPair> results){
        Map<String,Integer> table = new HashMap<>();
        String u1, u2;
        int u1Pts, u2Pts;
        
        for(ContestPair p : results){
            u1 = p.getFstUser();
            u2 = p.getSndUser();
            u1Pts = p.getUser1Pts();
            u2Pts = p.getUser2Pts();
            if(!table.containsKey(u1)) table.put(u1, u1Pts);/*primeira insercao*/
            else table.put(u1, u1Pts + table.get(u1));/*atualizacao dos pontos*/
            if(!table.containsKey(u2)) table.put(u2, u2Pts);
            else table.put(u2, u2Pts + table.get(u2));
        }
            
        return table;
    }
    

    public List<ContestPair> gamesResults(){
        /*gerar jogos sem resultados*/
        List<ContestPair> games = ((EventContest) mSelected).getGames();
        User u1=null, u2=null;
        int resultAux, u1Pts, u2Pts;
        
        /*ler users e criar resultados*/
        for(ContestPair p : games){
           String u1Key =  p.getFstUser();
           String u2Key =  p.getSndUser();
           try{
                u1 = Main.getDataSet().userManager().get(new User(u1Key));
                u2 = Main.getDataSet().userManager().get(new User(u2Key));
           }
           catch (ObjectDontExistException e) {
                 JOptionPane.showMessageDialog(null, "Email não existe");
                 return null;
           }
           /*simula resultado*/
           resultAux = EventSimulation.getSimulationContest(u1, u2, mSelected.getActivity().getClass());
           
           /*atribui pontos em funcao do resultado*/
           if(resultAux == 0) {
               u1Pts = u2Pts = ((EventContest) mSelected).getDrawPts();
               p.setUser1Pts(u1Pts);
               p.setUser1Pts(u2Pts);
           }
           else if(resultAux < 0) {
               u1Pts = ((EventContest) mSelected).getVicPts();
               u2Pts = ((EventContest) mSelected).getLossPts();
               p.setUser1Pts(u1Pts);
               p.setUser1Pts(u2Pts);
           }
           else{
               u2Pts = ((EventContest) mSelected).getVicPts();
               u1Pts = ((EventContest) mSelected).getLossPts();
               p.setUser1Pts(u1Pts);
               p.setUser1Pts(u2Pts);
           }
        }
        
        return games;
    }
    
    
    /*print auxiliares*/
    private final static void clearScreen (){
       System.out.print('\u000C');
    }
    
    private static void nextOutput(){
        
        Scanner reader = new Scanner(System.in);
        String c;
        System.out.println("--- Prima <n> para mais resultados ---");
        do {
            c = reader.nextLine();
        }
        while(c.endsWith("n") != true);
        
    }
    

    
    

    
    
    /**
     * @@@ cabula para percorrer users:
     * 
     * mSelected (Evento actual)
     * 
     * //Chaves dos users do Evento atual
     * List<Strings> = mSelected.getUserManager().collection();
     * 
     * //aceder a todos os users
     * List<User> = Main.getDataSet().userManager().collection();
     * 
     * //aceder a 1 User
     * User = Main.getDataSet().userManager().get(new User("stringKey"));
     * 
     */
}
