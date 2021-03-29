import Build_gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

// BuildScript
buildscript {
	repositories {
		maven(url = "https://files.minecraftforge.net/maven")
		jcenter()
		mavenCentral()
	}

	dependencies {
		classpath(group = "net.minecraftforge.gradle", name = "ForgeGradle", version = "4.1.+")
		classpath(group = "org.jetbrains.kotlin", name = "kotlin-gradle-plugin", version = "1.4.31")
	}
}

// Config -> Minecraft
val forgeVersion: String by extra
val mappingsChannel: String by extra
val mappingsVersion: String by extra
val minecraftVersion: String by extra
val kffVersion: String by extra

// Config -> Mod
val modId: String by extra
val modVersion: String by extra
val modGroup: String by extra
val author: String by extra

// Config -> Run Config
val level: String by extra
val markers: String by extra

// Config -> Gradle/Maven
val archivesBaseName: String by extra

// Plugins
plugins {
	`java-library`
	kotlin("jvm") version ("1.4.31")
}

apply(plugin = "net.minecraftforge.gradle")

// JVM Info
println(
	"Java: ${System.getProperty("java.version")}" +
			" JVM: ${System.getProperty("java.vm.version")}(${System.getProperty("java.vendor")})" +
			" Arch: ${System.getProperty("os.arch")}"
)

repositories {
	maven {
		name = "kotlinforforge"
		url = uri("https://thedarkcolour.github.io/KotlinForForge/")
	}

	maven(url = "https://jitpack.io")
}

// Note: Due to the way kotlin gradle works we need to define the minecraft dependency before we configure Minecraft
dependencies {
	"minecraft"(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")

	implementation(group = "thedarkcolour", name = "kotlinforforge", version = kffVersion)

	implementation(project.the<net.minecraftforge.gradle.userdev.DependencyManagementExtension>()
		.deobf(project.dependencies.create(group = "com.github.TheOnlyTails", name = "LootTables", version = "0.2.1")
			.apply {
				isTransitive = false
			}
		))
	// implementation(group = "com.github.TheOnlyTails", name = "LootTables", version = "0.2.1")
}

// Minecraft
minecraft {
	mappingChannel = mappingsChannel
	mappingVersion = minecraftVersion

	accessTransformer(file("src/main/resources/META-INF/accesstransformer.cfg"))

	runs {
		config("client")

		config("server")

		config("data") {
			args(
				"--mod",
				modId,
				"--all",
				"--output",
				file("src/generated/resources/"),
				"--existing",
				file("src/main/resources/")
			)
		}
	}
}

// Setup
project.group = modGroup
project.version = "$minecraftVersion-$modVersion"
base.archivesBaseName = archivesBaseName

// Java 8 Target
tasks.withType<JavaCompile> {
	sourceCompatibility = "1.8"
	targetCompatibility = "1.8"
}

tasks.withType<KotlinCompile>().configureEach {
	kotlinOptions.jvmTarget = "1.8"
	kotlinOptions.useIR = true
}

// Finalize the jar by Reobf
tasks.named<Jar>("jar") { finalizedBy("reobfJar") }

// Manifest
tasks.withType<Jar> {
	manifest {
		attributes(
			"Specification-Title" to modId,
			"Specification-Vendor" to author,
			"Specification-Version" to "1",
			"Implementation-Title" to project.name,
			"Implementation-Version" to project.version,
			"Implementation-Vendor" to author,
			"Implementation-Timestamp" to Date().format("yyyy-MM-dd'T'HH:mm:ssZ")
		)
	}
}

// Generated Resources
sourceSets["main"].resources.srcDir("src/generated/resources")

// Utilities

typealias Date = java.util.Date
typealias SimpleDateFormat = java.text.SimpleDateFormat

fun Date.format(format: String) = SimpleDateFormat(format).format(this)

typealias RunConfig = net.minecraftforge.gradle.common.util.RunConfig
typealias UserDevExtension = net.minecraftforge.gradle.userdev.UserDevExtension

typealias RunConfiguration = RunConfig.() -> Unit

fun minecraft(configuration: UserDevExtension.() -> Unit) =
	configuration(extensions.getByName("minecraft") as UserDevExtension)

fun NamedDomainObjectContainerScope<RunConfig>.config(name: String, additionalConfiguration: RunConfiguration = {}) {
	val runDirectory = project.file("run")
	val sourceSet = the<JavaPluginConvention>().sourceSets["main"]

	create(name) {
		workingDirectory(runDirectory)
		property("forge.logging.markers", markers)
		property("forge.logging.console.level", level)

		additionalConfiguration(this)

		mods { create(modId) { source(sourceSet) } }
	}
}
