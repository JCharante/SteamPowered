#!/usr/bin/env bash
jar -uf SteamPowered++.jar \
-C output/production/SteamPowered++/ Title.class \
-C output/production/SteamPowered++/ RedRobot.class \
-C src/ sounds/foghorn.wav \
-C src/ soundindex.list \
-C output/production/SteamPowered++/ Feeder.class \
-C output/production/SteamPowered++/ BlueRobot.class \
-C output/production/SteamPowered++/ PlusPlusSettings.class \
-C output/production/SteamPowered++/ Settings.class \
-C output/production/SteamPowered++/ ImprovedModableValue.class

rm jartmp*
