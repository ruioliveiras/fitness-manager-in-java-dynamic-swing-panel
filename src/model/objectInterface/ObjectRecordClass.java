package model.objectInterface;


public class ObjectRecordClass{


	/**
	 * Interface {@link ObjectRecord} para Objectos que tenham recordes. <br>
	 * 
	 * As principais funcionalidades são: <br>
	 * 		<li> porta de acesso aos varios recordes deste objecto
	 *          ({@link getRecordSize},{@link getRecord},{@link get} )
	 * 		<li> Comparar varios objetos e corrigir 
	 *          ({@link compareRecord},{@link correct})
	 * 
	 * 
	 * @author POO 
	 *
	 */
	public interface ObjectRecord {
			/**
		 * Quantidade de recordes que o objecto tem
		 * @return Quantidade de recordes que o objecto tem
		 */
		public  int getRecordSize();
		/**
		 * Getter de um Record de uma Actividade.
		 * @param index index do Record pretendido
		 * @return Record
		 */
		public Record getRecord(int index);
		/**
		 * Getter de um Atributo
		 * @param iAttr
		 * @return
		 */
		public long get(EnumAttr iAttr);
		/**
		 * Getter do valor Fixo de um record
		 * @param recordType
		 * @return
		 */
		public long getValue(Record recordType);
		/**
		 * Compara este {@link ObjectRecord} com outro {@link ObjectRecord}, e calcula qual é o melhor
		 * para o Record: recordType.
		 * 
		 * @param otherActivity outro {@link ObjectRecord}
		 * @param recordType o tipo de {@link Record} a comparar
		 * @return  1 se este {@link ObjectRecord} for melhor
		 * 			0 se forem igual
		 *  	   -1 se o otherActivity for melhor
		 */
		public long compareRecord(ObjectRecord otherActivity,Record recordType);
		/**
		 * Corrige esta actividade para este Record 
		 * @param record record para corrigir
		 */
		public void correct(Record record);
	
		/**
		 * Cria String representativa desta atividade no record recebido
		 * @param recordType tipo do Record
		 * @return String representativa desta atividade no record recordType
		 */
		public String getRecordToString(Record recordType);

		
		/**
		 * Verifica se este Record, é do tipo: Quanto maior melhor out quanto menor melhor
		 * 
		 * @param recordType tipo do record
		 * @return true se for Quanto maior melhor
		 */
		public boolean isRecordBiggerBetter(Record recordType);
	}

	/**
	 * Representa um Record
	 *
	 */
	public interface Record {
		/** 
		 * Getter
		 *  @return index do record neste contexto (devido a extender)
		 */
		public int ordinal();
		/**
		 * Getter 
		 *  @return index do record no contexto geral (devido a extender)
		 */
		public int getrecordType();
		/**
		 * Getter
		 * @return valor fixo, do record
		 */
		public long getValue();
		/**
		 * Testa se um valor é "parecido", isto é do mesmo tipo deste record.
		 * @param value valor para comparar com o {@link getValue}
		 * @return true se for do mesmo tipo.
		 *         false se for diferente
		 */
		public boolean similar(long value);
		/**
		 * Gettter
		 * @return {@link EnumAttr} que representa o atributo que é fixo neste {@link Record}, e deve ser comparado 
		 * com o {@link similar}
		 */
		public EnumAttr getFixed();
		/**
		 * Getter
		 * @return {@link EnumAttr} que representa o atributo que é variavel neste {@link Record}
		 */
		public EnumAttr getMov();
		/**
		 * Getter 
		 * @return o nome do Record
		 */
		public String getName();	
	}

	/**
	 * Atributo de um Objeto com com record 
	 */
	public interface EnumAttr{
		/** 
		 * Getter
		 *  @return index do EnumAttr neste contexto (devido a extender)
		 */
		public int ordinal();
		/**
		 * Getter 
		 *  @return index do EnumAttr no contexto geral (devido a extender)
		 */
		public int getAttrType();
		/**
		 * Getter 
		 * @return o nome do Record
		 */
		public String getName();
	}
	
}
