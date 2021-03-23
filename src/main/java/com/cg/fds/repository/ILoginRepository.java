package com.cg.fds.repository;

import com.cg.fds.entities.Login;

public interface ILoginRepository {
	
	public Login signIn(Login login);
	public Login signOut(Login login);
}
