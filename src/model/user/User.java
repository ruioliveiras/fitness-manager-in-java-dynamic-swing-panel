    /**TODO:
     *          Santos - acrescentar var. forma + metodos
     */ 

package model.user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import core.util.Manager;
import model.Clonable;
import model.ObjectKey;
import model.activity.Activity;
import model.activity.ActivityComparatorByDate;
import model.activity.Ciclismo;

/*
 * Classe com informacao dos utilizadores.
 */
public class User implements ObjectKey,Clonable{
    private String email;
    private String nome;
    private String password;
    private Genero genero;
    private int altura; /*cm*/
    private int peso; /*kg*/
    private GregorianCalendar dataNascimento;
    private String desportoFavorito;
    private Permissoes permissoes;
    private Map<Class<?>, HashMap<Integer, Activity>>  recordes; /* 1º level key class, values 2ºlevel key recordtype, values recordActivitys*/
    private int fcr; /*frequencia cardiaca em repouso - para calculo das calorias*/
    private Manager<String> amigos; /*emails de amigos: chaves para aceder ao HashMap da rede social*/
    private Manager<Activity> actividadesUser;
    private TreeSet<Activity> treeActividadesUser;

    
    
     
     /*
      * Construtores
      */
    public User(){
        this.nome = "guest";
        this.email = "";
        this.password = "guest";
        this.genero = Genero.Desconhecido;
        this.altura = 0;
        this.peso = 0;
        this.dataNascimento = new GregorianCalendar();
        this.recordes = new HashMap<Class<?>, HashMap<Integer, Activity>>();
        this.desportoFavorito = "";
        this.permissoes = Permissoes.Guest;
        this.fcr = 0;
        this.amigos = new Manager<String>(new HashSet<String>());
        this.treeActividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
        this.actividadesUser = new Manager<Activity>(this.treeActividadesUser);
    }
    
    public User(String nome, String email, String password, Genero genero, int altura, int peso, 
                int diaNascimento, int mesNascimento, int anoNascimento, 
                String desportoFavorito, Permissoes permissoes, int fcRepouso){
                    this.nome = nome;
                    this.email = email;
                    this.password = password;
                    this.altura = altura;
                    this.peso = peso;
                    this.genero = genero;
                    this.recordes = new HashMap<Class<?>, HashMap<Integer, Activity>>();
                    this.dataNascimento = new GregorianCalendar(anoNascimento, mesNascimento, diaNascimento);
                    this.desportoFavorito = desportoFavorito;
                    this.permissoes = permissoes;
                    this.fcr = fcRepouso;
                    this.amigos = new Manager<String>(new HashSet<String>());
                    this.treeActividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
                    this.actividadesUser = new Manager<Activity>(this.treeActividadesUser);
               }
                
    public User(User u){
        this.nome = u.getNome();
        this.email = u.getEmail();
        this.password = u.getPassword();
        this.altura = u.getAltura();
        this.peso = u.getPeso();
        this.genero = u.getGenero();
        this.dataNascimento = (GregorianCalendar) u.getDataNascimento().clone();
        this.desportoFavorito = u.desportoFavorito;
        this.permissoes = u.permissoes;
        this.fcr = u.getFreqCardio();
        this.amigos = new Manager<String>(new HashSet<String>(u.amigosManager().collection()));
        
        this.treeActividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
        this.treeActividadesUser.addAll(u.atividadesManager().collection());
        this.actividadesUser = new Manager<Activity>(this.treeActividadesUser);
    }
    
      /*
      * Getters
      */
    public String getNome()     {return this.nome;}
    public String getEmail()    {return this.email;}
    public String getPassword() {return this.password;}
    
    public int getAltura()      {return this.altura;}
    public int getPeso()        {return this.peso;}
    public int getFreqCardio()  {return this.fcr;}
    
    public GregorianCalendar getDataNascimento(){return this.dataNascimento;}
    public String getDesportoFavorito()         {return this.desportoFavorito;}
    
