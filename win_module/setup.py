from distutils.core import setup, Extension

module1=Extension('_idle_time', libraries = ['user32', 'psapi'], sources=['idle_time.cpp', 'idle_time.i'], swig_opts=["-c++"])
#module1=Extension('_idle_time', libraries = ['user32'], sources=['idle_time.cpp', 'idle_time_wrap.cpp'], swig_opts=["c++"])

setup(name='Idle Time extension', version='1.0', description='This is a demo', \
      ext_modules=[module1])







