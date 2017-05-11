#!/usr/bin/env bash
jar -uf SteamPowered++.jar \
-C output/production/SteamPowered++/ Title.class \
-C output/production/SteamPowered++/ RedRobot.class \
-C output/production/SteamPowered++/ Feeder.class \
-C output/production/SteamPowered++/ BlueRobot.class \
-C output/production/SteamPowered++/ PlusPlusSettings.class \
-C output/production/SteamPowered++/ Settings.class \
-C output/production/SteamPowered++/ ImprovedModableValue.class \
-C output/production/SteamPowered++/ MyWorld.class \
-C output/production/SteamPowered++/ Timer.class \
-C output/production/SteamPowered++/ End.class \
-C output/production/SteamPowered++/ Gear.class \
-C src/ sounds/foghorn.wav \
-C src/ soundindex.list \
-C src/ standalone.properties

rm jartmp*
