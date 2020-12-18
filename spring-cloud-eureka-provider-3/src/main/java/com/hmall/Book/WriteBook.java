package com.hmall.Book;

/**
 * description
 *
 * @author wenqian.zeng@hand-china.com
 * @date 2020/10/30 19:48
 */
public class WriteBook {
    String path;
    String fileName;
    String code;
    String middlePath;
    Boolean isAddInfo;
    Boolean isReplaceInfo;

    public WriteBook() {
    }


    public WriteBook(String path, String fileName, String code, String middlePath, Boolean isAddInfo, Boolean isReplaceInfo) {
        this.path = path;
        this.fileName = fileName;
        this.code = code;
        this.middlePath = middlePath;
        this.isAddInfo = isAddInfo;
        this.isReplaceInfo = isReplaceInfo;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMiddlePath() {
        return middlePath;
    }

    public void setMiddlePath(String middlePath) {
        this.middlePath = middlePath;
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
}
