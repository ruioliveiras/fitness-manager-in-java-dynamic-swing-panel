package user;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;

/*
 * Classe com informacao dos utilizadores.
 * @author andrerfcsantos
 */

public class User{
    
    public enum Genero{MASCULINO, FEMININO, NA}
    public enum Permissoes{ADMIN, USER, GUEST}
    
    private String email;
    private String nome;
    private String password;
    private Genero genero;
    private int altura;
    private int peso;
    private GregorianCalendar dataNascimento;
    private String desportoFavorito;
    private Permissoes permissoes;
    
    
    /*A implementar quando se tiver implementacoes concretas das classes das actividades.
     * - Informação das actividades que realizou;
     * - Records
     * - Lista de Amigos -> provavelmente tera que ser noutra classe.
       */
     
     /*
      * Construtores
      */
    public User(){
        this.nome = "guest";
        this.email = "";
        this.password = "guest";
        this.genero = Genero.NA;
        this.altura = 0;
        this.peso = 0;
        this.dataNascimento = new GregorianCalendar();
        this.desportoFavorito = "";
        this.permissoes = Permissoes.GUEST;
    }
    
    public User(String nome, String email, String Password, Genero genero, int altura, int peso, 
                int diaNascimento, int mesNascimento, int anoNascimento, 
                String desportoFavorito, Permissoes permissoes){
                    this.nome = nome;
                    this.email = email;
                    this.password = password;
                    this.altura = altura;
                    this.peso = peso;
                    this.genero = genero;
                    this.dataNascimento = new GregorianCalendar(anoNascimento, mesNascimento, diaNascimento);
                    this.desportoFavorito = desportoFavorito;
                    this.permissoes = permissoes;
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
    
    /*
      * Setters
      */
    public void setNome(String nome){this.nome=nome;}
    public void setEmail(String email){this.email=email;}
    public void setPassword(String password){this.password=password;}
    public void setAltura(){this.altura=altura;}
    public void setPeso(){this.peso=peso;}
    public void setGenero(Genero genero){this.genero=genero;}
    public void setDataNascimento(int ano, int mes, int dia){
        this.dataNascimento = new GregorianCalendar(ano, mes, dia);
    }
    public void setDesportoFavorito(String desporto){this.desportoFavorito = desporto;}
    public void setPermissoes(Permissoes permissoes){this.permissoes = permissoes;}
    
    public String generoToString(){
        String aux;
        switch(this.genero){
            case MASCULINO:
                aux = "Masculino";
                break;
            case FEMININO:
                aux = "Feminino";
                break;
            case NA:
                aux = "Nao inserido";
                break;
            default:
                System.err.println("ERRO: Passado género não conhecido.");
                aux = "ERRO";
                break;
        }
        return aux;
    }
    
    public String permissoesToString(){
        String aux;
        switch(this.permissoes){
            case ADMIN:
                aux = "ADMIN";
            case USER:
                aux = "USER";
            case GUEST:
                aux = "GUEST";
            default:
                System.err.println("ERRO: Passada permissão não conhecida.");
                aux = "ERRO";
                break;
        }
        return aux;
    }
    
    public User clone(){return new User(this);}
    
    public String toString(){
        StringBuilder stringb = new StringBuilder();
        stringb.append("Nome: " + this.nome + "\n");
        stringb.append("Email: " + this.email + "\n");
        stringb.append("Sexo: " + this.generoToString() + "\n");
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
