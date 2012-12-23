#!/usr/bin/python
from distutils.core import setup, Extension
import sys

libs=[]
source=['idle.i', 'idle.cpp']

if sys.platform.startswith('win'):
  libs.append('user32')
  libs.append('psapi')
  
  source.append('idle_win.cpp')

elif sys.platform.startswith('linux'):
  libs.append('Xss')
  
  source.append('idle_lin.cpp')

module1=Extension('_idle', libraries = libs, sources=source, swig_opts=["-c++"])

setup(name='Idle Time extension', version='1.0', description='This is a demo', \
      ext_modules=[module1])







