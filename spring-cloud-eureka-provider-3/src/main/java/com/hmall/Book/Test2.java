package com.hmall.Book;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;


public class Test2 {
    private static Logger logger = LoggerFactory.getLogger(Test2.class);
    private static int BYTE_SIZE = 8;
    private static String CODE_UTF8 = "UTF-8";
    private static String CODE_UTF8_LOWERCASE = "utf-8";
    private static String CODE_UTF8_BOM = "UTF-8_BOM";
    private static String CODE_GBK = "GBK";
    private static String CODE_UTF16 = "UTF-16";
    private static String CODE_UNICODE = "Unicode";
    private static String BGK_UTF16_SOLOPath = "C:\\Users\\24360\\Desktop\\inblueMoon\\单独";
    private static String YAN_SAVE_PATH = "C:\\Users\\24360\\Desktop\\inblueMoon\\yan更文目录,手机用wps打开可搜索查文.txt";
    private static String PO_SAVE_PATH = "C:\\Users\\24360\\Desktop\\inblueMoon\\2012月更文目录,手机用wps打开可搜索查文.txt";
    private static String INSERT_CONTENT = "\n"+"————————————————————————\n" +
            "本文由公众号inBlueMoon整理， \n" +
            "——\n" +
            "若失联请加 v in_BlueMoon\n" +
            "\n" +
            "本作品来自互联网及出版图书,本人不做任何负责，内容版权归作者所有，\n" +
            "\n" +
            "请在下载后24小时内删除。觉得本书不错，请购买正版书籍，感谢对作者的支持！\n" +
            "————————————————————————\n" ;
    private static String[] replaceLists = new String[]{
            "长腿老阿姨",
            "更多好看小说尽在扣扣群783160830",
            "895375337",
            "2827885120",
            "8547994",
            "失联请加扣8670829",
            "长T老阿姨",
            "长腿,老,阿姨",
            "长-腿,老,阿姨",
            "长腿老。阿。姨",
            "本文来自长T老阿姨的泼泼日更裙，更多好看小说尽在扣群78-316-0830，若失联请加扣⑻67O⒏2七",
            "婆婆",
            "婆！婆！",
            "婆]婆",
            "豆丁",
            "豆<<丁<<酱",
            "豆★丁酱",
            "豆-Dīng",
            "豆 丁 酱",
            "豆丁酱",
            "豆@丁！酱",
            "豆<丁<更<新",
            "小颜推文",
            "更多小说资源尽在QQ群1074137849",
            "若失联请加QQ3198547994或3049885120",
            "红 柚 推 文",
            "红柚",
            "1031927671",
            "1045962689",
            "3616484774",
            "更多小说资源尽在QQ群1031927671，若失联请加【】QQ1045962689或3616484774"
    };
    private static String FIRST_INSERT_WORD = "\n————————————————————————\n" ;
    private static String INBLUEMOON_LAST_NAME = "----公z号inBlueMoon" ;
//    private static String REPLACE_INFO = "GZH inBlueMoon" ;
    private static String REPLACE_INFO = "" ;
    private static String NEW_PATH = "G:\\z\\allbook" ;

    public static void main(String[] args) throws Exception {
//        List<String> arr2 = new ArrayList<>();
//        arr2.add("D:\\download\\qqFile\\12.16海棠");
//        InsertBook book2 = new InsertBook(arr2,"1216海棠",false,true,true,true);
//        insertFile(book2);

//        List<String> arr1 = new ArrayList<>();
//        arr1.add("D:\\download\\qqFile\\12.18完结");
//        arr1.add("D:\\download\\qqFile\\12.17连载");
//        InsertBook book1 = new InsertBook(arr1,"1219PO",false,true,true,true);
//        insertFile(book1);

        List<String> arr3 = new ArrayList<>();
        arr3.add("D:\\download\\qqFile\\2020.12.18（1V1）虐心合集");
        InsertBook book3 = new InsertBook(arr3,"1219（1V1）虐心合集",false,true,true,false);
        insertFile(book3);

        //移动到某文件夹
//        copyDir("G:\\z\\2011 ", "G:\\z\\allbook",true);

//        upDateName("C:\\Users\\24360\\Desktop\\inblueMoon\\单独\\校园合集" , true,"[完结]",true);

    }


