package com.example.domain;

public class Comment {
	/**id */
	private Integer id;
	/**名前 */
	private String name;
	/**内容 */
	private String content;
	/**内容 */
	private Integer atticleId;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getAtticleId() {
		return atticleId;
	}
	public void setAtticleId(Integer atticleId) {
		this.atticleId = atticleId;
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", name=" + name + ", content=" + content + ", atticleId=" + atticleId + "]";
	}
	
	
}
