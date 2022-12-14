rootProject.name = "server"
include("server:base:discovery")
findProject(":server:base:discovery")?.name = "discovery"
include("server:base:gateway")
findProject(":server:base:gateway")?.name = "gateway"
include("server:api:user")
findProject(":server:api:user")?.name = "user"
include("server:api:catalog")
findProject(":server:api:catalog")?.name = "catalog"
include("server:api:order")
findProject(":server:api:order")?.name = "order"
include("server:api:auth")
findProject(":server:api:auth")?.name = "auth"
