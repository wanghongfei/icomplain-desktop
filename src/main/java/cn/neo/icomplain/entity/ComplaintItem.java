package cn.neo.icomplain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * An entity representing a piece of complaint.
 * @author whf
 *
 */
@Entity(name = "complaint_item")
public class ComplaintItem {
	@Id @GeneratedValue
	private Integer id;
	
	/**
	 * An string-integer to identity user
	 */
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "nick_name")
	private String nickName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "post_time")
	private Date time;

	private String title;
	
	@Column(name = "post_content")
	private String content;
	
	/**
	 * range: 1 - 5
	 */
	@Column(name = "anger_level")
	private Integer angerLevel;
	
	/**
	 * PERSON / AFFAIR
	 */
	@Column(name = "post_type")
	private String type;

	
	// getters and setters
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}

	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	/**
	 * @return the time
	 */
	public Date getTime() {
		return time;
	}

	/**
	 * @param time the time to set
	 */
	public void setTime(Date time) {
		this.time = time;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the angerLevel
	 */
	public Integer getAngerLevel() {
		return angerLevel;
	}

	/**
	 * @param angerLevel the angerLevel to set
	 */
	public void setAngerLevel(Integer angerLevel) {
		this.angerLevel = angerLevel;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
