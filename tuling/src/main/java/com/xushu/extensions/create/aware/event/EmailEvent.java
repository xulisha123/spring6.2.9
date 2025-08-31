package com.xushu.extensions.create.aware.event;

import org.springframework.context.ApplicationEvent;

public class EmailEvent extends ApplicationEvent {
    private static final long serialVersionUID = -5481658020206295565L;
    private User user;
    //谁发布的这个事件，souce就是谁（对象）
    public EmailEvent(Object source, User user) {
        super(source);
        this.user = user;
    }

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}