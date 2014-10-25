
package com.yhj.app.bike.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SLog {
    public static final String BIKE = "BIKE_TO_TRAVEL";
    /**
     * 调试信息总开关
     */
    public static boolean ISDEBUG = MobileUtil.getDebugMode();
    /**
     * 是否输出运行时日志现场信息
     */
    private static final boolean IS_LOG_RUNTIME_INFO = true;

    public static void d(String msg) {
        if (ISDEBUG) {
            android.util.Log.d(BIKE, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (ISDEBUG) {
            android.util.Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if (ISDEBUG) {
            android.util.Log.e(BIKE, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (ISDEBUG) {
            android.util.Log.e(tag, msg);
        }
    }

    public static void e(String msg, Throwable tr) {
        if (ISDEBUG) {
            android.util.Log.e(BIKE, msg, tr);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (ISDEBUG) {
            android.util.Log.e(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (ISDEBUG) {
            android.util.Log.i(tag, msg);
        }
    }

    public static void i(String msg) {
        if (ISDEBUG) {
            android.util.Log.i(BIKE, msg);
        }
    }

    public static void v(String tag, String msg) {
        if (ISDEBUG) {
            android.util.Log.v(tag, msg);
        }
    }

    public static void v(String msg) {
        if (ISDEBUG) {
            android.util.Log.v(BIKE, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (ISDEBUG) {
            android.util.Log.w(tag, msg);
        }
    }

    public static void w(String msg) {
        if (ISDEBUG) {
            android.util.Log.w(BIKE, msg);
        }
    }

    /**
     * 输出程序运行时信息,在调试现场调用
     * 
     * @author jackiecheng
     * @return [<code>file</code>:当前运行文件;<code>method</code>:当前运行方法;
     *         <code>LineNumber</code>:当前运行代码行]
     */
    public static String getTraceInfo() {
        if (!ISDEBUG) {
            return "";
        }
        if (!IS_LOG_RUNTIME_INFO) {
            return "";
        }

        StringBuffer sb = new StringBuffer();
        StackTraceElement[] stacks = new Throwable().getStackTrace();
        sb.append("[file:").append(stacks[1].getFileName()).append(",line:").append(stacks[1].getLineNumber()).append(",method:").append(stacks[1].getMethodName() + "];");
        return sb.toString();
    }

    /**
     * 将日志写入文件
     * 
     * @param filepath
     * @param tag
     * @param msg
     */
    public static synchronized void f(String filepath, String tag, String msg) {
        if (ISDEBUG) {
            android.util.Log.d(tag, msg);

            if (MobileUtil.isSDCardExists()) {
                FileOutputStream fos = null;
                try {

                    filepath = filepath + tag + ".txt";

                    File file = new File(filepath);
                    if (!file.exists()) {
                        new File(file.getParent()).mkdirs();
                    }
                    fos = new FileOutputStream(filepath, true);
                    msg = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "-->" + msg + "\r\n";
                    byte[] m = msg.getBytes();
                    fos.write(m);
                } catch (Exception e) {
                    SLog.e(e.toString(), e);
                } finally {
                    try {
                        fos.close();
                        fos = null;
                    } catch (Exception e) {
                    }
                }
            }

        }
    }

    public static void push(String data, String name) {
        if (ISDEBUG) {
            SLog.d(data + ", " + name);
            FileUtil.writePushMsg(data, name);
        }
    }

    public static void ff(final String filepath, final String tag, final String msg) {
        // if (ISDEBUG) {
        // new Thread(new Runnable() {
        // @Override
        // public void run() {
        // SLog.f(filepath, tag, msg);
        // }
        // }).start();
        // }
    }
}
