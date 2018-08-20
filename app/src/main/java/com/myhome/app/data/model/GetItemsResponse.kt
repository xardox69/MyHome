package com.myhome.app.data.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
data class GetItemsResponse(@JsonProperty("_embedded")
                                   var objects: GetItemsResponse.EmbededItems) {

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    class EmbededItems constructor(@JsonProperty("articles")
                                   var articles: MutableList<Article>)
}



