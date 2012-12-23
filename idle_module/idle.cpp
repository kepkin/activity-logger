/**
@file
@brief
@details
@author   aln (aln@acronis.com)
@since    $Id: enterprise_sequence_factory.cpp 538224 2011-03-02 12:24:08Z Alexander.Nevskiy $
*/

#include "idle.h"

WinHelper::WinHelper(WinHelper *pimpl)
  : Pimpl(pimpl)
{
}

long WinHelper::GetWindowPid(long w)
{
  return Pimpl->GetWindowPid(w);
}

long WinHelper::GetForeGroundWindow()
{
  return Pimpl->GetForeGroundWindow();
}
