rootProject.name = "server"
include("server:base:discovery")
findProject(":server:base:discovery")?.name = "discovery"
include("server:base:gateway")
findProject(":server:base:gateway")?.name = "gateway"
