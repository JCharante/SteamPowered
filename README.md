# SteamPowered++

A fork of SteamPowered by frc2410.

A changelog is visible on the [website](http://steampowered.jcharante.com/)

## Notice

The modified `.java` files in the [src/](src/) folder were obtained by running the original release through a decompiler.

The resulting files aren't the best way to measure the ability of its creators, and are unpolished (we've all done it).

Regardless, I'm working on cleaning it here and there.

## Development Setup

### 1. Download `SteamPowered.jar` from CD thread.

### 2. Setup Dev Environment

I personally use IntelliJ. You would want to set the [src/](src/) folder as your source folder, `output/` as your output folder, and set `SteamPowered.jar` as a dependency.

### 2. Setup Output Jar

```bash
$ cp SteamPowered.jar SteamPowered++.jar
```

Whenever you run `./update_jar.sh`, files in `src/` and `output/` will be injected into `SteamPowered++.jar`
