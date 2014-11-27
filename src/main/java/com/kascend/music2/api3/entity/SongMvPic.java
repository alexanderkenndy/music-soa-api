package com.kascend.music2.api3.entity;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;


/** @author Hibernate CodeGenerator */
public class SongMvPic extends BaseData{

    /** identifier field */
    private Integer mvPicId;
    
    private Integer mvId;

    /** nullable persistent field */
    private String picUri;

    /** nullable persistent field */
    private Integer sortIndex;

    /** nullable persistent field */
    private String smallPic;

    /** nullable persistent field */
    private String middlePic;

    /** nullable persistent field */
    private Date createTime;

    

    public Integer getMvId() {
		return mvId;
	}

	public void setMvId(Integer mvId) {
		this.mvId = mvId;
	}

	public Integer getMvPicId() {
        return this.mvPicId;
    }

    public void setMvPicId(Integer mvPicId) {
        this.mvPicId = mvPicId;
    }

    public String getPicUri() {
        return this.picUri;
    }

    public void setPicUri(String picUri) {
        this.picUri = picUri;
    }

    public Integer getSortIndex() {
        return this.sortIndex;
    }

    public void setSortIndex(Integer sortIndex) {
        this.sortIndex = sortIndex;
    }

    public String getSmallPic() {
        return this.smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }

    public String getMiddlePic() {
        return this.middlePic;
    }

    public void setMiddlePic(String middlePic) {
        this.middlePic = middlePic;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String toString() {
        return new ToStringBuilder(this)
            .append("mvPicId", getMvPicId())
            .toString();
    }

}
