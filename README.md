JIDE Common Layer Open Source Project (MIRROR)
==============================================

This is a mirror of [jidesoft/jide-oss](https://github.com/jidesoft/jide-oss)
with the intention to build the latest versions on GitHub Actions and publish
them to Maven Central.

This repo uses **Gradle** to build. Ignore the Maven `pom.xml` files in this
repo.

The produced Jar is a multi-release Jar that contains classes for Java 8 and
some for Java 9+.


Download
--------

Binaries are available on **Maven Central**.

If you use Maven or Gradle, add a dependency with following coordinates to your
build script:

    groupId:     com.formdev
    artifactId:  jide-oss
    version:     (see button below)

Otherwise download `jide-oss-<version>.jar` here:

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.formdev/jide-oss/badge.svg?style=flat-square&color=007ec6)](https://maven-badges.herokuapp.com/maven-central/com.formdev/jide-oss)

### Snapshots

Snapshot binaries are available on
[Sonatype OSSRH](https://oss.sonatype.org/content/repositories/snapshots/com/formdev/jide-oss/).
To access the latest snapshot, change the version in your dependencies to
`<version>-SNAPSHOT` and add the repository
`https://oss.sonatype.org/content/repositories/snapshots/` to your build (see
[Maven](https://maven.apache.org/guides/mini/guide-multiple-repositories.html)
and
[Gradle](https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:declaring_custom_repository)
docs).


License
-------

JIDE Common Layer is a dual-licensed. The two licenses are GPL with classpath
exception and free commercial license.

See [LICENSE.txt](LICENSE.txt)
