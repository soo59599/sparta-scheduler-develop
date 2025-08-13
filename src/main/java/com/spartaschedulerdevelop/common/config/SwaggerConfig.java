package com.spartaschedulerdevelop.common.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        // API 기본 정보 설정
        Info info = new Info()
                .title("일정 관리 API Document")
                .version("1.0")
                .description(
                        "환영합니다! 이 문서는 스파르타 코딩 일정 관리 앱 과제를 위해서 만들었습니다. 이 API 문서는 일정 관리의 API를 사용하는 방법을 설명합니다.\n")
                .contact(new io.swagger.v3.oas.models.info.Contact().url("https://github.com/soo59599/sparta-scheduler-develop"));

        return new OpenAPI()
                .addServersItem(new Server().url("http://localhost:8080"))  // 추가적인 서버 URL 설정 가능
                .info(info);
    }
}
