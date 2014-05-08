package model.user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

import model.activityHigh.Activity;

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
    
    
    /*Should be called in addActivity*/
    /*TODO: TEST it*/
    @SuppressWarnings("unused")
    private void addRecord(Activity activity){
        HashMap<Integer, Activity> actRecords = recordes.get(activity.getClass());
        Activity actualRecord;
        
        /*iF Don't exist, add*/
        if (actRecords == null){
            recordes.put(activity.getClass(), new HashMap<Integer, Activity> ());
            actRecords = recordes.get(activity.getClass());
        }
        
        actualRecord = actRecords.get(activity.getRecordType());
        if (actualRecord == null || activity.compareRecord(actualRecord) > 0){
            actRecords.put(activity.getRecordType(), activity);
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
