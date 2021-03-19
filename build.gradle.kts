import java.lang.Boolean.getBoolean

val releaseVersion = "3.7.11.1"
val developmentVersion = "3.7.12-SNAPSHOT"

version = if( getBoolean( "release" ) ) releaseVersion else developmentVersion

plugins {
	`java-library`
}

// check required Java version
if( JavaVersion.current() < JavaVersion.VERSION_1_8 )
	throw RuntimeException( "Java 8 or later required (running ${System.getProperty( "java.version" )})" )

// log version, Gradle and Java versions
println()
println( "-------------------------------------------------------------------------------" )
println( "JIDE Version: $version" )
println( "Gradle ${gradle.gradleVersion} at ${gradle.gradleHomeDir}" )
println( "Java ${System.getProperty( "java.version" )}" )
println()

java {
	toolchain {
		languageVersion.set( JavaLanguageVersion.of( 8 ) )
	}
}

sourceSets {
	// main code compiled with Java 8
	main {
		java.setSrcDirs( listOf( "src", "src-jdk8", "src-apple" ) )
		resources.setSrcDirs( listOf( "src" ) )

		resources.exclude( "**/*.psd", "**/SwingWorker_COPYING" )
	}

	// Java 9+ sepecific code compiled with Java 9+
	create( "java9" ) {
		java {
			setSrcDirs( listOf( "src-jdk9" ) )
		}
	}
}

dependencies {
	add( "java9Compile", sourceSets.main.get().output )
}

tasks.withType<JavaCompile>().configureEach {
	sourceCompatibility = "1.8"
	targetCompatibility = "1.8"

	options.encoding = "ISO-8859-1"
}

tasks.named<JavaCompile>( "compileJava9Java" ) {
	javaCompiler.set( javaToolchains.compilerFor {
		languageVersion.set( JavaLanguageVersion.of( 9 ) )
	} )

	sourceCompatibility = "9"
	targetCompatibility = "9"

	options.compilerArgs.addAll( listOf(
		"--add-exports", "java.base/sun.security.action=ALL-UNNAMED",
		"--add-exports", "java.desktop/com.sun.java.swing.plaf.windows=ALL-UNNAMED",
		"--add-exports", "java.desktop/sun.awt.windows=ALL-UNNAMED",
		"--add-exports", "java.desktop/sun.awt.image=ALL-UNNAMED",
		"--add-exports", "java.desktop/sun.swing=ALL-UNNAMED"
	) )
}

tasks.jar {
	manifest.attributes(
		"Multi-Release" to "true",
		"Implementation-Version" to project.version
	)

	exclude( "apple/**", "com/apple/**" )

	into( "META-INF/versions/9" ) {
		from( sourceSets["java9"].output )
	}
}
