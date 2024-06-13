package com.esperanca.microservices.authservice.domain.auth.controller.openapi;

import com.esperanca.microservices.authservice.core.openapi.errorresponse.BadCredentialsResponseError;
import com.esperanca.microservices.authservice.core.openapi.errorresponse.InvalidDataResponseError;
import com.esperanca.microservices.authservice.domain.auth.model.Token;
import com.esperanca.microservices.authservice.domain.user.dtos.LoginDto;
import com.esperanca.microservices.authservice.domain.user.dtos.SignInDto;
import com.esperanca.microservices.authservice.domain.user.entity.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(
    name = "Authentication",
    description = "This controller handles user authentication operations " +
        "such as sign-in and login."
)
public interface AuthenticationControllerOpenApi {

  @Operation(
      summary = "Register a new user",
      responses = {
          @ApiResponse(responseCode = "200"),
          @ApiResponse(
              responseCode = "400",
              content = @Content(
                  schema = @Schema(implementation = InvalidDataResponseError.class),
                  mediaType = "application/json"
              )
          )
      }
  )
  User signIn(SignInDto signInDto);

  @Operation(
      summary = "Authenticate an existing user",
      responses = {
          @ApiResponse(responseCode = "200"),
          @ApiResponse(
              responseCode = "400",
              content = @Content(
                  schema = @Schema(implementation = BadCredentialsResponseError.class),
                  mediaType = "application/json"
              )
          )
      }
  )
  Token login(LoginDto loginDto);
}
