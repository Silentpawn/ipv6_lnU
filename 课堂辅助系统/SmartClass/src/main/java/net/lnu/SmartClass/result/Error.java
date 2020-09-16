/**
 * 
 */
package net.lnu.SmartClass.result;

/**
 * @author Administrator
 *
 */
public class Error implements Result {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String status;
	private String msg;
	private Object data;
	public Error(String status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public Error(String msg, Object data) {
		super();
		this.status = "error";
		this.msg = msg;
		this.data = data;
	}
	public Error(String msg) {
		super();
		this.status = "error";
		this.msg = msg;
		this.data = null;
	}
	public Error(Object data) {
		super();
		this.status = "error";
		this.msg = null;
		this.data = data;
	}
	public Error() {
		super();
		this.status = "error";
		this.msg = null;
		this.data = null;
	}
	/**
	 * @return the serialVersionUID
	 */
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	/**
	 * @param serialVersionUID the serialVersionUID to set
	 */
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
}
