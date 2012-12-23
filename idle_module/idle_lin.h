/**
@file
@brief
@details
@author   aln (aln@acronis.com)
@since    $Id: enterprise_sequence_factory.cpp 538224 2011-03-02 12:24:08Z Alexander.Nevskiy $
*/

#ifndef IDLE_LIN_H_
#define IDLE_LIN_H_

#include "idle.h"

#include <X11/extensions/scrnsaver.h>

class WindowHelper : public WinHelper
{
public:
  WindowHelper();
  ~WindowHelper();

  virtual long GetWindowPid(long w);
  virtual long GetForeGroundWindow();

private:
  Display *Dpy;
};

#endif
