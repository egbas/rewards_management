package com.egbas.RewardsMgtAPI.config;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

@OpenAPIDefinition(
        info = @Info(
                title = "Rewards Manager",
                description = "Rewards management System",
                version = "1.0",
                contact = @Contact(
                        name = "Onaivi Emmanuel",
                        email = "emmanuelonaivi@gmail.com",
                        url = "https://github.com/egbas"
                ),
                license = @License(
                        name = "Rewards Management Application",
                        url = "https://github.com/egbas/rewards_management"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "Rewards Management System",
                url = "https://github.com/egbas/rewards_management"
        ),
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT Authentication",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class Swagger {
}
