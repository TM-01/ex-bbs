package com.example.form;

public class ArticleForm {
	/**投稿者 */
	private String contributor;
	/**投稿内容 */
	private String postmessage;
	
	
	public String getContributor() {
		return contributor;
	}
	public void setContributor(String contributor) {
		this.contributor = contributor;
	}
	public String getPostmessage() {
		return postmessage;
	}
	public void setPostmessage(String postmessage) {
		this.postmessage = postmessage;
	}
	@Override
	public String toString() {
		return "ArticleForm [contributor=" + contributor + ", postmessage=" + postmessage + "]";
	}
	
	

}
