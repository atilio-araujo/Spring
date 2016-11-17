package br.com.alaraujo.musicdashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alaraujo.musicdashboard.constant.Services;
import br.com.alaraujo.musicdashboard.dao.ServiceAuthorizationKeyDAO;
import br.com.alaraujo.musicdashboard.model.ServiceAuthorizationKey;

@Service
public class ServiceAuthorizationKeyService {

	@Autowired
	private ServiceAuthorizationKeyDAO authorizationKeyDAO;

	public ServiceAuthorizationKey getValueByKeyAndService(String key, Services service){
		return authorizationKeyDAO.getValueByKeyAndService(key, service);
	}

}
