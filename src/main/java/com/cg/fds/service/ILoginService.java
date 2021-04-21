package com.cg.fds.service;

import com.cg.fds.entities.Login;

public interface ILoginService {
	
	 Login signIn(Login login);
	 Login signOut(Login login);
}
