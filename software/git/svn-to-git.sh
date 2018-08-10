#!/bin/bash
#
# Author: txazo
# Description: Init Git

function init() {
  repository="$1"
  userName="txazo"
  svnBase="/home/git/svn-to-git/svn"
  gitBase="/home/git/svn-to-git/git"
  svnPath="$svnBase/$repository"
  gitPath="$gitBase/$repository"
}

function initGit() {
  [ -d "$gitPath" ] && return
  mkdir -p $gitPath
  cd $gitPath
  git init
  echo "$repository" > README
  git add README
  git commit -m "init"
  git remote add origin git@github.com:$userName/$repository.git
  git push -u origin master
}

function checkoutSvn() {
  [ ! -d "$svnBase" ] && mkdir -p $svnBase
  cd $svnBase
  svn checkout svn://www.txazo.com/txazo/$repository
}

function copySvnToGit() {
  cd $gitPath
  find . | awk '$1 !~ /\.git/ && $1 !~ /^\.$/' | xargs rm -rf
  cp -rf $svnPath/. $gitPath
  find . | awk '$1 ~ /\.svn/' | xargs rm -rf
}

function commitToGit() {
  cd $gitPath
  git add .
  git commit -a -m 'svn to git'
  git push origin master
}

function svnToGit() {
  [ -z "$1" ] && exit 1
  init "$1"
  initGit
  checkoutSvn
  copySvnToGit
  commitToGit
}

function main() {
  svn list svn://112.124.6.220/txazo | sed 's/\///g' | while read repo
  do
    echo `date +'%Y-%m-%d %H:%M:%S'`" checkout from svn://112.124.6.220/txazo/$repo"
    svnToGit "$repo"
  done
}

main