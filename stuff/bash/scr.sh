#!/bin/bash
#
#
cd ~/stuff/js/
rm -r Full-stack-open/Bloglist
cp -r Bloglist Full-stack-open/
cd Full-stack-open/Bloglist/
rm -r node_modules
touch .gitignore
echo .env > .gitignore
cd ..
git add .
git commit -m "part4"
git push -u origin master
