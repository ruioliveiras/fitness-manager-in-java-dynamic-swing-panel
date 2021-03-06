/**TODO:
 *          Santos - acrescentar var. forma + metodos
 */ 

package model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import model.activity.ActivityComparatorByDate;
import model.activity.altimerty.Ciclismo;
import model.activity.distance.Natacao;
import model.exeption.RecordDontExitExeception;
import model.objectEnum.Genero;
import model.objectEnum.Permissoes;
import model.objectInterface.ObjectClonable;
import model.objectInterface.ObjectKey;
import core.FormaCalculation;
import core.util.Manager;
import core.util.Manager.OnManagerAdd;
import core.util.ManagerSet;

/*
 * Classe com informacao dos utilizadores.
 */
public class User implements ObjectKey,ObjectClonable,Serializable{

	private static final long serialVersionUID = 1848764924127290251L;
	private String email;
	private String nome;
	private String password;
	private Genero genero;
	private int altura; /*cm*/
	private int peso; /*kg*/
	private GregorianCalendar dataNascimento;
	private Activity desportoFavorito;
	private Permissoes permissoes;
	private Map<Class<?>, HashMap<Integer, Activity>>  recordes; /* 1º level key class, values 2ºlevel key recordtype, values recordActivitys*/
	private int fcr; /*frequencia cardiaca em repouso - para calculo das calorias*/

