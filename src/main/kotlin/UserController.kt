package com.roadrunner

import io.javalin.Context
import javax.ws.rs.client.ClientBuilder
import javax.ws.rs.core.MediaType

object UserController {

    fun getUserInfo(ctx : Context) {

        var fields = hashSetOf("id", "login", "name", "avatar_url", "html_url")

        execute(ctx, fields)
    }

    fun getUserRepositories(ctx : Context) {

        var fields = hashSetOf("id", "name", "description", "html_url")

        execute(ctx, fields)
    }

    private fun execute(ctx : Context, requiredFields : HashSet<String>) {

        var path = ctx.path().removePrefix("/api")

        var client = ClientBuilder.newClient()

        var response = client.target(Constants.GITHUB_API_BASE_URL)
                                        .path(path)
                                        .request(MediaType.APPLICATION_JSON)
                                        .get()

        val result : String
        val statusCode : Int

        if(response.status == 200) {

            var jsonBody = response.readEntity(String::class.java)

            statusCode = 200

            result = (Utils::reduceJson)(jsonBody, requiredFields)

        }else if(response.status == 404) {

            statusCode = 404

            result = "{ \"error_message\": \"Resource not found.\" }"

        } else {

            statusCode = 400

            result = "{ \"error_message\": \"Error on try process your request.\" }"
        }

        ctx.result(result)
            .status(statusCode)
            .contentType(MediaType.APPLICATION_JSON + ";charset=utf-8")
    }
}