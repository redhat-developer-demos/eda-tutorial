#!/bin/sh
docker run -v `pwd`:/antora --rm -t antora/antora:2.0.0 --pull --stacktrace site-gh-pages.yml
open gh-pages/eda-tutorial/1.0/index.html
