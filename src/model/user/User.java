package model.user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/*
 * Classe com informacao dos utilizadores.
 * @author andrerfcsantos
 */

public class User{
    private String email;
    private String nome;
    private String password;
    private Genero genero;
    private int altura;
    private int peso;
    private GregorianCalendar dataNascimento;
    private String desportoFavorito;
    private Permissoes permissoes;
    private int fcr; /*frequencia cardiaca em repouso - para calculo das calorias*/
    
    
    /**A implementar quando se tiver implementacoes concretas das classes das actividades.
     * - Informa��o das actividades que realizou;
     * - Records
     * - Lista de Amigos -> provavelmente tera que ser noutra classe.
     * - calendario das actividades do utilizador (requisito)!!!
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
        this.desportoFavorito = "";
        this.permissoes = Permissoes.Guest;
        this.fcr = 0;
    }
    
    public User(String nome, String email, String Password, Genero genero, int altura, int peso, 
                int diaNascimento, int mesNascimento, int anoNascimento, 
                String desportoFavorito, Permissoes permissoes, int fcRepouso){
                    this.nome = nome;
                    this.email = email;
                    this.password = password;
                    this.altura = altura;
                    this.peso = peso;
                    this.genero = genero;
                    this.dataNascimento = new GregorianCalendar(anoNascimento, mesNascimento, diaNascimento);
                    this.desportoFavorito = desportoFavorito;
                    this.permissoes = permissoes;
                    this.fcr = fcRepouso;
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
    public void setFreqCardio(){this.fcr=fcr;}
    
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
