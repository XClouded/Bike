package com.yhj.app.bike.model.pojo;

import java.io.Serializable;

import com.google.gson.Gson;

public class WriteBackState implements Serializable {

	private static final long serialVersionUID = -3461345938719211311L;

	String publish;
	WriteBackMsg comment;
	WriteBackMsg qqweibo;
	WriteBackMsg qzone;
	WriteBackMsg sina;

	public WriteBackMsg getComment() {
		return comment;
	}

	public String getPublish() {
		return publish;
	}

	public WriteBackMsg getQqweibo() {
		return qqweibo;
	}

	public WriteBackMsg getQzone() {
		return qzone;
	}

	public WriteBackMsg getSina() {
		return sina;
	}

	public void setComment(WriteBackMsg comment) {
		this.comment = comment;
	}

	public void setPublish(String publish) {
		this.publish = publish;
	}

	public void setQqweibo(WriteBackMsg qqweibo) {
		this.qqweibo = qqweibo;
	}

	public void setQzone(WriteBackMsg qzone) {
		this.qzone = qzone;
	}

	public void setSina(WriteBackMsg sina) {
		this.sina = sina;
	}

	@Override
	public String toString() {
		return new Gson().toJson(this);
	}

	public class Data implements Serializable {
		private static final long serialVersionUID = -3033016102754296300L;
		String access_token;
		String id;
		String refresh_token;
		String time;

		public String getAccess_token() {
			return access_token;
		}

		public String getId() {
			return id;
		}

		public String getRefresh_token() {
			return refresh_token;
		}

		public String getTime() {
			return time;
		}

		public void setAccess_token(String access_token) {
			this.access_token = access_token;
		}

		public void setId(String id) {
			this.id = id;
		}

		public void setRefresh_token(String refresh_token) {
			this.refresh_token = refresh_token;
		}

		public void setTime(String time) {
			this.time = time;
		}
	}

	public class WriteBackMsg implements Serializable {
		private static final long serialVersionUID = -7386905258765894761L;
		Data data;
		String ret;
		String retmsg;

		public Data getData() {
			return data;
		}

		public String getRet() {
			return ret;
		}

		public String getRetmsg() {
			return retmsg;
		}

		public void setData(Data data) {
			this.data = data;
		}

		public void setRet(String ret) {
			this.ret = ret;
		}

		public void setRetmsg(String retmsg) {
			this.retmsg = retmsg;
		}
	}

}
