rootProject.name = "server"
include("server:base:discovery")
findProject(":server:base:discovery")?.name = "discovery"
