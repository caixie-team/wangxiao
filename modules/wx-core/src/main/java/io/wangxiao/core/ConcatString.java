package io.wangxiao.core;

import java.io.Serializable;

/**
 * 把字符串用"_"线进行连接，因为多处用到，所以抽出来成为一个类
 */
public class ConcatString  implements Serializable{

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((this.result == null) ? 0 : this.result.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ConcatString other = (ConcatString) obj;
		if (result == null) {
			if (other.result != null)
				return false;
		} else if (!result.equals(other.result))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return result;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -982020859949549479L;
	
	private String result;
	
	public ConcatString(String ...strings ){
		StringBuffer buffer = new StringBuffer();
		if(strings != null){
			for( String string : strings){
				if(string != null){
					buffer.append(string).append("_");
				}					
			}
		}
		if(buffer.length() >0){
			result = buffer.toString().substring(0, buffer.length()-1);
		}
	}
	
}
