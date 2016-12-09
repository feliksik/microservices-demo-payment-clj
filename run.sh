#!/bin/sh

docker run -it -p 3000:3000 \
               -v `pwd`:/opt/code/payment-clj
              sockshop/payment-clj
