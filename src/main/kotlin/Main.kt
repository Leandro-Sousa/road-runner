package com.roadrunner

import io.javalin.Javalin
import io.javalin.apibuilder.ApiBuilder

fun main(args : Array<String>) {

    var app = Javalin.create().start(80)

    app.routes {
        ApiBuilder.path("api") {
            ApiBuilder.path("users/:username") {
                ApiBuilder.get(UserController::getUserInfo)
                ApiBuilder.path("repos") {
                    ApiBuilder.get(UserController::getUserRepositories)
                }
            }
        }
    }
}