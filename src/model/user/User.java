package model.user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

import model.activity.ActivityComparatorByDate;
import model.activity.Activity;
import model.activity.Ciclismo;

/*
 * Classe com informacao dos utilizadores.
 * @author andrerfcsantos
 */

public class User{
    private String email;
    private String nome;
    private String password;
    private Genero genero;
    private int altura; /*cm*/
    private int peso; /*kg*/
    private GregorianCalendar dataNascimento;
    private String desportoFavorito;
    private Permissoes permissoes;
    private TreeSet<Activity> actividadesUser;
    private Map<Class<?>, HashMap<Integer, Activity>>  recordes; /* 1º level key class, values 2ºlevel key recordtype, values recordActivitys*/
    private int fcr; /*frequencia cardiaca em repouso - para calculo das calorias*/
    private Set<String> amigos; /*emails de amigos: chaves para aceder ao HashMap da rede social*/
    
    /**TODO:    Camposinhos - Set de emails de amigos
     *          Camposinhos - hascode de Users --- DONE
     *          Santos - TreeSet de Activities (Calendario de Activities - ordenado por data)
     *          Santos - acrescentar var. forma + metodos
     *          Oliveira - implementar Recordes (facebook)
     */ 
    
    
     
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
        this.amigos = new HashSet<String>();
        this.actividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
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
                    this.amigos = new HashSet<String>();
                    this.actividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
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
        this.amigos = new HashSet<String>(u.getAmigos());
        for(Activity a : u.actividadesUser){
            /*TODO: necessita do clone de Activity implementado para compilar, caso contrário assume o clone() de Object que é protected.*/
            this.actividadesUser.add((Activity) a.clone());
        }
    }
    
      /*
      * Getters
      */
    public String getNome(){return this.nome;}
    public String getEmail(){return this.email;}
    public String getPassword(){return this.password;}
    public int getAltura(){return this.altura;}
    public int getPeso(){return this.peso;}
    public Genero getGenero(){return this.genero;}
    public GregorianCalendar getDataNascimento(){return this.dataNascimento;}
    public int getIdade(){
        /* Alternativa caso seja necessaria mais precisao:
         * 
         * GregorianCalendar agora = new GregorianCalendar();
         * long diferenca = agora.getTimeInMillis() - this.getTimeInMillis();
         * return (double) diferenca/(1000*60*60*24*30*365);*/
        
        GregorianCalendar agora = new GregorianCalendar();
        return agora.get(Calendar.YEAR) - this.dataNascimento.get(Calendar.YEAR);
    }
    public String getDesportoFavorito(){return this.desportoFavorito;}
    public Permissoes getPermissoes(){return this.permissoes;}
    public int getFreqCardio(){return this.fcr;}
    public Set<String> getAmigos(){
        Set<String> amg = new HashSet<String>();
        for(String n : amigos)
            amg.add(n);
        return amg;
    }
    
    
    /*
      * Setters
      */
    public void setNome(String nome){this.nome=nome;}
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password){this.password=password;}
    public void setAltura(int altura){this.altura=altura;}
    public void setPeso(int peso){this.peso=peso;}
    public void setGenero(Genero genero){this.genero=genero;}
    public void setDataNascimento(int ano, int mes, int dia){
        this.dataNascimento = new GregorianCalendar(ano, mes, dia);
    }
    public void setDesportoFavorito(String desporto){this.desportoFavorito = desporto;}
    public void setPermissoes(Permissoes permissoes){this.permissoes = permissoes;}
    public void setFreqCardio(int fcr){this.fcr=fcr;}
    public void setAmigos(Set<String> amg) {
        for(String n : amg)
            amigos.add(n);
    }
    
    public void addActivity(Activity a){
    	actividadesUser.add((Activity) a.clone());
    }
    
    public Set<Activity> actividadesEntre(GregorianCalendar dataInferior, GregorianCalendar dataSuperior){
    	 Set<Activity> res = new TreeSet<Activity>(new ActivityComparatorByDate());
         /*Actividades artificiais para efeitos de comparacao*/
         Activity a1 = new Ciclismo(); a1.setDate(dataInferior);
         Activity a2 = new Ciclismo(); a2.setDate(dataSuperior);
         
         for(Activity ac : this.actividadesUser.subSet(a1, true, a2, true)){
             res.add((Activity) ac.clone());
         }
         
         return res;
    }
    
    public double getForma(){
        /*WIP*/
        return 0.0;
    }
    
    /*Should be called in addActivity*/
    /*TODO: TEST it*/
    @SuppressWarnings("unused")
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
    /**
     * Metodos para gerir amigos (string code: email)
     */
    public void addAmigo(String amg){
        amigos.add(amg);
    }
    
    public boolean existeAmigo(String amg) {
        return (amigos.contains(amg));
    }
    
    public void removeAmigo(String amg) {
        amigos.remove(amg);
    }
    
    
    public int hashCode(){
        return getEmail().hashCode();
    }
    
    public User clone(){return new User(this);}
    
    public String toString(){
        StringBuilder stringb = new StringBuilder();
        stringb.append("Nome: " + this.nome + "\n");
        stringb.append("Email: " + this.email + "\n");
        stringb.append("Sexo: " + this.genero.name() + "\n");
        stringb.append("Data Nascimento: " + this.dataNascimento.toString() + "\n");
        return stringb.toString();
    }
    
    public boolean equals(Object obj) {
      if(this == obj) return true; 
      if((obj == null) || (this.getClass() != obj.getClass())) return false;
      User u = (User) obj;
      return this.email.equals(u.getEmail());
   } 
    
}
