package com.ship.common.jpa;
import com.google.inject.Inject;
import com.google.inject.persist.PersistService;

public class JPAInitializer    {
	
	@Inject
    JPAInitializer(PersistService service) {
        service.start();
    }
	
}
