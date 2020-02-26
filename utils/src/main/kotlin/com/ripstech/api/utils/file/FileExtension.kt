package com.ripstech.api.utils.file

import com.ripstech.api.connector.Api
import com.ripstech.api.connector.entity.send.filter.Expression
import com.ripstech.api.connector.exception.ApiException
import com.ripstech.api.connector.service.queryparameter.Filter
import com.ripstech.api.connector.service.queryparameter.JsonFilter
import com.ripstech.api.utils.kotlin.Failure
import com.ripstech.api.utils.kotlin.Success
import com.ripstech.api.utils.kotlin.result

object FileExtension {

    @JvmStatic
    @Throws(ApiException::class)
    fun getByLang(api: Api, lang: String): Set<String> {
        return getForExpression(
            api,
            JsonFilter.equal("name", lang)
        )
    }

    @JvmStatic
    @Throws(ApiException::class)
    fun getByAppId(api: Api, appId: Long): Set<String> {
        return getForExpression(api, when (val result = api.applications().get(appId).result()) {
            is Failure -> {
                throw result.exception()
            }
            is Success -> {
                result.value.chargedQuota.languages
                    .map { JsonFilter.equal("id", it.id) }
                    .let { JsonFilter.or(it) }
            }
        })
    }

    @Throws(ApiException::class)
    private fun getForExpression(api: Api, exp: Expression): Set<String> {
        return when (val result = api.languages().get(Filter(exp)).result()) {
            is Failure -> {
                throw result.exception()
            }
            is Success -> {
                result.value
                    .flatMap { lang -> lang.fileExtensions union lang.configFileExtensions }
                    .toSet()
            }
        }
    }
}
