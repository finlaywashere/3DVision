# Dependencies and usage

The two dependencies of 3DVision are a modified version of libfreenect (linked below) and the processing library at https://github.com/finlaywashere/F3DDPODRL

First you must download and compile my forked version of libfreenect

Step 1: do a git clone of https://github.com/finlaywashere/libfreenect
Step 2: run `cd libfreenect && mkdir build && cd build`
Step 3: run `cmake ..`
Step 4: run `make && sudo make install`
Step 5: run `sudo ln -sf ../include/* /usr/include/`

`fix.o` must be run every time the connect is plugged in.
It can be compiled using `fix.c` as the source code with the compile flags of `-lfreenect`. `fix.c` is example code taken from the libfreenect examples that I don't know how or why but it updates firmware and it needs to be run.
This can be done through a udev rule

Before running `fix.o` you must run `python fwfetcher.py` in the same directory as `fix.o`, it will download `audios.bin` and make the kinect work properly
