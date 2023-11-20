#!/bin/bash
#
mkdir ~/.config

sudo pacman -Syyu --needed base-devel alacritty neovim waybar firefox ttf-jetbrains-mono-nerd

git clone https://aur.archlinux.org/paru.git
cd paru
makepkg -si


paru -S hyprland-git ttf-merriweather


cd ..
cp -r hypr waybar nvim alacritty ~/.config

