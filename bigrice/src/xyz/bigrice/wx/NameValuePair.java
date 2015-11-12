package xyz.bigrice.wx;
/**
	 * 参数键值对.
	 * @author tangxiucai
	 *
	 */
	public class NameValuePair{
		private String name;
		private String value;
		
		/**
		 * 参数键值对对象.
		 * @param name 参数名称
		 * @param value 参数值
		 */
		public NameValuePair(String name, String value){
			this.name = name;
			this.value = value;
		}
		
		public String getName(){
			return this.name;
		}
		
		public String getValue(){
			return this.value;
		}
	}