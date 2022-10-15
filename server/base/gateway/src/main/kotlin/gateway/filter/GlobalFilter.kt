package gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono.fromRunnable


@Component
class GlobalFilter : AbstractGatewayFilterFactory<GlobalFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(GlobalFilter::class.java)

    data class Config(
        val baseMessage: String,
        val preLogger: Boolean,
        val postLogger: Boolean
    )

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val response = exchange.response
            logger.info("Global filter  baseMessage: {}", config.baseMessage)

            if (config.preLogger) {
                logger.info("Logging Filter pre: request id -> {}", request.id)
                logger.info("Logging Filter pre: request id -> {}", request.uri)
            }

            chain.filter(exchange).then(
                fromRunnable {
                    if (config.postLogger) {
                        logger.info("Logging Filter post: response code -> {}", response.statusCode)
                    }
                }
            )
        }
    }
}