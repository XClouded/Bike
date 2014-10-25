package com.yhj.app.bike.download;

import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Hashtable;


public class HttpMsg {

	//request type
	public static final int UNKNOWN = 50;

	public static final int HTML = 51;//include html and wml

	public static final int IMAGE = 52;//jpg, png, gif

	public static final int CSS = 53;

	//big package
	public static final int MAX_PACKAGE = 400; //*500

	public static final int PACKAGE_BYTE = 1024; //*500

	//cache
	public static final String CACHE_CONTROL = "cache-control";

	public static final String LAST_MODIFIED = "last-modified";

	public static final String MAX_AGE = "max-age";

	public static final String NO_CACHE = "no-cache";

	//content-type
	public static final String CONTENT_TYPE = "Content-Type";

	public static final String SET_COOKIE = "Set-Cookie";//Add by spirit 2008-6-10, for cookie

	public static final String COOKIE = "Cookie";//Add by spirit 2008-6-18, for cookie

	public static final String HTTP_REFERER = "REFERER";

	public static final String TYPE_WML = "text/vnd.wap.wml";

	public static final String TYPE_WMLC = "application/vnd.wap.wmlc";//  add by aki

	public static final String TYPE_WBXML = "application/vnd.wap.wbxml"; // add by peter

	public static final String TYPE_JAD = "text/vnd.sun.j2me.app-descriptor";

	public static final String TYPE_JAR = "application/java-archive";

	public static final String TYPE_HTML = "text/html";

	public static final String TYPE_TEXT = "text/plain";

	public static final String TYPE_IMAGE = "image";

	public static final String TYPE_OCTET_STREAM = "octet-stream"; //二进制流

	//err
	public static final String ERR_BIGPACHAGE = "the package is over max limit";

	//file
	public static final String FILE_JAD = "jad";
	public static final String FILE_JAR = "jar";
	public static final String FILE_SIS = "sis";
	public static final String FILE_SISX = "sisx";
	public static final String FILE_NTH = "nth";

	public static final String FILE_TEL = "wtai://wp/mc;";

	public static final String FILE_UNKNOWN = "unknown";

	//charset
	public static final String CHARSET = "charset";

	public static final String UTF8 = "utf-8";

	public static final String GB2312 = "GB2312";

	//request method
	public static final String METHOD_GET = "GET";

	public static final String METHOD_POST = "POST";

	private int threadPriority = Thread.MIN_PRIORITY;

	private int requestType = UNKNOWN;

	private String errString = null;

	private String connectString;

	private String realConnectString;

	private String requestMethod = "GET";

	private int serial = 0;

	private IProcessor processor;

	//private IErrorProcessor errorProcessor;

	private byte[] data;

	private Hashtable<String, String> requestProperty = new Hashtable<String, String>();

	private boolean downloadSign = false;

	private boolean igoreBigPkg = false;

	private int downloadBufSize = 16384;

	private OutputStream output = null;

	/**
	 * �ڷ�������ʱ���ô��ֶα�ʾ����ţ�����Ƿ��������ش��󣬺�4λ�Ƿ��������صĴ���š�
	 */
	public int responseCode = -1;

	/**
	 * �ܽ�������ֽ���
	 */
	public long totalLen = 0;

	/**
	 * ��ǰ��������ֽ���
	 */
	public long curLen = 0;

	private String limitedContentType = null;

	/**
	 * if deal the payment page send by china mobile
	 */
	public final boolean bVerifyPayment;

	//�Ƿ������
	private boolean isProcessingError;

	public boolean isProcessingError() {
		return isProcessingError;
	}

	public void setProcessingError(boolean isProcessingError) {
		this.isProcessingError = isProcessingError;
	}

	public String getLimitedContentType() {
		return limitedContentType;
	}

	public void setLimitedContentType(String limitedContentType) {
		this.limitedContentType = limitedContentType;
	}

	//add by 081204
	private int MSG_ID = 0;

	public int getMSGID() {
		return MSG_ID;
	}

	public HttpMsg(int MSG_ID, String connectString, byte[] data, IProcessor processor) {
		this(connectString, data, processor, false);
		this.MSG_ID = MSG_ID;
	}

	public HttpMsg(String connectString, byte[] data, IProcessor processor) {
		this(connectString, data, processor, false);
	}

	public HttpMsg(String connectString, byte[] data, IProcessor processor, boolean verifyPaymentPage) {
		this.connectString = connectString;
		if (connectString != null)
			this.realConnectString = connectString.substring(0, connectString.length());
		else
			this.realConnectString = null;
		this.processor = processor;
		this.data = data;
		this.bVerifyPayment = verifyPaymentPage;
	}

	/**
	 * ��ȡ����
	 * 
	 * @return
	 */
	public String getUrl() {
		return connectString;
	}

	public void setUrl(String url) {
		connectString = url;
		this.realConnectString = connectString.substring(0, connectString.length());
	}

	public void setRealUrl(String realUrl) {
		realConnectString = realUrl;
	}

	public String getRealUrl() {
		return realConnectString;
	}

	public IProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(IProcessor p) {
		processor = p;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public void setDownloadSign(boolean sign) {
		downloadSign = sign;
	}

	public boolean getDownloadSign() {
		return downloadSign;
	}

	public int getSerial() {
		return serial;
	}

	public void setSerial(int serial) {
		this.serial = serial;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int type) {
		requestType = type;
	}

	public void setErrorString(String errStr) {
		errString = errStr;
	}

	public String getErrorString() {
		return errString;
	}

	public String getRequestMethod() {
		return requestMethod;
	}

	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}

	public void setPriority(int priority) {
		threadPriority = priority;
	}

	public int getPriority() {
		return threadPriority;
	}

	/**
	 * @return ���� totalLen��
	 */
	public long getTotalLen() {
		return totalLen;
	}

	/**
	 * @return ���� curLen��
	 */
	public long getCurLen() {
		return curLen;
	}

	/**
	 * �������ӵ�����
	 */
	public void setRequestProperty(String key, String property) {
		requestProperty.put(key, property);
	}

	public String getRequestProperty(String key) {
		return (String) requestProperty.get(key);
	}

	public Enumeration<String> keys() {
		return requestProperty.keys();
	}

	public boolean isIgoreBigPkg() {
		return igoreBigPkg;
	}

	public void setIgoreBigPkg(boolean igoreBigPkg) {
		this.igoreBigPkg = igoreBigPkg;
	}

	public int getDownloadBufSize() {
		return downloadBufSize;
	}

	public void setDownloadBufSize(int downloadBufSize) {
		if (downloadBufSize > 0) {
			this.downloadBufSize = downloadBufSize;
		}
	}

	public OutputStream getOutput() {
		return output;
	}

	public void setOutput(OutputStream output) {
		if (this.output == null) {
			this.output = output;
		}
	}

	public int getResponseCode() {
		return responseCode;
	}

}
