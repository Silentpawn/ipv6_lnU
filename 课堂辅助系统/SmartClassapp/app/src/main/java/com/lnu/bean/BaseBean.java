package com.lnu.bean;


/**
 */
public class BaseBean {

	public int result;//
	public String message;
	public int flag;

	// 1:��
	public final static int MESSAGE_SUCCESS = 1;
	// 0:��
	public final static int MESSAGE_FAILED = 0;

	// -1:����
	public final static int MESSAGE_DECODE_WRONG = -1;

	public void setResult(int result) {
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

}
