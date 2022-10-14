package gateway.filter

import org.slf4j.LoggerFactory
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.GatewayFilterChain
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono.fromRunnable


@Component
class CustomFilter : AbstractGatewayFilterFactory<CustomFilter.Config>(Config::class.java) {

    private val logger = LoggerFactory.getLogger(CustomFilter::class.java)

    class Config
    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange: ServerWebExchange, chain: GatewayFilterChain ->
            val request = exchange.request
            val response = exchange.response
            // pre filter
            logger.info("Custom PRE filter : request id -> {}", request.id)
            chain.filter(exchange).then(
                fromRunnable {
                    // post filter
                    logger.info("Custom POST filter: response code ->{}", response.statusCode)
                }
            )
        }
    }
}