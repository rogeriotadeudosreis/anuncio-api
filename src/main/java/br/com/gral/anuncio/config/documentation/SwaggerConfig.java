package br.com.gral.anuncio.config.documentation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Response;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@ConfigurationProperties(prefix="app")
public class SwaggerConfig implements WebMvcConfigurer{

	private String name;
	
	private String version;
	
	private String description;
	
	private String organization;
	
	private String contextPath;
	
	  @Override
	  public void addResourceHandlers(ResourceHandlerRegistry registry) {
	    String baseUrl = StringUtils.trimTrailingCharacter(this.contextPath, '/');
	    registry.
	        addResourceHandler(baseUrl + "/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/")
	        .resourceChain(false);
	  }

	  @Override
	  public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addViewController(this.contextPath + "/")
	        .setViewName("forward:/swagger-ui/index.html");
	  }	
	
	
	
	@Bean
	public Docket api() throws IOException, URISyntaxException {

        final List<Response> globalResponses = Arrays.asList( 		
        		
        new ResponseBuilder()
        		.code("500")
        		.description("Erro desconhecido")
        		.isDefault(true)
        		.build(),
        
        new ResponseBuilder()
        		.code("400")
        		.description("Requisição inválida")
        		.isDefault(true)
        		.build(),        
        
        new ResponseBuilder()
        		.code("401")
        		.description("Acesso não autenticado")
        		.isDefault(true)
        		.build(),        
        
        new ResponseBuilder()
        		.code("403")
        		.description("Acesso não autorizado")
        		.isDefault(true)
        		.build());        

		
		return new Docket(DocumentationType.SWAGGER_2)
					.useDefaultResponseMessages(false)
					.globalResponses(HttpMethod.GET, globalResponses)
					.globalResponses(HttpMethod.POST, globalResponses)
					.globalResponses(HttpMethod.PUT, globalResponses)
					.globalResponses(HttpMethod.PATCH, globalResponses)
					.globalResponses(HttpMethod.DELETE, globalResponses)					
					.select()
					.apis(RequestHandlerSelectors.basePackage("br.com.gral.anuncio.resource"))
					.build()
					.apiInfo(apiInfo());
		
	}
	
	private ApiInfo apiInfo() {

		return new ApiInfo(
				name, 
				description, 
				version, 
				organization,
				new Contact("Desenvolvimento", "", "contatov@gral.com"), // inserir emails grupo 
				"", 
				"", 
				Collections.emptyList());			
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	@Override
	public String toString() {
		return "SwaggerConfig [name=" + name + ", version=" + version + ", description=" + description
				+ ", organization=" + organization + ", contextPath=" + contextPath + "]";
	}
	
	
}
