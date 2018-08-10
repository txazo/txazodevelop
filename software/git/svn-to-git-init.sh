#!/bin/bash
#
# Author: txazo
# Description: Init Git

function init() {
  repository="$1"
  userName="txazo"
  gitPath="/home/git/svn-to-git/git"
}

function initGitWithoutRepository() {
  rm -rf $gitPath/$repository
  mkdir -p $gitPath/$repository
  cd $gitPath/$repository
  git init
  echo "$repository" > README
  git add README
  git commit -m "init"
  git remote add origin git@github.com:$userName/$repository.git
  git push -u origin master
}

function initGitWithRepository() {
  rm -rf $gitPath/$repository
  mkdir -p $gitPath
  cd $gitPath
  [ ! -d "$gitPath/.git" ] && git init
  git clone https://github.com/txazo/$repository.git
}

function main() {
  [ -z "$1" ] && exit 1
  
  init "$1"
  initGitWithoutRepository
}

main "$1"