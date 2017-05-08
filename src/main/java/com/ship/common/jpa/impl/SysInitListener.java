package com.ship.common.jpa.impl;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.ship.common.jpa.JPAInitializer;
import com.ship.common.jpa.BaseDao;
import com.ship.common.jpa.BaseDaoImpl;
import com.ship.common.util.PropertiesUtil;
import com.ship.dao.*;
import com.ship.dao.impl.*;
import com.ship.handler.*;
import com.ship.handler.impl.*;
import com.ship.rest.LoginInterceptor;
import com.ship.rest.RemoteAppKeyChecker;
import com.ship.rest.UserAccountService;
import com.ship.rest.manage.*;
import com.ship.rest.web.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.guice.JerseyServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.Path;
import java.util.HashMap;
import java.util.Map;
/**
 * @author Mryang
 * @createTime 2017年3月16日
 * @version 1.0
 */
public class SysInitListener extends GuiceServletContextListener{
	 
	@Override
	protected Injector getInjector(){

		Injector injector = Guice.createInjector(new JerseyServletModule() {
			 @Override
			protected void configureServlets() {

				 Names.bindProperties(binder(), PropertiesUtil.getProperties());
				 // 初始化Jersey客户端
				 ClientConfig config = new  DefaultClientConfig();
				 config.getClasses().add(JacksonJsonProvider.class);
				 Client client = Client.create(config);
	             bind(Client.class).toInstance(client);

                // 安装和启动JPA 服务
                JpaPersistModule jpaPersistModule = new JpaPersistModule("jpa");
                install(jpaPersistModule);
                bind(JPAInitializer.class).asEagerSingleton();
                //dao
	            bind(BaseDao.class).to(BaseDaoImpl.class);
				//验证码绑定
	            bind(SmsCodeHander.class).to(SmsCodeHanderImpl.class);
	            bind(SmsCodeDao.class).to(SmsCodeDaoImpl.class);
	            //银行账户绑定
				bind(BankInfoRest.class);
				bind(BankInfoContoller.class);
				bind(BankInfoHandler.class).to(BankInfoHandlerImpl.class);
				bind(BankInfoDao.class).to(BankInfoDaoImpl.class);
				//分润记录
				bind(BranchRecordRest.class);
				bind(BranchRecordController.class);
				bind(BranchRecordHandler.class).to(BranchRecordHandlerImpl.class);
				bind(BranchRecordDao.class).to(BranchRecordDaoImpl.class);
				//管理员绑定
				bind(AdminController.class);
				bind(AdminHandler.class).to(AdminHandlerImpl.class);
				bind(AdminDao.class).to(AdminDaoImpl.class);
				//系统参数
				bind(SysParamController.class);
				bind(SysParamHandler.class).to(SysParamHandlerImpl.class);
				bind(SysParamDao.class).to(SysParamDaoImpl.class);
				//会员vip
				bind(VipUserRest.class);
				bind(VipUserController.class);
				bind(VipUserHandler.class).to(VipUserHandlerImpl.class);
				bind(VipUserDao.class).to(VipUserDaoImpl.class);
				//提现记录
				bind(WithDrawalsController.class);
				bind(WithDrawalsRest.class);
				bind(WithDrawalsHandler.class).to(WithDrawalsHandlerImpl.class);
				bind(WithDrawalsDao.class).to(WithDrawalsDaoImpl.class);
				bind(UserBankDao.class).to(UserBankDaoImpl.class);
				bind(UserBankHandler.class).to(UserBankHandlerImpl.class);
				bind(FundSummaryRest.class);
				bind(UploadFileRest.class);


                RemoteAppKeyChecker appKeyChecker = new RemoteAppKeyChecker();
                 requestInjection(appKeyChecker);
                 bindInterceptor(Matchers.any(), Matchers.annotatedWith(Path.class), appKeyChecker);

				 LoginInterceptor login = new LoginInterceptor();
				 requestInjection(login);
				 bindInterceptor(Matchers.any(),Matchers.annotatedWith(Path.class),login);

                //拦截加载对MetaConsumer的初始化
                Map<String, String> params = new HashMap<String, String>();
                params.put(PackagesResourceConfig.PROPERTY_PACKAGES, "com.ship.rest");
                params.put("com.sun.jersey.api.json.POJOMappingFeature", "true");
                params.put(ResourceConfig.PROPERTY_CONTAINER_REQUEST_FILTERS, "com.sun.jersey.api.container.filter.LoggingFilter");
                params.put(ResourceConfig.PROPERTY_CONTAINER_RESPONSE_FILTERS, "com.sun.jersey.api.container.filter.LoggingFilter");
                serve("*.do").with(GuiceContainer.class, params);

			}
		});		
		return injector;
	}
}
