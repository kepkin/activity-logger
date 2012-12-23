/**
@file
@brief
@details
@author   aln (aln@acronis.com)
@since    $Id: enterprise_sequence_factory.cpp 538224 2011-03-02 12:24:08Z Alexander.Nevskiy $
*/

#ifndef IDLE_WIN_H_
#define IDLE_WIN_H_

#include "idle.h"

class WindowHelper : public WinHelper
{
public:
  WindowHelper();
  virtual long GetWindowPid(long w);
  virtual long GetForeGroundWindow();
};

#endif
