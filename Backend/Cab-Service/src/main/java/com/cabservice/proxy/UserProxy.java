package com.cabservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cabservice.constant.UserProxyConstant;
import com.cabservice.externalclass.Registration;

@FeignClient(name = UserProxyConstant.SERVICE, url = UserProxyConstant.URL)
public interface UserProxy {

	@GetMapping(UserProxyConstant.GET_USER_BY_USERNAME)
	public Registration showUserByUserName(@PathVariable String username);
}
