
package com.yhj.app.bike.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.util.ByteArrayBuffer;

import android.content.Context;

import com.yhj.app.bike.config.Constants;
import com.yhj.app.bike.system.Application;
import com.yhj.app.bike.system.ExternalStorageReceiver;

public class FileUtil {
    private static SimpleDateFormat dataFormatFileName = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SS");
    private static SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
    private static int lineNum = 0;
    private static final int BUFFER_SIZE = 4096;

    /**
     * 把str写入文件
     * 
     * @param filePath 文件路径
     * @param str json字符串
     * @return 写入成功与否
     * @author haiyandu
     * @since 2011-10-24
     */
    public static boolean writeString(String filePath, String str, boolean isAppend) {
        FileOutputStream out = null;
        try {
            File file = makeDIRAndCreateFile(filePath);
            out = new FileOutputStream(file, isAppend);
            out.write(str.getBytes());
            out.flush();
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (Exception e) {
                    SLog.e(SLog.BIKE, e.toString());
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean writeBytes(String filePath, byte[] str, boolean isAppend) {
        FileOutputStream out = null;
        try {
            File file = makeDIRAndCreateFile(filePath);
            out = new FileOutputStream(file, isAppend);
            out.write(str);
            out.flush();
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
            return false;
        } finally {
            if (out != null) {
                try {
                    out.close();
                    out = null;
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                    return false;
                }
            }
        }
        return true;
    }

    // /**
    // * 把json串写入文件
    // *
    // * @param filePath
    // * 文件路径
    // * @param json
    // * json字符串
    // * @return 写入成功与否
    // * @author haiyandu
    // * @since 2011-10-24
    // */
    // public static boolean json2File(String filePath, String json) {
    // FileOutputStream out = null;
    // try {
    // File file = makeDIRAndCreateFile(filePath);
    // out = new FileOutputStream(file);
    // out.write(json.getBytes());
    // out.flush();
    // } catch (IOException e) {
    // SLog.e(SLog.TENCENT, e.toString());
    // return false;
    // } finally {
    // if (out != null) {
    // try {
    // out.close();
    // out = null;
    // } catch (IOException e) {
    // SLog.e(SLog.TENCENT, e.toString());
    // return false;
    // }
    // }
    // }
    // return true;
    // }

    /**
     * 读取文件json串
     * 
     * @param filePath 文件路径
     * @param JSON json字符串
     * @return 写入成功与否
     * @author haiyandu
     * @since 2011-10-24
     */
    public static String readString(String filePath) {
        String sb = "";
        FileInputStream in = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            in = new FileInputStream(file);
            ByteArrayBuffer bytBuffer = new ByteArrayBuffer(0);
            int readNum;
            byte[] byt = new byte[Constants.READ_BUFFER];
            while ((readNum = in.read(byt, 0, Constants.READ_BUFFER)) != -1) {
                bytBuffer.append(byt, 0, readNum);
            }
            sb = new String(bytBuffer.toByteArray(), Constants.UTF8);
            in.close();
        } catch (IOException e) {
            SLog.e(SLog.BIKE, e.toString());
        } finally {
            if (in != null) {
                try {
                    in.close();
                    in = null;
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                    return "";
                }
            }
        }
        return sb;
    }

    /**
     * 创建目录和文件， 如果目录或文件不存在，则创建出来
     * 
     * @param filePath 文件路径
     * @return 创建后的文件
     * @throws IOException
     * @author haiyandu
     * @since 2011-10-24
     */
    public static synchronized File makeDIRAndCreateFile(String filePath) throws Exception {
        // Auto-generated method stub

        if (!ExternalStorageReceiver.isSDCardMounted) {
            throw new Exception("没有sd卡");
        }

        File file = new File(filePath);
        String parent = file.getParent();
        File parentFile = new File(parent);
        if (!parentFile.exists()) {
            if (parentFile.mkdirs()) {
                file.createNewFile();
            } else {
                throw new IOException("创建目录失败！");
            }
        } else {
            if (!file.exists()) {
                file.createNewFile();
            }
        }
        return file;
    }

    /**
     * 写byte到文件
     * 
     * @param filePath 文件路径
     * @param data byte数据
     * @return 成功与否
     */
    public static boolean writeByteArray2File(String filePath, byte[] data) {
        OutputStream outputStream = null;
        DataOutputStream dos = null;
        try {
            File file = makeDIRAndCreateFile(filePath);
            outputStream = new FileOutputStream(file);
            dos = new DataOutputStream(outputStream);
            dos.write(data);
            dos.flush();
            outputStream.flush();
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
            return false;
        } finally {
            try {
                if (dos != null) {
                    dos.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (IOException e) {
                SLog.e(SLog.BIKE, e.toString());
                return false;
            }
        }

        return true;
    }

    public static byte[] gzipCompress(byte[] src) {
        if (src == null || src.length == 0) {
            return src;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gout = null;
        byte[] desbyte = null;

        try {
            gout = new GZIPOutputStream(out);
            gout.write(src);
            gout.close();
            gout = null;
            desbyte = out.toByteArray();

        } catch (IOException e) {
            SLog.e(SLog.BIKE, "Gzip exception: " + e.toString());
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (gout != null) {
                    gout.close();
                    gout = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (IOException e) {
                SLog.e(SLog.BIKE, e.toString());
                e.printStackTrace();
                return null;
            }
        }

        return desbyte;
    }

    // public static String compress(String str) throws IOException {
    // if (str == null || str.length() == 0) {
    // return str;
    // }
    // ByteArrayOutputStream out = new ByteArrayOutputStream();
    // GZIPOutputStream gzip = new GZIPOutputStream(out);
    // gzip.write(str.getBytes());
    // gzip.close();
    // return out.toString("utf-8");
    // }

    /**
     * gZip压缩方法
     */
    public static byte[] gZip(byte[] data) {
        byte[] b = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            GZIPOutputStream gzip = new GZIPOutputStream(bos);
            gzip.write(data);
            gzip.finish();
            gzip.close();
            b = bos.toByteArray();
            bos.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return b;
    }

    public static byte[] gzipDecoder(byte[] src) {
        ByteArrayInputStream is = new ByteArrayInputStream(src);
        GZIPInputStream gis = null;
        byte[] desbyte = null;

        try {
            gis = new GZIPInputStream(is);
            ByteArrayBuffer buffer = new ByteArrayBuffer(0);
            int byteRead = 0;
            byte[] tempBytes = new byte[BUFFER_SIZE];

            while ((byteRead = gis.read(tempBytes)) != -1) {
                buffer.append(tempBytes, 0, byteRead);
            }
            desbyte = buffer.toByteArray();

        } catch (IOException e) {
            SLog.e(SLog.BIKE, "Gzip exception: " + e.toString());
            return null;
        } finally {
            try {
                is.close();
                if (gis != null) {
                    gis.close();
                }
            } catch (IOException e) {
                SLog.e(SLog.BIKE, e.toString());
                return null;
            }
        }

        return desbyte;
    }

    /**
     * 删除file文件或者file文件夹下所有文件
     * 
     * @param file 文件对象
     * @param deleteSelf 如果是文件夹的话,是否删除文件夹自身
     */
    public static void delete(File file, boolean deleteSelf) {
        try {
            if (file != null && file.exists()) {
                if (file.isFile()) { // 文件
                    file.delete();
                } else { // 文件夹

                    File fl[] = file.listFiles();// 获得当前文件夹下的所有子文件和子文件夹
                    if (fl != null && fl.length > 0) {
                        for (int i = 0, j = fl.length; i < j; i++) {// 循环处理每个对象
                            delete(fl[i], true);// 递归调用，处理每个文件对象
                        }
                    }

                    if (deleteSelf) {// 是否删除当前文件夹
                        file.delete();
                    }
                }
            }
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
        }
    }

    /**
     * 重命名文件
     */
    public static void renameTo(String srcFile, String destFile) {
        try {
            new File(srcFile).renameTo(new File(destFile));
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
        }
    }

    /**
     * 移动srcFile文件夹里的所有内容到destFile文件夹下,文件夹srcFile仍然存在<br/>
     * 移动srcFile文件到destFile文件夹下,文件srcFile将消失
     * 
     * @param srcFile 源文件对象
     * @param destFile 移动到目标文件夹
     */
    public static void move(File srcFile, File destFile) {
        try {
            if (srcFile != null && destFile != null && srcFile.exists()) {
                if (!destFile.exists()) {
                    destFile.mkdirs();
                }
                boolean reCreatSrcFile = false;
                if (srcFile.isDirectory()) {
                    reCreatSrcFile = true;
                }
                File f1 = new File(srcFile.getAbsolutePath());
                File f2 = new File(destFile.getAbsolutePath() + File.separator + srcFile.getName() + System.currentTimeMillis());
                srcFile.renameTo(f2);
                if (reCreatSrcFile) {
                    f1.mkdirs();
                }
            }
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
        }
    }

    /**
     * 清空缓存
     * 
     * @param ctx
     * @param fileCacheName
     * @author haiyandu
     * @since 2011-12-6
     */
    public static void clearFileCache(Context ctx, String fileCacheName) {
        save2FileCache(ctx, fileCacheName, "".getBytes());
    }

    /**
     * 将data写入到/data/data/com.cola.twisohu/file/路径下的fileCacheName文件中
     * 
     * @param ctx
     * @param fileCacheName
     * @param data
     * @author haiyandu
     * @since 2011-11-3
     */
    public static void save2FileCache(Context ctx, String fileCacheName, byte[] data) {
        FileOutputStream fos = null;
        try {
            fos = ctx.openFileOutput(fileCacheName, Context.MODE_WORLD_READABLE);
            fos.write(data);
            fos.close();
        } catch (FileNotFoundException e) {
            SLog.e(SLog.BIKE, e.toString());
        } catch (IOException e) {
            SLog.e(SLog.BIKE, e.toString());
        } catch (Exception e) {
            SLog.e(SLog.BIKE, e.toString());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos = null;
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                } finally {
                    fos = null;
                }
            }
        }
    }

    /**
     * 从/data/data/com.xxx.xxx/file/路径下的fileCacheName文件中加载数据
     * 
     * @param ctx
     * @param fileCacheName
     * @param data
     * @author haiyandu
     * @since 2011-11-3
     */
    public static String loadFromFileCache(Context ctx, String fileCacheName) {
        FileInputStream inStream = null;
        ByteArrayOutputStream stream = null;
        String data = null;
        try {
            inStream = ctx.openFileInput(fileCacheName);
            stream = new ByteArrayOutputStream();
            byte[] buffer = new byte[Constants.READ_BUFFER];
            int length = -1;
            while ((length = inStream.read(buffer)) != -1) {
                stream.write(buffer, 0, length);
            }
            data = stream.toString();
        } catch (FileNotFoundException e) {
            // SLog.e(SLog.DEFAULT_TAG, SLog.getTraceInfo() + e.toString(), e);
            return "";
        } catch (IOException e) {
            SLog.e(SLog.BIKE, e.toString());
            return "";
        } finally {
            if (inStream != null) {
                try {
                    inStream.close();
                    inStream = null;
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                }
            }
            if (stream != null) {
                try {
                    stream.close();
                    stream = null;
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                }

            }
        }
        return data;
    }

    /***
     * 从指定的文件读取系统时间
     * 
     * @param filePath
     * @author haiyandu
     * @since 2011-11-17
     */
    public static long getSystemTimeFromFile(String filePath) {
        File file = new File(filePath);

        if (!file.exists()) {
            return 0;
        }

        FileInputStream is = null;
        long time = 0;
        try {
            is = new FileInputStream(file);
            byte[] data = new byte[Constants.READ_BUFFER];
            int num = 0;
            ByteArrayBuffer bytBuffer = new ByteArrayBuffer(0);
            while ((num = is.read(data, 0, Constants.READ_BUFFER)) != -1) {
                bytBuffer.append(data, 0, num);
            }
            byte[] allData = bytBuffer.toByteArray();

            String res = new String(allData);

            time = Long.parseLong(res);
        } catch (IOException e) {
            SLog.e(SLog.BIKE, e.toString());
        } catch (NumberFormatException e) {
            time = 0;
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                SLog.e(SLog.BIKE, e.toString());
            }
        }

        return time;
    }

    /***
     * 将系统时间戳写入到指定的文件
     * 
     * @param filePath
     * @return
     * @author haiyandu
     * @since 2011-11-17
     */
    public static void writeSystemTimeToFile(String filePath) {

        if (!ExternalStorageReceiver.isSDCardMounted) {
            return;
        }

        File file = new File(filePath);
        File dir = new File(file.getParent());
        if (!dir.exists()) {
            dir.mkdirs();
        }
        FileOutputStream os = null;
        try {
            os = new FileOutputStream(file);
            Long time = System.currentTimeMillis();
            os.write(Long.toString(time).getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            SLog.e(SLog.BIKE, e.toString());
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                SLog.e(SLog.BIKE, e.toString());
            }
        }
    }

    /***
     * 文件复制
     * 
     * @param srcFile
     * @param destFile
     * @author haiyandu
     * @since 2011-11-21
     */
    public static boolean copy(File srcFile, File destFile) {
        InputStream is = null;
        OutputStream os = null;

        try {
            is = new BufferedInputStream(new FileInputStream(srcFile));
            os = new BufferedOutputStream(new FileOutputStream(destFile));

            byte[] b = new byte[256];
            int len = 0;
            while ((len = is.read(b)) != -1) {
                os.write(b, 0, len);
            }
            os.flush();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            SLog.e(SLog.BIKE, e.toString());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                SLog.e(SLog.BIKE, e.toString());
                return false;
            }
        }

        return true;
    }

    /***
     * 文件写入
     * 
     * @param srcFile
     * @param destFile
     * @author haiyandu
     * @since 2011-11-21
     */
    public static boolean copy(InputStream srcStream, File destFile) {
        if (srcStream == null) {
            return false;
        }
        OutputStream os = null;
        try {
            os = new BufferedOutputStream(new FileOutputStream(destFile));

            byte[] b = new byte[256];
            int len = 0;
            while ((len = srcStream.read(b)) != -1) {
                os.write(b, 0, len);
            }
            os.flush();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            SLog.e(SLog.BIKE, e.toString());
            return false;
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (srcStream != null) {
                    srcStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                SLog.e(SLog.BIKE, e.toString());
                return false;
            }
        }

        return true;
    }

    /**
     * 将InputStream读取成String
     * 
     * @param srcStream
     * @return
     * @author haiyandu
     * @since 2011-11-22
     */
    public static String read(InputStream srcStream) {
        if (srcStream == null) {
            return null;
        }

        byte[] res = new byte[1024];
        int num = 0;
        String result = "";
        ByteArrayBuffer bytBuffer = new ByteArrayBuffer(0);
        try {
            while ((num = srcStream.read(res, 0, 1024)) != -1) {
                bytBuffer.append(res, 0, num);
            }
            result = new String(bytBuffer.toByteArray());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            SLog.e(SLog.BIKE, e.toString());
            return null;
        } finally {
            try {
                if (srcStream != null) {
                    srcStream.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                SLog.e(SLog.BIKE, e.toString());
                return null;
            }
        }

        if (result.equals("")) {
            return null;
        }

        return result;
    }

    public static byte[] readBytesFromFile(File file) {
        if (!file.exists()) {
            return null;
        }
        InputStream inputStream = null;
        byte[] bytes = null;
        byte[] byt = new byte[Constants.READ_BUFFER];
        try {
            inputStream = new FileInputStream(file);
            ByteArrayBuffer bytBuffer = new ByteArrayBuffer(0);
            int num = 0;
            while ((num = inputStream.read(byt, 0, Constants.READ_BUFFER)) != -1) {
                bytBuffer.append(byt, 0, num);
            }
            bytes = bytBuffer.toByteArray();
        } catch (FileNotFoundException e) {
            SLog.e(SLog.BIKE, e.toString());
            ;
        } catch (IOException e) {
            SLog.e(SLog.BIKE, e.toString());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                    return null;
                }
            }
        }

        return bytes;
    }

    public static long getDirFiles(List<File> fileList, File dir) {
        /* SLog.v(SLog.KCS_TAG, "FileUtil getDirFiles dir = " + dir); */
        if (dir == null) {
            return 0;
        }
        if (!dir.isDirectory()) {
            return 0;
        }
        long dirSize = 0L;
        File[] files = dir.listFiles();
        if (files == null) {
            return 0;
        }
        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
                dirSize += file.length();
            } else if (file.isDirectory()) {
                dirSize += file.length();
                dirSize += getDirFiles(fileList, file); // 如果遇到目录则通过递归调用继续统计
            }
        }
        return dirSize;
    }

    /**
     * 序列化一个对象
     * 
     * @param object
     * @return
     * @author haiyandu
     * @since 2012-2-10 下午02:21:31
     */
    public static String getSeriString(Object object) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        String productBase64 = null;
        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            productBase64 = new String(Base64.encodeBase64(baos.toByteArray()));
        } catch (IOException e) {
            SLog.e(SLog.BIKE, e.toString());
        } catch (OutOfMemoryError e) {
            SLog.e(SLog.BIKE, e.toString());
        } finally {
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    SLog.e(SLog.BIKE, e.toString());
                }
            }
        }
        if (productBase64 == null) {
            productBase64 = "";
        }
        return productBase64;
    }

    /**
     * 反序列化一个对象
     * 
     * @param data
     * @return
     * @throws Exception
     * @author haiyandu
     * @since 2012-2-10 下午02:23:18
     */
    public static Object getObjectFromBytes(String data) throws Exception {
        byte[] objBytes = Base64.decodeBase64(data.getBytes());
        if (objBytes == null || objBytes.length == 0) {
            return null;
        }
        ByteArrayInputStream bi = null;
        ObjectInputStream oi = null;
        Object object = null;
        try {
            bi = new ByteArrayInputStream(objBytes);
            oi = new ObjectInputStream(bi);
            object = oi.readObject();
        } finally {
            if (oi != null) {
                oi.close();
            }
            if (bi != null) {
                bi.close();
            }
        }
        return object;
    }

    /**
     * 将一个序列化对象写入文件
     * 
     * @param object
     * @return
     * @author haiyandu
     * @since 2012-2-10 下午02:21:31
     */
    public synchronized static void saveSerObjectToFile(Object object, String fileName) {
        ObjectOutputStream out = null;
        try {
            makeDIRAndCreateFile(fileName);
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(object);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从文件中直接读一个对象
     * 
     * @param data
     * @return
     * @throws Exception
     * @author haiyandu
     * @since 2012-2-10 下午02:23:18
     */
    public synchronized static Object readSerObjectFromFile(String fileName) {
        Object b = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            b = in.readObject();
            return b;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return b;
    }

    /**
     * 向指定文件中写入缓存
     * 
     * @param object
     * @param fileName
     */
    public synchronized static void saveCache(File fileName, Object object) {
        ObjectOutputStream out = null;
        try {
            String parent = fileName.getParent();
            File parentFile = new File(parent);
            if (!parentFile.exists()) {
                if (parentFile.mkdirs()) {
                    fileName.createNewFile();
                } else {
                    throw new IOException("创建目录失败！");
                }
            } else {
                if (!fileName.exists()) {
                    fileName.createNewFile();
                }
            }
            out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(object);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 从文件中直接读一个对象
     * 
     * @param data
     * @return
     * @author haiyandu
     * @since 2012-2-10 下午02:23:18
     */
    public synchronized static Object readCache(File fileName) {
        Object b = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(fileName));
            b = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return b;
    }

    /**
     * 从文件中直接读一个对象
     * 
     * @param data
     * @return
     * @throws Exception
     * @author haiyandu
     * @since 2012-2-10 下午02:23:18
     */
    public synchronized static Object readSerObjectFromInStream(InputStream srcStream) {
        Object b = null;
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(srcStream);
            b = in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

        return b;
    }

    /**
     * 判断文件是否存在
     * 
     * @param filePath
     * @return
     * @author haiyandu
     * @since 2012-2-12
     */
    public static boolean fileExists(String filePath) {
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        }
        return false;
    }

    /**
     * 删除一个路径下的所有文件
     * 
     * @param file
     * @author haiyandu
     * @since 2012-2-17
     */
    public static void deleteFile(File file) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                file.delete(); // delete()方法 你应该知道 是删除的意思;
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
                }
                file.delete();
            }
        } else {
            SLog.v(SLog.BIKE, "所删除的文件不存在！" + '\n');
        }
    }

    /**
     * 可以清除以str结尾的名的所有文件，用于批量删除私信缓存
     * 
     * @param file
     * @param str
     * @author haiyandu
     * @since 2012-3-31
     */
    public static void deleteFileByEnd(File file, String str) {
        if (file.exists()) { // 判断文件是否存在
            if (file.isFile()) { // 判断是否是文件
                if (file.getName().endsWith(str)) {
                    SLog.e(SLog.BIKE, "删除");
                    file.delete(); // delete()方法 你应该知道 是删除的意思;
                }
            } else if (file.isDirectory()) { // 否则如果它是一个目录
                File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
                for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
                    deleteFileByEnd(files[i], str); // 把每个文件 用这个方法进行迭代
                }
                file.delete();
            }
        } else {
            SLog.e(SLog.BIKE, "文件不存在!");
        }
    }

    /**
     * 删除一个路径下的所有文件
     * 
     * @param file
     * @author haiyandu
     * @since 2012-2-17
     */
    public static void deleteFile(String fileName) {
        deleteFile(new File(fileName));
    }

    public static void writeMsg(String data, String name) {
        if (MobileUtil.getDebugMode()) {
            StringBuffer sb = new StringBuffer();
            sb.append("Time:" + dataFormat.format(new Date()));
            sb.append("\n");
            sb.append(data);
            String fileName = dataFormatFileName.format(new Date()) + "_" + name + ".txt";
            FileUtil.writeString(Constants.PUSH_PATH + fileName, sb.toString(), false);
        }
    }

    public static void writePushMsg(String data, String name) {
        if (MobileUtil.getDebugMode()) {
            StringBuffer sb = new StringBuffer();
            sb.append("[");
            sb.append(++lineNum);
            sb.append("], " + dataFormat.format(new Date()));
            sb.append(", ");
            sb.append("thread_id=" + Thread.currentThread().getId());
            sb.append(", ");
            sb.append(data);
            sb.append("\n--------------------------\n");
            String fileName = "push_log.txt";
            FileUtil.writeString(Constants.PUSH_PATH + fileName, sb.toString(), true);
        }
    }

    /**
     * 解压离线下载的压缩包
     * 
     * @param compressedData
     * @return
     * @throws IOException
     */
    public static byte[] unZipInflate(byte[] compressedData) {
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);

        byte[] buf = new byte[1024];
        while (!decompressor.finished()) {
            try {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            } catch (DataFormatException e) {
                e.printStackTrace();
                break;
            }
        }

        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bos.toByteArray();
    }

    public static void unZipCompressedDataToFile(byte[] compressedData, String fileName) throws Exception {
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressedData);
        ByteArrayOutputStream bos = new ByteArrayOutputStream(compressedData.length);

        byte[] buf = new byte[1024];
        while (!decompressor.finished()) {
            try {
                int count = decompressor.inflate(buf);
                bos.write(buf, 0, count);
            } catch (DataFormatException e) {
                e.printStackTrace();
                break;
            }
        }
        try {
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        OutputStream outputStream = new FileOutputStream(makeDIRAndCreateFile(fileName));
        bos.writeTo(outputStream);

        outputStream.close();

    }

    public static void Unzip(File zip, String dir) {
        String strEntry = null;
        try {
            BufferedOutputStream dest = null;
            FileInputStream fis = new FileInputStream(zip);
            ZipInputStream zis = new ZipInputStream(new BufferedInputStream(fis));
            ZipEntry entry = null;
            while ((entry = zis.getNextEntry()) != null) {

                try {
                    strEntry = entry.getName();

                    File entryFile = new File(dir + strEntry);

                    if (entry.isDirectory()) {
                        entryFile.mkdirs();
                        continue;
                    } else {
                        makeDIRAndCreateFile(dir + strEntry);
                    }

                    int count;
                    byte data[] = new byte[BUFFER_SIZE];
                    FileOutputStream fos = new FileOutputStream(entryFile);
                    dest = new BufferedOutputStream(fos, BUFFER_SIZE);
                    while ((count = zis.read(data, 0, BUFFER_SIZE)) != -1) {
                        dest.write(data, 0, count);
                    }

                    dest.flush();
                    dest.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }

            zis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static File getCacheRootDir() {
        Boolean isSDPresent = android.os.Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED);
        if(isSDPresent) {
            return android.os.Environment.getExternalStorageDirectory();
        } else {
            return Application.getInstance().getCacheDir();            
        }       
    }
}