    /**
     *遍历文件判断文件是什么编码格式
     */
    public static void insertFile(InsertBook book) throws Exception {
        List<String> pathList = book.getPathList() ;
        String writeWord =book.getWriteWord() ;
        Boolean isAddInfo =book.getAddInfo() ;
        Boolean isReplaceInfo = book.getReplaceInfo() ;
        Boolean isFirst =book.getFirst() ;
        Boolean isInsertFileName =book.getInsertFileName() ;
        File textFile =new File(PO_SAVE_PATH);
        String titlePath = PO_SAVE_PATH;
        for (String path : pathList) {
            File file = new File(path);
            boolean isDirectory = file.isDirectory();
            if (!isDirectory) {
                System.out.println(path + "不是文件夹！");
                return;
            }
            String[] files = file.list();
            String oldFileName = "";
            for (int i = 0; i < files.length; i++) {
                oldFileName = files[i];
                //查看其数组中每一个是文件还是文件夹
                if(new File(path+File.separator+oldFileName).isDirectory()) {
                    //为文件夹，进行递归
                    List<String> arr = new ArrayList<>();
                    arr.add(path+File.separator+oldFileName);
                    InsertBook book1 = new InsertBook(arr,writeWord,isAddInfo,isReplaceInfo,false,isInsertFileName);
                    insertFile(book1);
                }else {
                    if(oldFileName.endsWith(".txt") ){
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path+File.separator+oldFileName));
                        String code = getEncode(bis, false);
                        WriteBook writeBook = new WriteBook();
                        writeBook.setPath(path+File.separator+oldFileName);
                        writeBook.setFileName(oldFileName);
                        writeBook.setMiddlePath(writeWord);
                        writeBook.setAddInfo(isAddInfo);
                        writeBook.setReplaceInfo(isReplaceInfo);
                        if(code == CODE_GBK || code == CODE_UTF16 || code == CODE_UNICODE){
                            writeBook.setCode(code);
                            writeAndRead(writeBook);
                        }else{
                            writeBook.setCode(CODE_UTF8_LOWERCASE);
                            writeAndRead(writeBook);
                        }
                        String[] arr =  oldFileName.split(".txt");
                        if( arr.length > 0){
                            oldFileName = arr[0];
                        }
                    }
                    String[] arr = oldFileName.split("作者");
                    System.out.println(arr[0]);
                    if(isInsertFileName){
                        insert(titlePath,false,"\n" + oldFileName);
                    }
                }
            }
            System.out.println("");
        }

        if(isFirst && isInsertFileName){
            insert(titlePath,false,FIRST_INSERT_WORD+writeWord);
            System.out.println("");
        }


    }

    /***
     *
     * @param path
     */
    public static void upDateName(String path,Boolean isAdd,String keyName,Boolean isHead) {
        File file = new File(path);
        boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            System.out.println(path + "不是文件夹！");
            return;
        }else{
            String[] files = file.list();
            File f = null;
            String newFileName = "";
            String oldFileName = "";
            for (int i = 0; i < files.length; i++) {
                oldFileName = files[i];
                //查看其数组中每一个是文件还是文件夹
                if(new File(path+File.separator+oldFileName).isDirectory()) {
                    //为文件夹，进行递归
                    upDateName(path+File.separator+oldFileName,isAdd,keyName,isHead);
                }else {
                    if (oldFileName.endsWith(".txt")) {
                        f = new File(path + "/" + oldFileName);
                        //增加后缀
                        String[] nameList1 = oldFileName.split(".txt");
//                        newFileName = nameList1.length > 0 ? nameList1[0] + INBLUEMOON_LAST_NAME : oldFileName;
                        newFileName = nameList1.length > 0 ? nameList1[0] : oldFileName;

                        String[] nameList2 = newFileName.split(keyName);
                        if(isAdd){
                            newFileName = keyName + newFileName ;
                        }else{
                            if(isHead){
                                //删除前缀
                                newFileName = nameList2.length > 1 ? nameList2[1]: newFileName;
                            }else{
                                //删除后缀
                                newFileName = nameList2.length > 1 ? nameList2[0]: newFileName;
                            }
                        }

                        //增加结构
                        /*File newFile = new File(path + "/" + newFileName);
                        newFile.mkdir();*/
                        f.renameTo(new File(path  + "/" + newFileName + ".txt"));

                    }
                }
            }
        }

    }

    //批量压缩，并加目录
    public static void test1(String[] args) {
        String path = "H:\\story\\y\\po\\2004U";
        File file = new File(path);
        boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            System.out.println(path + "不是文件夹！");
            return;
        }
        String[] files = file.list();
        File f = null;
        String newFileName = "";
        String oldFileName = "";
        for (int i = 0; i < files.length; i++) {

            oldFileName = files[i];
            System.out.println(files[i]);
            newFileName = oldFileName.substring(0,4)+"[U]";
//            f.renameTo(new File(path + "/" + newFileName));
            f = new File(path + "/" + oldFileName);
            File newFile = new File(path + "/" + newFileName);
            newFile.mkdir();
            f.renameTo(new File(path +"/"+newFileName+"/"+newFileName + ".zip"));

        }
    }


    /**
     * 通过文件缓存流获取编码集名称，文件流必须为未曾
     *
     * @param bis
     * @param ignoreBom 是否忽略utf-8 bom
     * @return
     * @throws Exception
     */
    public static String getEncode(BufferedInputStream bis, boolean ignoreBom) throws Exception {
        bis.mark(0);

        String encodeType = "未识别";
        byte[] head = new byte[3];
        bis.read(head);
        if (head[0] == -1 && head[1] == -2) {
            encodeType = CODE_UTF16;
        } else if (head[0] == -2 && head[1] == -1) {
            encodeType = CODE_UNICODE;
        } else if (head[0] == -17 && head[1] == -69 && head[2] == -65) { //带BOM
            if (ignoreBom) {
                encodeType = CODE_UTF8;
            } else {
                encodeType = CODE_UTF8_BOM;
            }
        } else if (CODE_UNICODE.equals(encodeType)) {
            encodeType = CODE_UTF16;
        } else if (isUTF8(bis)) {
            encodeType = CODE_UTF8;
        } else {
            encodeType = CODE_GBK;
        }
        return encodeType;
    }

    //以code方式读取文件，utf-8方式写入
    public static void writeAndRead(WriteBook book) throws IOException {
        String path = book.getPath();
        String fileName = book.getFileName();
        String code = book.getCode();
        String middlePath = book.getMiddlePath();
        Boolean isAddInfo = book.getAddInfo();
        Boolean isReplaceInfo = book.getReplaceInfo();
        FileInputStream fis = new FileInputStream(path);     //文件字节流
        InputStreamReader isr = new InputStreamReader(fis,code);//"GBK"为源文件编码格式
        BufferedReader br = new BufferedReader(isr);
        //不包含txt后缀的文件名
        String noTxtFileName = fileName ;
        String[] arr =  fileName.split(".txt");
        if( arr.length > 0){
            noTxtFileName = arr[0];
            String[] arr1 = noTxtFileName.split("【豆丁】");
            if(arr1.length > 0){
                noTxtFileName = arr1[0];
            }
        }
        //为文件创建一层分日期类型的文件夹(如1027PO)
        File file = new File(BGK_UTF16_SOLOPath+File.separator + middlePath) ;
        if(!file.exists()){
            file.mkdir();
        }
        //为文件多创建一层同名文件夹
       /* File file1 = new File(BGK_UTF16_SOLOPath+File.separator + middlePath + File.separator + noTxtFileName) ;
        if(!file1.exists()){
            file1.mkdir();
        }*/
//        FileOutputStream out = new FileOutputStream(new File(BGK_UTF16_SOLOPath+File.separator + middlePath + File.separator + noTxtFileName +  File.separator + fileName),true);
        FileOutputStream out = new FileOutputStream(new File(BGK_UTF16_SOLOPath+File.separator + middlePath +  File.separator + fileName),true);
        StringBuffer sb = new StringBuffer();
        String str = null;
        while((str = br.readLine()) != null)
        {
            if(isReplaceInfo){
                for(String data : replaceLists){
                    if(str.contains(data)){
                        str = str.replaceAll(data,REPLACE_INFO);
                        break;
                    }
                }
            }
            sb.append(str + "\r\n");
        }
        if(isAddInfo){
            out.write(INSERT_CONTENT.getBytes(CODE_UTF8_LOWERCASE));
        }
        out.write(sb.toString().getBytes(CODE_UTF8_LOWERCASE));
        if(isAddInfo){
            out.write(INSERT_CONTENT.getBytes(CODE_UTF8_LOWERCASE));
        }
        out.close();
        br.close();
        isr.close();
        fis.close();
    }

    public static String getFileEncode(String path) {
        String charset ="asci";
        byte[] first3Bytes = new byte[3];
        BufferedInputStream bis = null;
        try {
            boolean checked = false;
            bis = new BufferedInputStream(new FileInputStream(path));
            bis.mark(0);
            int read = bis.read(first3Bytes, 0, 3);
            if (read == -1) {
                return charset;
            }
            if (first3Bytes[0] == (byte) 0xFF && first3Bytes[1] == (byte) 0xFE) {
                charset = "Unicode";//UTF-16LE
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xFE && first3Bytes[1] == (byte) 0xFF) {
                charset = "Unicode";//UTF-16BE
                checked = true;
            } else if (first3Bytes[0] == (byte) 0xEF && first3Bytes[1] == (byte) 0xBB && first3Bytes[2] == (byte) 0xBF) {
                charset = "UTF8";
                checked = true;
            }
            bis.reset();
            if (!checked) {
                int len = 0;
                int loc = 0;
                while ((read = bis.read()) != -1) {
                    loc++;
                    if (read >= 0xF0)
                    {break;}
                    if (0x80 <= read && read <= 0xBF) //单独出现BF以下的，也算是GBK
                    { break;}
                    if (0xC0 <= read && read <= 0xDF) {
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF)
                        //双字节 (0xC0 - 0xDF) (0x80 - 0xBF),也可能在GB编码内
                        {continue;}
                        else
                        { break;}
                    } else if (0xE0 <= read && read <= 0xEF) { //也有可能出错，但是几率较小
                        read = bis.read();
                        if (0x80 <= read && read <= 0xBF) {
                            read = bis.read();
                            if (0x80 <= read && read <= 0xBF) {
                                charset = CODE_UTF8;
                                break;
                            } else
                            {  break;}
                        } else
                        {break;}
                    }
                }
                //TextLogger.getLogger().info(loc + " " + Integer.toHexString(read));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException ex) {
                }
            }
        }
        return charset;
    }

    /**
     * 将一指定编码的文件转换为另一编码的文件
     *
     * @param oldFullFileName
     * @param oldCharsetName
     * @param newFullFileName
     * @param newCharsetName
     */
    public static void convert(String oldFullFileName, String oldCharsetName, String newFullFileName, String newCharsetName) throws Exception {
        logger.info("the old file name is : {}, The oldCharsetName is : {}", oldFullFileName, oldCharsetName);
        logger.info("the new file name is : {}, The newCharsetName is : {}", newFullFileName, newCharsetName);

        StringBuffer content = new StringBuffer();

        BufferedReader bin = new BufferedReader(new InputStreamReader(new FileInputStream(oldFullFileName), oldCharsetName));
        String line;
        while ((line = bin.readLine()) != null) {
            content.append(line);
            content.append(System.getProperty("line.separator"));
        }
        newFullFileName = newFullFileName.replace("\\", "/");
        File dir = new File(newFullFileName.substring(0, newFullFileName.lastIndexOf("/")));
        if (!dir.exists()) {
            dir.mkdirs();
        }
        Writer out = new OutputStreamWriter(new FileOutputStream(newFullFileName), newCharsetName);
        out.write(content.toString());
    }

    /**
     * 是否是无BOM的UTF8格式，不判断常规场景，只区分无BOM UTF8和GBK
     *
     * @param bis
     * @return
     */
    private static boolean isUTF8( BufferedInputStream bis) throws Exception {
        bis.reset();

        //读取第一个字节
        int code = bis.read();
        do {
            BitSet bitSet = convert2BitSet(code);
            //判断是否为单字节
            if (bitSet.get(0)) {//多字节时，再读取N个字节
                if (!checkMultiByte(bis, bitSet)) {//未检测通过,直接返回
                    return false;
                }
            } else {
                //单字节时什么都不用做，再次读取字节
            }
            code = bis.read();
        } while (code != -1);
        return true;
    }

    /**
     * 检测多字节，判断是否为utf8，已经读取了一个字节
     *
     * @param bis
     * @param bitSet
     * @return
     */
    private static boolean checkMultiByte(BufferedInputStream bis, BitSet bitSet) throws Exception {
        int count = getCountOfSequential(bitSet);
        byte[] bytes = new byte[count - 1];//已经读取了一个字节，不能再读取
        bis.read(bytes);
        for (byte b : bytes) {
            if (!checkUtf8Byte(b)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检测单字节，判断是否为utf8
     *
     * @param b
     * @return
     */
    private static boolean checkUtf8Byte(byte b) throws Exception {
        BitSet bitSet = convert2BitSet(b);
        return bitSet.get(0) && !bitSet.get(1);
    }

    /**
     * 检测bitSet中从开始有多少个连续的1
     *
     * @param bitSet
     * @return
     */
    private static int getCountOfSequential( BitSet bitSet) {
        int count = 0;
        for (int i = 0; i < BYTE_SIZE; i++) {
            if (bitSet.get(i)) {
                count++;
            } else {
                break;
            }
        }
        return count;
    }


    /**
     * 将整形转为BitSet
     *
     * @param code
     * @return
     */
    private static BitSet convert2BitSet(int code) {
        BitSet bitSet = new BitSet(BYTE_SIZE);

        for (int i = 0; i < BYTE_SIZE; i++) {
            int tmp3 = code >> (BYTE_SIZE - i - 1);
            int tmp2 = 0x1 & tmp3;
            if (tmp2 == 1) {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    /**
     * 通过路径获取文件的内容，这个方法因为用到了字符串作为载体，为了正确读取文件（不乱码），只能读取文本文件，安全方法！
     */
    public static String readFile(String path){
        String data = null;
        // 判断文件是否存在
        File file = new File(path);
        if(!file.exists()){
            return data;
        }
        // 获取文件编码格式
        String code = getFileEncode(path);
        InputStreamReader isr = null;
        try{
            // 根据编码格式解析文件
            if("asci".equals(code)){
                // 这里采用GBK编码，而不用环境编码格式，因为环境默认编码不等于操作系统编码
                // code = System.getProperty("file.encoding");
                code = CODE_GBK;
            }
            isr = new InputStreamReader(new FileInputStream(file),code);
            // 读取文件内容
            int length = -1 ;
            char[] buffer = new char[1044];
            StringBuffer sb = new StringBuffer();
            while((length = isr.read(buffer, 0, 1044) ) != -1){
                sb.append(buffer,0,length);
            }
            data = new String(sb);
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("getFile IO Exception:"+e.getMessage());
        }finally{
            try {
                if(isr != null){
                    isr.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("getFile IO Exception:"+e.getMessage());
            }
        }
        return data;
    }
    /**
     * 按照指定的路径和编码格式保存文件内容，这个方法因为用到了字符串作为载体，为了正确写入文件（不乱码），只能写入文本内容，安全方法
     *
     * @param data
     *     将要写入到文件中的字节数据
     * @param path
     *     文件路径,包含文件名
     * @return boolean
     *      当写入完毕时返回true;
     */
    public static boolean writeFile(byte data[], String path , String code){
        boolean flag = true;
        OutputStreamWriter osw = null;
        try{
            File file = new File(path);
            if(!file.exists()){
                file = new File(file.getParent());
                if(!file.exists()){
                    file.mkdirs();
                }
            }
            if("asci".equals(code)){
                code = CODE_GBK;
            }
            osw = new OutputStreamWriter(new FileOutputStream(path),code);
            osw.write(new String(data,code));
            osw.flush();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("toFile IO Exception:"+e.getMessage());
            flag = false;
        }finally{
            try{
                if(osw != null){
                    osw.close();
                }
            }catch(IOException e){
                e.printStackTrace();
                System.out.println("toFile IO Exception:"+e.getMessage());
                flag = false;
            }
        }
        return flag;
    }

    public static void getFileNameAndInsert(String path,Boolean isYan,Boolean isFirst){
        File file = new File(path);
        boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            System.out.println(path + "不是文件夹！");
            return;
        }
        String[] files = file.list();
        String oldFileName = "";

        try {
            File textFile =new File("C:\\Users\\24360\\Desktop\\inblueMoon\\po更文目录,手机用wps打开可搜索查文.txt");

            if(isYan){
                textFile =new File("C:\\Users\\24360\\Desktop\\inblueMoon\\yan更文目录,手机用wps打开可搜索查文.txt");
            }
            BufferedWriter writer = null;
            writer = new BufferedWriter(new FileWriter(textFile, true));
            if(isFirst){
                writer.append('\n');
                writer.append("0405");
            }
            for (int i = 0; i < files.length; i++) {
                oldFileName = files[i];
                //查看其数组中每一个是文件还是文件夹
                if(new File(path+File.separator+oldFileName).isDirectory()) {
                    //为文件夹，进行递归
                    getFileNameAndInsert(path+File.separator+oldFileName,isYan,false);
                }else {
                    //插入公众号信息
                    insert(path+File.separator+oldFileName,false,INSERT_CONTENT);
                    insert(path+File.separator+oldFileName,true,INSERT_CONTENT);

                    if(oldFileName.endsWith(".txt") ){
                        String[] arr =  oldFileName.split(".txt");
                        if( arr.length > 0){
                            oldFileName = arr[0];
                        }
                    }
                    System.out.println(oldFileName);
                    writer.append('\n');
                    writer.append(oldFileName);
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //输出文件名
    public static void getFileName(){
        String path = "C:\\Users\\24360\\Desktop\\3.31yan";
        File file = new File(path);
        boolean isDirectory = file.isDirectory();
        if (!isDirectory) {
            System.out.println(path + "不是文件夹！");
            return;
        }
        String[] files = file.list();
        String oldFileName = "";
//        File textFile =new File("C:\\Users\\24360\\Desktop\\inblueMoon\\po更文目录,手机用wps打开可搜索查文.txt");
        File textFile =new File("C:\\Users\\24360\\Desktop\\inblueMoon\\yan更文目录,手机用wps打开可搜索查文.txt");
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(textFile, true));
            writer.append('\n');
            writer.append("0531");
            for (int i = 0; i < files.length; i++) {
                oldFileName = files[i];
                if(oldFileName.endsWith(".txt") ){
                    String[] arr =  oldFileName.split(".txt");
                    if( arr.length > 0){
                        oldFileName = arr[0];
                    }
                }
                System.out.println(oldFileName);
                writer.append('\n');
                writer.append(oldFileName);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //文末插入
    public static void appendMethodA(String fileName){
        String content = "————————————————————————\n" +
                "本文由公众号inBlueMoon整理， \n" +
                "——\n" +
                "若失联请加 v in_BlueMoon\n" +
                "\n" +
                "本作品来自互联网及出版图书,本人不做任何负责，内容版权归作者所有，\n" +
                "\n" +
                "请在下载后24小时内删除。觉得本书不错，请购买正版书籍，感谢对作者的支持！\n" +
                "————————————————————————\n" ;
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName, "rw");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.writeBytes(content);
            randomFile.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //首行插入 isYan ： 是否插入ansi isEnd:true 文末插入
    public static void insert2(String filename ,Boolean isYan, Boolean isEnd) throws IOException {//pos是插入的位置
        long pos = 0 ;
        String insertContent = "————————————————————————\n" +
                "本文由公众号inBlueMoon整理， \n" +
                "——\n" +
                "若失联请加 v in_BlueMoon\n" +
                "\n" +
                "本作品来自互联网及出版图书,本人不做任何负责，内容版权归作者所有，\n" +
                "\n" +
                "请在下载后24小时内删除。觉得本书不错，请购买正版书籍，感谢对作者的支持！\n" +
                "————————————————————————\n" ;

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, true),CODE_GBK));
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename),CODE_GBK));
        bw.write(insertContent);
    }

    //首行插入 isYan ： 是否插入ansi isEnd:true 文末插入
    public static void insert(String filename , Boolean isEnd,String insertContent) throws IOException {//pos是插入的位置
        long pos = 0 ;
        File tmp = File.createTempFile("tmp",null);
        tmp.deleteOnExit();
        RandomAccessFile raf = new RandomAccessFile(filename,"rw");
        FileOutputStream tmpOut = new FileOutputStream(tmp);
        FileInputStream tmpIn = new FileInputStream(tmp);
        if(isEnd){
            pos =  raf.length();
        }
        raf.seek(pos);//首先的话是0
        byte[] buf = new byte[1044];
        int hasRead = 0;
        while((hasRead = raf.read(buf))>0){
            //把原有内容读入临时文件
            tmpOut.write(buf,0,hasRead);
            /*String str =  new String(buf,0,hasRead) ;
            boolean isAdd = true ;
            for(String data : replaceLists){
                if(str.contains(data)){
                    isAdd = false ;
                    str = str.replaceAll(data,"GZH： inBlueMoon ");
                    break;
                }
            }
            if(isAdd){
                //把原有内容读入临时文件
                tmpOut.write(buf,0,hasRead);
            }else{
                tmpOut.write(str.getBytes());
            }*/
        }
        raf.seek(pos);
        raf.write(insertContent.getBytes());

        //追加临时文件的内容
        while((hasRead = tmpIn.read(buf))>0){
            raf.write(buf,0,hasRead);
        }
    }

    /**
     * 移动到某文件夹
     * @param sourcePath 原路径
     * @param newPath 目的路径
     * @param onlyFile 是否只移动文件，不移动目录结构
     */
    public static void copyDir(String sourcePath, String newPath,Boolean onlyFile) {
        File start = new File(sourcePath);
        File end = new File(newPath);
        String[] filePath = start.list();   //获取该文件夹下的所有文件以及目录的名字
        String finalPath = NEW_PATH ;
        if(!end.exists()) {
            end.mkdir();
        }
        for(String fileName:filePath) {
            //查看其数组中每一个是文件还是文件夹
            if(new File(sourcePath+File.separator+fileName).isDirectory()) {
                if(onlyFile){
                    finalPath = newPath ;
                }else{
                    finalPath = newPath+File.separator+fileName ;
                }
                //为文件夹，进行递归
                copyDir(sourcePath+File.separator+fileName,finalPath ,onlyFile);
            }else {
                //为文件则进行拷贝
                copyFile(sourcePath+File.separator+fileName, fileName,newPath);
            }
        }
    }

    /**
     * 移动文件
     * @param sourcePath 源地址
     * @param fileName 文件名
     * @param newPath 目的地址
     */
    public static void copyFile(String sourcePath, String fileName,String newPath) {
        File start = new File(sourcePath);
        File end = new File(newPath +File.separator+fileName);
        try(BufferedInputStream bis=new BufferedInputStream(new FileInputStream(start));
            BufferedOutputStream bos=new BufferedOutputStream(new FileOutputStream(end))) {
            int len = 0;
            byte[] flush = new byte[1044];
            while((len=bis.read(flush)) != -1) {
                bos.write(flush, 0, len);
            }
            bos.flush();
        }catch(FileNotFoundException e) {
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }


}

