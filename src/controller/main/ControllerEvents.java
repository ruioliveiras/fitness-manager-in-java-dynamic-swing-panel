package controller.main;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
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
import model.events.Event;
import model.events.EventContest;
import model.events.EventDistance;
import model.user.Permissoes;
import model.user.User;
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

public class ControllerEvents implements ListSelectionListener{
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
                adedir();
            }
        });
        mHandler.addButtonListener(FormButtonEnum.EDITAR, new ActionListener() {    
            @Override
            public void actionPerformed(ActionEvent arg0) {
                boolean isEdit = mHandler.getTextIndex(FormButtonEnum.EDITAR) == 1; 
                setComponentsEnable(isEdit); // enable if isEdit
                if (isEdit){
                    mHandler.setText2(FormButtonEnum.EDITAR);
                }else{
                    mHandler.setText1(FormButtonEnum.EDITAR);
                    saveProfileChanges();
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
                    saveProfileChanges();
                }
            }
        });
        mHandler.addButtonListener(FormButtonEnum.INICIAR, new ActionListener() {   
            @Override
            public void actionPerformed(ActionEvent arg0) {
                iniciar();
                //system out println directamente na consola
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
    
	private void checkRight(){
		boolean edit;
		if (mRight == Permissoes.Admin || mRight == Permissoes.User){
			edit = true;
		}else{
			edit = false;
		}
		
		for(FormButtonEnum e: FormButtonEnum .values()){
			mHandler.getButton(e).setEnabled(edit);
		}
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
        records.setModel(new ComboRecordModel(event.getActivity()));
        
        try {
            activitys.setSelectedIndex(Main.getActivityIndex(event.getActivity().getName()));
        } catch (NameDontExistException e1) {
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
    
    
    
    
    private void saveProfileChanges() {
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
            
            String name = nome.getName();
            GregorianCalendar eventDate = new GregorianCalendar();  
            GregorianCalendar endDate = new GregorianCalendar();   
            int preRequisite = Integer.parseInt(requisito.getText());
            int maxNumUsers = Integer.parseInt(nUser.getText());
            int recordType = records.getSelectedIndex();
            int distancia = Integer.parseInt(dist.getText());
            int games = Integer.parseInt(njogos.getText());
            int victoryPts = Integer.parseInt(pVit.getText());
            int drawPts = Integer.parseInt(pEmp.getText());
            int lossPts = Integer.parseInt(pDer.getText());
            
            eventDate.setTime(new SimpleDateFormat(FormUtils.DATA_PATTERM).parse(dateEvent.getText()));
            endDate.setTime(new SimpleDateFormat(FormUtils.DATA_PATTERM).parse(dateFim.getText()));
            
            Activity activity = Main.getActivity(activitys  .getSelectedIndex());
            if (activity instanceof Distance){
                event = new EventDistance(name, activity, eventDate, endDate, preRequisite, maxNumUsers, recordType, distancia);
            }else if (activity instanceof Contest){
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

    protected void setComponentsEnable(boolean b){
        for(FormAttEnum e: FormAttEnum .values()){
            mHandler.getComponent(e).setEnabled(b);
        }
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int index = mHandler.getSelectIndex();
        mSelected = mEvents.get(index);
    }
    
    
    private void iniciar() {
        if(mSelected.getActivity() instanceof Distance)
            iniciarDistanceEvent();
        else
            iniciarContestEvent();
    }
    
    private void adedir() {
        
    }
    
    
    private void printDistanceEvent(TreeSet<DistancePair> results, int stage){
        clearScreen();
        System.out.println("--- Classificação após a ronda " + stage + " ---");
        for(DistancePair p : results)
            System.out.println(p.toString());
        
        nextOutput();
    }
    
    private void iniciarDistanceEvent(){
        List<User> users = Main.getDataSet().userManager().collection();
        Distance act = (Distance) mSelected.getActivity();
        int recordType = mSelected.getRecordType();
        long eventDistance = act.getValue(act.getRecord(recordType));
        int stages = (eventDistance > 1000) ? (int) eventDistance/1000 : 1;
        
        Map<String,ArrayList<Long>> allResults = EventSimulation.getAllResults(users, act, recordType, stages);
        
        for(int i = 0; i < stages; i++){
            TreeSet<DistancePair> aux = EventSimulation.getStageClassification(allResults, i);
            printDistanceEvent(aux, i);
        }
    }
    
    private void iniciarContestEvent(){
        /**TODO*/
    }
    
    
    /*print auxiliares*/
    private final static void clearScreen (){
       System.out.print('\u000C');
    }
    
    private static void nextOutput(){
        
        Scanner reader = new Scanner(System.in);
        String c;
        System.out.println("--- Prima <n> para a próxima ronda ---");
        do {
            c = reader.nextLine();
        }
        while(c.endsWith("n") != true);
        
    }
    
    
    /**
     * percorrer users
     * 
     * mSelected (Evento actual)
     * 
     * //Chaves dos users do Evento atual
     * List<Strings> = mSelected.getUserManager().collection();
     * 
     * //aceder a todos os users
     * List<User> = Main.getDataSet().userManager().collection();
     * 
     * User = Main.getDataSet().userManager().get(new User("stringKey"));
     * 
     * 
     * static public Map<String,ArrayList<Long>> getAllResults(List<User> users, Distance act, 
                                                int recordType, int stages)
                                                
       static public TreeSet<DistancePair> getStageClassification(Map<String,ArrayList<Long>> results, int stage)
     * 
     */
}
