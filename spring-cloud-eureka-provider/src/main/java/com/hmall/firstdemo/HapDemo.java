package com.hmall.firstdemo;

import java.io.Serializable;
import java.util.Date;

public class HapDemo implements Serializable {
    /**
	* 
	*/
    private Long id;

    /**
	* 
	*/
    private String name;

    /**
	* 
	*/
    private Long objectVersionNumber;

    /**
	* 
	*/
    private Long requestId;

    /**
	* 
	*/
    private Long programId;

    /**
	* 
	*/
    private Long createdBy;

    /**
	* 
	*/
    private Date creationDate;

    /**
	* 
	*/
    private Long lastUpdatedBy;

    /**
	* 
	*/
    private Date lastUpdateDate;

    /**
	* 
	*/
    private Long lastUpdateLogin;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getObjectVersionNumber() {
        return objectVersionNumber;
    }

    public void setObjectVersionNumber(Long objectVersionNumber) {
        this.objectVersionNumber = objectVersionNumber;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getProgramId() {
        return programId;
    }

    public void setProgramId(Long programId) {
        this.programId = programId;
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Long getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(Long lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getLastUpdateLogin() {
        return lastUpdateLogin;
    }

    public void setLastUpdateLogin(Long lastUpdateLogin) {
        this.lastUpdateLogin = lastUpdateLogin;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", objectVersionNumber=").append(objectVersionNumber);
        sb.append(", requestId=").append(requestId);
        sb.append(", programId=").append(programId);
        sb.append(", createdBy=").append(createdBy);
        sb.append(", creationDate=").append(creationDate);
        sb.append(", lastUpdatedBy=").append(lastUpdatedBy);
        sb.append(", lastUpdateDate=").append(lastUpdateDate);
        sb.append(", lastUpdateLogin=").append(lastUpdateLogin);
        sb.append("]");
        return sb.toString();
    }
}