    public Permissoes getPermissoes()           {return this.permissoes;}
    public Genero getGenero()                   {return this.genero;}
       
    /*
      * Setters
      */
    public void setNome(String nome)        {this.nome=nome;}
    public void setEmail(String email)      {this.email=email;}
    public void setPassword(String password){this.password=password;}
    
    public void setAltura(int altura)       {this.altura=altura;}
    public void setPeso(int peso)           {this.peso=peso;}
    public void setFreqCardio(int fcr)      {this.fcr=fcr;}
      
    public void setDataNascimento(int ano, int mes, int dia)
                          {this.dataNascimento = new GregorianCalendar(ano, mes, dia);}
    public void setDesportoFavorito(String desporto){this.desportoFavorito = desporto;}
    public void setPermissoes(Permissoes permissoes){this.permissoes = permissoes;}
    public void setGenero(Genero genero)              {this.genero=genero;}
        
    /*
     * Getttes not direct (with calculation)
     */
    //ESTA A CALCULAR BEM???? TEM QUE LER A DATA COMPLETA PARA CALCULAR O NUMERO DE ANOS
    public int getIdade(){        
        GregorianCalendar agora = new GregorianCalendar();
        return agora.get(Calendar.YEAR) - this.dataNascimento.get(Calendar.YEAR);
    }
    
    public double getForma(){
        /*WIP*/
        //return 0.0;
        throw new RuntimeException("DO NOT IMPLEMENT YET, IMPLEENT BEFORE USER IT");
    }
    
    public Manager<String> amigosManager(){return this.amigos;}    
    public Manager<Activity> atividadesManager(){return this.actividadesUser;}    
    
    
    
    public Set<Activity> actividadesEntre(GregorianCalendar dataInferior, GregorianCalendar dataSuperior){
         Set<Activity> res = new TreeSet<Activity>(new ActivityComparatorByDate());
         /*Actividades artificiais para efeitos de comparacao*/
         Activity a1 = new Ciclismo(); a1.setDate(dataInferior);
         Activity a2 = new Ciclismo(); a2.setDate(dataSuperior);
         
         for(Activity ac : this.treeActividadesUser.subSet(a1, true, a2, true)){
             res.add((Activity) ac.clone());
         }
         
         return res;
    }

    public void addActivityRecord(Activity a){
        Activity aClone = (Activity) a.clone();
        actividadesUser.add(aClone);
        addRecord(aClone);
    }
    
    private void addRecord(Activity activity){
        HashMap<Integer, Activity> actualRecords = recordes.get(activity.getClass());
        int numRecords;
        Activity record;
        
        
        /*iF Don't exist, add*/
        if (actualRecords == null){
            recordes.put(activity.getClass(), new HashMap<Integer, Activity> ());
            actualRecords = recordes.get(activity.getClass());
        }
        
        numRecords = activity.getRecordSize();
        for (int i=0;i<numRecords;i++){
            record = actualRecords.get(i);
            if (record == null || activity.compareRecord(record,i) > 0){
                actualRecords.put(activity.getRecordSize(), activity);
            }
        }
    }
    
    
    /*Object statements*/
    @Override
    public int hashCode(){
        return getEmail().hashCode();
    }
    
    @Override
    public Object clone(){return new User(this);}
   
    @Override
    public String toString(){
        StringBuilder stringb = new StringBuilder();
        stringb.append("Nome: " + this.nome + "\n");
        stringb.append("Email: " + this.email + "\n");
        stringb.append("Sexo: " + this.genero.name() + "\n");
        stringb.append("Data Nascimento: " + this.dataNascimento.toString() + "\n");
        return stringb.toString();
    }

    @Override
    public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      User u = (User) obj;
      return this.email.equals(u.getEmail());
   }

    @Override
    public Object getKey() {
        return getEmail();
    } 
    
}
