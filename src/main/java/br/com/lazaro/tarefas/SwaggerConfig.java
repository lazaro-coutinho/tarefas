package br.com.lazaro.tarefas;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.lazaro.tarefas.resource"))
				.paths(PathSelectors.any())
				.build()
				.useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET, responseMessageForGET())
				.globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
				.apiInfo(apiInfo());
	}
	
	private List<ResponseMessage> responseMessageForGET() {
		return Arrays.asList(
					new ResponseMessageBuilder()
					.code(500)
					.message("Erro 500")
					.build(),
					new ResponseMessageBuilder()
					.code(404)
					.message("Not found")
					.build()
				);
	}
	
	private List<ResponseMessage> responseMessageForPOST() {
		return Arrays.asList(
					new ResponseMessageBuilder()
					.code(500)
					.message("Erro 500")
					.build(),
					new ResponseMessageBuilder()
					.code(201)
					.message("Created")
					.build()
				);
	}
	
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("Tarefas API")
				.description("REST API para um Gerenciador de Tarefas")
				.version("0.0.1-SNAPSHOT")
				.build();
	}

}
