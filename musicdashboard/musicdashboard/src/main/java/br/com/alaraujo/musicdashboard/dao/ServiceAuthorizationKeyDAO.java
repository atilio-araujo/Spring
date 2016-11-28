package br.com.alaraujo.musicdashboard.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.com.alaraujo.musicdashboard.constant.Services;
import br.com.alaraujo.musicdashboard.model.ServiceAuthorizationKey;

@Repository
public class ServiceAuthorizationKeyDAO {

	@PersistenceContext
	private EntityManager manager;

	public List<ServiceAuthorizationKey> getServiceAuthorizationKeyList(){
		List<ServiceAuthorizationKey> keyList = new ArrayList<ServiceAuthorizationKey>();

		String hql = "SELECT keys FROM ServiceAuthorizationKey keys";
		keyList = this.manager.createQuery(hql, ServiceAuthorizationKey.class).getResultList();

		return keyList;
	}

	public ServiceAuthorizationKey getValueByKeyAndService(String key, Services service){
		ServiceAuthorizationKey serviceAuthorizationKey = new ServiceAuthorizationKey();

		String hql = "SELECT k FROM ServiceAuthorizationKey k WHERE k.key = :key AND k.serviceName = :service";
		List<ServiceAuthorizationKey> authorizationKeyList = this.manager.createQuery(hql, ServiceAuthorizationKey.class)
				.setParameter("key", key)
				.setParameter("service", service.getName())
				.getResultList();

		if ( authorizationKeyList.size() > 0 ) {
			serviceAuthorizationKey = authorizationKeyList.get(0);
		}

		return serviceAuthorizationKey;
	}
}