	private Manager<String> amigos;  /*emails de amigos: HashSet com chaves para aceder ao HashMap da rede social*/
	private Manager<String> convitesAmigos; /*emails de amigos: convites - HashSet*/
	private Manager<Activity> actividadesUser;/*Actividades do User: TreeSet -ComparatorByDate- */





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
		this.desportoFavorito = new Natacao();
		this.permissoes = Permissoes.Guest;
		this.fcr = 0;
		this.convitesAmigos = new ManagerSet<String>(new HashSet<String>());
		this.actividadesUser = new ManagerSet<Activity>(new TreeSet<Activity>(new ActivityComparatorByDate()));
		this.amigos = new ManagerSet<String>(new  HashSet<String>());
		this.amigos.setAddListener(new OnManagerAdd<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean beforeAdd(String obj) {
				convitesAmigos.remove(obj);
				return true;
			}
		});
	}
	public User(String email){
		this();
		this.email = email;
	}

	public User(String nome, String email, String password, Genero genero, int altura, int peso, 
			int diaNascimento, int mesNascimento, int anoNascimento, 
			Activity desportoFavorito, Permissoes permissoes, int fcRepouso){
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.altura = altura;
		this.peso = peso;
		this.genero = genero;
		this.recordes = new HashMap<Class<?>, HashMap<Integer, Activity>>();
		this.dataNascimento = new GregorianCalendar(anoNascimento, mesNascimento-1, diaNascimento);
		this.desportoFavorito = desportoFavorito;
		this.permissoes = permissoes;
		this.fcr = fcRepouso;
		this.amigos = new ManagerSet<String>(new HashSet<String>());
		this.convitesAmigos = new ManagerSet<String>(new HashSet<String>());
		this.actividadesUser = new ManagerSet<Activity>(mListenerBeforeAdd,new TreeSet<Activity>(new ActivityComparatorByDate()));
		this.amigos.setAddListener(new OnManagerAdd<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean beforeAdd(String obj) {
				convitesAmigos.remove(obj);
				return true;
			}
		});
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
		this.amigos = new ManagerSet<String>(new HashSet<String>(u.amigosManager().collection()));
		this.convitesAmigos = new ManagerSet<String>(new HashSet<String>(u.convitesManager().collection()));

		this.recordes = new HashMap<Class<?>, HashMap<Integer, Activity>>();

		recordes = u.getRecords();



		TreeSet<Activity> treeActividadesUser = new TreeSet<Activity>(new ActivityComparatorByDate());
		treeActividadesUser.addAll(u.atividadesManager().collection());
		this.actividadesUser = new ManagerSet<Activity>(mListenerBeforeAdd, treeActividadesUser);
		this.amigos.setAddListener(new OnManagerAdd<String>() {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean beforeAdd(String obj) {
				convitesAmigos.remove(obj);
				return true;
			}
		});
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
	public Activity getDesportoFavorito()         {return (Activity) this.desportoFavorito.clone();}

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
	{this.dataNascimento = new GregorianCalendar(ano, mes-1, dia);}//jan = 0 !!!
	public void setDesportoFavorito(Activity desporto){this.desportoFavorito = (Activity) desporto.clone();}
	public void setPermissoes(Permissoes permissoes){this.permissoes = permissoes;}
	public void setGenero(Genero genero)              {this.genero=genero;}

	/*
	 * Getttes not direct (with calculation)
	 */
	public int getIdade(){
		GregorianCalendar agora = new GregorianCalendar();
		int idade = agora.get(Calendar.YEAR) - this.dataNascimento.get(Calendar.YEAR);
		int idade_mes = this.dataNascimento.get(Calendar.MONTH) - (agora.get(Calendar.MONTH));
		int idade_dia = this.dataNascimento.get(Calendar.DAY_OF_MONTH) - agora.get(Calendar.DAY_OF_MONTH);
		if(idade_mes > 0) return (idade - 1);
		if((idade_mes == 0) && (idade_dia > 0)) return (idade - 1);

		return idade;
	}

	public double getForma(){
		return FormaCalculation.calculaForma(this);
	}

	public Manager<String> amigosManager(){return this.amigos;}
	public Manager<String> convitesManager(){return this.convitesAmigos;} 
	public Manager<Activity> atividadesManager(){return this.actividadesUser;}    

	public Activity getRecord(Class<? extends Activity> classe,int recordType) throws RecordDontExitExeception{
		if(recordes.get(classe) == null){
			throw new RecordDontExitExeception();
		}
		if (recordes.get(classe).get(recordType)==null){
			throw new RecordDontExitExeception();
		}

		return recordes.get(classe).get(recordType);
	}

	public long getRecordValue(Class<? extends Activity> classe,int recordType) throws RecordDontExitExeception{
		Activity a = getRecord(classe, recordType);
		return a.getValue(a.getRecord(recordType));
	}


	public Set<Activity> actividadesEntre(GregorianCalendar dataInferior, GregorianCalendar dataSuperior){
		TreeSet<Activity> aux = new TreeSet<Activity>(new ActivityComparatorByDate());
		Set<Activity> res =  new TreeSet<Activity>( new ActivityComparatorByDate());
		aux.addAll(actividadesUser.collection());
		/*Actividades artificiais para efeitos de comparacao*/
		Activity a1 = new Ciclismo(); a1.setDate(dataInferior);
		Activity a2 = new Ciclismo(); a2.setDate(dataSuperior);


		res.addAll( aux.subSet(a1, true, a2, true));



		return res;
	}

	private transient Manager.OnManagerAdd<Activity> mListenerBeforeAdd = new Manager.OnManagerAdd<Activity>() {
		private static final long serialVersionUID = 1L;

		@Override
		public boolean beforeAdd(Activity obj) {
			addRecord(obj);
			return true;
		}
	};

	public Map<Class<?>, HashMap<Integer, Activity>> getRecords(){
		Map<Class<?>, HashMap<Integer, Activity>> res = new HashMap<Class<?>, HashMap<Integer,Activity>>();
		Iterator<Class<?>> iteClass = recordes.keySet().iterator();
		Iterator<HashMap<Integer, Activity>> iteCateg = recordes.values().iterator();
		while(iteClass.hasNext()){
			Class<?> _class = iteClass.next();
			HashMap<Integer, Activity> hashMap = iteCateg.next();

			Iterator<Integer> _iteRecType = hashMap.keySet().iterator();
			Iterator<Activity> _iteAct = hashMap.values().iterator();

			hashMap = new HashMap<Integer, Activity>(); /*using hashMap to Add */
			res.put(_class,hashMap);
			while(_iteRecType.hasNext()){
				hashMap.put(_iteRecType.next(),_iteAct.next().clone());
			}
		}
		return res;
	}


	//	public Map<Integer,Activity> getRecords() {
	//		Map<Integer,Activity> res = new HashMap<Integer,Activity>();
	//		for (HashMap<Integer, Activity>  hash: recordes.values()) {
	//			Iterator<Integer> ite = hash.keySet().iterator();
	//			for(Activity act: hash.values()){
	//				res.put(ite.next(), act.clone());
	//			}
	//		}
	//		
	//		
	//		return res;
	//	} 


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
			record = actualRecords.get(i); /*record can be null, but compareRecord admits null activities*/
			if (activity.compareRecord(record,activity.getRecord(i)) >= 0){
				Activity rec = activity.clone();
				rec.correct(activity.getRecord(i));
				actualRecords.put(i, rec);
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
		stringb.append("Nome: " + this.nome + ", ");
		stringb.append("Email: " + this.email + ", ");
		stringb.append("Sexo: " + this.genero.name() + ", ");
		stringb.append("Data Nascimento: " + this.dataNascimento.toString());
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
