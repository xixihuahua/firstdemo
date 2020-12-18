package com.hmall.Book;

import java.util.List;

/**
 * description
 *
 * @author wenqian.zeng@hand-china.com
 * @date 2020/10/30 19:30
 */
public class InsertBook {

    public InsertBook(List<String> pathList, String writeWord, Boolean isAddInfo, Boolean isReplaceInfo, Boolean isFirst, Boolean isInsertFileName) {
        this.pathList = pathList;
        this.writeWord = writeWord;
        this.isAddInfo = isAddInfo;
        this.isReplaceInfo = isReplaceInfo;
        this.isFirst = isFirst;
        this.isInsertFileName = isInsertFileName;
    }

    //源路径
    List<String> pathList ;

//    要写入目录的头/ 最外层文件夹名
    String writeWord;
    //是否添加公众号信息
    Boolean isAddInfo;
    //是否修改其他公众号信息
    Boolean isReplaceInfo;
    //是否是第一次遍历
    Boolean isFirst;
    //是否将文件名插入目录文件
    Boolean isInsertFileName;


    public List<String> getPathList() {
        return pathList;
    }

    public void setPathList(List<String> pathList) {
        this.pathList = pathList;
    }

    public String getWriteWord() {
        return writeWord;
    }

    public void setWriteWord(String writeWord) {
        this.writeWord = writeWord;
    }

    public Boolean getAddInfo() {
        return isAddInfo;
    }

    public void setAddInfo(Boolean addInfo) {
        isAddInfo = addInfo;
    }

    public Boolean getReplaceInfo() {
        return isReplaceInfo;
    }

    public void setReplaceInfo(Boolean replaceInfo) {
        isReplaceInfo = replaceInfo;
    }

    public Boolean getFirst() {
        return isFirst;
    }

    public void setFirst(Boolean first) {
        isFirst = first;
    }

    public Boolean getInsertFileName() {
        return isInsertFileName;
    }

    public void setInsertFileName(Boolean insertFileName) {
        isInsertFileName = insertFileName;
    }
}
