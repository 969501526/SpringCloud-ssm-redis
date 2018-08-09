package com.clj.spring.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Msg implements Serializable {
	private int code;
	private String msg;
	private Map<String,Object> extend=new HashMap<String,Object>();
	public Msg() {
		// TODO Auto-generated constructor stub
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Map<String, Object> getExtend() {
		return extend;
	}
	public void setExtend(Map<String, Object> extend) {
		this.extend = extend;
	}
	public static Msg success(){
		Msg result=new Msg();
		result.setCode(10000);
		result.setMsg("请求成功");
		return result;
	}
	public static Msg fail(){
		Msg result=new Msg();
		result.setCode(20000);
		result.setMsg("请求失败");
		return result;
	}
	public static Msg fail(String msg){
		Msg result=new Msg();
		result.setCode(20000);
		result.setMsg(msg);
		return result;
	}

	@Override
	public String toString() {
		return "返回客户端结果：Msg [code=" + code + ", msg=" + msg + ", extend=" + extend + "]";
	}
	public Msg add(String key,Object value){
		this.getExtend().put(key, value);
		return this;
	}
	
	public Msg pramasFail() {
		this.getExtend().put("err_code", "请求参数格式异常！");
		return this;
	}
	
	public Msg resultFail() {
		this.getExtend().put("err_code", "未查询到合适的数据！");
		return this;
	}
}
