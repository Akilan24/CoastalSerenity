package com.trainservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.trainservice.constant.UserProxyConstant;
import com.trainservice.externalclass.Registration;

@FeignClient(name = UserProxyConstant.SERVICE, url = UserProxyConstant.URL)
public interface UserProxy {

	@GetMapping(UserProxyConstant.GET_USER_BY_USERNAME)
	public Registration showUserByUserName(@PathVariable String username);
}
