rootProject.name = "persistence-framework"

include("common")
include("proxy")
include("bukkit")

project(":common").name = "persistence-framework-common"
project(":proxy").name = "persistence-framework-proxy"
project(":bukkit").name = "persistence-framework-bukkit"
