package com.cg.fds.service;

import com.cg.fds.entities.Login;

public interface ILoginService {
	
	public Login signIn(Login login);
	public Login signOut(Login login);
}
