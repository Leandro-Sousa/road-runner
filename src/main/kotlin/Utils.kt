package com.roadrunner

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.node.JsonNodeType
import com.fasterxml.jackson.databind.node.ObjectNode

object Utils{

    fun reduceJson(jsonBody: String, fields : HashSet<String>) : String {

        if(jsonBody.isNullOrEmpty())
            throw IllegalArgumentException("JsonBody cannot be null or empty")

        if(fields.count() == 0)
            throw IllegalArgumentException("Fields cannot be null or empty")

        var mapper = ObjectMapper()

        var rootNode = mapper.readTree(jsonBody)

        if(rootNode.nodeType == JsonNodeType.OBJECT) {

            var jsonWriter = mapper.createObjectNode()

            copyNodes(fields, (rootNode as ObjectNode), jsonWriter)

            return jsonWriter.toString()

        } else if(rootNode.nodeType == JsonNodeType.ARRAY) {

            var jsonWriter = mapper.createArrayNode()

            for (node in rootNode.asIterable()) {

                var newNode = mapper.createObjectNode()

                copyNodes(fields, (node as ObjectNode), newNode)

                jsonWriter.add(newNode)
            }

            return jsonWriter.toString()
        }

        throw Exception("Invalid Json Body. Supported Types 'Object' and 'Array'.")
    }

    private fun copyNodes(fields : HashSet<String>, origin : ObjectNode, dest : ObjectNode) {

        for (field in fields) {
            if(origin.has(field)) {
                dest.put(field, origin.get(field).asText())
            }
        }
    }
}