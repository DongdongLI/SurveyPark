package surveypark.model;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Survey {
	private Integer id;
	private String titleTxt="No Name";
	private String prevTxt="Previous Page";
	private String nextTxt="Next Page";
	private String exitTxt="Exit";
	private Date createTime=new Date();
	private Set<Page> pages=new HashSet<>();
	private User user;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitleTxt() {
		return titleTxt;
	}
	public void setTitleTxt(String titleTxt) {
		this.titleTxt = titleTxt;
	}
	public String getPrevTxt() {
		return prevTxt;
	}
	public void setPrevTxt(String prevTxt) {
		this.prevTxt = prevTxt;
	}
	public String getNextTxt() {
		return nextTxt;
	}
	public void setNextTxt(String nextTxt) {
		this.nextTxt = nextTxt;
	}
	public String getExitTxt() {
		return exitTxt;
	}
	public void setExitTxt(String exitTxt) {
		this.exitTxt = exitTxt;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Set<Page> getPages() {
		return pages;
	}
	public void setPages(Set<Page> pages) {
		this.pages = pages;
	}
	
	
}
