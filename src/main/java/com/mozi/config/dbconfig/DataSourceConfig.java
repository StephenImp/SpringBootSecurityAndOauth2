package com.mozi.config.dbconfig;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;

/**
 ************************************************************
 * @类名 : DataSourceConfig.java
 *
 * @DESCRIPTION :数据源相关配置
 * @AUTHOR : wpw
 * @DATE : 2017年10月28日
 ************************************************************
 */
@Configuration
@EnableTransactionManagement
public class DataSourceConfig {
	
	//private static Log logger = LogFactory.getLog(DataSourceConfigMaster.class);

	//<!-- 配置数据源 -->
	@Bean(name = "masterDS")
	@ConfigurationProperties(prefix = "spring.datasource")
	@Primary
	public DataSource MasterSource() throws SQLException {

		DruidDataSource db = new DruidDataSource();

		return db;
	}

	//<!-- 配置 MyBaits 的 SessionFactory 实例: 通过 Spring 提供的 SqlSessionFactory 进行配置 -->
	@Bean(name = "masterDSsqlSessionFactory")
	@Primary
	public SqlSessionFactory masterSqlSessionFactory(
			@Qualifier("masterDS") DataSource dataSource) throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:sql/*.xml"));
		// 开启驼峰命名转换
		org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
		configuration.setMapUnderscoreToCamelCase(true);
		bean.setConfiguration(configuration);
        bean.setTypeAliasesPackage("com.mozi.entity");

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});
		return bean.getObject();
	}

	//<!-- 配置 Spirng 的 SqlSessionTemplate -->
	@Bean(name = "masterSessionTemplate")
	@Primary
	public SqlSessionTemplate masterSqlSessionTemplate(
			@Qualifier("masterDSsqlSessionFactory") SqlSessionFactory sqlSessionFactory)
			throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	//<!-- 1. 配置事务管理器 -->
	@Bean(name = "masterTransactionManager")
	@Primary
	public DataSourceTransactionManager MasterTransactionManager(
			@Qualifier("masterDS") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	//<!-- 2. 配置事务属性 -->
	@Bean(name = "masterInterceptor")
	@Primary
	public TransactionInterceptor masterTransactionInterceptor(
			@Qualifier("masterTransactionManager") PlatformTransactionManager masterTransactionManager) {
		final String PROPAGATION_REQUIRED = "PROPAGATION_REQUIRED,-Exception";
		TransactionInterceptor interceptor = new TransactionInterceptor();
		interceptor.setTransactionManager(masterTransactionManager);
		Properties transactionAttributes = new Properties();
		// TransactionDefinition.PROPAGATION_REQUIRED;
		transactionAttributes.setProperty("insert*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("update*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("delete*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("add*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("modify*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("update*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("login*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("biz*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("set*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("register*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("reset*", PROPAGATION_REQUIRED);
		// transactionAttributes.setProperty("acc*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("pass*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("refuse*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("use*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("add*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("sub*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("create*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("send*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("get*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("find*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("load*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("search*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("query*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("data*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("user*", PROPAGATION_REQUIRED);
		transactionAttributes.setProperty("select*", PROPAGATION_REQUIRED
				+ ",readOnly");
		interceptor.setTransactionAttributes(transactionAttributes);
		return interceptor;
	}

	//<!-- 3. 配置事务切入点, 以及把事务切入点和事务属性关联起来 -->
	@Bean("masterAdvisor")
	@Primary
	public Advisor txAdviceAdvisor(
			@Qualifier("masterInterceptor") TransactionInterceptor interceptor) {
		//String expression = "(execution(* com.Demo.opd.master.service..*.*(..))) or (execution(* com.Demo.opd.hotel.service..*.*(..))) ";
		String expression = "(execution(* com.mozi.service..*.*(..)))";
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression(expression);
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut,
				interceptor);
		return advisor;
	}

}
