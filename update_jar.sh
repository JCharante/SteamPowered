#!/usr/bin/env bash
jar -uf SteamPowered++.jar \
-C output/production/SteamPowered++/ Title.class \
-C output/production/SteamPowered++/ RedRobot.class \
-C src/ sounds/foghorn.wav \
-C src/ soundindex.list \
-C output/production/SteamPowered++/ Feeder.class

rm jartmp*
