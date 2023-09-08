#!/bin/bash
#
mkdir ~/git
mkdir ~/.config
cd ~/git

sudo pacman -Syyu --needed base-devel git alacritty neovim waybar firefox ttf-jetbrains-mono-nerd

git clone https://github.com/Morganamilo/paru
cd paru
makepkg -si


paru -S hyprland-git ttf-merriweather


git clone https://github.com/sajal90/dotfiles
cd dotfiles

cp -r hypr waybar neovim alacritty ~/.config

