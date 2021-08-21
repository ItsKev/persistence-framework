rootProject.name = "persistence-framework"

include("common")
include("proxy")

project(":common").name = "persistence-framework-common"
project(":proxy").name = "persistence-framework-proxy"
