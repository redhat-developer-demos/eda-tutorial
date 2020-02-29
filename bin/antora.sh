#!/bin/sh
docker run -v `pwd`:/antora --rm -t antora/antora:2.2.0 --cache-dir=./.cache/antora --stacktrace github-pages.yml
open gh-pages/eda-tutorial/index.html
