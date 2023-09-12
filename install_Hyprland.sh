#!/bin/bash
#
mkdir ~/.config

sudo pacman -Syyu --needed base-devel alacritty neovim waybar firefox ttf-jetbrains-mono-nerd

git clone https://github.com/Morganamilo/paru
cd paru
makepkg -si


paru -S hyprland-git ttf-merriweather



cp -r hypr waybar neovim alacritty ~/.config

