package com.silin.domain;

import java.util.Date;
import java.util.Locale.Category;

public class Product {
	//产品Javabean
	private String pid; //产品id
	private String pnum;//产品库存
	private String pname;//产品名称
	private String market_price;//产品市价
	private Double shop_price;//产品价格
	private int pNum;
	private String pimage;
	private Date pdate;
	private Integer is_hot; //0：不是热门 1：是热门
	private String pdesc; //降序排列
	private Integer pflag;//0:未下架  1：已结下架
	//分类，以面向对象的方式描述商品与分类之间的关系
	private com.silin.domain.Category category;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getMarket_price() {
		return market_price;
	}
	public void setMarket_price(String market_price) {
		this.market_price = market_price;
	}
	public Double getShop_price() {
		return shop_price;
	}
	public void setShop_price(Double shop_price) {
		this.shop_price = shop_price;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Integer getIs_hot() {
		return is_hot;
	}
	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}
	public String getPdesc() {
		return pdesc;
	}
	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}
	public Integer getPflag() {
		return pflag;
	}
	public void setPflag(Integer pflag) {
		this.pflag = pflag;
	}
	public com.silin.domain.Category getCategory() {
		return category;
	}
	public void setCategory(com.silin.domain.Category category) {
		this.category = category;
	}
	public int getpNum() {
		return pNum;
	}
	public void setpNum(int pNum) {
		this.pNum = pNum;
	}
	public String getPnum() {
		return pnum;
	}
	public void setPnum(String pnum) {
		this.pnum = pnum;
	}
	
}
